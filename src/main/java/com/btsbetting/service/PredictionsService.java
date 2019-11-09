package com.btsbetting.service;

import com.btsbetting.client.ApiFootballClient;
import com.btsbetting.constants.LeagueConstants;
import com.btsbetting.domain.fixture.Fixture;
import com.btsbetting.entity.Prediction;
import com.btsbetting.entity.Predictions;
import com.btsbetting.utils.ApiCallCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PredictionsService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionsService.class);

    private ApiFootballClient apiFootballClient;
    private ApiCallCountUtil apiCallCountUtil;

    @Autowired
    public PredictionsService(ApiFootballClient apiFootballClient, ApiCallCountUtil apiCallCountUtil) {
        this.apiFootballClient = apiFootballClient;
        this.apiCallCountUtil = apiCallCountUtil;
    }

    public List<Fixture> refineMatchesByDate(String date) {
       return apiFootballClient.getFixturesByDate(date).getApi().getFixtures();

    }


    /**
     *
     * @param fixturesByDate all fixtures happening on a specific date
     * @return a list of fixtures from the date that was not started yet, but only from a specific league
     */
    public List<Fixture> refineMatchesByLeague(List<Fixture> fixturesByDate, int leagueId) {
        return fixturesByDate.stream().filter(fixture -> fixture.getLeagueId().equals(leagueId) && fixture.getStatusShort().equals("NS")).collect(Collectors.toList());
    }

    /**
     * from the refined list of fixtures find home and away team id's
     * @return a list of map where each map contains the home and away team's id of a given game
     */
    public List<Map<String, Integer>> findFixtureTeamIds(String date) {

        List<Map<String, Integer>> teamIds = new ArrayList<>();

        List<Fixture> matchesByDate = refineMatchesByDate(date);

        List<Fixture> matchesByLeagueAndDate = new ArrayList<>();

        List<Integer> leagueIds
                = LeagueConstants.getLeagueIds();

        for(Integer id : leagueIds) {
            matchesByLeagueAndDate.addAll(refineMatchesByLeague(matchesByDate, id));
        }



        for (Fixture f : matchesByLeagueAndDate) {
            Map<String, Integer> match = new HashMap<>();
            match.put("h", f.getHomeTeam().getTeamId());
            match.put("a", f.getAwayTeam().getTeamId());
            teamIds.add(match);
        }

        return teamIds;
    }


    /**
     *
     * @return a teams list of finished fixtures from the past 6 weeks based on team id
     */
    public List<Fixture> getRelevantFixturesByTeamId(int teamId) {

        long currentUnixTime = Instant.now().getEpochSecond();
        List<Fixture> fixtures = apiFootballClient.getFixturesByTeamId(teamId).getApi().getFixtures()
                .stream()
                .filter(fixture -> fixture.getStatusShort().equals("FT")
                        && ((currentUnixTime - fixture.getEventTimestamp()) < 5184000)
                        && (fixture.getLeagueId() != 803)).collect(Collectors.toList());

        try {
            fixtures = fixtures.subList(fixtures.size() - 6, fixtures.size());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("Team " + teamId + "had less than 6 games in the past 60 days", e);
        }

       return fixtures;
    }

    public Predictions calculatePoints(List<Map<String, Integer>> matchesByTeamIds) {

        String homeTeam;
        String awayTeam;
        int homeTeamId;
        int awayTeamId;
        double homePoints = 0;
        double awayPoints = 0;
        List<Prediction> predictions = new ArrayList<>();


        for (Map<String, Integer> m : matchesByTeamIds) {

            homeTeamId = m.get("h");
            awayTeamId = m.get("a");

            List<Fixture> homeRelevantMatches = getRelevantFixturesByTeamId(homeTeamId);
            List <Fixture> awayRelevantMatches = getRelevantFixturesByTeamId(awayTeamId);

            if (homeRelevantMatches.get(0).getHomeTeam().getTeamId().equals(m.get("h"))) {
                 homeTeam = homeRelevantMatches.get(0).getHomeTeam().getTeamName();

            } else {
                 homeTeam = homeRelevantMatches.get(0).getAwayTeam().getTeamName();
            }

            if (awayRelevantMatches.get(0).getHomeTeam().getTeamId().equals(m.get("a"))) {
                 awayTeam = awayRelevantMatches.get(0).getHomeTeam().getTeamName();
            } else {
                 awayTeam = awayRelevantMatches.get(0).getAwayTeam().getTeamName();
            }


            homePoints = calculateTotalPointsForATeam(homeRelevantMatches, homeTeamId);
            awayPoints = calculateTotalPointsForATeam(awayRelevantMatches, awayTeamId);

           double totalPoints = homePoints + awayPoints;
            predictions.add(new Prediction(homeTeam, awayTeam, totalPoints));
            homePoints = 0;
            awayPoints = 0;
        }

        int totalGames = predictions.size();
        predictions = predictions.stream().filter(p -> (p.getPrediction() >= 16.5)).collect(Collectors.toList());

        apiCallCountUtil.countCalls();

        return new Predictions(totalGames, predictions);

    }

    //-------------------------------------------------------//

    /**
     * method calculates the total points of one team within an upcoming match using the past 6 games
     * this is only a calculation for one team, it needs to be called for the away team as well
     *
     * @param relevantMatches 6 most recent matches for one team from a match
     * @return total points for one team in a match
     */
    private double calculateTotalPointsForATeam(List<Fixture> relevantMatches, Integer teamId) {

        double points = 0;

        for (Fixture f : relevantMatches) {

            if (getPointsWhenGoallessDraw(f))
                points -= 2;

            if (getPointsWhenBothScoredAndTeamIsHome(f, teamId)) {
                points += 2;
            } else if (getPointsWhenBothScoredAndTeamIsAway(f)) {
                points += 1;
            }

            points += calculatePointsForNumberOfScoredGoalsOverTwo(f.getGoalsHomeTeam());

            points += calculatePointsForNumberOfScoredGoalsOverTwo(f.getGoalsAwayTeam());

        }
        return points;
    }


    private boolean getPointsWhenGoallessDraw(Fixture fixture) {

        return (fixture.getGoalsHomeTeam() == 0) && (fixture.getGoalsAwayTeam() == 0);
    }

    private boolean getPointsWhenBothScoredAndTeamIsHome(Fixture fixture, Integer teamId) {
        return (fixture.getGoalsHomeTeam() > 0) && (fixture.getGoalsAwayTeam() > 0)
                && (fixture.getHomeTeam().getTeamId().equals(teamId));
    }

    private boolean getPointsWhenBothScoredAndTeamIsAway(Fixture fixture) {
        return (fixture.getGoalsHomeTeam() > 0) && (fixture.getGoalsAwayTeam() > 0);
    }

    private double calculatePointsForNumberOfScoredGoalsOverTwo(int numberOfGoals) {
        if (numberOfGoals > 2) {
           return (numberOfGoals - 2) * 0.5;
        }
        return 0;
    }

    private String findTeamName() {
        //TODO: move team name finding logic here without duplication, not possible to do other way just by calling the api again or maybe I could sub with an object?
    }

}

//TODO: add all docs
//TODO: add favourite point adjustment
//TODO: refactor code

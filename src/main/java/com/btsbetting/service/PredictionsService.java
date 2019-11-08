package com.btsbetting.service;

import com.btsbetting.client.ApiFootballClient;
import com.btsbetting.constants.LeagueConstants;
import com.btsbetting.domain.fixture.Fixture;
import com.btsbetting.entity.Prediction;
import com.btsbetting.entity.Predictions;
import com.btsbetting.utils.ApiCallCountUtil;
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
        List<Fixture> fixtures = apiFootballClient.getFixturesByTeamId(teamId).getApi().getFixtures();

        return fixtures.stream().filter(fixture -> fixture.getStatusShort().equals("FT") && (currentUnixTime - fixture.getEventTimestamp()  < 5184000) && fixture.getLeagueId() != 803).collect(Collectors.toList());

    }

//    /**
//     * method to narrow down the fixtures to the ones we concern about - last 6 games, no friendlies
//     *
//     * @param fixturesByTeamId all fixtures for a team specified by an id
//     * @return a list of the last 6 fixtures, friendlies excluded
//     */
//    public  List<Fixture> filterRelevantMatches(Map<String, Integer> fixturesByTeamId) {
//
//    }

    public Predictions calculatePoints(List<Map<String, Integer>> matchesByTeamIds) {

        String homeTeam;
        String awayTeam;
        int matchLeagueId;
        double homePoints = 0;
        double awayPoints = 0;
        List<Prediction> predictions = new ArrayList<>();


        for (Map<String, Integer> m : matchesByTeamIds) {

            List<Fixture> homeRelevantMatches = getRelevantFixturesByTeamId(m.get("h"));
            List <Fixture> awayRelevantMatches = getRelevantFixturesByTeamId(m.get("a"));

            try {
                homeRelevantMatches = homeRelevantMatches.subList(homeRelevantMatches.size() - 6, homeRelevantMatches.size());
                awayRelevantMatches = awayRelevantMatches.subList(awayRelevantMatches.size() - 6, awayRelevantMatches.size());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("one of the teams had less than 6 games in the past 2 months");
                //TODO: log properly instead of sys out
            }

            /*
            method to get team name for later assignment, fix it at one point as it's horrible impl
             */
            if (homeRelevantMatches.get(0).getHomeTeam().getTeamId().equals(m.get("h"))) {
                 homeTeam = homeRelevantMatches.get(0).getHomeTeam().getTeamName();
                 matchLeagueId = homeRelevantMatches.get(0).getLeagueId();

            } else {
                 homeTeam = homeRelevantMatches.get(0).getAwayTeam().getTeamName();
                 matchLeagueId = homeRelevantMatches.get(0).getLeagueId();
            }

            if (awayRelevantMatches.get(0).getHomeTeam().getTeamId().equals(m.get("a"))) {
                 awayTeam = awayRelevantMatches.get(0).getHomeTeam().getTeamName();
            } else {
                 awayTeam = awayRelevantMatches.get(0).getAwayTeam().getTeamName();
            }

            for (Fixture f : homeRelevantMatches) {

                if ((f.getGoalsHomeTeam() == 0) && (f.getGoalsAwayTeam() == 0)) {
                    homePoints -=2;
                }

                if ((f.getGoalsHomeTeam() > 0) && (f.getGoalsAwayTeam() > 0) && (f.getHomeTeam().getTeamId().equals(m.get("h")))) {
                    homePoints += 2;
                } else if ((f.getGoalsHomeTeam() > 0) && (f.getGoalsAwayTeam() > 0)) {
                    homePoints += 1;
                }

                if (f.getGoalsHomeTeam() > 2) {
                    homePoints += (f.getGoalsHomeTeam() - 2 ) * 0.5;
                }

                if (f.getGoalsAwayTeam() > 2) {
                    homePoints += (f.getGoalsAwayTeam() - 2 ) * 0.5;
                }

            }

            for (Fixture f : awayRelevantMatches) {

                if ((f.getGoalsHomeTeam() == 0) && (f.getGoalsAwayTeam() == 0)) {
                    awayPoints -=2;
                }

                if ((f.getGoalsHomeTeam() > 0) && (f.getGoalsAwayTeam() > 0) && (f.getHomeTeam().getTeamId().equals(m.get("a")))) {
                    awayPoints += 2;
                } else if ((f.getGoalsHomeTeam() > 0) && (f.getGoalsAwayTeam() > 0)) {
                    awayPoints += 1;
                }

                if (f.getGoalsHomeTeam() > 2) {
                    awayPoints += (f.getGoalsHomeTeam() - 2 ) * 0.5;
                }

                if (f.getGoalsAwayTeam() > 2) {
                    awayPoints += (f.getGoalsAwayTeam() - 2 ) * 0.5;
                }

            }

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

    private void getPointsWhenGoallessDraw(List<Fixture> relevantMatches) {

    }

}


//TODO: add favourite point adjustment
//TODO: refactor code

package com.btsbetting.service;

import com.btsbetting.client.ApiFootballClient;
import com.btsbetting.constants.LeagueConstants;
import com.btsbetting.domain.fixture.Fixture;
import com.btsbetting.entity.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RefineFixtureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefineFixtureService.class);

    private ApiFootballClient apiFootballClient;

    public RefineFixtureService() {
    }

    @Autowired
    public RefineFixtureService(ApiFootballClient apiFootballClient) {
        this.apiFootballClient = apiFootballClient;
    }

    private List<Fixture> refineMatchesByDate(String date) {
        return apiFootballClient.getFixturesByDate(date).getApi().getFixtures();
    }

    /**
     * @param fixturesByDate all fixtures happening on a specific date
     * @return a list of fixtures from the date that was not started yet, but only from a specific league
     */
    private List<Fixture> refineMatchesByLeague(List<Fixture> fixturesByDate, int leagueId) {
        return fixturesByDate.stream().filter(fixture -> fixture.getLeagueId().equals(leagueId) && fixture.getStatusShort().equals("NS")).collect(Collectors.toList());
    }

    /**
     * uses the refineByLeague and refineByDateMethods and the predefined available league ids to refine a final list
     *
     * @return a list of fixtures filtered by league, date and a list of eligible league id's
     */
    public List<Fixture> refineMatchesByLeagueDateAndEligibility(String date) {

        List<Fixture> matchesByDate = refineMatchesByDate(date);
        List<Fixture> matchesByEligibleLeagueAndDate = new ArrayList<>();

        List<Integer> leagueIds
                = LeagueConstants.getLeagueIds();

        for (Integer id : leagueIds) {
            matchesByEligibleLeagueAndDate.addAll(refineMatchesByLeague(matchesByDate, id));
        }
        return matchesByEligibleLeagueAndDate;
    }

    /**
     * from the refined list of fixtures find home and away team id's
     *
     * @return a list of Match object where each match object contains the home and away team's id and team names
     */
    public List<Match> findFixtureTeamIds(List<Fixture> refinedMatches) {

        List<Match> teamIds = new ArrayList<>();

        for (Fixture f : refinedMatches) {
            Map<String, Integer> teamId = new HashMap<>();
            teamId.put("h", f.getHomeTeam().getTeamId());
            teamId.put("a", f.getAwayTeam().getTeamId());
            Match match = new Match(teamId, f.getHomeTeam().getTeamName(), f.getAwayTeam().getTeamName(), f.getFixtureId());

            teamIds.add(match);
        }

        return teamIds;
    }

    /**
     * @return a teams list of finished fixtures from the past 6 weeks based on team id
     */
    List<Fixture> getRelevantFixturesByTeamId(int teamId) {

        long currentUnixTime = Instant.now().getEpochSecond();
        List<Fixture> fixtures = apiFootballClient.getFixturesByTeamId(teamId).getApi().getFixtures()
                .stream()
                .filter(fixture -> fixture.getStatusShort().equals("FT")
                        && ((currentUnixTime - fixture.getEventTimestamp()) <6048000)
                        && (fixture.getLeagueId() != 803)).collect(Collectors.toList());

        try {
            fixtures = fixtures.subList(fixtures.size() - 6, fixtures.size());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("Team " + teamId + " had less than 6 games in the past 60 days", e);
        }

        return fixtures;
    }

    /**
     * method to remove all games where the manager started in the past 60 days
     *
     * @param m the Match to be predicted
     * @return true if none of the coach started in the past 60 days, false otherwise
     */
    boolean isCoachEmployedForLongEnoughRefine(Match m) {

        boolean flag = true;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate cutoffDate = LocalDate.now().minusDays(60);
        LocalDate coachStartDate;

        for (Integer i : m.getTeamId().values()) {

            try {
                coachStartDate = format.parse(new ArrayList<>(
                        apiFootballClient.getCoachByTeamId(i)
                                .getApi()
                                .getCoachs())
                        .get(0)
                        .getCareer()
                        .get(0)
                        .getStart())
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                if (cutoffDate.compareTo(coachStartDate) < 0) {
                    flag = false;
                    LOGGER.info("Coach started in the past 60 days, match excluded: {}", i);
                    break;
                }
            } catch (ParseException e) {
                LOGGER.error("Unable to parse coachStartDate for match: {}", i, e);
            } catch (IndexOutOfBoundsException e) {
                LOGGER.error("Currently one of the teams from match {} don't have a manager or the API is missing data", i, e);
            }
        }
        return flag;
    }
}
package com.btsbetting.service;

import com.btsbetting.client.ApiFootballClient;
import com.btsbetting.entity.MatchOdds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OddsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiFootballClient.class);

    private ApiFootballClient apiFootballClient;


    public OddsService() {
    }

    @Autowired
    public OddsService(ApiFootballClient apiFootballClient) {
        this.apiFootballClient = apiFootballClient;
    }

    /**
     * method calls the football api to get the odds for a specific match, then it looks for whichever is the first
     * bookmaker
     * @param fixtureId the id of a match that need to be analysed
     * @return returns the final match adjustment
     */
    public double retrieveOddAdjustmentForFavourite(int fixtureId, double totalPoints) {


        if (totalPoints >= 16.5) {

            try {

                MatchOdds matchOdds = new MatchOdds(apiFootballClient.getOddsByFixtureIdAndLabel(fixtureId)
                        .getApi()
                        .getOdds().get(0)
                        .getBookmakers()
                        .get(0)
                        .getBets()
                        .get(0)
                        .getValues());

                return calculatePointAdjustmentBasedOnOdds(
                        retrievePlayingStatusSpecificOdds(matchOdds, "Home"),
                        retrievePlayingStatusSpecificOdds(matchOdds, "Away"));

            } catch (IndexOutOfBoundsException ex) {
               LOGGER.error("Issue with odds ", ex.getMessage());
            }

        }

        return 0;
    }

    /**
     * method grabs the relevant odds for home or away, ignoring the draw
     *
     * @param matchOdds 1x2 odds
     * @param teamPlayingLocation String - home or away
     * @return plain odds in double
     */
    private double retrievePlayingStatusSpecificOdds(MatchOdds matchOdds, String teamPlayingLocation) {
        return Double.parseDouble(matchOdds.getOdds().stream().filter(odds -> odds.getValue().equals(teamPlayingLocation)).collect(Collectors.toList()).get(0).getOdd());
    }

    /**
     *
     *  service method to calculate favourite pointing:
     *
     * R11) +2 points if the away team is slightly favourite
     * R12) +1 point if the home team is slightly favourite
     * R13) -2 points if the home team is heavy favourite
     * R14) -1 point if the away team is heavy favourite
     *
     * @param homeOdds odds for home team
     * @param awayOdds odds for away team
     * @return pointing based on favourite level
     */
    private double calculatePointAdjustmentBasedOnOdds(double homeOdds, double awayOdds) {

        if (homeOdds < awayOdds) {
            return (homeOdds <= 1.85) ? -2 : 1;
        } else {
            return (awayOdds >= 1.90) ? 2 : -1;
        }
    }
}

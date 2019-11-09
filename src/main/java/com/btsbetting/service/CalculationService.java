package com.btsbetting.service;

import com.btsbetting.domain.fixture.Fixture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService {

    /**
     * Sums the total point of an outcome
     *
     * @param relevantMatches last 6 fixtures of either home team or away team
     * @param teamId          the id of one team from a match either home or away
     * @return the prediction score
     */
    double sumPoints(List<Fixture> relevantMatches, int teamId) {
        return calculateTotalPointsForATeam(relevantMatches, teamId);
    }

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
}

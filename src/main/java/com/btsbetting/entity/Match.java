package com.btsbetting.entity;

import java.util.Map;

public class Match {

    private Map<String, Integer> teamId;
    private String homeTeamName;
    private String awayTeamName;
    private int fixtureId;
    int leagueId;


    public Match(Map<String, Integer> teamId, String homeTeamName, String awayTeamName, Integer fixtureId) {
        this.teamId = teamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.fixtureId = fixtureId;
    }

    public Map<String, Integer> getTeamId() {
        return teamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getFixtureId() {
        return fixtureId;
    }
}


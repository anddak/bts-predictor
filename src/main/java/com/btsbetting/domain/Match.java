package com.btsbetting.domain;

import java.util.Map;

public class Match {

    Map<String, Integer> teamId;
    String homeTeamName;
    String awayTeamName;

    public Map<String, Integer> getTeamId() {
        return teamId;
    }

    public void setTeamId(Map<String, Integer> teamId) {
        this.teamId = teamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }
}


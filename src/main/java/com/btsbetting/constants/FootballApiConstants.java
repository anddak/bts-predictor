package com.btsbetting.constants;

public class FootballApiConstants {

    public static final String GET_FIXTURES_BY_DATE_URL = "https://api-football-v1.p.rapidapi.com/v2/fixtures/date/";
    public static final String GET_FIXTURES_BY_TEAM_ID = "https://api-football-v1.p.rapidapi.com/v2/fixtures/team/";
    public static final String GET_COACH_BY_TEAM_ID = "https://api-football-v1.p.rapidapi.com/v2/coachs/team/";
    public static final String GET_ODDS_BY_FIXTURE_AND_LABEL = "https://api-football-v1.p.rapidapi.com/v2/odds/fixture/";
    public static final String LABEL_SUFFIX = "/label/1";
    public static final String TIMEZONE_URL = "?timezone=Europe/London";

    public static final String API_HEADER = "X-RapidAPI-Key";
    public static final String API_KEY = "61a1245a08msh9e6892b746bc888p10ae22jsnb8796cc88e31";

    private FootballApiConstants() {
        throw new IllegalStateException("Utility class");
    }
}

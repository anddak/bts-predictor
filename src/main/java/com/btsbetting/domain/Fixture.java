package com.btsbetting.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fixture_id",
        "league_id",
        "event_date",
        "event_timestamp",
        "firstHalfStart",
        "secondHalfStart",
        "round",
        "status",
        "statusShort",
        "elapsed",
        "venue",
        "referee",
        "homeTeam",
        "awayTeam",
        "goalsHomeTeam",
        "goalsAwayTeam",
        "score"
})
public class Fixture {

    @JsonProperty("fixture_id")
    private Integer fixtureId;
    @JsonProperty("league_id")
    private Integer leagueId;
    @JsonProperty("event_date")
    private String eventDate;
    @JsonProperty("event_timestamp")
    private Integer eventTimestamp;
    @JsonProperty("firstHalfStart")
    private Integer firstHalfStart;
    @JsonProperty("secondHalfStart")
    private Object secondHalfStart;
    @JsonProperty("round")
    private String round;
    @JsonProperty("status")
    private String status;
    @JsonProperty("statusShort")
    private String statusShort;
    @JsonProperty("elapsed")
    private Integer elapsed;
    @JsonProperty("venue")
    private String venue;
    @JsonProperty("referee")
    private Object referee;
    @JsonProperty("homeTeam")
    private HomeTeam homeTeam;
    @JsonProperty("awayTeam")
    private AwayTeam awayTeam;
    @JsonProperty("goalsHomeTeam")
    private Integer goalsHomeTeam;
    @JsonProperty("goalsAwayTeam")
    private Integer goalsAwayTeam;
    @JsonProperty("score")
    private Score score;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Fixture() {
    }

    /**
     *
     * @param venue
     * @param goalsHomeTeam
     * @param goalsAwayTeam
     * @param awayTeam
     * @param fixtureId
     * @param referee
     * @param elapsed
     * @param score
     * @param round
     * @param leagueId
     * @param statusShort
     * @param homeTeam
     * @param secondHalfStart
     * @param firstHalfStart
     * @param eventDate
     * @param eventTimestamp
     * @param status
     */
    public Fixture(Integer fixtureId, Integer leagueId, String eventDate, Integer eventTimestamp, Integer firstHalfStart, Object secondHalfStart, String round, String status, String statusShort, Integer elapsed, String venue, Object referee, HomeTeam homeTeam, AwayTeam awayTeam, Integer goalsHomeTeam, Integer goalsAwayTeam, Score score) {
        super();
        this.fixtureId = fixtureId;
        this.leagueId = leagueId;
        this.eventDate = eventDate;
        this.eventTimestamp = eventTimestamp;
        this.firstHalfStart = firstHalfStart;
        this.secondHalfStart = secondHalfStart;
        this.round = round;
        this.status = status;
        this.statusShort = statusShort;
        this.elapsed = elapsed;
        this.venue = venue;
        this.referee = referee;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;
        this.score = score;
    }

    @JsonProperty("fixture_id")
    public Integer getFixtureId() {
        return fixtureId;
    }

    @JsonProperty("fixture_id")
    public void setFixtureId(Integer fixtureId) {
        this.fixtureId = fixtureId;
    }

    @JsonProperty("league_id")
    public Integer getLeagueId() {
        return leagueId;
    }

    @JsonProperty("league_id")
    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    @JsonProperty("event_date")
    public String getEventDate() {
        return eventDate;
    }

    @JsonProperty("event_date")
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @JsonProperty("event_timestamp")
    public Integer getEventTimestamp() {
        return eventTimestamp;
    }

    @JsonProperty("event_timestamp")
    public void setEventTimestamp(Integer eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    @JsonProperty("firstHalfStart")
    public Integer getFirstHalfStart() {
        return firstHalfStart;
    }

    @JsonProperty("firstHalfStart")
    public void setFirstHalfStart(Integer firstHalfStart) {
        this.firstHalfStart = firstHalfStart;
    }

    @JsonProperty("secondHalfStart")
    public Object getSecondHalfStart() {
        return secondHalfStart;
    }

    @JsonProperty("secondHalfStart")
    public void setSecondHalfStart(Object secondHalfStart) {
        this.secondHalfStart = secondHalfStart;
    }

    @JsonProperty("round")
    public String getRound() {
        return round;
    }

    @JsonProperty("round")
    public void setRound(String round) {
        this.round = round;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("statusShort")
    public String getStatusShort() {
        return statusShort;
    }

    @JsonProperty("statusShort")
    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    @JsonProperty("elapsed")
    public Integer getElapsed() {
        return elapsed;
    }

    @JsonProperty("elapsed")
    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    @JsonProperty("venue")
    public String getVenue() {
        return venue;
    }

    @JsonProperty("venue")
    public void setVenue(String venue) {
        this.venue = venue;
    }

    @JsonProperty("referee")
    public Object getReferee() {
        return referee;
    }

    @JsonProperty("referee")
    public void setReferee(Object referee) {
        this.referee = referee;
    }

    @JsonProperty("homeTeam")
    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    @JsonProperty("homeTeam")
    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    @JsonProperty("awayTeam")
    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    @JsonProperty("awayTeam")
    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    @JsonProperty("goalsHomeTeam")
    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    @JsonProperty("goalsHomeTeam")
    public void setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    @JsonProperty("goalsAwayTeam")
    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    @JsonProperty("goalsAwayTeam")
    public void setGoalsAwayTeam(Integer goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    @JsonProperty("score")
    public Score getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score score) {
        this.score = score;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Fixture{" +
                "fixtureId=" + fixtureId +
                ", leagueId=" + leagueId +
                ", eventDate='" + eventDate + '\'' +
                ", eventTimestamp=" + eventTimestamp +
                ", firstHalfStart=" + firstHalfStart +
                ", secondHalfStart=" + secondHalfStart +
                ", round='" + round + '\'' +
                ", status='" + status + '\'' +
                ", statusShort='" + statusShort + '\'' +
                ", elapsed=" + elapsed +
                ", venue='" + venue + '\'' +
                ", referee=" + referee +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", goalsHomeTeam=" + goalsHomeTeam +
                ", goalsAwayTeam=" + goalsAwayTeam +
                ", score=" + score +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
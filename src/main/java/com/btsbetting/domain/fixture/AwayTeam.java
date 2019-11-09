package com.btsbetting.domain.fixture;

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
        "team_id",
        "team_name",
        "logo"
})
public class AwayTeam {

    @JsonProperty("team_id")
    private Integer teamId;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("logo")
    private String logo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("team_id")
    public Integer getTeamId() {
        return teamId;
    }

    @JsonProperty("team_id")
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @JsonProperty("team_name")
    public String getTeamName() {
        return teamName;
    }

    @JsonProperty("team_name")
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    @JsonProperty("logo")
    public void setLogo(String logo) {
        this.logo = logo;
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
        return "AwayTeam{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", logo='" + logo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
package com.btsbetting.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.btsbetting.domain.fixture.Fixture;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "results",
        "fixtures"
})
public class Api {

    @JsonProperty("results")
    private Integer results;
    @JsonProperty("fixtures")
    private List<Fixture> fixtures = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Api() {
    }

    /**
     *
     * @param results
     * @param fixtures
     */
    public Api(Integer results, List<Fixture> fixtures) {
        super();
        this.results = results;
        this.fixtures = fixtures;
    }

    @JsonProperty("results")
    public Integer getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(Integer results) {
        this.results = results;
    }

    @JsonProperty("fixtures")
    public List<Fixture> getFixtures() {
        return fixtures;
    }

    @JsonProperty("fixtures")
    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
package com.btsbetting.domain.odds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fixture",
        "bookmakers"
})
public class Odd {

    @JsonProperty("fixture")
    private OddsFixture fixture;
    @JsonProperty("bookmakers")
    private List<Bookmaker> bookmakers = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("fixture")
    public OddsFixture getFixture() {
        return fixture;
    }

    @JsonProperty("fixture")
    public void setFixture(OddsFixture fixture) {
        this.fixture = fixture;
    }

    @JsonProperty("bookmakers")
    public List<Bookmaker> getBookmakers() {
        return bookmakers;
    }

    @JsonProperty("bookmakers")
    public void setBookmakers(List<Bookmaker> bookmakers) {
        this.bookmakers = bookmakers;
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

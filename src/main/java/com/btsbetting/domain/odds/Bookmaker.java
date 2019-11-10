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
        "bookmaker_id",
        "bookmaker_name",
        "bets"
})
public class Bookmaker {

    @JsonProperty("bookmaker_id")
    private Integer bookmakerId;
    @JsonProperty("bookmaker_name")
    private String bookmakerName;
    @JsonProperty("bets")
    private List<Bet> bets = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("bookmaker_id")
    public Integer getBookmakerId() {
        return bookmakerId;
    }

    @JsonProperty("bookmaker_id")
    public void setBookmakerId(Integer bookmakerId) {
        this.bookmakerId = bookmakerId;
    }

    @JsonProperty("bookmaker_name")
    public String getBookmakerName() {
        return bookmakerName;
    }

    @JsonProperty("bookmaker_name")
    public void setBookmakerName(String bookmakerName) {
        this.bookmakerName = bookmakerName;
    }

    @JsonProperty("bets")
    public List<Bet> getBets() {
        return bets;
    }

    @JsonProperty("bets")
    public void setBets(List<Bet> bets) {
        this.bets = bets;
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

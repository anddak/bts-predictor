package com.btsbetting.entity;

import com.btsbetting.domain.odds.Values;

import java.util.List;

public class MatchOdds {

    List<Values> odds;

    public MatchOdds(List<Values> odds) {
        this.odds = odds;
    }

    public MatchOdds() {
    }

    public List<Values> getOdds() {
        return odds;
    }
}

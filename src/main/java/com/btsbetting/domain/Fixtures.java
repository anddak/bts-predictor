package com.btsbetting.domain;

import java.util.List;

public class Fixtures {

    private List<Fixture> fixtures;

    public Fixtures() {
    }


    public Fixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}

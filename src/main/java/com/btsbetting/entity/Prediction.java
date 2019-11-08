package com.btsbetting.entity;

public class Prediction {


    String homeTeam;
    String awayTeam;
    double prediction;

    public Prediction(String homeTeam, String awayTeam, double prediction) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.prediction = prediction;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public double getPrediction() {
        return prediction;
    }


    @Override
    public String toString() {
        return "Prediction{" +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", prediction=" + prediction +
                '}';
    }
}

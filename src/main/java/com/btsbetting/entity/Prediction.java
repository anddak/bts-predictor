package com.btsbetting.entity;

public class Prediction {


    private String homeTeam;
    private String awayTeam;
    private double predictionPoints;

    public Prediction(String homeTeam, String awayTeam, double predictionPoints) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.predictionPoints = predictionPoints;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public double getPredictionPoints() {
        return predictionPoints;
    }


    @Override
    public String toString() {
        return "Prediction{" +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", prediction=" + predictionPoints +
                '}';
    }
}

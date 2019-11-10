package com.btsbetting.entity;

public class Prediction {


    private String homeTeam;
    private String awayTeam;
    private double predictionPoints;
    private double oddsAdjustmentPoints;

    public Prediction(String homeTeam, String awayTeam, double predictionPoints, double oddsAdjustmentPoints) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.predictionPoints = predictionPoints;
        this.oddsAdjustmentPoints = oddsAdjustmentPoints;
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

    public double getOddsAdjustmentPoints() {
        return oddsAdjustmentPoints;
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

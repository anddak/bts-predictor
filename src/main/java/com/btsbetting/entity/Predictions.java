package com.btsbetting.entity;

import java.util.List;

public class Predictions {

    private int totalAnalysedGames;
    private List<Prediction> predictions;

    public int getTotalAnalysedGames() {
        return totalAnalysedGames;
    }

    public void setTotalAnalysedGames(int totalAnalysedGames) {
        this.totalAnalysedGames = totalAnalysedGames;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public Predictions(int totalAnalysedGames, List<Prediction> predictions) {
        this.totalAnalysedGames = totalAnalysedGames;
        this.predictions = predictions;
    }
}

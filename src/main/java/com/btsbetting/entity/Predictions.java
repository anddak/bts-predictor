package com.btsbetting.entity;

import java.util.List;

public class Predictions {

    private int totalAnalysedGames;
    private List<Prediction> predictionList;

    public int getTotalAnalysedGames() {
        return totalAnalysedGames;
    }

    public void setTotalAnalysedGames(int totalAnalysedGames) {
        this.totalAnalysedGames = totalAnalysedGames;
    }

    public List<Prediction> getPredictionList() {
        return predictionList;
    }

    public void setPredictionList(List<Prediction> predictionList) {
        this.predictionList = predictionList;
    }

    public Predictions(int totalAnalysedGames, List<Prediction> predictionList) {
        this.totalAnalysedGames = totalAnalysedGames;
        this.predictionList = predictionList;
    }
}

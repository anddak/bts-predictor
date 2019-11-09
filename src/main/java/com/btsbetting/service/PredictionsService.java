package com.btsbetting.service;

import com.btsbetting.domain.fixture.Fixture;
import com.btsbetting.entity.Match;
import com.btsbetting.entity.Prediction;
import com.btsbetting.entity.Predictions;
import com.btsbetting.utils.ApiCallCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PredictionsService {

    private ApiCallCountUtil apiCallCountUtil;
    private CalculationService calculationService;
    private RefineFixtureService refineFixtureService;

    @Autowired
    public PredictionsService(ApiCallCountUtil apiCallCountUtil, CalculationService calculationService, RefineFixtureService refineFixtureService) {
        this.apiCallCountUtil = apiCallCountUtil;
        this.calculationService = calculationService;
        this.refineFixtureService = refineFixtureService;
    }

    public Predictions generateFinalPrediction(List<Match> refinedMatchesByLeagueDateAndEligibility) {

        int homeTeamId;
        int awayTeamId;
        List<Prediction> predictions = new ArrayList<>();

        for (Match m : refinedMatchesByLeagueDateAndEligibility) {

            homeTeamId = m.getTeamId().get("h");
            awayTeamId = m.getTeamId().get("a");

            List<Fixture> homeRelevantMatches = refineFixtureService.getRelevantFixturesByTeamId(homeTeamId);
            List<Fixture> awayRelevantMatches = refineFixtureService.getRelevantFixturesByTeamId(awayTeamId);

            double totalPoints = calculationService.sumPoints(homeRelevantMatches, homeTeamId) + calculationService.sumPoints(awayRelevantMatches, awayTeamId);
            predictions.add(new Prediction(m.getHomeTeamName(), m.getAwayTeamName(), totalPoints));
        }

        apiCallCountUtil.countCalls();

        return generatePredictionsObject(predictions);

    }

    /**
     * @param predictions takes in the list of final predictions
     * @return the final predictions object
     */
    private Predictions generatePredictionsObject(List<Prediction> predictions) {
        return new Predictions(predictions.size(), predictions.stream().filter(p -> (p.getPredictionPoints() >= 16.5)).collect(Collectors.toList()));
    }

}

//TODO: add all docs
//TODO: add favourite point adjustment
//TODO: add team name
//TODO: add coach adjustment

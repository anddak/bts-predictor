package com.btsbetting.controller;

import com.btsbetting.constants.LocalApiConstants;
import com.btsbetting.entity.Predictions;
import com.btsbetting.service.PredictionsService;
import com.btsbetting.service.RefineFixtureService;
import com.btsbetting.utils.ApiCallCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LocalApiConstants.API_URL + LocalApiConstants.API_VERSION_URL)
public class PredictionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionsController.class);

    private RefineFixtureService refineFixtureService;
    private PredictionsService predictionsService;
    private ApiCallCountUtil apiCallCountUtil;

    @Autowired
    public PredictionsController(PredictionsService predictionsService, ApiCallCountUtil apiCallCountUtil, RefineFixtureService refineFixtureService) {
        this.predictionsService = predictionsService;
        this.apiCallCountUtil = apiCallCountUtil;
        this.refineFixtureService = refineFixtureService;
    }

    @GetMapping(value = LocalApiConstants.PREDICTIONS_ENDPOINT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Predictions> getPredictionsByDate(@RequestParam String date) {

        if (apiCallCountUtil.readFile() < 5000) {

            try {
                return new ResponseEntity<>(predictionsService
                        .generateFinalPrediction(refineFixtureService
                                .findFixtureTeamIds(refineFixtureService
                                        .refineMatchesByLeagueDateAndEligibility(date))), HttpStatus.OK);
            } catch (Exception e) {
                LOGGER.error("Issue when calling local services: ", e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }

    }

}

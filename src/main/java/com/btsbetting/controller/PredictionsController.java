package com.btsbetting.controller;

import com.btsbetting.entity.Prediction;
import com.btsbetting.entity.Predictions;
import com.btsbetting.service.PredictionsService;
import com.btsbetting.utils.ApiCallCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PredictionsController {

    private PredictionsService predictionsService;
    private ApiCallCountUtil apiCallCountUtil;

    @Autowired
    public PredictionsController(PredictionsService predictionsService, ApiCallCountUtil apiCallCountUtil) {
        this.predictionsService = predictionsService;
        this.apiCallCountUtil = apiCallCountUtil;
    }

    @GetMapping(value = "/predictions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Predictions> getPredictionsByDate(@RequestParam String date) {

        if (apiCallCountUtil.readFile() < 5000) {

            try {
                return new ResponseEntity<>(predictionsService.calculatePoints(predictionsService.findFixtureTeamIds(date)), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }

    }

}

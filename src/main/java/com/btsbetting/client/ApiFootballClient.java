package com.btsbetting.client;

import com.btsbetting.constants.FootballApiConstants;
import com.btsbetting.domain.ApiWrapper;
import com.btsbetting.service.RefineFixtureService;
import com.btsbetting.utils.ApiCallCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiFootballClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiFootballClient.class);


    private ApiCallCountUtil apiCallCountUtil;

    public ApiFootballClient() {
    }

    @Autowired
    public ApiFootballClient(ApiCallCountUtil apiCallCountUtil) {
        this.apiCallCountUtil = apiCallCountUtil;
    }

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders header = new HttpHeaders();

    public ApiWrapper getFixturesByDate(String date) {

        String url = FootballApiConstants.GET_FIXTURES_BY_DATE_URL +
                date +
                FootballApiConstants.TIMEZONE_URL;

        header.set(FootballApiConstants.API_HEADER, FootballApiConstants.API_KEY);
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ApiWrapper> request = new HttpEntity<>(header);
        ResponseEntity<ApiWrapper> response = restTemplate.exchange(url, HttpMethod.GET, request, ApiWrapper.class);

        apiCallCountUtil.setApiCallsMade(apiCallCountUtil.getApiCallsMade()+1);
        return response.getBody();
    }

    public ApiWrapper getFixturesByTeamId(int teamId) {

        String url = FootballApiConstants.GET_FIXTURES_BY_TEAM_ID +
                teamId +
                FootballApiConstants.TIMEZONE_URL;

        header.set(FootballApiConstants.API_HEADER, FootballApiConstants.API_KEY);
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ApiWrapper> request = new HttpEntity<>(header);
        ResponseEntity<ApiWrapper> response = restTemplate.exchange(url, HttpMethod.GET, request, ApiWrapper.class);

        apiCallCountUtil.setApiCallsMade(apiCallCountUtil.getApiCallsMade()+1);
        return response.getBody();
    }

    public ApiWrapper getCoachByTeamId(int teamId) {

        String url = FootballApiConstants.GET_COACH_BY_TEAM_ID + teamId;

        header.set(FootballApiConstants.API_HEADER, FootballApiConstants.API_KEY);
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ApiWrapper> request = new HttpEntity<>(header);
        ResponseEntity<ApiWrapper> response = restTemplate.exchange(url, HttpMethod.GET, request, ApiWrapper.class);

        apiCallCountUtil.setApiCallsMade(apiCallCountUtil.getApiCallsMade()+1);
        return response.getBody();
    }

    int n = 0;

    /**
     *
     * @param fixtureId the id of the match
     * @return json odds for 1x2 from all bookmakers for the requested match
     */
    public ApiWrapper getOddsByFixtureIdAndLabel(int fixtureId) {

        String url = FootballApiConstants.GET_ODDS_BY_FIXTURE_AND_LABEL + fixtureId + FootballApiConstants.LABEL_SUFFIX;

        header.set(FootballApiConstants.API_HEADER, FootballApiConstants.API_KEY);
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ApiWrapper> request = new HttpEntity<>(header);
        ResponseEntity<ApiWrapper> response = restTemplate.exchange(url, HttpMethod.GET, request, ApiWrapper.class);

        apiCallCountUtil.setApiCallsMade(apiCallCountUtil.getApiCallsMade()+1);
        n++;
        LOGGER.info("Odds service called {} times", n);
        return response.getBody();
    }

}

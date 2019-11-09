package com.btsbetting.client;

import com.btsbetting.constants.FootballApiConstants;
import com.btsbetting.constants.LocalApiConstants;
import com.btsbetting.domain.ApiWrapper;
import com.btsbetting.utils.ApiCallCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiFootballClient {

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

}

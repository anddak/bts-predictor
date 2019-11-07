package com.btsbetting.client;

import com.btsbetting.domain.ApiWrapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ApiFootballClient {

    private RestTemplate restTemplate = new RestTemplate();

    HttpHeaders header = new HttpHeaders();
    HttpEntity<ApiWrapper> request = new HttpEntity<>(header);



    public ApiWrapper getFixturesByDate(String date) {

        String url = "https://api-football-v1.p.rapidapi.com/v2/fixtures/date/" +
                date +
                "?timezone=Europe/London";

        header.set("X-RapidAPI-Key", "61a1245a08msh9e6892b746bc888p10ae22jsnb8796cc88e31");
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ApiWrapper> request = new HttpEntity<>(header);
        ResponseEntity<ApiWrapper> response = restTemplate.exchange(url, HttpMethod.GET, request , ApiWrapper.class);

        return response.getBody();
    }

    public ApiWrapper getFixturesByTeamId(int teamId) {

        String url = "https://api-football-v1.p.rapidapi.com/v2/fixtures/team/" + teamId + "?timezone=Europe/London";

        header.set("X-RapidAPI-Key", "61a1245a08msh9e6892b746bc888p10ae22jsnb8796cc88e31");
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ApiWrapper> request = new HttpEntity<>(header);
        ResponseEntity<ApiWrapper> response = restTemplate.exchange(url, HttpMethod.GET, request , ApiWrapper.class);

        return response.getBody();
    }

}

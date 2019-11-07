package com.btsbetting;

import com.btsbetting.client.ApiFootballClient;
import com.btsbetting.domain.Fixture;
import com.btsbetting.service.PredictionsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;

@SpringBootApplication
public class BtsBettingApplication {

	public static void main(String[] args) {

		SpringApplication.run(BtsBettingApplication.class, args);

		PredictionsService predictionsService = new PredictionsService();

//		List<Fixture> filteredFixtures = predictionsService.refineMatchesByLeague(predictionsService.getMatchesByDate(apiFootballClient));

//		List<Fixture> fixturesByTeamIdAndDateRestriction = predictionsService.getFixturesByTeamId(apiFootballClient);

//		fixturesByTeamIdAndDateRestriction.forEach(System.out::println);

		predictionsService.calculatePoints().forEach(System.out::println);

//		predictionsService.findFixtureTeamIds().forEach(System.out::println);
	}


}

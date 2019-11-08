package com.btsbetting;

import com.btsbetting.client.ApiFootballClient;
import com.btsbetting.utils.ApiCallCountUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BtsBettingApplication {

	public static void main(String[] args) {

		SpringApplication.run(BtsBettingApplication.class, args);

	}

	@Bean
	public ApiCallCountUtil getApiCallCountUtil() {
		return new ApiCallCountUtil();
	}

	@Bean
	public ApiFootballClient getApiFootballClient() { return new ApiFootballClient(); }


}

package com.example.lottery.config;

import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.example.lottery.service.RandomNumberService;

@Configuration
public class AppConfig {

	@Bean
	//@ServiceQuality(QualityLevel.CHEAP)
	@ConditionalOnProperty(name = "quality.level", havingValue = "CHEAP")
	@Lazy
	public RandomNumberService getCheapRandomNumberService() {
		var cheap = new RandomNumberService() {
			
			@Override
			public int draw(int min, int max) {
				System.err.println("CheapRandomNumberService::draw");
				return new Random().nextInt(min, max);
			}
		};
		return cheap;
	}
	
}

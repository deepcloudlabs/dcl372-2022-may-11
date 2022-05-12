package com.example.lottery.service.business;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.ServiceQuality;

@Service
@Lazy
//@ServiceQuality(QualityLevel.FAST)
@ConditionalOnProperty(name = "quality.level", havingValue = "FAST")
public class FastRandomNumberService implements RandomNumberService {

	private Random random = new SecureRandom();

	public FastRandomNumberService() {
		System.err.println("FastRandomNumberService is created.");
	}

	@Override
	public int draw(int min, int max) {
		System.err.println("FastRandomNumberService::draw");
		return random.nextInt(min, max);
	}

}

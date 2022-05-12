package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.ServiceQuality;

@Service
@Lazy
//@ServiceQuality(QualityLevel.SECURE)
@ConditionalOnProperty(name = "quality.level", havingValue = "SECURE")
public class SecureRandomNumberService implements RandomNumberService {
	public SecureRandomNumberService() {
		System.err.println("SecureRandomNumberService is created.");
	}

	@Override
	public int draw(int min, int max) {
		System.err.println("SecureRandomNumberService::draw");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}

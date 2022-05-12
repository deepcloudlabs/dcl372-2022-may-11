package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
@Lazy
@Profile({"prod", "preprod"})
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

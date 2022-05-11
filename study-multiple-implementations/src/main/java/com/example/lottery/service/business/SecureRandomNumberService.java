package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
public class SecureRandomNumberService implements RandomNumberService {

	@Override
	public int draw(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}

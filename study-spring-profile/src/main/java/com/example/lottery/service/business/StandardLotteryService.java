package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
@Lazy
public class StandardLotteryService implements LotteryService {
	private RandomNumberService randomNumberService;
	
	@Value("${lotteryMax}") 
	private int lotteryMax;
	@Value("${lotterySize}") 
	private int lotterySize;
	
	public StandardLotteryService(RandomNumberService randomNumberService) {
		System.err.println("StandardLotteryService is created");
		this.randomNumberService = randomNumberService;
	}

	public List<Integer> getNumbers() {
		return IntStream.generate(() -> randomNumberService.draw(1, lotteryMax))
				        .distinct()
				        .limit(lotterySize)
				        .sorted()
				        .boxed()
				        .toList();
	}

	@Override
	public List<List<Integer>> getNumbers(int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> getNumbers())
				        .toList();
	}

}
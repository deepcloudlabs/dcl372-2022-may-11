package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.QualityLevel;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.ServiceQuality;

@Service
@Lazy
public class StandardLotteryService implements LotteryService {
	private List<RandomNumberService> randomNumberServices;
	private int index = 0;
	
	public StandardLotteryService(
			@ServiceQuality(QualityLevel.SECURE) List<RandomNumberService> randomNumberServices) {
		System.err.println("StandardLotteryService is created");
		this.randomNumberServices = randomNumberServices;
		this.randomNumberServices.forEach(service -> System.err.println(service.getClass()));

	}

	@Override
	public List<Integer> getNumbers(int max, int size) {
		index  = (index+1) % randomNumberServices.size();
		var randomNumberService = randomNumberServices.get(index);
		return IntStream.generate(() -> randomNumberService.draw(1, max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .toList();
	}

	@Override
	public List<List<Integer>> getNumbers(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> getNumbers(max,size))
				        .toList();
	}

}
package com.example.lottery.service;

import java.util.List;

public interface LotteryService {
	List<Integer> getNumbers(int max, int size);

	List<List<Integer>> getNumbers(int max, int size, int column);
}

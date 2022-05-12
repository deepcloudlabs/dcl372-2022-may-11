package com.example.lottery.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lottery.service.LotteryService;

@RestController
//@RequestScope
@RequestMapping("numbers")
@Validated
@CrossOrigin
public class LotteryController {

	private LotteryService lotteryService;

	public LotteryController(LotteryService lotteryService) {
		System.err.println("LotteryController is created.");
		this.lotteryService = lotteryService;
	}

	@GetMapping(params = "column")
	public List<List<Integer>> getLotteryNumbers(@RequestParam int column) {
		return lotteryService.getNumbers(column);
	}
}

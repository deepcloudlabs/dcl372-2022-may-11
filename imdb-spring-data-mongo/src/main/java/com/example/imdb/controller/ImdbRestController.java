package com.example.imdb.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Validated
public class ImdbRestController {
	private final MovieRepository movieRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	public ImdbRestController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@GetMapping(value = "/movies", params = { "fromYear", "toYear" })
	public List<Movie> getMoviesByYearRangeAndGenre(@RequestParam @Min(1940) int fromYear, @RequestParam @Max(2022) int toYear) {
		return movieRepository.findAllByYearBetween(fromYear, toYear);
	}

}

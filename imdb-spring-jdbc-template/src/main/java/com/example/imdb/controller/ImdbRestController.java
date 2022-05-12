package com.example.imdb.controller;

import java.util.List;

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
public class ImdbRestController {
	private final MovieRepository movieRepository;

	public ImdbRestController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@GetMapping(value = "/movies", params = { "fromYear", "toYear" })
	public List<Movie> getMoviesByYearRangeAndGenre(@RequestParam int fromYear, @RequestParam int toYear) {
		return movieRepository.findAllByYearBetween(fromYear, toYear);
	}

}

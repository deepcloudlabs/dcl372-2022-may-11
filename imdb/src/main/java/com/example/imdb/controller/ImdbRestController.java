package com.example.imdb.controller;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Director;
import com.example.imdb.domain.Movie;
import com.example.imdb.dto.response.MovieResponse;
import com.example.imdb.model.CriteriaBean;
import com.example.imdb.service.MovieRepository;


// Http: GET, DELETE -> Empty Request Body, Response Body
//       POST, PUT, PATCH -> Request+Response Body
@RestController
@RequestScope
@RequestMapping("/api/v1/movies")
@Validated
@CrossOrigin
public class ImdbRestController {
	private MovieRepository movieRepository;
	private ModelMapper modelMapper;
	
	public ImdbRestController(MovieRepository movieRepository,ModelMapper modelMapper) {
		this.movieRepository = movieRepository;
		this.modelMapper = modelMapper;
	}
	
	// Resource: Movie ? URL -> Canonical Form
	// GET http://localhost:7100/imdb/api/v1/movies/42
	@GetMapping("{id}")
	public MovieResponse findMovieById(@PathVariable int id){
		return modelMapper.map(movieRepository.findMovieById(id),MovieResponse.class);
	}

	// GET http://localhost:7100/imdb/api/v1/movies?fromYear=1970&toYear=1979
	@GetMapping(params = {"fromYear","toYear"})
	public List<MovieResponse> findAllByYearBetween(@RequestParam int fromYear,@RequestParam int toYear){
		return movieRepository.findAllMoviesByYearRange(fromYear,toYear)
				              .stream()
				              .map(movie -> modelMapper.map(movie,MovieResponse.class)).toList();
	}
	
	// POST http://localhost:7100/imdb/api/v1/movies/criteria
	@PostMapping
	public CriteriaBean findMovieByCriteria(@RequestBody CriteriaBean criteria){
		Collection<Movie> movies = movieRepository.findAllMoviesByCriteria(criteria);
		criteria.setMovies(movies);
		return criteria;
	}
	
	// GET http://localhost:7100/imdb/api/v1/movies/42/directors/2
	@GetMapping(value="/{id}/directors")
	public List<Director> getMovieDirectorsById(@PathVariable int id){
		return movieRepository.findMovieById(id).getDirectors();
	}
	
}

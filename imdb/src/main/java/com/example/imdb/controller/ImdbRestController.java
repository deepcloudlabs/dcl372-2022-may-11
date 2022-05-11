package com.example.imdb.controller;

import java.util.Collection;
import java.util.List;

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
@RequestMapping("movies")
@Validated
@CrossOrigin
public class ImdbRestController {
	private MovieRepository movieRepository;
	
	public ImdbRestController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	// Resource: Movie ? URL -> Canonical Form
	// GET http://localhost:7100/imdb/api/v1/movies/42
	@GetMapping("{id}")
	public Movie findMovieById(@PathVariable int id){
		return movieRepository.findMovieById(id);
	}

	// GET http://localhost:7100/imdb/api/v1/movies?genre=Comedy
	@GetMapping(params = {"genre","director","range"})
	public Collection<Movie> findMovieByGenre(@RequestParam String genre){
		return movieRepository.findAllMoviesByGenre(genre);
	}
	
	// POST http://localhost:7100/imdb/api/v1/movies/criteria
	@PostMapping
	public CriteriaBean findMovieByCriteria(@RequestBody CriteriaBean criteria){
		Collection<Movie> movies = movieRepository.findAllMoviesByCriteria(criteria);
		criteria.setMovies(movies);
		return criteria;
	}
	
	// GET http://localhost:7100/imdb/api/v1/movies/42/directors/2
	@GetMapping(value="/{id}/directors/{no}")
	public List<Director> getMovieDirectorsById(@PathVariable int id,@PathVariable int no){
		return movieRepository.findMovieById(id).getDirectors();
	}
	
}

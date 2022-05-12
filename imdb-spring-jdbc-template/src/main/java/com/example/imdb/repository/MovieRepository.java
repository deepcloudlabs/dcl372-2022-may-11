package com.example.imdb.repository;

import java.util.List;
import java.util.Optional;

import com.example.imdb.entity.Movie;

public interface MovieRepository {
	Movie addMovie(Movie movie);

	Optional<Movie> findById(int movieId);
	
	List<Movie> findAllMovies(int pageNo,int pageSize);
	
	List<Movie> findAllMoviesByYear(int year);

	Movie update(Movie movie);

	Optional<Movie> removeById(int movieId);

	List<Movie> findAllByYearBetween(int fromYear, int toYear);
	
}

package com.example.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

@SpringBootApplication
public class ImdbSpringJdbcTemplateApplication implements ApplicationRunner {
	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(ImdbSpringJdbcTemplateApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		movieRepository.removeById(1024).ifPresent(System.err::println);
		movieRepository.findAllMoviesByYear(2009).forEach(System.err::println);
		if (movieRepository.findById(1024).isEmpty())
			movieRepository.addMovie(new Movie(1024, "test", 2022, "tt123456"));
	}

}

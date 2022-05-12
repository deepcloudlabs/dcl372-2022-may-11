package com.example.imdb;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.imdb.service.MovieRepository;

@SpringBootApplication
public class ImdbApplication implements ApplicationRunner {
	private MovieRepository movieRepository;
	
	public ImdbApplication(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ImdbApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		movieRepository.findAllMoviesByGenre("Comedy")
		               .forEach(System.err::println);
		movieRepository.findAllGenres();
		
	}

}

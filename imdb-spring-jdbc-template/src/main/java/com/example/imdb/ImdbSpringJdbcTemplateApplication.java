package com.example.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		var movie = movieRepository.findById(1);
		System.err.println(movie);
	}

}

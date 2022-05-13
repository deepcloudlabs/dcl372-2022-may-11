package com.example.imdb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.imdb.entity.Movie;

public interface MovieRepository extends MongoRepository<Movie, Integer>{
	
	@Query("{year: {$eq : ?}}")
	List<Movie> yilaGoreGetir(int year);
	List<Movie> findFirst10ByYear(int year);
	Optional<Movie> findOneByYearAndImdb(int year,String imdb);
	List<Movie> findAllByYearBetween(int fromYear, int toYear);
	
	
}

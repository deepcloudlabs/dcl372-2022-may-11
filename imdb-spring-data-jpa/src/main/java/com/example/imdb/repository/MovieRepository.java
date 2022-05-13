package com.example.imdb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.imdb.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	@Query("select m from Movie m where m.year=:year")
	List<Movie> yilaGoreGetir(int year);
	List<Movie> findFirst10ByYear(int year);
	Optional<Movie> findOneByYearAndImdb(int year,String imdb);
	@Query(nativeQuery = true, value = """
			select m.movieID,m.title,m.year,m.imdb 
			from movies m 
			where m.year between :fromYear and :toYear
			""")
	List<Movie> findAllByYearBetween(int fromYear, int toYear);
	
	
}

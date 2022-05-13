package com.example.imdb.repository.orm;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

@Repository
public class JpaMovieRepository implements MovieRepository {
	private EntityManager entityManager;
	
	public JpaMovieRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public Movie addMovie(Movie movie) {
		entityManager.persist(movie);
		return movie;
	}

	@Override
	public Optional<Movie> findById(int movieId) {
		return Optional.ofNullable(entityManager.find(Movie.class, movieId));
	}

	@Override
	public List<Movie> findAllMovies(int pageNo, int pageSize) {
		return entityManager.createNamedQuery("Movie.findAll", Movie.class)
				             .setFirstResult(pageNo*pageSize)
				             .setMaxResults(pageSize)
				             .getResultList();
	}

	@Override
	public List<Movie> findAllMoviesByYear(int year) {
		return entityManager.createNamedQuery("Movie.findAllByYear", Movie.class)
							.setParameter("year", year)
				            .getResultList();
	}

	@Override
	@Transactional
	public Movie update(Movie movie) {
		var movieId = movie.getMovieId();
		var managedMovie = entityManager.find(Movie.class, movieId );
		managedMovie.setTitle(movie.getTitle());
		managedMovie.setYear(movie.getYear());
		entityManager.merge(managedMovie);
		return managedMovie;
	}

	@Override
	@Transactional
	public Optional<Movie> removeById(int movieId) {
		var movie = entityManager.find(Movie.class, movieId);
		if (Objects.nonNull(movie))
		   entityManager.remove(movie);
		return Optional.ofNullable(movie);
	}

	@Override
	public List<Movie> findAllByYearBetween(int fromYear, int toYear) {
		return entityManager.createNamedQuery("Movie.findAllByYearBetween", Movie.class)
				.setParameter("from", fromYear)
				.setParameter("to", toYear)
	            .getResultList();
	}

}

package com.example.imdb.repository.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

@Repository
public class JdbcTemplateMovieRepository implements MovieRepository {
	private final RowMapper<Movie> MOVIE_ROW_MAPPER = (rs, index) -> {
        var movie = new Movie();
        movie.setMovieId(rs.getInt("movieid"));
        movie.setTitle(rs.getString("title"));
        movie.setYear(rs.getInt("year"));
        movie.setImdb(rs.getString("imdb"));
        return movie;
             };
	private static final String SELECT_MOVIE_BY_ID = """
			SELECT MOVIEID, YEAR, TITLE, IMDB
			FROM MOVIES
			WHERE MOVIEID=:id
			""";
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public JdbcTemplateMovieRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Movie addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Movie> findById(int movieId) {
		return Optional.ofNullable(
//			jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID,Map.of("id",movieId),MOVIE_ROW_MAPPER)
			jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID,Map.of("id",movieId),new BeanPropertyRowMapper<Movie>(Movie.class))
				);
	}

	@Override
	public List<Movie> findAllMovies(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findAllMoviesByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie update(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Movie> removeById(int movieId) {
		// TODO Auto-generated method stub
		return null;
	}

}

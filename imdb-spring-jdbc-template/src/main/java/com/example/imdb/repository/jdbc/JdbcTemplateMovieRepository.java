package com.example.imdb.repository.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

@Repository
public class JdbcTemplateMovieRepository implements MovieRepository {
	private static final RowMapper<Movie> MOVIE_ROW_MAPPER = (rs, index) -> {
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
	private static final String SELECT_MOVIES_BY_PAGE = """
			SELECT MOVIEID, YEAR, TITLE, IMDB
			FROM MOVIES
			LIMIT :offset,:size
			""";
	private static final String SELECT_MOVIES_BY_YEAR_BETWEEN = """
			SELECT MOVIEID, YEAR, TITLE, IMDB
			FROM MOVIES
			WHERE YEAR BETWEEN :from AND :to
			""";
	private static final String INSERT_MOVIE = """
			INSERT INTO MOVIES(movieid,year, title, imdb) VALUES(:id,:year,:title,:imdb)
			""";
	private static final String UPDATE_MOVIE = """
			UPDATE MOVIES
			SET year=:year,title=:title,imdb=:imdb
			WHERE movieid=:id
			""";
	private static final String SELECT_MOVIES_BY_YEAR = """
			SELECT MOVIEID, YEAR, TITLE, IMDB
			FROM MOVIES
			WHERE year=:year
			""";
	private static final String REMOVE_MOVIE_BY_ID = """
			DELETE FROM MOVIES
			WHERE movieid=:id
			""";
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcTemplateMovieRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public Movie addMovie(Movie movie) {
		jdbcTemplate.update(INSERT_MOVIE, Map.of("id", movie.getMovieId(), "title", movie.getTitle(), "year",
				movie.getYear(), "imdb", movie.getImdb()));
		return movie;
	}

	@Override
	public Optional<Movie> findById(int movieId) {
		return Optional.ofNullable(
//			jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID,Map.of("id",movieId),MOVIE_ROW_MAPPER)
				jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID, Map.of("id", movieId),
						new BeanPropertyRowMapper<Movie>(Movie.class)));
	}

	@Override
	public List<Movie> findAllMovies(int pageNo, int pageSize) {
		return jdbcTemplate.query(SELECT_MOVIES_BY_PAGE, 
				Map.of(
						"offset", pageNo * pageSize, 
						"size", pageSize
						),
				MOVIE_ROW_MAPPER);
	}

	@Override
	public List<Movie> findAllMoviesByYear(int year) {
		return jdbcTemplate.query(SELECT_MOVIES_BY_YEAR, Map.of("year", year), MOVIE_ROW_MAPPER);
	}

	@Override
	@Transactional
	public Movie update(Movie movie) {
		jdbcTemplate.update(UPDATE_MOVIE, Map.of("id", movie.getMovieId(), "title", movie.getTitle(), "year",
				movie.getYear(), "imdb", movie.getImdb()));
		return movie;
	}

	@Override
	@Transactional
	public Optional<Movie> removeById(int movieId) {
		var movie = findById(movieId);
		return jdbcTemplate.execute(REMOVE_MOVIE_BY_ID, Map.of("id", movieId), (ps) -> movie);
	}

	@Override
	public List<Movie> findAllByYearBetween(int fromYear, int toYear) {
		return jdbcTemplate.query(SELECT_MOVIES_BY_YEAR_BETWEEN, Map.of("from", fromYear, "to", toYear),
				MOVIE_ROW_MAPPER);
	}

}

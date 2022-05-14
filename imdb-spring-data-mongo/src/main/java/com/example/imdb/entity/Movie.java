package com.example.imdb.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// Alt + Shift + S
@Document(collection = "movies1")
public class Movie {
	@Id
	private int movieId;
	@NotEmpty
	private String title;
	@Indexed
	@Min(1940)
	private int year;
	@Indexed(unique = true)
	@Pattern(regexp="tt[0-9]{6,}")
	private String imdb;

	public Movie() {
	}

	public Movie(int movieId, String title, int year, String imdb) {
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.imdb = imdb;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", year=" + year + ", imdb=" + imdb + "]";
	}

}

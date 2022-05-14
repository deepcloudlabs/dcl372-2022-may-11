package com.example.imdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// Alt + Shift + S
@Entity
@Table(name = "movies")
@NamedQueries({
	@NamedQuery(name="Movie.findAll", query = "select m from Movie m"), // JPQL
	@NamedQuery(name="Movie.findAllByYear", query="select m from Movie m where m.year=:year"),
	@NamedQuery(name="Movie.findAllByYearBetween", query="select m from Movie m where m.year between :from and :to")
})
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movieid")
	private int movieId;
	@Column(name = "title")
	private String title;
	@Column(name = "year")
	private int year;
	@Column(name = "imdb")
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

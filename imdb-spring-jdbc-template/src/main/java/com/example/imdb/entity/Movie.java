package com.example.imdb.entity;

// Alt + Shift + S
public class Movie {
	private int movieId;
	private String title;
	private int year;
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

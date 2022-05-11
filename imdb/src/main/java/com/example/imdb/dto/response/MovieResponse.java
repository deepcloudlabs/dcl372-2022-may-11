package com.example.imdb.dto.response;

import java.util.List;

import com.example.imdb.domain.Genre;

public class MovieResponse {
	private int id;
	private String title;
	private int year;
	private String imdb;
	private List<Genre> genres;

	public MovieResponse() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "MovieResponse [id=" + id + ", title=" + title + ", year=" + year + ", imdb=" + imdb + ", genres="
				+ genres + "]";
	}

}

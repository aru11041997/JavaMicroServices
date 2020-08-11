package com.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDto {

	@JsonProperty("id")
	private int id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("gener")
	private String gener;

	
	
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

	public String getGener() {
		return gener;
	}

	public void setGener(String gener) {
		this.gener = gener;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", title=" + title + ", gener=" + gener + "]";
	}
}

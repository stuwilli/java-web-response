package org.websure.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestModel {
	
	@JsonProperty
	private int id;
	
	@JsonProperty
	private String name;

	public TestModel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}

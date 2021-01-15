package com.abhilash.project.projectforsubmission.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document (collection = "outgoing_data")
public class OutputResponse {
	
	
	long id;
	String responsedata;
	
	@JsonIgnore
	
	@Transient
	public String SEQUENCE_NAME;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getResponsedata() {
		return responsedata;
	}
	public void setResponsedata(String responsedata) {
		this.responsedata = responsedata;
	}
	
	
	
	
	
	

}

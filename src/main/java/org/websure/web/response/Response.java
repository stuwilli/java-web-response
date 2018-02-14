package org.websure.web.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public interface Response<T> {
	
	@JsonProperty("statusCode")
	int getStatus();
	
	@JsonProperty("message")
	String getMessage();
	
	@JsonProperty("timestamp")
	long getTimestamp();
	
	@JsonProperty("errors")
	Map<String, String> getErrors();
	
	@JsonProperty("data")
	T getData();
}

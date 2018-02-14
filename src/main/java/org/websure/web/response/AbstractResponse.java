package org.websure.web.response;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public abstract class AbstractResponse {
	
	private int status;
	
	private String message;
	
	private long timestamp;
	
	private Map<String, String> errors;
	
	public AbstractResponse(int status) {
		this.timestamp = new Date().getTime();
		this.setStatus(status);
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.message = resolveStatusCode(status);
	}

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public void addError(String key, String val) {
		
		if (errors == null) {
			errors = new LinkedHashMap<String, String>();
		}
		
		errors.put(key, val);
	}
	
	public void addError(String val) {
		
		if (errors == null) {
			
			addError("error_1", val);
			return;
		}
		
		int count = errors.size() +1;
		String key = "error_" + count;
		
		addError(key, val);
	}
	
	public void addError(String key, Exception err) {
		
		addError(key, err.getMessage());
	}		
	
	private String resolveStatusCode(int code) {
		return HttpStatus.valueOf(code).getReasonPhrase();
	}
}

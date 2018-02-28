package org.websure.web.response;

import java.util.Map;

public class ErrorResponse extends AbstractResponse implements Response<Object>{
	
	public ErrorResponse(int status) {
		super(status);
	}
	
	public ErrorResponse(int status, String key, String val) {
		super(status);
		this.addError(key, val);
	}
	
	public ErrorResponse(int status, String val) {
		super(status);
		this.addError(val);
	}
	
	public ErrorResponse(int status, Exception err) {
		super(status);
		this.addError("error", err);
	}
	
	public ErrorResponse(int status, Map<String, String> errs) {
		super(status);
		this.setErrors(errs);
	}
	
	@Override
	public Object getData() {
		return null;
	}

}

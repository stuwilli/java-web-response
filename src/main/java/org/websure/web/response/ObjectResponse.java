package org.websure.web.response;

public class ObjectResponse<T> extends AbstractResponse implements Response<T> {
	
	private T data;
	
	public ObjectResponse(int status, T data) {
		super(status);
		this.data = data;
	}

	@Override
	public T getData() {
		return this.data;
	}

}

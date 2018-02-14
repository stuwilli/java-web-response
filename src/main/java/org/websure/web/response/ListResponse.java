package org.websure.web.response;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ListResponse extends AbstractResponse implements Response<List> {
	
	private List data;
	
	public ListResponse(int status, List data) {
		super(status);
		this.data = data;
	}	
	
	@Override
	public List getData() {
		return this.data;
	}

}

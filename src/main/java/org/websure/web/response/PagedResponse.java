package org.websure.web.response;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("rawtypes")
public class PagedResponse extends AbstractResponse implements Response<Page>{	
	
	private Page data;
	
	public PagedResponse(int status, Page data) {
		super(status);
		this.data = data;
	}
	
	@Override
	@JsonSerialize(using=JsonPageSerializer.class, as=Page.class)
	public Page getData() {
	
		return this.data;
	}

}

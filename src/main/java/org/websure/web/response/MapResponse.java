package org.websure.web.response;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class MapResponse extends AbstractResponse implements Response<Map> {

	private Map map;
	
	public MapResponse(int status, Map map) {
		super(status);
		this.map = map;
	}

	@Override
	public Map getData() {
		
		return this.map;
	}

}

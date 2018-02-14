package org.websure.web.response;

import static com.jayway.jsonassert.JsonAssert.with;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorResponseTest {

	@SuppressWarnings("rawtypes")
	private Response resp;	
	private ObjectMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();

	}

	@Test
	public void testErrorResponseIntStringString() throws Exception {
		
		resp = new ErrorResponse(500, "test", "error");
		String json = mapper.writeValueAsString(resp);
		System.out.println(json);
		with(json).assertEquals("statusCode", 500);
		with(json).assertEquals("errors.test", "error");
	}

	@Test
	public void testErrorResponseIntString() throws Exception {
		
		resp = new ErrorResponse(500, "error1");
		
		String json = mapper.writeValueAsString(resp);
		System.out.println(json);
		with(json).assertEquals("statusCode", 500);
		with(json).assertEquals("errors.error_1", "error1");
	}

	@Test
	public void testErrorResponseIntException() throws Exception {

		resp = new ErrorResponse(500, new Exception("an error"));
		String json = mapper.writeValueAsString(resp);
		System.out.println(json);
		with(json).assertEquals("statusCode", 500);
		with(json).assertEquals("errors.error", "an error");
	}

	@Test
	public void testErrorResponseIntMap() throws Exception {
		
		Map<String, String> errs = new HashMap<>();
		errs.put("error_1", "error1");
		errs.put("error_2", "error2");
		
		resp = new ErrorResponse(500, errs);
		String json = mapper.writeValueAsString(resp);
		System.out.println(json);
		with(json).assertEquals("statusCode", 500);
		with(json).assertEquals("errors.error_1", "error1");
		with(json).assertEquals("errors.error_2", "error2");
	}	
	
	@Test
	public void testErrorResponseAddErrorString() throws Exception {
		
		ErrorResponse resp = new ErrorResponse(500, "error1");
		resp.addError("error2");
		String json = mapper.writeValueAsString(resp);
		System.out.println(json);
		with(json).assertEquals("statusCode", 500);
		with(json).assertEquals("errors.error_1", "error1");
		with(json).assertEquals("errors.error_2", "error2");		
	}
	
}

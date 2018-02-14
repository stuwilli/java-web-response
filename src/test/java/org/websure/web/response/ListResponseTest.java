package org.websure.web.response;

//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
import static com.jayway.jsonassert.JsonAssert.with;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ListResponseTest {
	
	private List<TestModel> data;
	@SuppressWarnings("rawtypes")
	private Response resp;

	@Before
	public void setUp() throws Exception {
		TestModel one = new TestModel(1, "one");
		TestModel two = new TestModel(2, "two");
		data = Arrays.asList(one, two);
	}

	@Test
	public void testListResponse() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		resp = new ListResponse(200, data);
		String json = mapper.writeValueAsString(resp);
		System.out.println(json);
		with(json).assertEquals("statusCode", 200);
		with(json).assertEquals("data[0].id", 1);
		with(json).assertEquals("data[0].name", "one");
		with(json).assertEquals("data[1].id", 2);
		with(json).assertEquals("data[1].name", "two");		
	}

}

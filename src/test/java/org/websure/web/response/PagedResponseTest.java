package org.websure.web.response;

import static com.jayway.jsonassert.JsonAssert.with;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PagedResponseTest {

	@SuppressWarnings("rawtypes")
	private Response resp;	
	private ObjectMapper mapper;	
	private Page<TestModel> data;
	
	@Before
	public void setUp() throws Exception {
		
		mapper = new ObjectMapper();
		
		TestModel one = new TestModel(1, "one");
		TestModel two = new TestModel(2, "two");
		List<TestModel> list = Arrays.asList(one, two);		
		Pageable page = PageRequest.of(1, 10);
		data = new PageImpl<TestModel>(list, page, 0);
	}

	@Test
	public void testPagedResponse() throws Exception {
		
		resp = new PagedResponse(200, data);
		String json = mapper.writeValueAsString(resp);
		System.out.println(json);
		with(json).assertEquals("statusCode", 200);
		with(json).assertEquals("data.content[0].id", 1);
		with(json).assertEquals("data.content[0].name", "one");
		with(json).assertEquals("data.content[1].id", 2);
		with(json).assertEquals("data.content[1].name", "two");
		//with(json).assertEquals("data.pageable.offset", 10);
		//with(json).assertEquals("data.pageable.pageNumber", 1);
	}

}

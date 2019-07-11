package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// Only want field1 and field2
	@GetMapping("/filtering")
	public SomeBean retreiveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("/filtering2")
	public MappingJacksonValue retreiveSomeBean2() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		// Dynamic filtering use MappingJacksonValue
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		// Create a filter rule
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		// Put the rule into the providers
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		// Put the providers into the Jackson mapper
		mapping.setFilters(filters);

		return mapping;

	}

	// Only want field2 and field3
	@GetMapping("/filtering-list")
	public List<SomeBean> retreiveListofSomeBeans() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value12", "value22", "value32"));
	}

	@GetMapping("/filtering-list2")
	public MappingJacksonValue retreiveListofSomeBeans2() {
		List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value12", "value22", "value32"));
		MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filters);
		return mapping;
	}

}

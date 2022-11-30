package com.microservice.filters;


import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class FieldFilters {
	
	public MappingJacksonValue getFilter(Object sourceObject, String filterName, String... fieldsToFilterIn) {			
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(sourceObject);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToFilterIn);
		FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
		mappingJacksonValue.setFilters(filters);		
		return mappingJacksonValue;
	}

}

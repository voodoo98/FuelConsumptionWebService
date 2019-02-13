package com.fuelconsumption.providers;

import javax.enterprise.inject.Produces;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fuelconsumption.annotations.JacksonMapper;

public class JacksonProvider {

	@Produces
	@JacksonMapper
	public ObjectMapper mapper() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		return mapper;
	}
}

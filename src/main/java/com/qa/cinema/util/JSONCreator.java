package com.qa.cinema.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONCreator {
	
	public String toJSON(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return "null";
		}
	}
	
	public <K> K fromJSON(String json, Class<K> type) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readerFor(type).readValue(json);
		} catch (IOException e) {
			return null;
		}
	}
}

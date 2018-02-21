package com.qa.cinema.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONCreator {
	
	public String toJSON(Object obj) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(obj);
	}
}

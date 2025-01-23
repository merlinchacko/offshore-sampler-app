package com.fugro.osa.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fugro.osa.model.Location;
import com.fugro.osa.model.Sample;

public class BackendService {

	private String BASE_URL = "http://localhost:8081/api";
	private HttpClientService httpClientService = new HttpClientService();

	public List<Sample> loadSamples() throws IOException {
		
		String url = BASE_URL + "/samples";
		String response = httpClientService.fetchDataFromBackend(url);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.readValue(response, new TypeReference<List<Sample>>() {
		});
	}

	public List<Location> loadLocations() throws IOException {
		
		String url = BASE_URL + "/locations";
		String response = httpClientService.fetchDataFromBackend(url);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(response, new TypeReference<List<Location>>() {
		});
	}

}

package com.fugro.osa.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientService {
	
	 public String fetchDataFromBackend(String url) throws IOException {
	        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	            HttpGet request = new HttpGet(url);
	            try (CloseableHttpResponse response = httpClient.execute(request)) {
	                HttpEntity entity = response.getEntity();
	                return EntityUtils.toString(entity);
	            }
	        }
	    }
}

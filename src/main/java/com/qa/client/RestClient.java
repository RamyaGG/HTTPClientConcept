package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	//1. GET call
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient  httpClient = HttpClients.createDefault(); //Creating Http client
		HttpGet httpGet = new HttpGet(url); //HTTP Get request
		CloseableHttpResponse  closeableHttpResponse = httpClient.execute(httpGet);
		return closeableHttpResponse;
	}
	
	
	//2. POST call
	public CloseableHttpResponse  post(String url,String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient  httpClient = HttpClients.createDefault(); //Creating Http client
		HttpPost httpPost = new HttpPost(url); //http Post request
		httpPost.setEntity(new StringEntity(entityString)); //for adding payload
		
		//add headers
		 //why for each loop ? becoz hashmap does not store the value on the basis of indexes. 
			  //if it is arrayList then we can use for loop. Foe hashTable and hashMap we cannot iterate on the basis of indexes.
			  //So we cannot use for loop. 
		for(Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue()	);
			
		}
		
		CloseableHttpResponse  closeableHttpResponse  = httpClient.execute(httpPost);
		return closeableHttpResponse;
		
		
	}
	
	
	//3. Put call
		public CloseableHttpResponse  put(String url,String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient  httpClient = HttpClients.createDefault(); //Creating Http client
			HttpPut httpPut = new HttpPut(url); //http Put request
			httpPut.setEntity(new StringEntity(entityString)); //for adding payload
			
			//add headers
			 //why for each loop ? becoz hashmap does not store the value on the basis of indexes. 
				  //if it is arrayList then we can use for loop. Foe hashTable and hashMap we cannot iterate on the basis of indexes.
				  //So we cannot use for loop. 
			for(Entry<String, String> entry : headerMap.entrySet()) {
				httpPut.addHeader(entry.getKey(), entry.getValue()	);
				
			}
			
			CloseableHttpResponse  closeableHttpResponse  = httpClient.execute(httpPut);
			return closeableHttpResponse;
			
			
		}
		
		//3. Delete call
		public CloseableHttpResponse  delete(String url) throws ClientProtocolException, IOException {
			CloseableHttpClient  httpClient = HttpClients.createDefault(); //Creating Http client
			HttpDelete httpDelete = new HttpDelete(url); //http delete request
			
			CloseableHttpResponse  closeableHttpResponse  = httpClient.execute(httpDelete);
			return closeableHttpResponse;
			
			
		}
	
}

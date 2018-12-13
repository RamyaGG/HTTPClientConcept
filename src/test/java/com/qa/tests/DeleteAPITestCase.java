package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class DeleteAPITestCase {
	
	String url = "https://reqres.in/api/users"; 
	String apiUrl;
	RestClient restClient; 
	
	@BeforeMethod
	public void setUp() {
		apiUrl=url+"/2";
		
	}
	
	@Test
	public void deleteAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse closeableHttpResponse = restClient.delete(apiUrl);
		
		//1. Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code is : "+statusCode);
		Assert.assertEquals(statusCode, 204);
		
		//2. JSON String:
		HttpEntity httpEntity = closeableHttpResponse.getEntity();
		Assert.assertNull(httpEntity);
		
		//3. headers:
		Header headerArray[] = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
			
		}
		System.out.println("Headers are coming in Response: "+allHeaders);

	}
	

}

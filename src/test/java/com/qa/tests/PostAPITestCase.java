package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.users.data.Users;

public class PostAPITestCase {

	String url = "https://reqres.in";
	String apiUrl;
	RestClient restClient;

	@BeforeMethod
	public void setUp() {
		apiUrl = url + "/api/users";
	}

	@Test
	public void CreateUserTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		// 1. Header prep:
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// 2. prepare json payload : jackson api : core and databind APIs(dependencies i
		// Maven pom.xml file)
		//INT : ObjectMapper is a class coming from jackson api to convert java object(POJO) to JSON String
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Naveen", "Leader");

		// convert java object(POJO) to JSON String : Serialization -- marshelling
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println("Request Payload is :"+usersJsonString);
		
		CloseableHttpResponse  closeableHttpResponse = restClient.post(apiUrl, usersJsonString, headerMap);
		
		//4. Get the status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code is : "+ statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//5. Get the json payload from the Response
//		HttpEntity httpEntity = closeableHttpResponse.getEntity();
//		String responseString = EntityUtils.toString(httpEntity);
//		System.out.println("The response String is :"+responseString);
		
		String responseStringUsers = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject responseJSON = new JSONObject(responseStringUsers);
		System.out.println("The json response is "+ responseJSON);
		
		//6. convert JSON string to java object : DeSerialization -- Unmarshelling
		Users usersObj = mapper.readValue(responseStringUsers, Users.class);
		System.out.println(usersObj.getName());
		System.out.println(usersObj.getJob());
		
		Assert.assertEquals(usersObj.getName(), users.getName());
	}

}

package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import com.qa.Client.RestClientDemo;
import com.qa.data.Users;

public class PostApiMethod extends TestBase{
	
	String serviceUrl;
	String endpointUrl;
	String baseurl;
	RestClientDemo restClient;
	CloseableHttpResponse closeableHttpResponse	;
	@BeforeMethod
	public void setUp() {
		endpointUrl = prop.getProperty("EndPointUrl");
		serviceUrl= prop.getProperty("ServiceUrl");
		baseurl = endpointUrl+serviceUrl;
		
	}
	
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClientDemo();
		 HashMap <String, String> headerMap = new HashMap <String, String>();
		 headerMap.put("Content-Type", "application/json");
		 
		 //jackson Api, object
		 ObjectMapper mapper = new ObjectMapper();
		 //initialized the constructor.
		 Users user = new Users("morpheus", "leader");
		 //object to json file
		 mapper.writeValue(new File("C:\\Users\\alef1\\eclipse-workspace\\SeleniumMavenPractice\\src\\main\\java\\com\\qa\\data\\Users.json"), user);
		 
		 //object to json in string.
		String stringJson= mapper.writeValueAsString(user);
		System.out.println("the json string is ==> "+ stringJson);
		//call the post method.
		closeableHttpResponse = restClient.postMethod(baseurl, stringJson, headerMap);
		//get status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(" the status code is ==> " + statusCode);
		Assert.assertEquals(statusCode, 201, "entry is not created");
		//get json string.
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject jsonResponse = new JSONObject(responseString);
		System.out.println("the response entity string is ==>" + responseString);
		System.out.println("the response json string is ==>" + jsonResponse);
		//read value from response string
		Users userObj = mapper.readValue(responseString, Users.class);
		System.out.println("the read value is ==>"+ userObj);
		
		Assert.assertTrue(user.getName().equals(userObj.getName()));
		Assert.assertTrue(user.getJob().equals(userObj.getJob()));
		
		System.out.println(userObj.getId());
		System.out.println(userObj.getCreatedAt());
		
		
		
	}
	
	
	
}

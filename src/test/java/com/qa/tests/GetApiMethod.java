package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.Client.RestClientDemo;
import com.qa.Utility.TestUtility;

public class GetApiMethod extends TestBase{
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
	
	
	@Test(priority=1)
	public void GetApiMethodTest() throws ClientProtocolException, IOException {
		 restClient = new RestClientDemo();
		 closeableHttpResponse = restClient.getMethodWithoutHeader(baseurl);
		 int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		 System.out.println(" the status code is ==>" +statusCode);
		 Assert.assertEquals(statusCode, GET_HTTPRESPONSE_CODE_200, "Status code is not 200");
		 
		 String entityString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		 JSONObject jsonObject= new JSONObject(entityString);
		 System.out.println("Response json from api is ==>" +jsonObject);
		 
//		String perPageValue = TestUtility.getValueByJPath(jsonObject, "/per_page");
//		 String perPageValue = TestUtility.getValueByJPath(jsonObject, "/per_page");
//		 System.out.println("the value of per page is==> " + perPageValue);
//		 Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		 
		 String limit = TestUtility.getValueByJPath(jsonObject, "/limit");
		 System.out.println("the value of per page is==> " + limit);
		 Assert.assertEquals(Integer.parseInt(limit), 10);
//		 
//		 String totalValue = TestUtility.getValueByJPath(jsonObject, "/total");
//		 System.out.println("the value of total is==> " + totalValue);
//		 Assert.assertEquals(Integer.parseInt(totalValue), 12);
		 

		 String totalValue = TestUtility.getValueByJPath(jsonObject, "/total");
		 System.out.println("the value of total is==> " + totalValue);
		 Assert.assertEquals(Integer.parseInt(totalValue), 51957);
		 
		String id= TestUtility.getValueByJPath(jsonObject, "data[0]/id");
		System.out.println(id);
		
//		String email= TestUtility.getValueByJPath(jsonObject, "data[0]/email");
//		System.out.println(email);
//		
//		String first_name= TestUtility.getValueByJPath(jsonObject, "data[0]/first_name");
//		System.out.println(first_name);
//		
//		String last_name= TestUtility.getValueByJPath(jsonObject, "data[0]/last_name");
//		System.out.println(last_name);
//		Assert.assertEquals(last_name, "Bluth");
//		
//		String avatar= TestUtility.getValueByJPath(jsonObject, "data[0]/avatar");
//		System.out.println(avatar);
		
		Header headerArray [] = closeableHttpResponse.getAllHeaders();
		HashMap<String, Object> allHeaders = new HashMap<String, Object>();
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("the header Array is ==> " +allHeaders);
	}
	
	
	
	@Test(priority=2)
	public void GetApiMethodTestWithHeaders() throws ClientProtocolException, IOException {
		 restClient = new RestClientDemo();
		 
		 HashMap <String, String> headerMap = new HashMap <String, String>();
		 headerMap.put("Content-Type", "application/json");
//		 headerMap.put("first-name", "amazon123");
//		 headerMap.put("password", "123amazon");
//		 headerMap.put("Auth-Token", "123456");
		 
		 
		 closeableHttpResponse = restClient.getMethodWithHeader(baseurl, headerMap);
		 int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		 System.out.println(" the status code is ==>" +statusCode);
		 Assert.assertEquals(statusCode, GET_HTTPRESPONSE_CODE_200, "Status code is not 200");
		 
		 String entityString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		 JSONObject jsonObject= new JSONObject(entityString);
		 System.out.println("Response json from api is ==>" +jsonObject);
		 
		String perPageValue = TestUtility.getValueByJPath(jsonObject, "/per_page");
		 System.out.println("the value of per page is==> " + perPageValue);
		 Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		 
		 String totalValue = TestUtility.getValueByJPath(jsonObject, "/total");
		 System.out.println("the value of total is==> " + totalValue);
		 Assert.assertEquals(Integer.parseInt(totalValue), 12);
		 
		String id= TestUtility.getValueByJPath(jsonObject, "data[0]/id");
		System.out.println(id);
		
		String email= TestUtility.getValueByJPath(jsonObject, "data[0]/email");
		System.out.println(email);
		
		String first_name= TestUtility.getValueByJPath(jsonObject, "data[0]/first_name");
		System.out.println(first_name);
		
		String last_name= TestUtility.getValueByJPath(jsonObject, "data[0]/last_name");
		System.out.println(last_name);
		Assert.assertEquals(last_name, "Bluth");
		
		String avatar= TestUtility.getValueByJPath(jsonObject, "data[0]/avatar");
		System.out.println(avatar);
		
		Header headerArray [] = closeableHttpResponse.getAllHeaders();
		HashMap<String, Object> allHeaders = new HashMap<String, Object>();
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("the header Array is ==> " +allHeaders);
	}
	

}

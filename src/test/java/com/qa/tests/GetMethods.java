package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.Client.RestClient;

public class GetMethods extends TestBase {
	
	TestBase testBase;
	String endPointUrl;
	String serviceUrl;
	String baseUrl;
	RestClient restClient;
	@BeforeMethod
	public void tearDown() {
	testBase = new TestBase();
	endPointUrl = prop.getProperty("EndPointUrl");
	serviceUrl= prop.getProperty("ServiceUrl");
	baseUrl = endPointUrl + serviceUrl;
	
	}
	
	
	@Test
	public void getMethodTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		restClient.get(baseUrl);
	}

}

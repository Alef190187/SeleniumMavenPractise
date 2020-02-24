package com.qa.Client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	//Get method.
	public void get( String url) throws ClientProtocolException, IOException {
		//Create connection
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		//http Get request.
		//HttpGet httpGet = new HttpGet(url);
		//execute the get/hit the api.
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
		//get status code
		int httpStatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(" The Status code is====>" +httpStatusCode);
		//get response body.
		String responseString =EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		 System.out.println(" The response string is ===>" +responseString);
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println(" The response Json is ==>" +responseJson );
		//get all the headers.
		Header headersArray [] = closeableHttpResponse.getAllHeaders();
		 HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header header: headersArray ) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Header Array ==>" +allHeaders);
	}

}

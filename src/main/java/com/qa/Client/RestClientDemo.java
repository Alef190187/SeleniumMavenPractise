package com.qa.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClientDemo {
	//without header method.
	public CloseableHttpResponse getMethodWithoutHeader(String url) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		CloseableHttpResponse closeableHttpResponse	=closeableHttpClient.execute(httpGet);
	    return closeableHttpResponse;
	}
	//with header method
	public CloseableHttpResponse getMethodWithHeader(String url,HashMap <String, String> headerMap) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		for(Map.Entry<String, String> entry: headerMap.entrySet() ) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse	=closeableHttpClient.execute(httpGet);
	    return closeableHttpResponse;

}
	
	  public CloseableHttpResponse postMethod(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		  //HttpPost httpPost = new HttpPost(url);
		  CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		  HttpPost httpPost = new HttpPost(url);
		  for(Map.Entry<String, String> entry: headerMap.entrySet() ) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		  CloseableHttpResponse closeableHttpResponse =closeableHttpClient.execute(httpPost);
		   return closeableHttpResponse;
		  
	  }
}
package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Test2_Get_Single_Emp extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	public void getEmployeeData() throws InterruptedException{
		logger.info("********** Started Test2_Get_Single_Emp ****************");
		RestAssured.baseURI = "https://gorest.co.in/public-api";
		httpRequest = RestAssured.given();
		//response = httpRequest.request(Method.GET,"/users/1618?access-token=FA-zdYzYFH88kgejwLxiBPeqUq0ooKtTJxzu");
		response = httpRequest.header("Authorization","Bearer FA-zdYzYFH88kgejwLxiBPeqUq0ooKtTJxzu")
				.request(Method.GET,"/users/1673");		
		Thread.sleep(7000);
	}
	
	@Test
	public void checkResponseBody()
	{		
		String responseBody = response.getBody().asString();
		
		logger.info("Response body for Single Employe: ==> "+ responseBody);
		Assert.assertEquals(responseBody.contains("1673"), true);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("********** Check response Code **************");
		int StatusCode = response.getStatusCode();
		logger.info("Status code: ==>" + StatusCode);
		Assert.assertEquals(200,StatusCode);
	}
	
	@Test
	void checkResponseTime(){
		logger.info("******** Check Response Time *****************");
		long responseTime = response.getTime(); //get Response time
		logger.info("Response Time: ==>"+ responseTime);
		
		if(responseTime>2000){
			logger.warn("Response Time is greater than 2000");
			
			Assert.assertTrue(responseTime<10000);
		}
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("*********** Check Status line *******************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line:  ==> "+statusLine );
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("********** Check Content Type **********************");
		String contentType = response.header("Content-Type"); // Content type getting from header
		String serverType = response.header("Server"); //Server type getting from header
		String contentEncoding = response.header("Content-Encoding"); //getting the Content Encoding from the Header
		String contentLength = response.header("Content-Length");
		
		logger.info("Content Type: ==> "+ contentType);
		
		Assert.assertEquals(contentType, "application/json;charset=utf");
	}
	@Test
	void contentLength()
	{
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	
	@Test
	void checkCookies()
	{
		logger.info("*********** Checking Cookies **********************");
		
		String cookie = response.getCookie("PHPSESSID");
		System.out.println("cookie="+cookie);
	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********** Finshed the test case Test2_Get_Single_Emp **************");
	}
	
	
/*	
	private HashMap < String, String > headerMap() {
		HashMap < String, String > header = new HashMap < String, String > ();
		header.put("Content-Type", "application/json");
		header.put("X-Api-Key", apiKey);
		return header;
	}
	
	public JSONObject postRequest(String url, String path, Object payLoadJson, HashMap<String, String> headerMap,
            HashMap<String, String> paramMap, int expectedResponsecode) throws Exception {
SoftAssert softAssert = new SoftAssert();
RestAssured.baseURI = url + path;
RequestSpecification httpRequest = RestAssured.given();
if (!(headerMap == null)) {
Set<Entry<String, String>> entrySet = headerMap.entrySet();
for (Map.Entry<String, String> entry : entrySet) {
String paramKey = entry.getKey();
String value = entry.getValue();
httpRequest.header(paramKey, value);
}
}
if (!(paramMap == null)) {
Set<Entry<String, String>> entSet = paramMap.entrySet();
for (Map.Entry<String, String> entry : entSet) {
String paramKey = entry.getKey();
String value = entry.getValue();
httpRequest.param(paramKey, value);
}
}
byte[] objAsBytes = payLoadJson.toString().getBytes("UTF-8");
httpRequest.body(objAsBytes);
currentResponse = httpRequest.post();
int actualResponseCode = currentResponse.statusCode();
responseObject = new JSONObject(currentResponse.asString());
softAssert.assertEquals(actualResponseCode, expectedResponsecode);
softAssert.assertAll();
return responseObject;
}*/
	

}

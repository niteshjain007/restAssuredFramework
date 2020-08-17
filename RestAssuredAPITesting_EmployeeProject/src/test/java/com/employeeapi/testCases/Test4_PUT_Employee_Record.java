package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Test4_PUT_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSalary();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	public void createEmployee() throws InterruptedException{
		logger.info("*********** Test4_PUT_Employee_Record ************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest= RestAssured.given();
		
		//JSON object as a body we need to pass.. for that we need to create JSONObject
		JSONObject requestparams = new JSONObject();
		requestparams.put("name", empName);
		requestparams.put("salary", empSalary);
		requestparams.put("age", empAge);
		
		//Add a header stating that request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		//add the Json to the body of the request
		httpRequest.body(requestparams.toJSONString());
		
		response = httpRequest.request(Method.PUT,"/update/"+empID);
		
		Thread.sleep(5000);
	}
	
	@Test
	public void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		logger.info("Response body for Single Employe: ==> "+ responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("********** Check response Code **************");
		int StatusCode = response.getStatusCode();
		logger.info("Status code: ==>" + StatusCode);
		Assert.assertEquals(StatusCode,200);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********** Finshed the test case Test4_PUT_Employee_Record **************");
	}
}

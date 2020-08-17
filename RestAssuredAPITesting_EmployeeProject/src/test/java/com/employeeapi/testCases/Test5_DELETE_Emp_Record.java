package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Test5_DELETE_Emp_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	public void deleteEmployee() throws InterruptedException{
		logger.info("************* Started Executing Test5_DELETE_Emp_Record");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employees");
		
		//First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator= response.jsonPath();
		
		//Capture the Id
		String empID = jsonPathEvaluator.get("[5].id");
		logger.info("Employee id record delete for: "+empID);
		
		response = httpRequest.request(Method.DELETE,"/delete/"+empID);
		Thread.sleep(5000);
	}
	
	@Test
	public void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		logger.info("Response body for  Employe: ==> "+ responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
		
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
		logger.info("*********** Finshed the test case Test5_DELETE_Emp_Record **************");
	}

	

}

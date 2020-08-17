package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

// get user firstname = Johnny and check if lastname is ferry
public class Test6_Get_EmployeeByFirstNameAndVerifyLastName extends TestBase{
	
	@BeforeClass
	void getEmployeesByFirstName() throws InterruptedException 
	{
		logger.info("************ Started Test1_Get_All_Emp ************");
		
		RestAssured.baseURI = "https://gorest.co.in/public-api";
		httpRequest=RestAssured.given();
		
		response = httpRequest.header("Authorization","Bearer FA-zdYzYFH88kgejwLxiBPeqUq0ooKtTJxzu")
				.request(Method.GET,"/users?first_name=john");
		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody()
	{
		logger.info("********* Checking Response Body **********");
		String responseBody = response.getBody().asString();
		logger.info("Response Body: ==> "+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("********** Check response Code **************");
		int StatusCode = response.getStatusCode();
		logger.info("Status code: ==>" + StatusCode);
		Assert.assertEquals(StatusCode,200);
	}
	
	@Test
	void checkLastName()
	{
		logger.info("********* Checking last name **********");
		String responseBody = response.getBody().asString();
		logger.info("Response Body: ==> "+responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String fname= jp.get("result[0].first_name");
		
		if(fname.contains("John"))
		{
			String lname= jp.get("result[0].last_name");
			Assert.assertTrue(lname.equalsIgnoreCase("ferry"));
		}
		
		
	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********** Finshed the test case Test1_Get_All_Emp **************");
	}
	
	
}

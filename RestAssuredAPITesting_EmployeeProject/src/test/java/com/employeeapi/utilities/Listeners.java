package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


// We are not using this class because it is not generating Extent report in Specify path --- So we are Ignoring this Listeners Class -- We are created another classes for EXTENT REPORT Generation
public class Listeners extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	
	public void onStart(ITestContext testContext ){
		//Specify location to the report
		//htmlReporter = new ExtentHtmlReporter("C:/Users/kpraveek/workspace/RestAssuredAPITesting_EmployeeProject/Reports/myReport.html");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
		htmlReporter.config().setDocumentTitle("Rest Assure Automation Report"); //Title of the Report
		htmlReporter.config().setReportName("Functional Testing"); //name of the Report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Employee Database API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Praveen");
		
	}
	
	public void onTestSucess(ITestResult result){
//		test = extent.createTest(result.getClass().getName());
//		test.createNode(result.getName());
		
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.PASS,"Test case PASSED Is"+result.getName());
	}
	
	
	public void OnTestFailure(ITestResult result){
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.FAIL,"Test case FAILED Is"+result.getName()); // to add name in the extent report
		test.log(Status.FAIL,"Test case FAILED Is"+result.getThrowable()); //to add error/exception in the extent report
	}
	
	public void OnTestSkipped(ITestResult result){
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.SKIP,"Test case SKIPPED Is"+result.getName());
	}
	
	public void onFinish(ITestContext testContext){
		extent.flush();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

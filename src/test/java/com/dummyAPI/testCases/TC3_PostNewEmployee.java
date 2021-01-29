package com.dummyAPI.testCases;

import com.dummyAPI.base.TestBase;
import com.dummyAPI.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC3_PostNewEmployee extends TestBase {

    public String empName = RestUtils.empName();
    public String empSalary = RestUtils.empSalary();
    public String empAge = RestUtils.empAge();


    @BeforeClass
    public void createNewEmployee() throws InterruptedException{

        logger.info("Started Creating new employee");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        httpRequest = RestAssured.given();

        JSONObject requestBody = new JSONObject();

        requestBody.put("name", empName);
        requestBody.put("age", empAge);
        requestBody.put("salary", empSalary);

        httpRequest.contentType(ContentType.JSON);

        httpRequest.body(requestBody);

        response = httpRequest.when().post("/create");//endpoint

        Thread.sleep(2000);

    }

    @Test
    public void testResponseBody(){

        logger.info("Checking response body");

        String responseBody = response.getBody().asString();

        System.out.println("responseBody = " + responseBody);

        Assert.assertTrue(response.path("status").equals("success"));
        Assert.assertTrue(response.path("message").equals("Successfully! Record has been added."));


    }

    @Test
    public void testStatusCode(){

        logger.info("Checking status code");

        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;

        Assert.assertEquals(actualStatusCode, expectedStatusCode);


    }

    @Test
    public void testResponseTime(){

        logger.info("Checking response time");

        long responseTime = response.getTime();

        System.out.println("responseTime = " + responseTime);

        Assert.assertTrue( responseTime > 500);

    }

    @Test
    public void testStatusLine(){

        logger.info("Checking status line");

        String actualStatusLine = response.statusLine();

        Assert.assertEquals(actualStatusLine,"HTTP/1.1 200 OK");

    }

    @Test
    public void testContentType(){

        logger.info("Checking content type");

        String actualContentType = response.contentType();
        String expectedContentType = "application/json";

        Assert.assertEquals(actualContentType, expectedContentType);

    }

    @Test
    public void testServerType(){

        logger.info("Checking server type");

        String actualServerType = response.header("Server");
        String expectedServerType = "nginx/1.16.0";

        Assert.assertEquals(actualServerType, expectedServerType);

    }

    @Test(enabled = false)
    public void testContentLength(){

        logger.info("Checking content length");

        String actualContentLength = response.header("Content-Length");
        String expectedContentLength = "130";

        Assert.assertEquals(actualContentLength, expectedContentLength);

    }

    @AfterClass
    public void tearDown(){

        logger.info("Finished test case Get All Employees endpoint");

        RestAssured.reset();
    }


}

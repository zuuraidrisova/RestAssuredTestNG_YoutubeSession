package com.dummyAPI.testCases;

import com.dummyAPI.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC2_GetSingleEmployee extends TestBase {


    @BeforeClass
    public void getSingleEmployee() throws InterruptedException{

        logger.info("Started Get single employee endpoint");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        httpRequest = RestAssured.given();

        response = httpRequest.when().get("/employee/"+empID);//endpoint

        Thread.sleep(2000);

    }

    @Test
    public void testResponseBody(){

        logger.info("Checking response body");

        String responseBody = response.getBody().asString();

        System.out.println("responseBody = " + responseBody);

        JsonPath jsonPath = response.jsonPath();

//        Assert.assertEquals(jsonPath.get("[0].id"),(empID));
//        Assert.assertTrue(jsonPath.get("[0].employee_name").equals("Herrod Chandler"));
//        Assert.assertTrue(jsonPath.get("[0].employee_salary").equals("137500"));
//        Assert.assertTrue(jsonPath.get("[0].employee_age").equals("59"));
        Assert.assertTrue(jsonPath.get("status").equals("success"));
        Assert.assertTrue(jsonPath.get("message").equals("Successfully! Record has been fetched."));


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
        String expectedContentLength = "83";

        Assert.assertEquals(actualContentLength, expectedContentLength);

    }


    @AfterClass
    public void tearDown(){

        logger.info("Finished test case Get All Employees endpoint");

        RestAssured.reset();
    }

}

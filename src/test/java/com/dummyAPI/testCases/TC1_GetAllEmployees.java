package com.dummyAPI.testCases;

import com.dummyAPI.base.TestBase;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC1_GetAllEmployees extends TestBase {

    @BeforeClass
    public void getAllEmployees() throws InterruptedException{

        logger.info("Started Get all employees endpoint");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        httpRequest = RestAssured.given();

        response = httpRequest.when().get("/employees");//endpoint

        Thread.sleep(2000);

    }

    @Test
    public void testResponseBody(){

        logger.info("Checking response body");

        String responseBody = response.getBody().asString();

        System.out.println("responseBody = " + responseBody);

        Assert.assertTrue(responseBody != null);

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

        Assert.assertTrue( responseTime < 2000);

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
        String expectedContentType = "application/json;charset=utf-8";

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
        String expectedContentLength = "595";

        Assert.assertEquals(actualContentLength, expectedContentLength);

    }

    @Test
    public void testContentEncoding(){

        logger.info("Checking content encoding");

        String actualContentEncoding = response.header("Content-Encoding");
        String expectedContentEncoding = "gzip";

        Assert.assertEquals(actualContentEncoding, expectedContentEncoding);

    }


    @AfterClass
    public void tearDown(){

        logger.info("Finished test case Get All Employees endpoint");

        RestAssured.reset();
    }





}

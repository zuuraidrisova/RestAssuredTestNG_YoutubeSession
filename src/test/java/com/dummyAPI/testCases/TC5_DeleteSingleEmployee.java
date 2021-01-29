package com.dummyAPI.testCases;

import com.dummyAPI.base.TestBase;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC5_DeleteSingleEmployee extends TestBase {

    //Successfully! Record has been deleted

    @BeforeClass(enabled = false)
    public void deleteSingleEmployee() throws InterruptedException{

        logger.info("Started deleting single employee endpoint");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        httpRequest = RestAssured.given();

        response = httpRequest.when().delete("/delete/"+empID);//endpoint

        Thread.sleep(2000);

    }

    @Test(enabled = false)
    public void testResponseBody(){

        logger.info("Checking response body");

        String responseBody = response.getBody().asString();

        System.out.println("responseBody = " + responseBody);

        Assert.assertTrue(response.path("status").equals("success"));
        Assert.assertTrue(response.path("message").equals("Successfully! Record has been deleted"));


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


    @AfterClass
    public void tearDown(){

        logger.info("Finished test case Get All Employees endpoint");

        RestAssured.reset();
    }

}

package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utils;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MyStepdefs extends Utils {
    RequestSpecification request;
    Response res;

    @Given("endpoints with no auth and no headers")
    public void endpoints_with_no_auth_and_no_headers() {
        System.out.println("given code");
        throw new io.cucumber.java.PendingException();
    }

    @When("user calls GETAPI request")
    public void user_calls_GETAPI_request() throws IOException {
         res = given().when().get(getGlobalValue("baseUrl")).then()
                .extract().response();
        System.out.println("GET Request Successful");
        //System.out.println(res1);
    }


    @Then("response status code should be {int}")
    public void response_status_code_should_be(int statusCode) {
        System.out.println("Status Code is: " +res.getStatusCode());
        assertEquals("Status code " + res.getStatusCode() + " does not match, Test failed", statusCode,res.getStatusCode());
    }

    @Then("user should get {int} networks under response body")
    public void user_should_get_the_count_networks_under_response_body(int count) {
        System.out.println("count validation test");
        int actualCount = (getCount(res, "networks.size()"));
        System.out.println("Count is:: " +actualCount);
        assertEquals("network count " + res.getStatusCode() + " does not match, Test failed", count,actualCount);

    }


    @Then("^user should get the city \"([^\"]*)\" and \"([^\"]*)\"$")
   // @Then("user should get the city {string}")
    public void user_should_get_the_city_something_and_something(String validateCity, String validateCountry) {
        System.out.println("Frankfurt in Germany validation test");
        boolean flag =  getCity(res,"networks.size()", validateCity, validateCountry);
        assertTrue("city is not listed against correct country, Test failed", flag);
       // assertEquals(true, flag + " city is not listed against correct country, Test failed", true,flag);
    }

    //@Then("user should get {double} and {double} corresponding to city {string}")
    @Then("^user should get (.+) and (.+) corresponding to city \"([^\"]*)\"$")
    public void user_should_get_latitude_and_longitude_corresponding_to_city(double latitude, double longitude,  String validateCity) {
        System.out.println("Longitude and Latitude for " +validateCity);

        HashMap<String, String> frankfurtTest;

        frankfurtTest = getCityData(res, "networks.size()", validateCity);


        System.out.println("Latitude for Frankfurt data set is " +frankfurtTest.get("latitude"));
        System.out.println("Longitude for Frankfurt data set is " +frankfurtTest.get("longitude"));

        if(frankfurtTest.get("country").equalsIgnoreCase(validateCity)){
            System.out.println(frankfurtTest.get("city") +" is in Country " +frankfurtTest.get("country"));
        }


    }

}

package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        RestAssured.baseURI = "http://api.citybik.es/v2/networks";


        String res = given().log().all()
                .when().get("http://api.citybik.es/v2/networks")
                .then().log().all().statusCode(200).extract().response().asString();


        JsonPath js1 = new JsonPath(res);
    }
}




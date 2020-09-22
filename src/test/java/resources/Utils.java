package resources;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Utils {

// this function fetching the base url for the get request
    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/globalProp");
        prop.load(fis);
        return prop.getProperty(key);
    }

    //get the count of networks (which is 643) under response body
    public int getCount(Response response, String message) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        int count = js.getInt(message);
        return count;
    }

// check the city name = Frankfurt und return its index value response body and return its index
    public int getIndex(Response response, String message, String validateCity) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        int index;
        int count = getCount(response, message);
        for (int i = 0; i < count; i++) {
            String city = js.getString("networks[" + i + "].location.city");
            if (city.equalsIgnoreCase(validateCity)) {
                System.out.println("Index for city " + validateCity + " is:" + i);
                index = i;
                return index;
            }
        }
        return 0;
    }

    // getting the Longitude and Latitude for Frankfurt and asserting their corresponding values against the parameters passed under Feature file
    public boolean getCity(Response response, String message, String validateCity, String validateCountry) {

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        int index = getIndex(response,message,validateCity);


        int count = getCount(response, message);
            String city = js.getString("networks[" + index + "].location.city");
            String country = js.getString("networks[" + index + "].location.country");
            System.out.println("City listed in data set is: " + city);
            System.out.println("Country listed in data set is: " +country);

            if (city.equalsIgnoreCase(validateCity) && country.equalsIgnoreCase(validateCountry)) {
                System.out.println(validateCity + " is listed correctly against " +validateCountry + " in the data set.");
                return true;
            } else {
                System.out.println(validateCity + " is not listed against " +validateCountry + " in the data set.");
                return false;
            }

    }
// this function return type is Hash Map, where it stores city, country, Longitude and Latitude value against the parameter which passed in feature file
public HashMap<String, String> getCityData(Response response, String message, String validateCity){

        HashMap<String, String> frankfurtData = new HashMap<>();
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        int index = getIndex(response,message,validateCity);

        String varCity = js.getString("networks[" + index + "].location.city");
        String varLatitude = js.getString("networks[" + index + "].location.latitude");
        String varLongitude = js.getString("networks[" + index + "].location.longitude");
        String varCountry = js.getString("networks[" + index + "].location.country");

        frankfurtData.put("country",varCountry );
        frankfurtData.put("city",varCity );
        frankfurtData.put("latitude",varLatitude );
        frankfurtData.put("longitude",varLongitude );

/*
        System.out.println("Latitude for Frankfurt data set is " +frankfurtData.get("latitude"));
        System.out.println("Longitude for Frankfurt data set is " +frankfurtData.get("longitude"));

        if(frankfurtData.get("country").equalsIgnoreCase(validateCity)){
            System.out.println(frankfurtData.get("city") +" is in Country " +frankfurtData.get("country"));
        }

 */
        return frankfurtData;

    }

}
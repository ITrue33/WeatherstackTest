package test;

import io.cucumber.java.After;
import io.restassured.RestAssured;

public class Hooks {

    @After
    public static void after(){
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}

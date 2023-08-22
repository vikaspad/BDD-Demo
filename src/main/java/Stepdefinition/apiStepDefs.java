package Stepdefinition;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Utilities.LoggerLoad;
import Utilities.Utility;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.Base64;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertEquals;

public class apiStepDefs {
    //generate step definition from feature file
    String URI = "";
    LoggerLoad log = new LoggerLoad();
    Utility utility = new Utility();

    JSONObject updatedPayload;
    String updatedBody = "";
    String updatedStringBody = "";
    HttpRequest request;
    ResponseBody authResponse;
    RequestSpecification requestSpec;
    Response response;
    ResponseBody body;

    @Given("API resource {string}")
    public void apiURI(String url) {
        URI = url;
        log.info("API resource " + URI.toString());
    }

    //generate step definition for "When I make a request to the API"
    @When("I form a basic authentication request with username {string} and password {string}")
    public void basicAuth(String userName, String password) {
        //rest api auth with username and password
        log.info("I form a basic authentication request with username " + userName + " and password " + password);
    }

    //generate step definition for "Then I should get a response"
    @And("Form payload")
    public void formPayload(DataTable dataTable) {
        //get data from data table for header "Payload"
        log.info("Payload " + utility.returnData(dataTable, "Payload"));
        //get Payload from data table
        String payload = utility.returnData(dataTable, "Payload");
        //read json file Json1.json and return json object
        updatedPayload = utility.readJsonFile(payload);
        log.info("Json object " + utility.readJsonFile(payload).toString());
    }

    @And("Update the payload for {string} with {string}")
    public void updatePayload(String key, String value) {
        //get Payload from data table
        updatedStringBody = utility.updateJson(updatedPayload, key, value);
        updatedPayload = new JSONObject(updatedStringBody);
        log.info("Updated payload " + updatedPayload.toString());
    }


    @And("Authenticate API with userid {string} and password {string}")
    public void authenticateAPI(String userid, String password) {
        URI authUrl;
        try {
            authUrl = new URI("https://postman-echo.com/basic-auth");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        authResponse = RestAssured.given().auth().basic(userid, password).get(authUrl).body();
        //get json tag value from response
        log.logPass("true", authResponse.jsonPath().getString("authenticated"));
    }

    @And("Post the request {string}")
    public void postRequest(String URI, DataTable dataTable) {
        requestSpec = RestAssured.given().
                header(utility.returnData(dataTable, "Header"), utility.returnData(dataTable, "Value")).
                log().
                all();
        //post the payload updatedPayload
        response = requestSpec.body(updatedPayload.toString()).post(URI);
        //get response body
        body = response.getBody();
        //get response body as string
        String bodyAsString = body.asString();
        log.info("Response body " + bodyAsString);
    }

    //generate step definition for "And the response should have status code {int}"
    @Then("the response should have status code {int}")
    public void the_response_should_have_status_code(Integer statusCode) {
        log.logPass(String.valueOf(statusCode), String.valueOf(response.getStatusCode()));
    }

    @Then("Response should have tag {string} and value {string}")
    public void the_response_should_have_a_body(String expectedTag, String expectedValue) {
        log.logPass(expectedValue, body.jsonPath().getString(expectedTag));
    }

    //generate step definition for "I should receive a JSON response with the following:"
    @Then("I should receive a JSON response with the following")
    public void i_should_receive_a_json_response_with_the_following() {
        System.out.println("I should receive a JSON response with the following:");
    }


}
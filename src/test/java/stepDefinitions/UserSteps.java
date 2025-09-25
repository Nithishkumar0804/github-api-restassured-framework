package stepDefinitions;

import Utils.APIResources;
import Utils.ScenarioContext;
import base.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSteps {
    RequestSpecification reqSpec;
    Response response;

    @Given("I am an authenticated user")
    public void i_am_an_authenticated_user() throws IOException {
        reqSpec = APIResources.getRequestSpecification();
    }

    @When("I send a {string} request to {string}")
    public void i_send_a_request_to(String method, String resource) {
        if (method.equalsIgnoreCase("get")) {
            response = given().spec(reqSpec).when().get(Endpoints.valueOf(resource).getResources()).then().extract().response();
            ScenarioContext.setResponse(response);
        }
    }

    @Then("the response {string} should be {string}")
    public void the_response_should_be(String key, String expectedValue) {
        String actualValue = APIResources.getJsonValue(ScenarioContext.getResponse(), key);
        assertEquals(actualValue, expectedValue);

    }

    @Then("Get the repo details")
    public void getTheRepoDetails() {
        ScenarioContext.getResponse().then().body("owner.login", everyItem(equalTo("rnithishkumar080422")));
        assertEquals(200, ScenarioContext.getResponse().getStatusCode());
    }
}

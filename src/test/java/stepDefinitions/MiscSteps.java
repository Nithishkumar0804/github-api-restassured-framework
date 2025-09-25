package stepDefinitions;

import Utils.APIResources;
import Utils.ConfigReader;
import Utils.ScenarioContext;
import base.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class MiscSteps {
    RequestSpecification reqSpec;
    Response response;

    @When("Providing the Authentication for {string}")
    public void providingTheAuthenticationFor(String resources) throws IOException {
        reqSpec = APIResources.getRequestSpecification();
        response = given().spec(reqSpec).when().get(Endpoints.valueOf(resources).getResources())
                .then().extract().response();
        ScenarioContext.setResponse(response);
    }

    @Given("{string} the repo with {string}")
    public void theRepoWith(String resource, String method) throws IOException {
        reqSpec = APIResources.getRequestSpecification();
        if (method.equalsIgnoreCase("put")) {
            response = given().spec(reqSpec)
                    .pathParam("owner", ConfigReader.getPropValue("userId"))
                    .pathParam("repo", "Test_Repo_1")
                    .when().put(Endpoints.valueOf(resource).getResources()).then().extract().response();
        } else if (method.equalsIgnoreCase("delete")) {
            response = given().spec(reqSpec)
                    .pathParam("owner", ConfigReader.getPropValue("userId"))
                    .pathParam("repo", "Test_Repo_1")
                    .when().put(Endpoints.valueOf(resource).getResources()).then().extract().response();
        }
        ScenarioContext.setResponse(response);
    }
}

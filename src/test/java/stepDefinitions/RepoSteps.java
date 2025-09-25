package stepDefinitions;

import Utils.APIResources;
import Utils.ConfigReader;
import Utils.ScenarioContext;
import Utils.TestDataBuilder;
import base.Endpoints;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepoSteps {
    RequestSpecification reqSpec;
    Response response;

    @When("I send a {string} request to {string} with repository payload")
    public void i_send_a_request_to_with_repository_payload(String method, String resources) throws IOException {
        reqSpec = APIResources.getRequestSpecification();
        if (method.equalsIgnoreCase("post")) {
            response = given().spec(reqSpec).body(TestDataBuilder.createRepoPayload()).when()
                    .post(Endpoints.valueOf(resources).getResources())
                    .then().extract().response();
            ScenarioContext.setResponse(response);
        }

    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int code) {
        assertEquals(code, ScenarioContext.getResponse().getStatusCode());

    }

    @Then("the response should contain the created repository details")
    public void the_response_should_contain_the_created_repository_details() {
        String repoName = APIResources.getJsonValue(ScenarioContext.getResponse(), "name");
        assertEquals(repoName, TestDataBuilder.getRepoName());
    }

    @When("I send a {string} request to {string} with stored repository name")
    public void i_send_a_request_to_with_stored_repository_name(String method, String resources) throws IOException, InterruptedException {
        reqSpec = APIResources.getRequestSpecification();
        if (method.equalsIgnoreCase("patch")) {
            Thread.sleep(2000);
            response = given().spec(reqSpec).body(TestDataBuilder.getUpdateRepo())
                    .pathParam("owner", ConfigReader.getPropValue("userId"))
                    .pathParam("repo", TestDataBuilder.getRepoName())
                    .when().patch(Endpoints.valueOf(resources).getResources()).then().extract().response();
        } else if (method.equalsIgnoreCase("delete")) {
            Thread.sleep(3000);
            response = given().spec(reqSpec).pathParam("owner", ConfigReader.getPropValue("userId"))
                    .pathParam("repo", TestDataBuilder.getRepoName())
                    .when().delete(Endpoints.valueOf(resources).getResources()).then().extract().response();
        }
        ScenarioContext.setResponse(response);
    }

    @Then("the response should reflect the updated repository details")
    public void the_response_should_reflect_the_updated_repository_details() {
        String isPrivate = APIResources.getJsonValue(ScenarioContext.getResponse(), "private");
        String description = APIResources.getJsonValue(ScenarioContext.getResponse(), "description");
        assertEquals(isPrivate, TestDataBuilder.getUpdateRepo().getPrivate().toString());
        assertEquals(description, TestDataBuilder.getUpdateRepo().getDescription());
    }

    @Then("fetching the repository should return {int}")
    public void fetching_the_repository_should_return(int code) throws IOException {
        reqSpec = APIResources.getRequestSpecification();
        response = given().spec(reqSpec).pathParam("owner", ConfigReader.getPropValue("userId"))
                .pathParam("repo", TestDataBuilder.getRepoName())
                .when().get("repos/{owner}/{repo}").then().extract().response();
        ScenarioContext.setResponse(response);
        assertEquals(code, ScenarioContext.getResponse().getStatusCode());
    }
}

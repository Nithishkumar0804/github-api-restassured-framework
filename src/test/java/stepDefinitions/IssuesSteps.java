package stepDefinitions;

import Utils.APIResources;
import Utils.ConfigReader;
import Utils.ScenarioContext;
import Utils.TestDataBuilder;
import base.Endpoints;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssuesSteps {
    RequestSpecification reqSpec;
    Response response;
    static String issueNumber;

    private static final String DYNAMIC_REPO_NAME = Hooks.repoName;

    @When("I {string} the issue with {string} request from POJO")
    public void iTheIssueWithRequestFromPOJO(String resource, String method) throws IOException {
        reqSpec = APIResources.getRequestSpecification();
        String owner = ConfigReader.getPropValue("userId");

        if (method.equalsIgnoreCase("post")) {
            response = given().spec(reqSpec).body(TestDataBuilder.getCreateIssue())
                    .pathParam("owner", owner)
                    .pathParam("repo", DYNAMIC_REPO_NAME)
                    .when().post(Endpoints.valueOf(resource).getResources())
                    .then().extract().response();

            if (response.getStatusCode() != 201) {
                System.err.println("ERROR: Issue creation failed. Status: " + response.getStatusCode());
                System.err.println("RESPONSE BODY:\n" + response.asString());
                throw new RuntimeException("Issue creation failed with status code " + response.getStatusCode() + ". Check logs.");
            }
            issueNumber = APIResources.getJsonValue(response, "number");

        } else if (method.equalsIgnoreCase("get") || method.equalsIgnoreCase("patch")) {
            if (issueNumber == null) {
                throw new IllegalStateException("issueNumber is NULL. Initial POST failed.");
            }

            RequestSpecification commonSpec = given().spec(reqSpec)
                    .pathParam("owner", owner)
                    .pathParam("repo", DYNAMIC_REPO_NAME)
                    .pathParam("issue_number", issueNumber);

            if (method.equalsIgnoreCase("get")) {
                response = commonSpec.when().get(Endpoints.valueOf(resource).getResources()).then().extract().response();
            } else if (method.equalsIgnoreCase("patch")) {
                response = commonSpec.body(TestDataBuilder.getUpdateIssue())
                        .when().patch(Endpoints.valueOf(resource).getResources())
                        .then().extract().response();
            }
        }
        ScenarioContext.setResponse(response);
    }

    @And("the issue details should match the POJO")
    public void theIssueDetailsShouldMatchThePOJO() {
        response = ScenarioContext.getResponse();
        String actualIssueNumber = APIResources.getJsonValue(response, "number");
        assertEquals(actualIssueNumber, issueNumber);
    }

    @And("the issue should be updated successfully")
    public void theIssueShouldBeUpdatedSuccessfully() throws IOException {
        iTheIssueWithRequestFromPOJO("getIssue", "get");
        String actualBugState = APIResources.getJsonValue(ScenarioContext.getResponse(), "state");
        assertEquals(TestDataBuilder.getUpdateIssue().getState() + "d", actualBugState);
    }

    @When("I {string} the issue with {string}")
    public void iTheIssueWith(String resource, String method) throws IOException {
        reqSpec = APIResources.getRequestSpecification();

        if (issueNumber == null) {
            throw new IllegalStateException("issueNumber is NULL. Initial POST failed.");
        }

        RequestSpecification lockUnlockSpec = given().spec(reqSpec)
                .pathParam("owner", ConfigReader.getPropValue("userId"))
                .pathParam("repo", DYNAMIC_REPO_NAME)
                .pathParam("ISSUE_NUMBER", issueNumber);

        if (resource.equalsIgnoreCase("lockIssue") && method.equalsIgnoreCase("put")) {
            response = lockUnlockSpec.body("{\"lock_reason\":\"off-topic\"}")
                    .when().put(Endpoints.valueOf(resource).getResources()).then().extract().response();
        } else if (resource.equalsIgnoreCase("unlockIssue") && method.equalsIgnoreCase("delete")) {
            response = lockUnlockSpec
                    .when().delete(Endpoints.valueOf(resource).getResources()).then().extract().response();
        }
        ScenarioContext.setResponse(response);
    }
}
package stepDefinitions;

import Utils.ConfigReader;
import Utils.TestDataBuilder;
import io.restassured.RestAssured;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class Hooks {

    public static final String repoName = TestDataBuilder.createRepoForIssue().getName();
    private static String owner;
    private static String token;
    static {
        try {
            RestAssured.baseURI = "https://api.github.com";
            owner = ConfigReader.getPropValue("userId");
            token = ConfigReader.getPropValue("accessToken");

            createRepoForIssues();

            Runtime.getRuntime().addShutdownHook(new Thread(Hooks::deleteRepoForIssues));
        } catch (IOException e) {
            throw new RuntimeException("Configuration or initial setup failed.", e);
        }
    }

    private static void createRepoForIssues() {
        given()
                .header("Authorization", "token " + token)
                .header("Accept", "application/vnd.github+json")
                .body(TestDataBuilder.createRepoForIssue())
                .when()
                .post("/user/repos")
                .then()
                .statusCode(201);
    }

    private static void deleteRepoForIssues() {
        if (owner != null && token != null) {
            given()
                    .header("Authorization", "token " + token)
                    .header("Accept", "application/vnd.github+json")
                    .when()
                    .delete("https://api.github.com/repos/" + owner + "/" + repoName)
                    .then()
                    .statusCode(204);
        }
    }
}
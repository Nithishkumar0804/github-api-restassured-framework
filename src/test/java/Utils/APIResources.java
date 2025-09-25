package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class APIResources {
    private static RequestSpecification reqSpec;

    public static RequestSpecification getRequestSpecification() throws IOException {
        if (reqSpec == null) {
            String baseURI = ConfigReader.getPropValue("baseURI");
            String accessToken = ConfigReader.getPropValue("accessToken");
            PrintStream logfile = new PrintStream(new File(System.getProperty("user.dir") + "/target/logging.txt"));
            reqSpec = new RequestSpecBuilder()
                    .setBaseUri(baseURI)
                    .addHeader("Accept", "application/vnd.github+json")
                    .addHeader("X-GitHub-Api-Version", "2022-11-28")
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .addFilter(new RequestLoggingFilter(logfile))
                    .addFilter(new ResponseLoggingFilter(logfile))
                    .build();
        }
        return reqSpec;
    }

    public static String getJsonValue(Response response, String key) {
        JsonPath js = new JsonPath(response.asString());
        return js.get(key).toString();
    }
}

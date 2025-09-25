package Utils;

import io.restassured.response.Response;

public class ScenarioContext {
    private static Response response;

    public static void setResponse(Response res) {
        response = res;
    }

    public static Response getResponse() {
        return response;
    }

    public static void clear() {
        response = null;
    }
}

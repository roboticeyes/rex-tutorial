package com.roboticeyes.rex.tutorial.steps;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.roboticeyes.rex.tutorial.Globals;
import org.json.JSONObject;

public class T01_GetAccessToken {

    private static final String OAUTH_URL = Globals.RexBaseUri + "/oauth/token";

    public static String start(String clientId, String clientSecret) {

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(OAUTH_URL)
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("cache-control", "no-cache")
                    .basicAuth(clientId, clientSecret)
                    .body("grant_type=client_credentials").asJson();

            if (jsonResponse.getStatus() != 200) {
                System.out.println("ERROR: cannot get access token: " + jsonResponse.getBody().getObject().getString("message"));
                return "";
            }
            JSONObject body = jsonResponse.getBody().getObject();
            return body.getString("access_token");
        } catch (UnirestException e) {
            System.out.println("ERROR: cannot get access token: " + e.getMessage());
        }
        return "";
    }
}

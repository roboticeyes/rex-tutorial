package com.roboticeyes.rex.tutorial.steps;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.roboticeyes.rex.tutorial.Globals;
import org.json.JSONObject;

public class T02_GetUserId {

    private static final String USER_URL = Globals.RexBaseUri + "/api/v2/users/current";

    public static String start(String token) {

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(USER_URL)
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .header("authorization", "bearer " + token).asJson();

            if (jsonResponse.getStatus() != 200) {
                System.out.println("ERROR: cannot get user id: " + jsonResponse.getBody().getObject().getString("message"));
                return "";
            }
            JSONObject body = jsonResponse.getBody().getObject();
            return body.getString("userId");
        } catch (UnirestException e) {
            System.out.println("ERROR: cannot get user id: " + e.getMessage());
        }
        return "";
    }

}

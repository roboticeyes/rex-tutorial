/*
 * Copyright (c) 2018 Robotic Eyes GmbH software
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */

package com.roboticeyes.rex.tutorial.steps;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.roboticeyes.rex.tutorial.Globals;
import org.json.JSONObject;

import java.io.File;

public class T03_CreateModel {

    private static final String MODEL_URL = Globals.RexBaseUri + "/api/v2/models";

    public static String start(String token, String userId, String modelName, int modelArea, String rexFileName) {

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("name", modelName);
        jsonRequest.put("area", modelArea);
        jsonRequest.put("owner", userId);

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(MODEL_URL)
                    .header("content-type", "application/json")
                    .header("accept", "application/json")
                    .header("cache-control", "no-cache")
                    .header("authorization", "bearer " + token)
                    .body(jsonRequest)
                    .asJson();

            if (jsonResponse.getStatus() != 201) {
                System.out.println("ERROR: cannot create model: " + jsonResponse.getBody().getObject().getString("message"));
                return "";
            }

            // Upload content (3d geometry)
            JSONObject links = jsonResponse.getBody().getObject().getJSONObject("_links");
            String uploadUrl = links.getJSONObject("geometry.upload").getString("href");

            HttpResponse<JsonNode> contentUploadResponse = Unirest.post(uploadUrl)
                    .header("accept", "application/json")
                    .header("authorization", "bearer " + token)
                    .field("file", new File(rexFileName))
                    .asJson();

            if (contentUploadResponse.getStatus() != 200) {
                System.out.println("ERROR: cannot create model: " + jsonResponse.getBody().getObject().getString("message"));
                return "";
            }
            return links.getJSONObject("self").getString("href");
        } catch (UnirestException e) {
            System.out.println("ERROR: cannot create model: " + e.getMessage());
        }
        return "";
    }

}

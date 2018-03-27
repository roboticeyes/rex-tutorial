/*
 * Copyright (c) 2018 Robotic Eyes GmbH software
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
package com.roboticeyes.rex.tutorial;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.roboticeyes.rex.tutorial.steps.T00_ConvertGeometry;
import com.roboticeyes.rex.tutorial.steps.T01_GetAccessToken;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) {

        LogManager.getLogManager().reset();

        System.out.println("------------------------------------------");
        System.out.println("             Welcome to REX               ");
        System.out.println("------------------------------------------");
        System.out.println();

        if (args.length < 1) {
            System.out.println("Missing argument. Please provide a JSON input file");
            System.exit(1);
        }

        RexInput rexInput = null;
        try {
            rexInput = (new ObjectMapper()).readValue(new File(args[0]), RexInput.class);
        } catch (IOException e) {
            System.err.println("Cannot read json file: " + e.getMessage());
            System.exit(1);
        }

        T00_ConvertGeometry.start(rexInput.getInputFileName(), rexInput.getOutputFileName());
        String token = T01_GetAccessToken.start(rexInput.getClientId(), rexInput.getClientSecret());
        if (!token.isEmpty())
            System.out.println("Your access token is:\n" + token + "\n");

        try {
            Unirest.shutdown();
        } catch (IOException e) {
            System.err.println("Cannot shutdown REST client: " + e.getMessage());
        }
    }
}

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
import com.roboticeyes.rex.tutorial.samples.T00_ConvertGeometry;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) {

        LogManager.getLogManager().reset();

        System.out.println("------------------------------------------");
        System.out.println("Welcome to REX tutorials");
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
    }
}

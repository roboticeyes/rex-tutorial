/*
 * Copyright (c) 2018 Robotic Eyes GmbH software
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
package com.roboticeyes.rex.tutorial.steps;

import com.roboticeyes.rex.core.io.ErrorCode;
import com.roboticeyes.rex.core.io.dae.ColladaReader;
import com.roboticeyes.rex.core.io.obj.ObjReader;
import com.roboticeyes.rex.core.io.rex.RexWriter;
import com.roboticeyes.rex.core.model.RexModel;
import com.roboticeyes.rex.core.processing.TriangleOptimization;
import com.roboticeyes.rex.tutorial.ConsoleProgress;

public class T00_ConvertGeometry {

    private static String FILE_EXT_OBJ = ".obj";
    private static String FILE_EXT_DAE = ".dae";

    public static void start(String inputFile, String outputFile) {
        System.out.println("------------------------------------------");
        System.out.println("T00 - Convert geometry file");
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("\t input:  " + inputFile);
        System.out.println("\t output: " + outputFile);
        System.out.println();

        RexModel rexModel = new RexModel();
        ConsoleProgress consoleProgress = new ConsoleProgress();

        if (inputFile.toLowerCase().endsWith(FILE_EXT_OBJ)) {
            try {
                rexModel = (new ObjReader()).read(inputFile, consoleProgress);
            } catch (Exception e) {
                consoleProgress.message(Integer.parseInt(e.getMessage()));
            }
        } else if (inputFile.toLowerCase().endsWith(FILE_EXT_DAE)) {
            try {
                rexModel = (new ColladaReader()).read(inputFile, consoleProgress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Unsupported file format");
        }

        // optimize models (enabled by default)
        rexModel = TriangleOptimization.mergeSubMeshes(rexModel, false, consoleProgress);

        try {
            if (rexModel.getNumberOfTriangles() != 0) {
                RexWriter.writeRex(rexModel, outputFile, false);
                consoleProgress.message(ErrorCode.SUCCESS_REX);

                consoleProgress.done(outputFile, consoleProgress.getStatus());
                System.out.println(consoleProgress.toString());
            } else {
                consoleProgress.message(ErrorCode.ERROR_TRIANGLES);
                consoleProgress.done("", consoleProgress.getStatus());
                System.out.println(consoleProgress.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

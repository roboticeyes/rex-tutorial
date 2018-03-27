/*
 * Copyright (c) 2018 Robotic Eyes GmbH software
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
package com.roboticeyes.rex.tutorial;

import com.roboticeyes.rex.core.model.BoundingBox;
import com.roboticeyes.rex.core.utils.ProgressIndicator;

import java.util.ArrayList;
import java.util.List;

public class ConsoleProgress implements ProgressIndicator {

    private List<String> missingFiles = new ArrayList<>();
    private List<Integer> codes = new ArrayList<>();
    private int code = 0;

    @Override
    public void updateProgress(int value) {
    }

    @Override
    public void startProgress(int speed) {

    }

    @Override
    public void stopProgress(boolean delay) {

    }

    @Override
    public void message(int status) {
        codes.add(status);
    }

    @Override
    public void missingFiles(List<String> fileNames) {
        missingFiles = fileNames;
    }

    @Override
    public void statistics(int nrOfVertices, int nrOfNormals, int nrOfTextures, int nrOfFaces, BoundingBox boundingBox) {
        System.out.println("vertices      : " + nrOfVertices);
        System.out.println("normals       : " + nrOfNormals);
        System.out.println("textures      : " + nrOfTextures);
        System.out.println("faces         : " + nrOfFaces);
        System.out.println("bbox [m]      : " + boundingBox.getCenter().x + " | " + boundingBox.getCenter().y + " | " + boundingBox.getCenter().z);
        System.out.println("extension [m] : " + boundingBox.getExtension().x + " | " + boundingBox.getExtension().y + " | " + boundingBox.getExtension().z);
        System.out.println("missing       : " + missingFiles);
        System.out.println("status        : " + code);
    }

    @Override
    public String toString() {
        return "\nDONE!\n";
    }


    @Override
    public void done(String fileName, int status) {
        this.code = status;
    }

    @Override
    public int getStatus() {
        if (codes.isEmpty())
            return 0;
        return codes.stream().max(Integer::compare).get();
    }
}

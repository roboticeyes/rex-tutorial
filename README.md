# Getting started with the REX platform

This repository contains some sample code snippets which should help REX developers to work with the REX platform.

## Pre-requisite

* Since all samples are written in Java you have to make sure that Java 8 is installed on your system.
* Please make sure that you have a REX account, if not register for one https://rex.robotic-eyes.com
* Make sure that you have a REX API token (please see https://support.robotic-eyes.com/docs/apitoken/)
* It is a good starting point to read through the development tutorial on our support page https://support.robotic-eyes.com/docs/api-introduction/

### Edit sample.json

Before executing the tutorial, please make sure to edit the `sample.json` file which will be picked as a command line
argument upon program execution.

The `sample.json` needs to have a valid `clientId` and `clientSecret`. Both can be obtained from the REX portal (Settings -> API token).
If you want to change the demo model, to something useful, just edit the `inputFileName` accordingly.

## Start application with gradle

In order to start the tutorial(s) you just have to open up your shell and type `./gradlew -q run`

## Build fat jar

You can build a fat jar by using shadowJar and execute the application using `java`:

```
./gradlew shadowJar
java -jar build/libs/*-all.jar sample.json
```

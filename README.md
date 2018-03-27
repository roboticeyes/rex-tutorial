# Getting started with the REX platform

This repository contains some sample code snippets which should help REX developers to work with the REX platform.

## Pre-requisite

* Since all samples are written in Java you have to make sure that Java 8 is installed on your system.
* Please make sure that you have a REX account, if not register for one https://rex.robotic-eyes.com
* Make sure that you have a REX API token (please see https://support.robotic-eyes.com/docs/apitoken/)
* It is a good starting point to read through the development tutorial on our support page https://support.robotic-eyes.com/docs/api-introduction/

## Start application with gradle

In order to start the tutorial(s) you just have to open up your shell and type `./gradlew -q run`

## Build fat jar

You can build a fat jar by using shadowJar

```
./gradlew shadowJar
java -jar build/libs/*-all.jar sample.json
```

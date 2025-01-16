package org.utils;

import io.restassured.path.json.JsonPath;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonFileManager {
    private static  String jsonFilePath;
    private static FileReader reader;

    public JsonFileManager(String jsonFilePath) {
        JsonFileManager.jsonFilePath = jsonFilePath;
        try {
            reader = new FileReader(jsonFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getTestData(String jsonPath) {
        Object testData ;
        try {
            reader = new FileReader(jsonFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        testData = JsonPath.from(reader).getString(jsonPath);
        return String.valueOf(testData);
    }

}

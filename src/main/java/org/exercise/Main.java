package org.exercise;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.exercise.Solution.readJSONFromFile;
import static org.exercise.Solution.resourceDoesNotContainsSingleAsterisk;

public class Main {
    public static void main(String[] args) {
        Object data = null;

        try {
            data = readJSONFromFile("./src/main/resources/data.json");
        } catch (FileNotFoundException e) {
            System.err.printf("File not found: %s%n", e.getMessage());
        } catch (IOException e) {
            System.err.printf("IOException: %s%n", e.getMessage());
        } catch (ParseException e) {
            System.err.printf("Parse Exception: %s%n", e.getMessage());
        }

        JSONArray jsonArray = null;

        if (data == null) return;

        try {
            jsonArray = (data instanceof JSONObject
                ? (JSONArray) new JSONParser().parse(((JSONObject) data).toJSONString())
                : (JSONArray) ((JSONArray) data).clone());
        } catch (ParseException e) {
            System.err.printf("Parse Exception: %s%n", e.getMessage());
            return;
        }

        if (jsonArray == null) return;
        int i = 0;
        for (Object o : jsonArray) {
            try {
                System.out.printf("%s) %s%n", i, resourceDoesNotContainsSingleAsterisk((JSONObject) o)
                    ? "Not single asterisk."
                    : "Single asterisk"
                );
            } catch (RuntimeException e) {
                System.err.printf("%s) %s%n", i, e.getMessage());
            }
            i++;
        }
    }
}

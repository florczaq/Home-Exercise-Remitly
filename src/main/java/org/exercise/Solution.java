package org.exercise;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.NoSuchObjectException;

/**
 * Home exercise for the first stage of recruitment for Java Software Developer Internship in Remitly Poland.
 *
 * @author Mikołaj Florczak
 */
public class Solution {
    /**
     * @param rolePolicy AWS::IAM::Role Policy json object
     * @return true if at least one of resources contains single asterisk
     * @throws NoSuchObjectException
     */
    public static boolean resourceDoesNotContainsSingleAsterisk(JSONObject rolePolicy) throws RuntimeException {
        try {

            RolePolicyManager.validateJSONFormat(rolePolicy);

            JSONObject policyDocument = RolePolicyManager.getPolicyDocument(rolePolicy);
            String policyName = RolePolicyManager.getPolicyName(rolePolicy);
            JSONArray statement = RolePolicyManager.getStatement(policyDocument);

            boolean containsSingleAsterisk = false;

            for (Object object : statement)
                if (RolePolicyManager.getResource((JSONObject) object).equals("*"))
                    containsSingleAsterisk = true;

            return !containsSingleAsterisk;
        } catch (NoSuchObjectException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @param path path to a json file
     * @return Object which either JSONObject or JSONArray, depending on file content
     * @throws ParseException
     * @throws IOException
     */
    public static Object readJSONFromFile(String path) throws ParseException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileReader reader = new FileReader(path);

        int data = -1;
        while ((data = reader.read()) != -1)
            stringBuilder.append((char) data);
        reader.close();

        return new JSONParser().parse(stringBuilder.toString());
    }


}
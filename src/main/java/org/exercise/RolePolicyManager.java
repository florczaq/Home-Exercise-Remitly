package org.exercise;


import org.exercise.exception.JSONFormatException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.rmi.NoSuchObjectException;

/**
 *
 */
public class RolePolicyManager {

    /**
     * @param rolePolcyObject in AWS::IAM::Role Policy format
     * @return "PolicyDocument" json object value
     * @throws NoSuchObjectException
     */
    public static JSONObject getPolicyDocument(JSONObject rolePolcyObject) throws NoSuchObjectException {
        if (!rolePolcyObject.containsKey("PolicyDocument"))
            throw new NoSuchObjectException("PolicyDocument not found");
        return (JSONObject) rolePolcyObject.get("PolicyDocument");
    }

    /**
     * @param rolePolicyObject in AWS::IAM::Role Policy format
     * @return "PolicyName" field value
     * @throws NoSuchObjectException
     */
    public static String getPolicyName(JSONObject rolePolicyObject) throws NoSuchObjectException {
        if (!rolePolicyObject.containsKey("PolicyName"))
            throw new NoSuchObjectException("PolicyName not found");
        return (String) rolePolicyObject.get("PolicyName");
    }

    /**
     * @param policyDocument AWS::IAM::Role Policy "PolicyDocument" field json object
     * @return List of JSON Objects from "Statement" field
     * @throws NoSuchObjectException
     */
    public static JSONArray getStatement(JSONObject policyDocument) throws NoSuchObjectException {
        if (!policyDocument.containsKey("Statement"))
            throw new NoSuchObjectException("Statement not found");
        return (JSONArray) policyDocument.get("Statement");
    }

    /**
     * @param statementElement json object from AWS::IAM::Role Policy list of statements
     * @return String value of Resource field
     * @throws NoSuchObjectException
     */
    public static String getResource(JSONObject statementElement) throws NoSuchObjectException {
        if (!statementElement.containsKey("Resource"))
            throw new NoSuchObjectException("Resource not found");
        return statementElement.get("Resource").toString();
    }

    /**
     * Methods throws RuntimeException if JSON format is different from AWS::IAM::Role Policy
     *
     * @param rolePolicy
     */
    public static void validateJSONFormat(JSONObject rolePolicy) throws JSONFormatException {
        try {
            JSONObject policyDocument = getPolicyDocument(rolePolicy);
            String policyName = getPolicyName(rolePolicy);
            JSONArray statement = getStatement(policyDocument);
            String resource = getResource((JSONObject) statement.get(0));
        } catch (NoSuchObjectException e) {
            throw new JSONFormatException(e.getMessage());
        }
    }

}

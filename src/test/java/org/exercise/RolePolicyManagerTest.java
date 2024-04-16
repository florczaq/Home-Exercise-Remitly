package org.exercise;


import org.exercise.exception.JSONFormatException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.rmi.NoSuchObjectException;

public class RolePolicyManagerTest {

    @Test
    public void JSONContainsSingleAsterisk() throws ParseException, JSONFormatException, NoSuchObjectException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyName": "root",
                "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Sid": "IamListAccess",
                            "Effect": "Allow",
                            "Action": [
                                "iam:ListRoles",
                                "iam:ListUsers"
                            ],
                            "Resource": "*"
                        }
                    ]
                }
            }
            """);

        Assertions.assertFalse(Solution.resourceDoesNotContainsSingleAsterisk(jsonObject));
    }

    @Test
    public void JSONDoesNotContainsSingleAsterisk() throws ParseException, JSONFormatException, NoSuchObjectException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyName": "root",
                "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Sid": "IamListAccess",
                            "Effect": "Allow",
                            "Action": [
                                "iam:ListRoles",
                                "iam:ListUsers"
                            ],
                            "Resource": "aws_iam_role_policy_attachment"
                        }
                    ]
                }
            }
            """);

        Assertions.assertTrue(Solution.resourceDoesNotContainsSingleAsterisk(jsonObject));
    }

    @Test
    public void throwsExceptionNoResource() throws ParseException, NoSuchObjectException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyName": "root",
                "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Sid": "IamListAccess",
                            "Effect": "Allow",
                            "Action": [
                                "iam:ListRoles",
                                "iam:ListUsers"
                            ],
                        }
                    ]
                }
            }
            """);
        JSONObject policyDocument = RolePolicyManager.getPolicyDocument(jsonObject);
        JSONArray statement = RolePolicyManager.getStatement(policyDocument);
        Exception exception = Assertions.assertThrows(NoSuchObjectException.class, () -> RolePolicyManager.getResource((JSONObject) statement.get(0)));
        String expectedMessage = "Resource not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void throwsExceptionNoStatement() throws ParseException, NoSuchObjectException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyName": "root",
                "PolicyDocument": {
                    "Version": "2012-10-17"
                }
            }
            """);

        JSONObject policyDocument = RolePolicyManager.getPolicyDocument(jsonObject);
        Exception exception = Assertions.assertThrows(NoSuchObjectException.class, () -> RolePolicyManager.getStatement(policyDocument));

        String expectedMessage = "Statement not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void throwsExceptionNoPolicyDocument() throws ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyName": "root"
            }
            """);

        Exception exception = Assertions.assertThrows(NoSuchObjectException.class, () -> RolePolicyManager.getPolicyDocument(jsonObject));

        String expectedMessage = "PolicyDocument not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void validJSONFormat() throws ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyName": "root",
                "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Sid": "IamListAccess",
                            "Effect": "Allow",
                            "Action": [
                                "iam:ListRoles",
                                "iam:ListUsers"
                            ],
                            "Resource": "*"
                        }
                    ]
                }
            }
            """);
        Assertions.assertDoesNotThrow(() -> RolePolicyManager.validateJSONFormat(jsonObject));
    }

    @Test
    public void invalidJSONFormat() throws ParseException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Sid": "IamListAccess",
                            "Effect": "Allow",
                            "Action": [
                                "iam:ListRoles",
                                "iam:ListUsers"
                            ],
                            "Resource": "*"
                        }
                    ]
                }
            }
            """);
        Assertions.assertThrows(JSONFormatException.class, () -> RolePolicyManager.validateJSONFormat(jsonObject));
    }

    @Test
    public void throwEmptyResource() throws ParseException, NoSuchObjectException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse("""
            {
                "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                        {
                            "Sid": "IamListAccess",
                            "Effect": "Allow",
                            "Action": [
                                "iam:ListRoles",
                                "iam:ListUsers"
                            ],
                            "Resource": ""
                        }
                    ]
                }
            }
            """);
        JSONObject policyDocument = RolePolicyManager.getPolicyDocument(jsonObject);
        JSONArray statement = RolePolicyManager.getStatement(policyDocument);
        Exception exception = Assertions.assertThrows(
            NoSuchObjectException.class,
            () -> RolePolicyManager.getResource((JSONObject) statement.get(0))
        );

        String expectedMessage = "Resource is empty";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}

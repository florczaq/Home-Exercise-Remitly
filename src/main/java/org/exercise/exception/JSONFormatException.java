package org.exercise.exception;

public class JSONFormatException extends Exception {
    public JSONFormatException(String errorMsg) {
        super(String.format("Wrong JSON format: %s",errorMsg));
    }
}

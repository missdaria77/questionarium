package org.example.exceptions;

public class SQLGetByTopicException extends RuntimeException {

    public SQLGetByTopicException(String message) {
        super(message);
    }
}

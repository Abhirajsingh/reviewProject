package com.zemoso.project.exception;

/**
 * Wrap all exception related to map-object conversion and vice-versa
 */
public class MapperException extends Exception {
    /**
     * wrap over the actual exception
     *
     * @param message
     * @param cause
     */
    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }
}

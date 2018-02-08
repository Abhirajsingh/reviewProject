package com.zemoso.project.exception;

/**
 * Wrap all exception related to File handling ;
 */
public class FileException extends Exception {
    /**
     * wrap over the actual exception
     *
     * @param message
     * @param cause
     */
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }


}

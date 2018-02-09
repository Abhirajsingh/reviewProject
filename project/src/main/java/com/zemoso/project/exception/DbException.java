package com.zemoso.project.exception;

/**
 * Wrap all exception related to database;
 */
public class DbException extends Exception {
    /**
     * wrap over the actual exception
     *
     * @param message
     * @param cause
     */
    public DbException(String message, Throwable cause) {
        super(message, cause);
    }
}

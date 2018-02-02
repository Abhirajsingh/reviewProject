package com.zemoso.project.exception;

/**
 * Wrap all exception related to database;
 */
public class DbException extends Exception {
    public DbException(String message , Throwable cause){
        super(message , cause);
    }
}

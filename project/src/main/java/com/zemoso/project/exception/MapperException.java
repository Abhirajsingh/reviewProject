package com.zemoso.project.exception;

/**
 * Wrap all exception related to map-object conversion and vice-versa
 */
public class MapperException extends Exception {
    public MapperException(String message , Throwable cause){
        super(message ,cause);
    }
}

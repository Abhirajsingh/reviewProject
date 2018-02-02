package com.zemoso.project.exception;

/**
 *Wrap all exception related to File handling ;
 */
public class FileException extends Exception{

    public FileException(String message , Throwable cause){
        super(message , cause);
    }


}

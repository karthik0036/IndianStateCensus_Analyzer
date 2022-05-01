package com.bridge.service;

public class CensusAnalyzerException extends Exception {


    public enum ExceptionType {
        CENSUS_FILE_INCORRECT,CENSUS_INCORRECT_FILE_FORMAT;
    }

    public ExceptionType type;

    public  CensusAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}

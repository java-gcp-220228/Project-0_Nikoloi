package com.revature.exception;

public class ExpectedExceptions extends Exception {


    public ExpectedExceptions(){};

    public ExpectedExceptions(String message){
        super(message);
    }

    public ExpectedExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpectedExceptions(Throwable cause) {
        super(cause);
    }

    public ExpectedExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

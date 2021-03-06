package com.kaufda.exceptions

/**
 * Created by biniam on 10/11/2014.
 */
/**
 A custom exception class (Super Class for Other Exception Classes)
 is defined which extends RuntimeException
 */
class ServiceException extends RuntimeException {

    private final int code

    public ServiceException(String message, int code) {

        super(message)
        this.code = code
    }

    public int getCode() {
        return code
    }
}

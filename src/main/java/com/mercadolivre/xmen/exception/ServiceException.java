package com.mercadolivre.xmen.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {

	private final int code;
    private final String messageError;
    private final int httpStatusCode;

    public ServiceException(int code, String messageError, HttpStatus httpStatus) {
        this.code = code;
        this.messageError = messageError;
        this.httpStatusCode = httpStatus.value();
    }

    public String getMessageError() {
        return messageError;
    }

    public int getCode() {
        return code;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

}

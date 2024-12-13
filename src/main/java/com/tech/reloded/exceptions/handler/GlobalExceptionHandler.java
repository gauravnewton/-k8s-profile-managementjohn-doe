/*
 * Copyright gauravnewton
 * 13-12-2024 at 8:52 IST
 */

package com.tech.reloded.exceptions.handler;

import com.tech.reloded.exceptions.types.*;
import com.tech.reloded.model.GlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static com.tech.reloded.constants.ErrorCodes.*;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle invalid eks config id exception response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(InvalidEksConfigIdException.class)
    public ResponseEntity<Object> handleInvalidEksConfigIdException(InvalidEksConfigIdException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(List.of(INVALID_EKS_CONFIG_ID));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle null pointer exception response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(List.of(INTERNAL_SERVER_ERROR));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle write permission exception response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(WritePermissionException.class)
    public ResponseEntity<Object> handleWritePermissionException(WritePermissionException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(List.of(WRITE_PERMISSION_ERROR));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle file not found exception response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(List.of(FILE_NOT_FOUND));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle invalid file exception response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Object> handleInvalidFileException(InvalidFileException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(List.of(INVALID_FILE));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle file upload exception response entity.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<Object> handleFileUploadException(FileUploadException exception, WebRequest webRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrorCodes(List.of(FILE_UPLOAD_ERROR));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

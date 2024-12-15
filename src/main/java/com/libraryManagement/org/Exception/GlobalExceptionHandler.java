package com.libraryManagement.org.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for the library management application.
 * This class handles exceptions thrown by the controllers and provides
 * appropriate responses to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BookNotFoundException {
    public static String message;

    public GlobalExceptionHandler() {
        super(message);
    }

    /**
     * Handle missing or invalid request body exceptions.
     *
     * @param ex      The exception that was thrown
     * @param request The web request information
     * @return A response entity containing the error details and a BAD REQUEST status
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Request body is missing or invalid. Please provide valid data.");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle Book Not Found exceptions.
     *
     * @param ex      The exception that was thrown
     * @param request The web request information
     * @return A response entity containing the error details and a NOT FOUND status
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle all other exceptions that are not specifically handled.
     *
     * @param ex      The exception that was thrown
     * @param request The web request information
     * @return A response entity containing the error details and an INTERNAL SERVER ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error: " + ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

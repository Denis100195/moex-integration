package org.businessday.api.calendar.handler;

import org.businessday.api.calendar.exception.ApiError;
import org.businessday.api.calendar.exception.YearNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(YearNotSupportedException.class)
    protected ResponseEntity<Object> handleYearNotSupported(YearNotSupportedException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_IMPLEMENTED, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_IMPLEMENTED);
    }

}

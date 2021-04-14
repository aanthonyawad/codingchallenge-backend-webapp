package de.iplytics.codingchallenge_backend_webapp.controller;

import de.iplytics.codingchallenge_backend_webapp.dto.response.ResponseMessage;
import de.iplytics.codingchallenge_backend_webapp.exception.InvalidArgumentException;
import de.iplytics.codingchallenge_backend_webapp.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseMessage itemNotFoundException(ItemNotFoundException ex, WebRequest request) {
        return new ResponseMessage(400, ex.toString());
    }

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public ResponseMessage invalidArgumentException(InvalidArgumentException ex, WebRequest request) {
        return new ResponseMessage(400, ex.toString());
    }
}

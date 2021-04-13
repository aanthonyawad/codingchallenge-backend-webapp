package de.iplytics.codingchallenge_backend_webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException{
    private final String s;
    public InvalidArgumentException(String s) {
        this.s = s;
    }
    @Override
    public String toString() {
        return s;
    }
}

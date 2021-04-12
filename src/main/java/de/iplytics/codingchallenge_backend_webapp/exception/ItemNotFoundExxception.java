package de.iplytics.codingchallenge_backend_webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ItemNotFoundExxception extends RuntimeException{
    private final String s;
    public ItemNotFoundExxception(String s) {
        this.s = s;
    }
    @Override
    public String toString() {
        return s;
    }
}

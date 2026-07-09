package it.its.demo.demo_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(CustomException e){
        return e.getMessage();
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bookNotFound(BookNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(BooksNotAvailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bookNotFound(BooksNotAvailable e){
        return e.getMessage();
    }

}

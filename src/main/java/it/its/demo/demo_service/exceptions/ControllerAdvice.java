package it.its.demo.demo_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BookDeletedException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleBookDeletedException(BookDeletedException e){
        return e.getMessage();
    }

    @ExceptionHandler(BookDeletedException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleBookDeletedException(BookDeletedException e){
        return e.getMessage();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(CustomException e){
        return e.getMessage();
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> bookNotFound(BookNotFoundException e){

        Map<String, Object> result = new HashMap<>();

        result.put("timestamp", LocalDateTime.now());
        result.put("status", HttpStatus.NOT_FOUND);
        result.put("error", e.getMessage());

        return result;
    }

    @ExceptionHandler(BooksNotAvailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bookNotFound(BooksNotAvailable e){
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String badInput(MethodArgumentNotValidException ex){
        return ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }



}

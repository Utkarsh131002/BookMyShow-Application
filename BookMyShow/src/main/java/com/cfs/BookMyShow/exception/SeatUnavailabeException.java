package com.cfs.BookMyShow.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SeatUnavailabeException extends RuntimeException {

    public  SeatUnavailabeException(String message){
        super(message);
    }
}

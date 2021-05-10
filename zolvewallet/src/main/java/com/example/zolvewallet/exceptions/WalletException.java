package com.example.zolvewallet.exceptions;

//import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WalletException extends RuntimeException{
    public WalletException(String message) { 
//    	return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    	super(message);
//    	super(HttpStatus.BAD_REQUEST.entity(message).type(MediaType.APPLICATION_JSON).build()));
    	}
}
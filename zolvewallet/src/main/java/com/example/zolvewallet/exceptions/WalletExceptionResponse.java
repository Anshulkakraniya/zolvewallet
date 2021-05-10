package com.example.zolvewallet.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletExceptionResponse {
	private String id;
    public WalletExceptionResponse(String message) throws Exception {
		// TODO Auto-generated constructor stub
    	throw new Exception(message);
	}


}
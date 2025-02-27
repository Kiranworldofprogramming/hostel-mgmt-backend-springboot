package com.nt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_GATEWAY)
public class RoomNotFoundException extends RuntimeException {
	
	public RoomNotFoundException() {
		super();
	}
	
	public RoomNotFoundException(String msg) {
		super(msg);
	}
}

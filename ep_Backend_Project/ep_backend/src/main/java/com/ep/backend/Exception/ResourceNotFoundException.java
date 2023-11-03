package com.ep.backend.Exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends  RuntimeException {

	  public ResourceNotFoundException(){
	        super("Resource not found !!");
	    }

	    public ResourceNotFoundException(String message){
	        super(message);
	    }
	    
	    public ResourceNotFoundException(String string, String string2, String studentName) {
			// TODO Auto-generated constructor stub
		}
}

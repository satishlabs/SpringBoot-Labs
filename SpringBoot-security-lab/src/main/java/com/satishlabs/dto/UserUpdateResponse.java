package com.satishlabs.dto;

public class UserUpdateResponse {
    private String message;

    public UserUpdateResponse(String message) {
        this.message = message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

   
}

package com.challenge.application.exception;

public enum ErrorCode {

	INVALID_USER("101", "User Name is missing/invalid"), DATABASE_NOT_FOUND("102", "User does not exist in the database"), NO_CONNECTION("103", "There is no feasible path"), DATABASE_ERROR("104",
			"Sorry something went wrong could not fetch data from database");

	private final String code;
	private final String description;

	ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}

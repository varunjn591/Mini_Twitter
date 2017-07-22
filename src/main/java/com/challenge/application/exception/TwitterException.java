package com.challenge.application.exception;

public class TwitterException extends Exception {

	private static final long serialVersionUID = -3595095751041366609L;

	private ErrorCode errorCode;

	public TwitterException(ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}

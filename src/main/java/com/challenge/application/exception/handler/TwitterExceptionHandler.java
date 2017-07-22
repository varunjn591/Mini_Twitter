package com.challenge.application.exception.handler;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.application.exception.ErrorCode;
import com.challenge.application.exception.TwitterException;
import com.challenge.application.response.BaseResponse;

@ControllerAdvice
public class TwitterExceptionHandler {

	private static final Logger logger = Logger.getLogger(TwitterExceptionHandler.class);

	@ExceptionHandler(TwitterException.class)
	@ResponseBody
	public BaseResponse handleException(TwitterException twitterException) {
		logger.info("Twitter Exception caused by " + twitterException.getMessage());
		BaseResponse response = new BaseResponse();
		ErrorCode errorCode = twitterException.getErrorCode();
		response.setErrorCode(errorCode);
		return response;
	}

}

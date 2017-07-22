package com.challenge.application.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.challenge.application.constants.TwitterConstants;
import com.challenge.application.exception.ErrorCode;
import com.challenge.application.exception.TwitterException;
import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;
import com.challenge.application.service.TwitterService;

@Component
public class TwitterControllerImpl implements TwitterController {

	@Autowired
	TwitterService twitterService;

	private static final Logger logger = Logger.getLogger(TwitterControllerImpl.class);

	@Override
	public String ping() {
		return TwitterConstants.PING_MESSAGE;
	}

	public TwitterResponse getNewsFeed(@AuthenticationPrincipal final UserDetails userDetails) throws TwitterException {
		if (userDetails.getUsername() == null || "".equals(userDetails.getUsername())) {
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		logger.info("Getting News Feed");
		return twitterService.getNewsFeed(userDetails.getUsername());
	}

	public TwitterResponse getMyNetwork(@AuthenticationPrincipal final UserDetails userDetails) throws TwitterException {
		if (userDetails.getUsername() == null || "".equals(userDetails.getUsername())) {
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		logger.info("Getting " + userDetails.getUsername() + " Network");
		return twitterService.getMyNetwork(userDetails.getUsername());
	}

	public BaseResponse follow(@AuthenticationPrincipal final UserDetails userDetails, @PathVariable(TwitterConstants.FOLLOWEE) String followee) throws TwitterException {
		if (userDetails.getUsername() == null || "".equals(followee) || followee == null) {
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		logger.info("Making a request to Follow : " + followee);
		return twitterService.follow(userDetails.getUsername(), followee);
	}

	public BaseResponse unfollow(@AuthenticationPrincipal final UserDetails userDetails, @PathVariable(TwitterConstants.FOLLOWEE) String followee) throws TwitterException {
		if (userDetails.getUsername() == null || "".equals(followee) || followee == null) {
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		logger.info("Making a request to Unfollow : " + followee);
		return twitterService.unfollow(userDetails.getUsername(), followee);
	}

	public BaseResponse getShortestPath(@AuthenticationPrincipal final UserDetails userDetails, @PathVariable(TwitterConstants.FRIEND) String friend) throws TwitterException {
		if (userDetails.getUsername() == null || "".equals(friend) || friend == null) {
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		logger.info("Getting a shortest path between : " + userDetails.getUsername() + " and " + friend);
		return twitterService.getShortestPath(userDetails.getUsername(), friend);
	}

}

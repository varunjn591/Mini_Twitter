package com.challenge.application.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.application.constants.TwitterConstants;
import com.challenge.application.exception.TwitterException;
import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;


@RestController
@RequestMapping("/r/v1")
public interface TwitterController {
	
	@GetMapping(TwitterConstants.PING_URL)
	public String ping(); 
	
	@GetMapping(value = TwitterConstants.URI_GET_NEWS_FEED, produces = MediaType.APPLICATION_JSON_VALUE)
	public TwitterResponse getNewsFeed(@AuthenticationPrincipal final UserDetails userDetails) throws TwitterException;
	
	@GetMapping(value = TwitterConstants.URI_GET_MY_NETWORK, produces = MediaType.APPLICATION_JSON_VALUE)
	public TwitterResponse getMyNetwork(@AuthenticationPrincipal final UserDetails userDetails) throws TwitterException;

	@PutMapping(value = TwitterConstants.URI_FOLLOW)
	public BaseResponse follow(@AuthenticationPrincipal final UserDetails userDetails, @PathVariable(TwitterConstants.FOLLOWEE) String followee) throws TwitterException;
	
	@PutMapping(value = TwitterConstants.URI_UNFOLLOW)
	public BaseResponse unfollow(@AuthenticationPrincipal final UserDetails userDetails,@PathVariable(TwitterConstants.FOLLOWEE) String followee) throws TwitterException;
	
	@GetMapping(value = TwitterConstants.URI_GET_SHORTEST_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse getShortestPath(@AuthenticationPrincipal final UserDetails userDetails,@PathVariable(TwitterConstants.FRIEND) String friend) throws TwitterException;

}

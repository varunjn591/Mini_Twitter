package com.challenge.application.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.application.constants.TwitterConstants;
import com.challenge.application.model.Message;
import com.challenge.application.model.People;
import com.challenge.application.service.TwitterServiceImpl;

@RestController
@RequestMapping("/r/v1")
public class TwitterController {

	@Autowired
	TwitterServiceImpl twitterService;

	@GetMapping(value = TwitterConstants.URI_GET_NEWS_FEED, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<People> message(@AuthenticationPrincipal final UserDetails userDetails) {
		return (List<People>) twitterService.getMessages(userDetails.getUsername());
	}

	@GetMapping(value = TwitterConstants.URI_GET_NETWORK, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Message> getNewsFeed(@AuthenticationPrincipal final UserDetails userDetails) {

		return twitterService.getMessages();
	}

	@PutMapping(value = TwitterConstants.URI_FOLLOW)
	public List<String> follow(@AuthenticationPrincipal final UserDetails userDetails, @PathVariable(TwitterConstants.FOLLOWEE_ID) String followeeid){

		return Arrays.asList("123", "123", "123");
	}
	
	@PutMapping(value = TwitterConstants.URI_UNFOLLOW)
	public List<String> unfollow(@AuthenticationPrincipal final UserDetails userDetails,@PathVariable(TwitterConstants.FOLLOWEE_ID) String followeeid){

		return Arrays.asList("123", "123", "123");
	}
	
	@GetMapping(value = TwitterConstants.URI_GET_SHORTEST_PATH)
	public List<String> getShortestPath(@AuthenticationPrincipal final UserDetails userDetails,@PathVariable(TwitterConstants.USER_ID) String id){

		return Arrays.asList("123", "123", "123");
	}
}

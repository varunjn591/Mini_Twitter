package com.challenge.application.service;

import com.challenge.application.exception.TwitterException;
import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;

public interface TwitterService {
	
	public TwitterResponse getNewsFeed(String user) throws TwitterException;
	
	public TwitterResponse getMyNetwork(String user) throws TwitterException;
	
	public BaseResponse follow(String user, String followee) throws TwitterException;
	
	public BaseResponse unfollow(String user, String followee) throws TwitterException;
	
	public BaseResponse getShortestPath(String user, String friend) throws TwitterException;

}

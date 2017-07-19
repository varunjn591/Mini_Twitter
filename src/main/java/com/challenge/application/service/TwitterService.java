package com.challenge.application.service;

import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;

public interface TwitterService {
	
	public TwitterResponse getNewsFeed(String user);
	
	public TwitterResponse getMyNetwork(String user);
	
	public BaseResponse follow(String user, String followee);
	
	public BaseResponse unfollow(String user, String followee);
	
	public int getShortestPath(String user, String friend);

}

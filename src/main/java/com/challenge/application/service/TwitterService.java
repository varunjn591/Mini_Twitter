package com.challenge.application.service;

import org.springframework.stereotype.Component;

import com.challenge.application.exception.TwitterException;
import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;

@Component
public interface TwitterService {

	/**
	 * Service to get Newsfeed and User posts
	 * 
	 *  @param String UserName
	 */
	public TwitterResponse getNewsFeed(String user) throws TwitterException;

	/**
	 * Service to get User followers and followees
	 * 
	 *  @param String UserName
	 */
	public TwitterResponse getMyNetwork(String user) throws TwitterException;

	/**
	 * Service to update user follow a user
	 * 
	 *  @param String UserName
	 *  @param String followee
	 */	
	public BaseResponse follow(String user, String followee) throws TwitterException;

	/**
	 * Service to update user unfollow a user
	 * 
	 *  @param String UserName
	 *  @param String followee
	 */	
	public BaseResponse unfollow(String user, String followee) throws TwitterException;

	
	/**
	 * Service to get Shortest path between user and friend
	 * 
	 *  @param String UserName
	 *  @param String friend
	 */	
	public BaseResponse getShortestPath(String user, String friend) throws TwitterException;

}

package com.challenge.application.dao;

import java.util.List;

import com.challenge.application.exception.TwitterException;
import com.challenge.application.model.Message;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;

public interface TwitterDAO {
    
    	public int getUserId(String user) throws TwitterException;
	
	public List<Message> getNewsFeed(String user) throws TwitterException;
	
	public List<Message> getMyPosts(String user) throws TwitterException;
	
	public List<People> getMyFollowers(String user) throws TwitterException;
	
	public List<People> getMyFollowees(String user) throws TwitterException;
	
	public void follow(String user, String followee) throws TwitterException;
	
	public void unfollow(String user, String followee) throws TwitterException;
	
	public List<Network> getAllNetwork() throws TwitterException;

}

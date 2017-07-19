package com.challenge.application.dao;

import java.util.List;

import com.challenge.application.model.Message;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;

public interface TwitterDAO {
	
	public List<Message> getNewsFeed(String user);
	
	public List<Message> getMyPosts(String user);
	
	public List<People> getMyFollowers(String user);
	
	public List<People> getMyFollowees(String user);
	
	public void follow(String user, String followee);
	
	public void unfollow(String user, String followee);
	
	public List<Network> getAllNetwork();

}

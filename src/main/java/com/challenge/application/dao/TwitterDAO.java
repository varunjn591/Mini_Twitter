package com.challenge.application.dao;

import java.util.Collection;

import com.challenge.application.model.People;

public interface TwitterDAO {
	
	public Collection<People> getNewsFeed(String id);
	
	public void getNetworkFeed();
	
	public void follow();
	
	public void unfollow();

}

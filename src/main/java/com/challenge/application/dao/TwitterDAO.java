package com.challenge.application.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.challenge.application.model.Message;
import com.challenge.application.model.People;

public interface TwitterDAO {
	
	public Collection<People> getNewsFeed(String id);
	
	public List<Message> getMessages();
	
	public void getMyNetwork();
	
	public void follow();
	
	public void unfollow();

}

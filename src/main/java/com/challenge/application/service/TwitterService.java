package com.challenge.application.service;

import java.util.Collection;
import java.util.List;

import com.challenge.application.model.Message;
import com.challenge.application.model.People;

public interface TwitterService {
	
	public Collection<People> getMessages(String userid);
	
	public List<Message> getMessages();
	

}

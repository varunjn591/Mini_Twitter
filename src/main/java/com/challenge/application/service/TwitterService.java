package com.challenge.application.service;

import java.util.Collection;

import com.challenge.application.model.People;

public interface TwitterService {
	
	public Collection<People> getMessages(String userid);
	

}

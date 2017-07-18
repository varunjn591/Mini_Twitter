package com.challenge.application.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.application.dao.TwitterDAO;
import com.challenge.application.model.Message;
import com.challenge.application.model.People;

@Service
public class TwitterServiceImpl implements TwitterService{
	
	@Autowired
	TwitterDAO twitterDAO;

	@Override
	public Collection<People> getMessages(String userId) {
		return twitterDAO.getNewsFeed(userId);
	}

	@Override
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return twitterDAO.getMessages();
	}

}

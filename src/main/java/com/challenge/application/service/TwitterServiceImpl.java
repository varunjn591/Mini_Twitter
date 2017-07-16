package com.challenge.application.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.application.dao.TwitterDAO;
import com.challenge.application.model.People;

@Service
public class TwitterServiceImpl implements TwitterService{
	
	@Autowired
	TwitterDAO twitterDAO;

	@Override
	public Collection<People> getMessages(String userId) {
		return twitterDAO.getNewsFeed(userId);
	}

}

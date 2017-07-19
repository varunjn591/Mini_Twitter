package com.challenge.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.application.dao.TwitterDAO;
import com.challenge.application.model.People;
import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;

@Service
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    TwitterDAO twitterDAO;

    @Override
    public TwitterResponse getNewsFeed(String user) {
	TwitterResponse response = new TwitterResponse();
	response.setNewsfeed(twitterDAO.getNewsFeed(user));
	response.setMyPosts(twitterDAO.getMyPosts(user));
	return response;
    }

    @Override
    public TwitterResponse getMyNetwork(String user) {
	TwitterResponse response = new TwitterResponse();
	List<People> followers = twitterDAO.getMyFollowers(user);
	response.setFollowers(followers);
	List<People> followees = twitterDAO.getMyFollowees(user);
	response.setFollowees(followees);
	return response;
    }

    @Override
    public BaseResponse follow(String user, String followee) {
	// TODO Auto-generated method stub
	return null;

    }

    @Override
    public BaseResponse unfollow(String user, String followee) {
	// TODO Auto-generated method stub
	return null;

    }

    @Override
    public int getShortestPath(String user, String friend) {
	// TODO Auto-generated method stub
	return 0;
    }

}

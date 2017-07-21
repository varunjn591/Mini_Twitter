package com.challenge.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.application.dao.TwitterDAO;
import com.challenge.application.exception.TwitterException;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;
import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;

@Service
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    TwitterDAO twitterDAO;
    
    @Autowired
    RouteFinder routeFinder;

    @Override
    public TwitterResponse getNewsFeed(String user) throws TwitterException{
	TwitterResponse response = new TwitterResponse();
	response.setNewsfeed(twitterDAO.getNewsFeed(user));
	response.setMyPosts(twitterDAO.getMyPosts(user));
	return response;
    }

    @Override
    public TwitterResponse getMyNetwork(String user) throws TwitterException{
	TwitterResponse response = new TwitterResponse();
	List<People> followers = twitterDAO.getMyFollowers(user);
	response.setFollowers(followers);
	List<People> followees = twitterDAO.getMyFollowees(user);
	response.setFollowees(followees);
	return response;
    }

    @Override
    public BaseResponse follow(String user, String followee) throws TwitterException{
	twitterDAO.follow(user, followee);
	return new BaseResponse();
    }

    @Override
    public BaseResponse unfollow(String user, String followee) throws TwitterException{
	twitterDAO.unfollow(user, followee);
	return new BaseResponse();
    }

    @Override
    public BaseResponse getShortestPath(String user, String friend) throws TwitterException{
	List<Network> followers = twitterDAO.getAllNetwork();
	int userId = twitterDAO.getUserId(user);
	int friendId = twitterDAO.getUserId(friend);
	long distance = routeFinder.shortestPath(followers, userId, friendId);
	BaseResponse response = new BaseResponse();
	response.setMessage("The distance between " + user + " and " + friend + " is : " + distance);
	return response;
    }

}

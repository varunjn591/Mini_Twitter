package com.challenge.application.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.application.dao.TwitterDAO;
import com.challenge.application.exception.ErrorCode;
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

	private static final Logger logger = Logger.getLogger(TwitterServiceImpl.class);

	@Override
	public TwitterResponse getNewsFeed(String user) throws TwitterException {
		if (user == null || "".equals(user)) {
			logger.info("No user was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		TwitterResponse response = new TwitterResponse();
		response.setNewsfeed(twitterDAO.getNewsFeed(user));
		response.setMyPosts(twitterDAO.getMyPosts(user));
		return response;
	}

	@Override
	public TwitterResponse getMyNetwork(String user) throws TwitterException {
		if (user == null || "".equals(user)) {
			logger.info("No user was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		TwitterResponse response = new TwitterResponse();
		List<People> followers = twitterDAO.getMyFollowers(user);
		if (followers.isEmpty()) {
			logger.info("No followers were found");
		}
		response.setFollowers(followers);
		List<People> followees = twitterDAO.getMyFollowees(user);
		if (followers.isEmpty()) {
			logger.info("No followees were found");
		}
		response.setFollowees(followees);
		return response;
	}

	@Override
	public BaseResponse follow(String user, String followee) throws TwitterException {
		if (user == null || "".equals(user)) {
			logger.info("No user was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}

		if (followee == null || "".equals(followee)) {
			logger.info("No followee was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}

		twitterDAO.follow(user, followee);
		return new BaseResponse();
	}

	@Override
	public BaseResponse unfollow(String user, String followee) throws TwitterException {
		if (user == null || "".equals(user)) {
			logger.info("No user was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}

		if (followee == null || "".equals(followee)) {
			logger.info("No followee was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		twitterDAO.unfollow(user, followee);
		return new BaseResponse();
	}

	@Override
	public BaseResponse getShortestPath(String user, String friend) throws TwitterException {

		if (user == null || "".equals(user)) {
			logger.info("No user was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}

		if (friend == null || "".equals(friend)) {
			logger.info("No friend was provided");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}

		List<Network> followers = twitterDAO.getAllNetwork();
		int userId = twitterDAO.getUserId(user);
		int friendId = twitterDAO.getUserId(friend);

		if (friendId == 0 || userId == 0) {
			logger.info("invalid userId/friendId");
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		long distance = routeFinder.shortestPath(followers, userId, friendId);
		BaseResponse response = new BaseResponse();
		response.setMessage("The distance between " + user + " and " + friend + " is : " + distance);
		return response;
	}

}

package com.challenge.application.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.challenge.application.dao.TwitterDAO;

public class TwitterServiceImplTest {
	
	@InjectMocks
	TwitterService twitterService;

	@Mock
	TwitterDAO twitterDAO;
	
	@Mock
	RouteFinder routeFinder;
	
	@Test
	public void getUserId_NoUser_Exception() {
		

	}

	@Test
	public void getUserId_NoAvailableUser_Exception() {
		// int getUserId(String user) throws TwitterException;
	}

	@Test
	public void getUserId_CorrectUser_Result() {
		// int getUserId(String user) throws TwitterException;
	}

	@Test
	public void getNewsFeed_NoUser_Exception() {
		// public List<Message> getNewsFeed(String user) throws
		// TwitterException;
	}

	@Test
	public void getNewsFeed_NoAvailableUser_Exception() {
		// public List<Message> getNewsFeed(String user) throws
		// TwitterException;
	}

	@Test
	public void getNewsFeed_CorrectUser_Result() {
		// public List<Message> getNewsFeed(String user) throws
		// TwitterException;
	}

	@Test
	public void getMyPosts_NoUser_Exception() {
		// public List<Message> getMyPosts(String user) throws TwitterException;
	}

	@Test
	public void getMyPosts_CorrectUser_Result() {
		// public List<Message> getMyPosts(String user) throws TwitterException;
	}

	@Test
	public void getMyPosts_NoAvailableUser_Exception() {
		// public List<Message> getMyPosts(String user) throws TwitterException;
	}

	@Test
	public void getMyFollowers_NoUser_Exception() {
		// public List<People> getMyFollowers(String user) throws
		// TwitterException;
	}

	@Test
	public void getMyFollowers_CorrectUser_Result() {
		// public List<People> getMyFollowers(String user) throws
		// TwitterException;
	}

	@Test
	public void getMyFollowers_NoAvailableUser_Exception() {
		// public List<People> getMyFollowers(String user) throws
		// TwitterException;
	}

	@Test
	public void getMyFollowees_NoUser_Exception() {
		// public List<People> getMyFollowees(String user) throws
		// TwitterException;
	}

	@Test
	public void getMyFollowees_CorrectUser_Result() {
		// public List<People> getMyFollowees(String user) throws
		// TwitterException;
	}

	@Test
	public void getMyFollowees_NoAvailableUser_Result() {
		// public List<People> getMyFollowees(String user) throws
		// TwitterException;
	}

	@Test
	public void follow_NoUser_Exception() {
		// public void follow(String user, String followee) throws
		// TwitterException;
	}

	@Test
	public void unfollow_CorrectUser_Result() {
		// public void unfollow(String user, String followee) throws
		// TwitterException;
	}

	@Test
	public void getAllNetwork_NoAvailableUser_Exception() {
		// public List<Network> getAllNetwork() throws TwitterException;
	}

}

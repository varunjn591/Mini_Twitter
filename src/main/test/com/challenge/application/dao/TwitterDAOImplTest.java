package com.challenge.application.dao;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TwitterDAOImplTest {

	@InjectMocks
	TwitterDAOImpl twitterDAO;

	@Mock
	NamedParameterJdbcTemplate jdbcTemplate;

	@Test
	public void getUserId_NoUser_Exception() {
	//	when(jdbcTemplate.query(anyString(), anyObject(),anyObject())).thenReturn(anyObject());

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

package com.challenge.application.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.application.exception.TwitterException;
import com.challenge.application.model.Message;
import com.challenge.application.model.People;
import com.challenge.application.response.BaseResponse;
import com.challenge.application.response.TwitterResponse;
import com.challenge.application.service.TwitterService;

@RunWith(SpringRunner.class)
public class TwitterControllerImplTest {

	@InjectMocks
	TwitterControllerImpl twitterController;

	@Mock
	TwitterService twitterService;

	@Mock
	UserDetails userDetails;

	@Test
	public void getNewsFeed_Normal_Result() throws TwitterException {

		List<Message> myPosts = new ArrayList<>();
		Message message1 = new Message();
		message1.setId(1);
		message1.setMessages("Test Message My Post 1");

		List<Message> newsfeed = new ArrayList<>();
		Message message3 = new Message();
		message3.setId(1);
		message3.setMessages("Test Message My Post 1");

		myPosts.add(message1);
		newsfeed.add(message3);
		TwitterResponse twitterResponseMock = new TwitterResponse();
		twitterResponseMock.setMyPosts(myPosts);
		twitterResponseMock.setNewsfeed(newsfeed);
		when(twitterService.getNewsFeed(anyString())).thenReturn(twitterResponseMock);

		when(userDetails.getUsername()).thenReturn("Test");

		TwitterResponse twitterResponse = twitterController.getNewsFeed(userDetails);
		assertEquals("Test Message My Post 1", twitterResponse.getMyPosts().get(0).getMessages());
	}

	@Test(expected = TwitterException.class)
	public void getNewsFeed_NullUser_Exception() throws TwitterException {
		when(twitterService.getNewsFeed(anyString())).thenReturn(new TwitterResponse());
		when(userDetails.getUsername()).thenReturn(null);
		twitterController.getNewsFeed(userDetails);
	}

	@Test
	public void getMyNetwork_Normal_Result() throws TwitterException {

		List<People> followers = new ArrayList<>();
		People follower = new People();
		follower.setId(1);
		follower.setName("ABC");
		follower.setHandle("abc");
		
		List<People> followees = new ArrayList<>();
		People followee = new People();
		followee.setId(1);
		followee.setName("DEF");
		followee.setHandle("def");

		followers.add(follower);
		followees.add(followee);
		TwitterResponse twitterResponseMock = new TwitterResponse();
		twitterResponseMock.setFollowers(followers);
		twitterResponseMock.setFollowees(followees);
		when(twitterService.getMyNetwork(anyString())).thenReturn(twitterResponseMock);

		when(userDetails.getUsername()).thenReturn("Test");

		TwitterResponse twitterResponse = twitterController.getMyNetwork(userDetails);
		assertEquals("ABC", twitterResponse.getFollowers().get(0).getName());
		assertEquals("DEF", twitterResponse.getFollowees().get(0).getName());
	}

	@Test(expected = TwitterException.class)
	public void getMyNetwork_NullUser_Exception() throws TwitterException {
		when(twitterService.getNewsFeed(anyString())).thenReturn(new TwitterResponse());
		when(userDetails.getUsername()).thenReturn(null);
		twitterController.getMyNetwork(userDetails);
	}

	@Test(expected = TwitterException.class)
	public void follow_NullUser_Exception() throws TwitterException {
		when(twitterService.getNewsFeed(anyString())).thenReturn(new TwitterResponse());
		when(userDetails.getUsername()).thenReturn(null);
		twitterController.follow(userDetails, "");
	}

	@Test(expected = TwitterException.class)
	public void unfollow_NullUser_Exception() throws TwitterException {
		when(twitterService.getNewsFeed(anyString())).thenReturn(new TwitterResponse());
		when(userDetails.getUsername()).thenReturn(null);
		twitterController.unfollow(userDetails, "");
	}
	
	@Test
	public void getShortestPath_Normal_Result() throws TwitterException {
		BaseResponse responseMock = new BaseResponse();
		responseMock.setMessage("Shortest Path is 10");
		when(twitterService.getShortestPath(anyString(),anyString())).thenReturn(responseMock);
		when(userDetails.getUsername()).thenReturn("Test");

		BaseResponse twitterResponse = twitterController.getShortestPath(userDetails,"Destination Friend");
		assertEquals("Shortest Path is 10", twitterResponse.getMessage());
	}

	@Test(expected = TwitterException.class)
	public void getShortestPath_NullUser_Exception() throws TwitterException {
		when(twitterService.getNewsFeed(anyString())).thenReturn(new TwitterResponse());
		when(userDetails.getUsername()).thenReturn(null);
		twitterController.getShortestPath(userDetails, "");
	}

}

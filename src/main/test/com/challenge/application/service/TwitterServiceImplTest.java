package com.challenge.application.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.application.dao.TwitterDAO;
import com.challenge.application.exception.ErrorCode;
import com.challenge.application.exception.TwitterException;
import com.challenge.application.model.Message;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;
import com.challenge.application.response.TwitterResponse;

@RunWith(SpringRunner.class)
public class TwitterServiceImplTest {

	@InjectMocks
	TwitterServiceImpl twitterService;

	@Mock
	TwitterDAO twitterDAO;

	@Mock
	RouteFinder routeFinder;

	@Test(expected = TwitterException.class)
	public void getNewsFeed_NoUser_Exception() throws Exception {
		twitterService.getNewsFeed("");

	}

	@Test(expected = TwitterException.class)
	public void getNewsFeed_NoAvailableUser_Exception() throws Exception {
		when(twitterDAO.getNewsFeed(anyString())).thenThrow(new TwitterException(ErrorCode.INVALID_USER));
		when(twitterDAO.getNewsFeed(anyString())).thenThrow(new TwitterException(ErrorCode.INVALID_USER));
		twitterService.getNewsFeed("AHJ");
	}

	@Test
	public void getNewsFeed_CorrectUser_Result() throws Exception {
		when(twitterDAO.getUserId(anyString())).thenReturn(1);
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
		when(twitterDAO.getMyPosts(anyString())).thenReturn(myPosts);
		when(twitterDAO.getNewsFeed(anyString())).thenReturn(newsfeed);
		assertEquals("Test Message My Post 1", twitterService.getNewsFeed("Test").getMyPosts().get(0).getMessages());
		assertEquals("Test Message My Post 1", twitterService.getNewsFeed("Test").getNewsfeed().get(0).getMessages());
	}

	@Test(expected = TwitterException.class)
	public void getMyNetwork_NoUser_Exception() throws Exception {
		twitterService.getNewsFeed("");
	}

	@Test
	public void getMyNetwork_CorrectUser_Result() throws Exception {
		when(twitterDAO.getUserId(anyString())).thenReturn(1);
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
		when(twitterDAO.getMyFollowees(anyString())).thenReturn(followees);
		when(twitterDAO.getMyFollowers(anyString())).thenReturn(followers);
		assertEquals("ABC", twitterService.getMyNetwork("Test").getFollowers().get(0).getName());
		assertEquals("DEF", twitterService.getMyNetwork("Test").getFollowees().get(0).getName());
	}

	@Test(expected = TwitterException.class)
	public void getMyNetwork_NoAvailableUser_Exception() throws Exception {
		when(twitterDAO.getNewsFeed(anyString())).thenThrow(new TwitterException(ErrorCode.INVALID_USER));
		when(twitterDAO.getNewsFeed(anyString())).thenThrow(new TwitterException(ErrorCode.INVALID_USER));
		twitterService.getNewsFeed("AHJ");
	}

	@Test(expected = TwitterException.class)
	public void getAllNetwork_NoAvailableUser_Exception() throws Exception {
		List<Network> networks = new ArrayList<>();

		Network n1 = new Network();
		n1.setFollowee(1);
		n1.setFollower(2);
		networks.add(n1);
		when(twitterDAO.getAllNetwork()).thenReturn(networks);
		when(routeFinder.shortestPath(networks, 1, 2)).thenReturn(1);
		assertEquals("The distance between Test and Result is : 1", twitterService.getShortestPath("Test", "Result").getMessage());
	}

}

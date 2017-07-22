package com.challenge.application.dao;

import java.util.List;

import com.challenge.application.exception.TwitterException;
import com.challenge.application.model.Message;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;

public interface TwitterDAO {

	/**
	 * Returns a int value which is the userID
	 * 
	 * @param String
	 *            user
	 */
	public int getUserId(String user) throws TwitterException;

	/**
	 * Returns user's followees posts for a user
	 * 
	 * @param String
	 *            user
	 */
	public List<Message> getNewsFeed(String user) throws TwitterException;

	/**
	 * Returns user posts
	 * 
	 * @param String
	 *            user
	 */
	public List<Message> getMyPosts(String user) throws TwitterException;

	/**
	 * Returns user followers
	 * 
	 * @param String
	 *            user
	 */
	public List<People> getMyFollowers(String user) throws TwitterException;

	/**
	 * Returns users followees
	 * 
	 * @param String
	 *            user
	 */
	public List<People> getMyFollowees(String user) throws TwitterException;

	/**
	 * updates user's followers
	 * 
	 * @param String
	 *            user
	 * @param String
	 *            followee
	 */
	public void follow(String user, String followee) throws TwitterException;

	/**
	 * removes user's followers
	 * 
	 * @param String
	 *            user
	 */
	public void unfollow(String user, String followee) throws TwitterException;

	/**
	 * Returns all the network
	 * 
	 * @param String
	 *            user
	 */
	public List<Network> getAllNetwork() throws TwitterException;

}

package com.challenge.application.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.challenge.application.constants.SQLQueries;
import com.challenge.application.exception.ErrorCode;
import com.challenge.application.exception.TwitterException;
import com.challenge.application.exception.handler.TwitterExceptionHandler;
import com.challenge.application.model.Message;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;

@Repository
public class TwitterDAOImpl implements TwitterDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final Logger logger = Logger.getLogger(TwitterExceptionHandler.class);

	@Override
	public int getUserId(String user) throws TwitterException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("username", user);
		int result = -1;
		try {
			logger.info("Making a database call for Query: " + SQLQueries.USER_ID);
			result = jdbcTemplate.queryForObject(SQLQueries.USER_ID, namedParameters, Integer.class);
		} catch (DataAccessException e) {
			logger.info("Could not get information from database for Get_User_ID");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}
		return result;
	}

	@Override
	public List<Message> getNewsFeed(String user) throws TwitterException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
		List<Message> result = null;
		try {
			logger.info("Making a database call for Query: " + SQLQueries.NEWS_FEED_SQL);
			result = jdbcTemplate.query(SQLQueries.NEWS_FEED_SQL, namedParameters, new BeanPropertyRowMapper<Message>(Message.class));
		} catch (DataAccessException e) {
			logger.info("Could not get information from database for Get_News_Feed");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}
		return result;
	}

	@Override
	public List<Message> getMyPosts(String user) throws TwitterException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
		List<Message> result = null;
		try {
			logger.info("Making a database call for Query: " + SQLQueries.MY_POSTS_SQL);
			result = jdbcTemplate.query(SQLQueries.MY_POSTS_SQL, namedParameters, new BeanPropertyRowMapper<Message>(Message.class));
		} catch (DataAccessException e) {
			logger.info("Could not get information from database for Get_My_Posts");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}
		return result;
	}

	@Override
	public List<People> getMyFollowers(String user) throws TwitterException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
		List<People> result = null;
		try {
			logger.info("Making a database call for Query: " + SQLQueries.MY_FOLLOWERS);
			result = jdbcTemplate.query(SQLQueries.MY_FOLLOWERS, namedParameters, new BeanPropertyRowMapper<People>(People.class));
		} catch (DataAccessException e) {
			logger.info("Could not get information from database for Get_My_Followers");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}
		return result;
	}

	@Override
	public List<People> getMyFollowees(String user) throws TwitterException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
		List<People> result = null;
		try {
			logger.info("Making a database call for Query: " + SQLQueries.MY_FOLLOWEES);
			result = jdbcTemplate.query(SQLQueries.MY_FOLLOWEES, namedParameters, new BeanPropertyRowMapper<People>(People.class));
		} catch (DataAccessException e) {
			logger.info("Could not get information from database for Get_My_Followees");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}
		return result;
	}

	@Override
	public void follow(String user, String followee) throws TwitterException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user)).addValue("followee", getUserId(followee));
		try {
			logger.info("Making a database call for Query: " + SQLQueries.FOLLOW_SQL);
			jdbcTemplate.queryForRowSet(SQLQueries.FOLLOW_SQL, namedParameters);
		} catch (DataAccessException e) {
			logger.info("Database exception for follow");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}
	}

	@Override
	public void unfollow(String user, String followee) throws TwitterException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user)).addValue("followee", getUserId(followee));
		try {
			logger.info("Making a database call for Query: " + SQLQueries.UNFOLLOW_SQL);
			jdbcTemplate.queryForRowSet(SQLQueries.UNFOLLOW_SQL, namedParameters);
		} catch (DataAccessException e) {
			logger.info("Database exception for Unfollow");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}

	}

	@Override
	public List<Network> getAllNetwork() throws TwitterException {
		List<Network> result = null;
		try {
			logger.info("Making a database call for Query: " + SQLQueries.NETWORK_SQL);
			result = jdbcTemplate.query(SQLQueries.NETWORK_SQL, new BeanPropertyRowMapper<Network>(Network.class));
		} catch (DataAccessException e) {
			logger.info("Could not get information from database for Get_All_Network");
			throw new TwitterException(ErrorCode.DATABASE_ERROR);
		}
		return result;
	}

}

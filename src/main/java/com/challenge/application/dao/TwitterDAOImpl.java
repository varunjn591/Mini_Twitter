package com.challenge.application.dao;

import java.util.List;

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
import com.challenge.application.model.Message;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;

@Repository
public class TwitterDAOImpl implements TwitterDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int getUserId(String user) throws TwitterException {
	SqlParameterSource namedParameters = new MapSqlParameterSource("username", user);
	int result = -1;
	try{
	    result = jdbcTemplate.queryForObject(SQLQueries.USER_ID, namedParameters, Integer.class);
	}catch(DataAccessException e){
	    throw new TwitterException(ErrorCode.INVALID_USER);
	}
	return result;
    }

    @Override
    public List<Message> getNewsFeed(String user) throws TwitterException{
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
	return jdbcTemplate.query(SQLQueries.NEWS_FEED_SQL, namedParameters, new BeanPropertyRowMapper<Message>(Message.class));
    }

    @Override
    public List<Message> getMyPosts(String user) throws TwitterException{
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
	return jdbcTemplate.query(SQLQueries.MY_POSTS_SQL, namedParameters, new BeanPropertyRowMapper<Message>(Message.class));
    }

    @Override
    public List<People> getMyFollowers(String user) throws TwitterException{
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
	return jdbcTemplate.query(SQLQueries.MY_FOLLOWERS, namedParameters, new BeanPropertyRowMapper<People>(People.class));
    }

    @Override
    public List<People> getMyFollowees(String user) throws TwitterException{
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user));
	return jdbcTemplate.query(SQLQueries.MY_FOLLOWEES, namedParameters, new BeanPropertyRowMapper<People>(People.class));
    }

    @Override
    public void follow(String user, String followee) throws TwitterException{
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user)).addValue("followee", getUserId(followee));
	jdbcTemplate.queryForRowSet(SQLQueries.FOLLOW_SQL, namedParameters);

    }

    @Override
    public void unfollow(String user, String followee) throws TwitterException{
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", getUserId(user)).addValue("followee", getUserId(followee));
	jdbcTemplate.queryForRowSet(SQLQueries.UNFOLLOW_SQL, namedParameters);
    }

    @Override
    public List<Network> getAllNetwork() throws TwitterException{
	return jdbcTemplate.query(SQLQueries.NETWORK_SQL, new BeanPropertyRowMapper<Network>(Network.class));
    }

}

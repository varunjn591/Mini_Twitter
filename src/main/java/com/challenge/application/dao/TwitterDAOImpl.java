package com.challenge.application.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.challenge.application.constants.SQLQueries;
import com.challenge.application.model.Message;
import com.challenge.application.model.Network;
import com.challenge.application.model.People;

@Repository
public class TwitterDAOImpl implements TwitterDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Message> getNewsFeed(String user) {
	SqlParameterSource namedParameters = new MapSqlParameterSource("username", user);
	int userId = jdbcTemplate.queryForObject(SQLQueries.USER_ID, namedParameters, Integer.class);
	namedParameters = new MapSqlParameterSource("id", userId);
	return jdbcTemplate.query(SQLQueries.NEWS_FEED_SQL, namedParameters, new BeanPropertyRowMapper<Message>(Message.class));
    }
    
    @Override
    public List<Message> getMyPosts(String user) {
	SqlParameterSource namedParameters = new MapSqlParameterSource("username", user);
	int userId = jdbcTemplate.queryForObject(SQLQueries.USER_ID, namedParameters, Integer.class);
	namedParameters = new MapSqlParameterSource("id", userId);
	return jdbcTemplate.query(SQLQueries.MY_POSTS_SQL, namedParameters, new BeanPropertyRowMapper<Message>(Message.class));
    }

    @Override
    public List<People> getMyFollowers(String user) {
	SqlParameterSource namedParameters = new MapSqlParameterSource("username", user);
	int userId = jdbcTemplate.queryForObject(SQLQueries.USER_ID, namedParameters, Integer.class);
	namedParameters = new MapSqlParameterSource("id", userId);
	return jdbcTemplate.query(SQLQueries.MY_FOLLOWERS, namedParameters,new BeanPropertyRowMapper<People>(People.class));
    }

    @Override
    public List<People> getMyFollowees(String user) {
	SqlParameterSource namedParameters = new MapSqlParameterSource("username", user);
	int userId = jdbcTemplate.queryForObject(SQLQueries.USER_ID, namedParameters, Integer.class);
	namedParameters = new MapSqlParameterSource("id", userId);
	return jdbcTemplate.query(SQLQueries.MY_FOLLOWEES, namedParameters,new BeanPropertyRowMapper<People>(People.class));
    }
    
    @Override
    public void follow(String user, String followee) {
	// TODO Auto-generated method stub
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", user).addValue("followee", followee);
	jdbcTemplate.queryForList(SQLQueries.FOLLOW_SQL, namedParameters);

    }

    @Override
    public void unfollow(String user, String followee) {
	// TODO Auto-generated method stub
	SqlParameterSource namedParameters = new MapSqlParameterSource("id", user).addValue("followee", followee);
	jdbcTemplate.queryForList(SQLQueries.UNFOLLOW_SQL, namedParameters);
    }

    @Override
    public List<Network> getAllNetwork() {
	// TODO Auto-generated method stub
	return jdbcTemplate.query(SQLQueries.NEWS_FEED_SQL, new BeanPropertyRowMapper<Network>(Network.class));
    }

}

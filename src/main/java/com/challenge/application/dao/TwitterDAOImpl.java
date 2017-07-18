package com.challenge.application.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.challenge.application.constants.SQLQueries;
import com.challenge.application.model.Message;
import com.challenge.application.model.People;

@Repository
public class TwitterDAOImpl implements TwitterDAO{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Collection<People> getNewsFeed(String userId) {
		String sql = "SELECT * FROM PEOPLE";//(ID,NAME) VALUES (:id,:name)";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", userId);
		
		return jdbcTemplate.query(sql , new BeanPropertyRowMapper<People>(People.class));
	}
	
	@Override
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", 1);
		return jdbcTemplate.query(SQLQueries.NEWS_FEED_SQL, namedParameters,new BeanPropertyRowMapper<Message>(Message.class));
	}

	@Override
	public void getMyNetwork() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void follow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollow() {
		// TODO Auto-generated method stub
		
	}

}

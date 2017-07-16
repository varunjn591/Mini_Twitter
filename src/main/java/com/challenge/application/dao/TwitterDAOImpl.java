package com.challenge.application.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
	public void getNetworkFeed() {
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

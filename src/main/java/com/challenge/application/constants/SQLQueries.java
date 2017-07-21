package com.challenge.application.constants;

public final class SQLQueries{
	
	public static final String USER_ID = "SELECT ID FROM PEOPLE WHERE HANDLE = :username LIMIT 1";
	
	public static final String NEWS_FEED_SQL = "SELECT FOLLOWERS.FOLLOWER_PERSON_ID AS id, MESSAGES.CONTENT AS messages FROM FOLLOWERS LEFT JOIN MESSAGES ON FOLLOWERS.FOLLOWER_PERSON_ID = MESSAGES.PERSON_ID WHERE FOLLOWERS.PERSON_ID = :id";
	
	public static final String MY_POSTS_SQL = "SELECT PERSON_ID AS id, CONTENT AS messages FROM MESSAGES WHERE PERSON_ID = :id";
	
	public static final String MY_FOLLOWEES = "SELECT PEOPLE.ID, PEOPLE.HANDLE, PEOPLE.NAME  FROM PEOPLE INNER JOIN FOLLOWERS ON PEOPLE.ID =  FOLLOWERS.PERSON_ID WHERE FOLLOWERS.FOLLOWER_PERSON_ID = :id";
	
	public static final String MY_FOLLOWERS = "SELECT PEOPLE.ID, PEOPLE.HANDLE, PEOPLE.NAME  FROM PEOPLE INNER JOIN FOLLOWERS ON PEOPLE.ID =  FOLLOWERS.FOLLOWER_PERSON_ID WHERE FOLLOWERS.PERSON_ID = :id";
	
	public static final String FOLLOW_SQL = "INSERT INTO FOLLOWERS (PERSON_ID, FOLLOWER_PERSON_ID) SELECT * FROM (SELECT :followee, :follower) AS TEMP WHERE NOT EXISTS (SELECT PERSON_ID FROM FOLLOWERS WHERE PERSON_ID= :followee AND FOLLOWER_PERSON_ID = :follower) LIMIT 1";

	public static final String UNFOLLOW_SQL = "DELETE FROM FOLLOWERS WHERE FOLLOWER_PERSON_ID = :follower AND PERSON_ID = :followee LIMIT 1";
	
	public static final String NETWORK_SQL = "SELECT PERSON_ID AS FOLLOWEE, FOLLOWER_PERSON_ID AS FOLLOWER FROM FOLLOWERS";
}
package com.challenge.application.response;

import java.util.List;

import com.challenge.application.model.Message;
import com.challenge.application.model.People;

public class TwitterResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private List<Message> newsfeed;
    private List<Message> myPosts;
    private List<People> followers;
    private List<People> followees;
    

    public List<Message> getNewsfeed() {
	return newsfeed;
    }

    public void setNewsfeed(List<Message> newsfeed) {
	this.newsfeed = newsfeed;
    }

    public List<Message> getMyPosts() {
        return myPosts;
    }

    public void setMyPosts(List<Message> myPosts) {
        this.myPosts = myPosts;
    }

    public List<People> getFollowers() {
	return followers;
    }

    public void setFollowers(List<People> followers) {
	this.followers = followers;
    }

    public List<People> getFollowees() {
	return followees;
    }

    public void setFollowees(List<People> followees) {
	this.followees = followees;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }
}

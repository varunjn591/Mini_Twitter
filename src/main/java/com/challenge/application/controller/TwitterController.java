package com.challenge.application.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.application.service.TwitterService;

@RestController
public class TwitterController {

	@Autowired
	TwitterService twitterService;
	
	@RequestMapping("/message")
	public String message(){
		
		return "HI";
	}
	
	@RequestMapping("/messages")
	public List<String> messages(){
		
		return Arrays.asList("123","123","123");
	}
	
}

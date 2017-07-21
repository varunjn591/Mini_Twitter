package com.challenge.application.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.challenge.application.exception.ErrorCode;
import com.challenge.application.exception.TwitterException;
import com.challenge.application.model.Network;
import com.challenge.application.model.Node;

@Component("routeFinder")
public class RouteFinder {
    
    private static final Logger logger = Logger.getLogger(RouteFinder.class);
    
    public int shortestPath(List<Network> networks, int root, int destination) throws TwitterException{

	if (root == destination)
	    return 0;
	Map<Integer, Node> map = new LinkedHashMap<>();
	Map<Node, Node> prev = new HashMap<Node, Node>();

	for (Network n : networks) {
	    if (!map.containsKey(n.getFollower())) {
		map.put(n.getFollower(), new Node(n.getFollower()));
	    }
	}

	for (Network n : networks) {
	    if (!map.containsKey(n.getFollowee())) {
		map.put(n.getFollowee(), new Node(n.getFollowee()));
	    }
	}

	for (Network n : networks) {
	    map.get(n.getFollower()).getFollows().add(map.get(n.getFollowee()));
	}

	Queue<Node> queue = new LinkedList<>();
	Node curr = map.get(root);
	curr.setDiscovered(true);
	queue.add(curr);
	while (!queue.isEmpty()) {
	    curr = queue.poll();
	    if (curr == map.get(destination))
		break;
	    for (Node n : curr.getFollows()) {
		if (n.isDiscovered() == false) {
		    queue.add(n);
		    n.setDiscovered(true);
		    prev.put(n, curr);
		}
	    }
	}

	if (!curr.equals(map.get(destination))) {
	    throw new TwitterException(ErrorCode.NO_CONNECTION);
	}

	List<Node> directions = new LinkedList<>();
	for (Node node = map.get(destination); node != null; node = prev.get(node)) {
	    directions.add(node);
	}

	return directions.size();

    }
}

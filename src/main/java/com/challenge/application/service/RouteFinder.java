/** Class that uses BFS to search connection between Network **/
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

	public int shortestPath(List<Network> networks, int root, int destination) throws TwitterException {

		if(root < 1 || destination < 1){
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		
		if (root == destination)
			return 0;

		Map<Integer, Node> graph = new LinkedHashMap<>();
		Map<Node, Node> links = new HashMap<Node, Node>();

		/** Adding networks to graph **/
		for (Network n : networks) {
			if (!graph.containsKey(n.getFollower())) {
				graph.put(n.getFollower(), new Node(n.getFollower()));
			}
		}

		/** Adding networks to graph **/
		for (Network n : networks) {
			if (!graph.containsKey(n.getFollowee())) {
				graph.put(n.getFollowee(), new Node(n.getFollowee()));
			}
		}

		/** Adding followers to each user **/
		for (Network n : networks) {
			graph.get(n.getFollower()).getFollows().add(graph.get(n.getFollowee()));
		}

		/** Running BFS on graph **/
		Queue<Node> queue = new LinkedList<>();
		if(graph.get(root) == null){
			throw new TwitterException(ErrorCode.INVALID_USER);
		}
		Node curr = graph.get(root);
		curr.setDiscovered(true);
		queue.add(curr);
		while (!queue.isEmpty()) {
			curr = queue.poll();
			if (curr == graph.get(destination))
				break;
			for (Node n : curr.getFollows()) {
				if (n.isDiscovered() == false) {
					queue.add(n);
					n.setDiscovered(true);
					links.put(n, curr);
				}
			}
		}

		/** Checking if there is a valid connection **/
		if (!curr.equals(graph.get(destination))) {
			logger.info("There is no connection between user and friend");
			throw new TwitterException(ErrorCode.NO_CONNECTION);
		}

		List<Node> directions = new LinkedList<>();
		for (Node node = graph.get(destination); node != null; node = links.get(node)) {
			directions.add(node);
		}

		return directions.size() - 1;

	}
}

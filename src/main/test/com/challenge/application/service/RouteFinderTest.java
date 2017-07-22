package com.challenge.application.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.application.exception.TwitterException;
import com.challenge.application.model.Network;

@RunWith(SpringRunner.class)
public class RouteFinderTest {

	@InjectMocks
	RouteFinder routeFinder;

	@Test(expected = TwitterException.class)
	public void getShortestPath_NoSourceInGraph_Exception() throws Exception {
		List<Network> networks = new ArrayList<>();
		Network networkl = new Network();
		networkl.setFollowee(1);
		networkl.setFollower(2);
		networks.add(networkl);
		int result = routeFinder.shortestPath(networks, 3, 1);
		assertEquals(result, 1);
	}

	@Test
	public void getShortestPath_Normal_ResultWithDistance() throws Exception {
		List<Network> networks = new ArrayList<>();
		Network networkl = new Network();
		networkl.setFollowee(2);
		networkl.setFollower(1);
		networks.add(networkl);
		int result = routeFinder.shortestPath(networks, 1, 2);
		assertEquals(result, 1);
	}

	@Test(expected = TwitterException.class)
	public void getShortestPath_NoDestination_Exception() throws Exception {
		List<Network> networks = new ArrayList<>();
		Network networkl = new Network();
		networkl.setFollowee(2);
		networkl.setFollower(1);
		networks.add(networkl);
		routeFinder.shortestPath(networks, 1, 3);
	}

	@Test
	public void getShortestPath_SourceAndDestinationSame_ResultTo0() throws Exception {
		List<Network> networks = new ArrayList<>();
		Network networkl = new Network();
		networkl.setFollowee(2);
		networkl.setFollower(1);
		networks.add(networkl);
		int result = routeFinder.shortestPath(networks, 1, 1);
		assertEquals(0, result);
	}

	@Test
	public void getShortestPath_NodeDistanceByTwo_ResultTo2() throws Exception {
		List<Network> networks = new ArrayList<>();
		Network networkl = new Network();
		networkl.setFollowee(2);
		networkl.setFollower(1);
		networks.add(networkl);
		Network network2 = new Network();
		network2.setFollowee(3);
		network2.setFollower(2);
		networks.add(network2);
		int result = routeFinder.shortestPath(networks, 1, 3);
		assertEquals(result, 2);
	}

}

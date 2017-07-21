package com.challenge.application.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Node {
    private int id;
    private boolean discovered;
    private Set<Node> follows;

    public Node(int id) {
	this.id = id;
	discovered = false;
	this.follows = new LinkedHashSet<Node>();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public boolean isDiscovered() {
	return discovered;
    }

    public void setDiscovered(boolean discovered) {
	this.discovered = discovered;
    }

    public Set<Node> getFollows() {
	return follows;
    }

    public void setFollows(Set<Node> follows) {
	this.follows = follows;
    }

}

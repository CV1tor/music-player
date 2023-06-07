package com.project.model;
import java.util.ArrayList;

public class User {
	private String id;
	private String username;
	private String password;
	private boolean isVip;
	private ArrayList<String> directories;
	
	public User() {
		this.directories = new ArrayList<String>();
		this.isVip = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}

	public ArrayList<String> getDirectories() {
		return directories;
	}

	public void setDirectories(ArrayList<String> directories) {
		this.directories = directories;
	}
	
	
	
}

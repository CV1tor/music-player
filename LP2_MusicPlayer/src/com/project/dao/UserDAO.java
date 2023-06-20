package com.project.dao;
import java.util.ArrayList;
import com.project.model.User;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserDAO {
	private ArrayList<HashMap<String, String>> users;
	private static UserDAO dataUser;
	
	public UserDAO() {
		
		this.users = new ArrayList<HashMap<String, String>>();
		
		try {
			readUsersFile("users.txt");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static UserDAO getInstance() {
		if (dataUser == null) {
			dataUser = new UserDAO();
		}
		
		return dataUser;
	}
	
	public void addUser(User newUser) {
		HashMap<String,String> aux = new HashMap<String, String>();
		aux.put(newUser.getUsername(), newUser.getPassword());
		users.add(aux);
	}
	
	public int searchId() {
		return users.size();
	}

	public ArrayList<HashMap<String, String>> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<HashMap<String, String>> users) {
		this.users = users;
	}
	
	public void readUsersFile(String path) throws IOException {
		
		ArrayList<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
		
		FileReader file = new FileReader(path);
		BufferedReader readFile = new BufferedReader(file);
		String line = readFile.readLine();
		
		while (line != null) {
			addUser(makeUser(line));
			line = readFile.readLine();
		}
		
	}
	
	public User makeUser(String line) {
		
		User user = new User();
		
		String[] infos = line.split(",");
		user.setUsername(infos[0]);
		user.setPassword(infos[1]);
		
		return user;
	}
	
	
	
}

package com.project.dao;
import java.util.ArrayList;
import com.project.model.User;
import java.util.HashMap;

public class UserDAO {
	private ArrayList<HashMap<String, String>> users;
	private static UserDAO dataUser;
	
	public UserDAO() {
		this.users = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> admin = new HashMap<String, String>();
		admin.put("admin", "000");
		users.add(admin);
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
}

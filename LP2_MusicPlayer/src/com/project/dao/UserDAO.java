package com.project.dao;
import java.util.ArrayList;
import com.project.model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserDAO {
	private ArrayList<User> users;
	private static UserDAO dataUser;
	
	public UserDAO() {
		
		this.users = new ArrayList<User>();
		
		try {
			readUsersFile(new File("src/com/project/data/users.txt"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (users.size() != 0) {
				for (User user : users) {
					createFiles(user);
				}
			}
		}
		
		
		
		
	}
	
	public static UserDAO getInstance() {
		if (dataUser == null) {
			dataUser = new UserDAO();
		}
		
		return dataUser;
	}
	
	public void addUser(User newUser) {
		users.add(newUser);
	}
	
	public int searchId() {
		return users.size();
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	// Lê as informações de users.txt e armazena elas em users
	public void readUsersFile(File usersFile) throws IOException {
		
		FileReader file = new FileReader(usersFile);
		BufferedReader readFile = new BufferedReader(file);
		String line = readFile.readLine();
		
		while (line != null) {
			addUser(makeUser(line));
			line = readFile.readLine();
		}
		
	}
	
	// Separa e armazena as informações de users.txt em um objeto User
	public User makeUser(String line) {
		
		User user = new User();
		
		String[] infos = line.split(",");
		user.setUsername(infos[0]);
		user.setPassword(infos[1]);
		
		return user;
	}
	
	public void createFiles(User user) {
		
		// Criando pasta para usuário
		File userDir = new File("src/com/project/data/users/" + user.getUsername());
		File directories = new File(userDir.getAbsolutePath() + "/directories.txt");
		File songs = new File(userDir.getAbsolutePath() + "/songs.txt");
		
		if (!userDir.exists()) {
			userDir.mkdir();
			
			
			if (user.isVip()) {
				File playlists = new File (userDir.getAbsolutePath() + "/playlists");
				if (!playlists.exists()) {
					playlists.mkdir();
					user.setPlaylistsPath(playlists.getAbsolutePath());
				}
			}
			
			try {
				directories.createNewFile();
				songs.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		}
		user.setSongsPath(songs.getAbsolutePath());
		user.setDirectoriesPath(directories.getAbsolutePath());
	}
	
	
	
}

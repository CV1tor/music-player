package com.project.dao;
import java.util.ArrayList;

import com.project.model.Song;
import com.project.model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

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
		if (users.size() != 0) {
			for (User user : users) {
				createFiles(user);
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
		System.out.println(".:. Adicionando novo usuário... .:.");
		try {
			writeUsersFile(new File("src/com/project/data/users.txt"), newUser);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		users.add(newUser);
		System.out.println(".:. Usuário adicionado com sucesso! .:.");
		
	}
	
	public int userId() {
		return users.size() + 1;
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
		BufferedReader reader = new BufferedReader(file);
		String line = reader.readLine();
			
		while (line != null) {
			if (makeUser(line) == null) {
				System.out.println(".:. Nenhum usuário cadastrado! .:.");
				break;
			}
			users.add(makeUser(line));
			
			line = reader.readLine();
		}
	}
		

	
	public void writeUsersFile(File usersFile, User user) throws IOException {
		FileWriter file = new FileWriter(usersFile, true);
		BufferedWriter writeFile = new BufferedWriter(file);
		
		if (user.isVip()) {
			writeFile.write(user.getUsername() + "," + user.getPassword() + "," + userId() + "," +  "vip\n");
		}
		else {
			writeFile.write(user.getUsername() + "," + user.getPassword() + "," + userId() + "\n");
		}
		
		writeFile.close();
	}
	
	// Separa e armazena as informações de users.txt em um objeto User
	public User makeUser(String line) {
		
		User user = new User();
		
		String[] infos = line.split(",");
		
		if (infos.length <= 1) {
			return null;
		}
		user.setUsername(infos[0]);
		user.setPassword(infos[1]);
		user.setId(infos[2]);
		
		if (infos.length > 3 && infos[3].equals("vip")) {
			user.setVip(true);
		}
		
		return user;
	}
	
	public void createFiles(User user) {
		
		
		// Criando pasta para usuário
		File userDir = new File("src/com/project/data/users/" + user.getUsername());
		File directories = new File(userDir.getAbsolutePath() + "/directories.txt");
		File songs = new File(userDir.getAbsolutePath() + "/songs.txt");
		
		if (user.isVip()) {
			File playlists = new File (userDir.getAbsolutePath() + "/playlists");
			user.setPlaylistsPath(playlists.getAbsolutePath());
			if (!playlists.exists()) {
				playlists.mkdir();
				
			}
		}
		
		if (!userDir.exists()) {
			userDir.mkdir();
			
			
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

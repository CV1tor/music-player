package com.project.model;
import java.util.ArrayList;

public class User {
	private String id;
	private String username;
	private String password;
	private boolean isVip;
	private ArrayList<Song> songs;
	private String songsPath;
	private String directoriesPath;
	private ArrayList<Playlist> playlists;
	
	public User() {
		this.isVip = false;
		this.songs = new ArrayList<Song>();
		this.playlists = new ArrayList<Playlist>();
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
	
	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> musics) {
		this.songs = musics;
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}

	public String getSongsPath() {
		return songsPath;
	}

	public void setSongsPath(String songsPath) {
		this.songsPath = songsPath;
	}

	public String getDirectoriesPath() {
		return directoriesPath;
	}

	public void setDirectoriesPath(String directoriesPath) {
		this.directoriesPath = directoriesPath;
	}
	
	
}

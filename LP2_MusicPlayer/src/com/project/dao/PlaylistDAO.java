package com.project.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.project.model.Playlist;
import com.project.model.Song;
import com.project.model.User;

public class PlaylistDAO {
	private ArrayList<Playlist> playlists;
	private static PlaylistDAO playlistData;
	
	public PlaylistDAO() {
		this.playlists = new ArrayList<Playlist>();
	}
	
	public static PlaylistDAO getInstance() {
		if (playlistData == null) {
			playlistData = new PlaylistDAO();
		}
		
		return playlistData;
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public void addPlaylist(Playlist newPlaylist) {
		playlists.add(newPlaylist);
	}
	
	public void createPlaylist(User playlistOwner, String playlistName) throws IOException {
		File playlistsDirectory = new File(playlistOwner.getPlaylistsPath());
		File newPlaylist = new File(playlistsDirectory.getAbsolutePath() + "/playlist_" + playlistId() + ".txt");
		
		
		
		if (!newPlaylist.exists()) {
			newPlaylist.createNewFile();
			FileWriter file = new FileWriter(newPlaylist);
			BufferedWriter writer = new BufferedWriter(file);
			
			writer.write(playlistOwner.getUsername() + "," + playlistOwner.getId() + "," + playlistName + "\n");
			writer.close();
		}
		
		
	}
	
	public void readPlaylistsDirectory (User user) throws IOException {
		Playlist aux = new Playlist();
		File playlistDirectory = new File(user.getPlaylistsPath());
		
		File[] files = playlistDirectory.listFiles();
		
		if (files.length > 0) {
			for (File file : files) {
				FileReader readFile = new FileReader(file);
				BufferedReader reader = new BufferedReader(readFile);
				String line = reader.readLine();
				
				aux.setPath(file.getAbsolutePath());
				aux.setName(playlistName(line, user));
				aux.setSongs(readPlaylistFile(file));
				playlists.add(aux);
			}
		}
		
		
		
		
		
	}
	
	public ArrayList<Song> readPlaylistFile(File file) throws IOException {
		FileReader readFile = new FileReader(file);
		BufferedReader reader = new BufferedReader(readFile);
		String line = reader.readLine();
		// pula a linha inicial;
		line = reader.readLine();
		
		ArrayList<Song> songsInPlaylist =  new ArrayList<Song>();
		while(line != null) {
			File songFile = new File(line);
			Song song = new Song();
			song.setName(songFile.getName());
			song.setPath(songFile.getAbsolutePath());
			songsInPlaylist.add(song);
		}
		
		return songsInPlaylist;
		
		
		
		
	}
	
	public void writePlaylistFile(Playlist playlist, Song song) throws IOException {
		File file = new File(playlist.getPath());
		FileWriter writeFile = new FileWriter(file, true);
		BufferedWriter writer = new BufferedWriter(writeFile);
		
		playlist.getSongs().add(song);
		writer.write(song.getPath() + "\n");
		
	}
	public int playlistId() {
		return playlists.size() + 1;
	}
	
	public String playlistName(String line, User owner) {
		Playlist aux = new Playlist();
		String[] infos = line.split(",");
		
		if (infos[0].equals(owner.getUsername()) && infos[1].equals(owner.getId())) {
			return infos[2];
		}
		
		return "unknown playlist";
		
	}
	
	public ArrayList<String> playlistList() {
		ArrayList<String> playlistsNames = new ArrayList<String>();
		
		for (Playlist playlist : playlists) {
			playlistsNames.add(playlist.getName());
		}
		
		return playlistsNames;
	}
	
}

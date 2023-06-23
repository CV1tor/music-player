package com.project.dao;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

import com.project.model.CurrentUser;
import com.project.model.Song;
import com.project.model.User;


public class SongDAO {
	private ArrayList<Song> songs;
	private static SongDAO dataSong;
	
	public SongDAO() {
		this.songs = new ArrayList<Song>();
	}
	
	public static SongDAO getInstance() {
		if (dataSong == null) {
			dataSong = new SongDAO();
		}
		
		return dataSong;
	}
	
	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	public void addSong(Song newSong) {
		songs.add(newSong);
	}

	public void writeSongData(User currentUser, Song song) throws IOException {
		FileWriter songsFile = new FileWriter(currentUser.getSongsPath(), true);
		BufferedWriter writer = new BufferedWriter(songsFile);
		
		writer.write(song.getPath() + "\n");
		songs.add(song);
		writer.close();
		
		System.out.println(".:. Musica adicionada com sucesso! .:.");
	
	}
	
	public void writeDirectoriesData(User currentUser, File directory) throws IOException {
		FileWriter directoriesFile = new FileWriter(currentUser.getDirectoriesPath(), true);
		BufferedWriter writer = new BufferedWriter(directoriesFile);
		
		writer.write(directory.getPath() + "\n");
		writer.close();
	}
	
 	
	public void readSongsData(User songOwner) throws IOException {
		FileReader file = new FileReader(songOwner.getSongsPath());
		BufferedReader reader = new BufferedReader(file);
		String line = reader.readLine();
		
		while (line != null) {

			Song aux = new Song();
			File songLine = new File(line);
			aux.setPath(songLine.getAbsolutePath());
			aux.setName(songLine.getName());
			songs.add(aux);
			songOwner.getSongs().add(aux);
			line = reader.readLine();
			
		}
		
	}
	
	public ArrayList<String> songsList() {
		ArrayList<String> songsNames = new ArrayList<String>();
		
		for (Song song : songs) {
			songsNames.add(song.getName());
		}
		
		return songsNames;
	}
	
}

package com.project.dao;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

import com.project.model.Song;
import com.project.model.User;


public class SongDAO {
	private ArrayList<Song> songs;
	
	public SongDAO() {
		this.songs = new ArrayList<Song>();
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

	public void writeSongData(User songOwner, Song song) throws IOException {
		System.out.println(songOwner.getSongsPath());
		FileWriter songsFile = new FileWriter(songOwner.getSongsPath());
		BufferedWriter writer = new BufferedWriter(songsFile);
		
		writer.write(song.getName() + "\n");
		writer.close();
		System.out.println("oi");
	
	}
	
	public ArrayList<String> songsList(User songOwner) {
		ArrayList<String> songsNames = new ArrayList<String>();
		try {
			FileReader songsFile = new FileReader(songOwner.getSongsPath());
			BufferedReader reader = new BufferedReader(songsFile);
			String line = reader.readLine();
			
			while (line != null) {
				songsNames.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return songsNames;
		
	}
	
}

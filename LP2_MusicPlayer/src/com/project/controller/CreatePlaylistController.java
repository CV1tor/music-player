package com.project.controller;

import java.io.File;
import java.util.List;

import com.project.dao.SongDAO;
import com.project.model.CurrentUser;
import com.project.model.Playlist;
import com.project.model.Song;
import com.project.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class CreatePlaylistController {
	private Stage createPlaylistStage;
	private CurrentUser currentUser;
	private SongDAO songData = SongDAO.getInstance();
	private Playlist playlist;
	
	@FXML
	private TextField playlistName;
	
	@FXML
	private Button createBtn;
	
	@FXML
	private Button cancelBtn;
	
	@FXML
	private Button newSongBtn;
	
	@FXML
	private ListView<String> playlistSongs;
	
	
	@FXML
	void createPlaylist() {
		playlist.setName(playlistName.getText());
		CurrentUser.getUser().getPlaylists().add(playlist);
	}
	
	@FXML
	void addSongToPlaylist() {
		User current = CurrentUser.getUser();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Add some music!");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Audio Files", "*.wav" , "*.mp3"));
		
		List<File> songsFiles = fileChooser.showOpenMultipleDialog(createPlaylistStage);
		
		if (songsFiles != null) {
			for (File file : songsFiles) {
				Song song = new Song();
				song.setName(file.getName());
				song.setPath(file.getAbsolutePath());
				playlistSongs.getItems().add(song.getName());
				playlist.getSongs().add(song);
			}
		}
		
		
	}
	
	public Stage getCreatePlaylistStage() {
		return createPlaylistStage;
	}

	public void setCreatePlaylistStage(Stage createPlaylistStage) {
		this.createPlaylistStage = createPlaylistStage;
	}

	@FXML
	void cancelCreation() {
		
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
}

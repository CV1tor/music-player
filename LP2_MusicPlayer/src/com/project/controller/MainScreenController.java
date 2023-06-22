package com.project.controller;

import com.project.model.User;
import com.project.model.Song;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.IOException;

import com.project.dao.SongDAO;
import java.util.ArrayList;


public class MainScreenController {
	private Stage mainStage;
	private User currentUser; 
	
	@FXML
	private ImageView iconAddDirectory;
	
	@FXML
	private ImageView iconAddFile;
	
	@FXML
	private TitledPane songsDisplay;
	
	@FXML
	private TitledPane playlistSongsDisplay;
	
	@FXML
	private TitledPane playlistsDisplay;
	
	@FXML
	private ListView<String> songsList;

	
	
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	@FXML
	void addFile() {
		
		FileChooser fileChooser = new FileChooser();
		
		
		
		File file = fileChooser.showOpenDialog(mainStage);
		
		
		Song newSong = new Song();
		
		newSong.setPath(file.getAbsolutePath());
		newSong.setName(file.getName());
		

		currentUser.getSongs().add(newSong);
		System.out.println("Arquivo adicionado!");
		
		
		
		
	
		
		
	}
	
	@FXML
	void addDirectory() {
		
	}	
	
	
	
	
	
}

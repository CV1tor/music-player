package com.project.controller;

import com.project.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.File;

import com.project.dao.SongDAO;

public class MainScreenController {
	private Stage mainStage;
	private User currentUser;
	private SongDAO songData = new SongDAO();
	
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
		
		
	}
	
	@FXML
	void addDirectory() {
		
	}
	
	
	
	
}

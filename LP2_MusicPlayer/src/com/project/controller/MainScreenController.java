package com.project.controller;

import com.project.model.User;
import com.project.model.CurrentUser;
import com.project.model.PausablePlayer;
import com.project.model.Playlist;
import com.project.model.Song;
import com.project.controller.LoginScreenController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;



import com.project.dao.PlaylistDAO;
import com.project.dao.SongDAO;
import com.project.dao.UserDAO;

import java.util.ArrayList;




public class MainScreenController {
	private Stage mainStage;
	private SongDAO userSongs = SongDAO.getInstance();
	private PlaylistDAO userPlaylists = PlaylistDAO.getInstance();
	private CurrentUser currentUser;
	private PausablePlayer player;
	private boolean playIsClicked = false;
	private boolean isPaused = false;
	
	@FXML
	private ImageView iconAddDirectory;
	
	@FXML
	private ImageView iconAddFile;
	
	@FXML
	private ImageView iconAddPlaylist;
	
	@FXML
	private TitledPane songsDisplay;
	
	@FXML
	private TitledPane playlistSongsDisplay;
	
	@FXML
	private TitledPane playlistsDisplay;
	
	@FXML
	private ListView<String> songsList;
	
	@FXML
	private ListView<String> playlistsList;
	
	@FXML
	private Label vipLabel;
	
	@FXML
	private Label labelUser;
	
	@FXML
	private ImageView playerPlay;

	
	
	
	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	@FXML
	void addFile() throws IOException {
		
		User current = CurrentUser.getUser();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Add some music!");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Audio Files", "*.wav" , "*.mp3"));
		
		
		
		File file = fileChooser.showOpenDialog(mainStage);
		
		if (file != null) {
			Song newSong = new Song();
			
			newSong.setPath(file.getAbsolutePath());
			newSong.setName(file.getName());
			
			userSongs.writeSongData(current, newSong);
			
			songsList.getItems().add(newSong.getName());
		}
		
		
	}
	
	@FXML
	void addDirectory() throws IOException {
		User current = CurrentUser.getUser();
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
		
		File directory = directoryChooser.showDialog(mainStage);
		userSongs.writeDirectoriesData(current, directory);
		
		if (directory != null) {
			File[] filesInDirectory = directory.listFiles();
			
			
			for (File file : filesInDirectory) {
				System.out.println(file.getName());
				Song newSong = new Song();
				newSong.setPath(file.getAbsolutePath());
				newSong.setName(file.getName());
				
				userSongs.writeSongData(current, newSong);
				songsList.getItems().add(newSong.getName());
	
			}
		}
		
		
		
		
	}
	
	@FXML
	void addPlaylist() throws IOException {
		
		User current = CurrentUser.getUser();
		
		if (current.isVip()) {
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(CreatePlaylistController.class.getResource("/com/project/view/CreatePlaylistScreen.fxml"));
	    	Pane page = (Pane) loader.load();
	    	
	    	// Criando um novo Stage
	    	Stage createPlaylistStage = new Stage();
	    	createPlaylistStage.setTitle("Create a new playlist");
	    	createPlaylistStage.setResizable(false);
	    	Scene scene = new Scene(page);
	    	createPlaylistStage.setScene(scene);
	    	
	    	CreatePlaylistController controller = loader.getController();
	    	controller.setCreatePlaylistStage(createPlaylistStage);
	    	createPlaylistStage.showAndWait();
			
			
			
			
		}
		
		
		
	}
	
	
	@FXML
	void initialize() throws IOException {
		
		User current = CurrentUser.getUser();
		
		userSongs.readSongsData(current);
		if (current.isVip()) {
			
			userPlaylists.readPlaylistsDirectory(current);
			playlistsList.getItems().addAll(userPlaylists.playlistList());
			
		}
		
		if (userSongs.getSongs().size() != 0) {
			songsList.getItems().addAll(userSongs.songsList());
		}
		
		if (!current.isVip()) {
			vipLabel.setText(null);
		}
		
		labelUser.setText(current.getUsername());
			
		songsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				String currentSong = songsList.getSelectionModel().getSelectedItem();
				
				for (Song song : userSongs.getSongs()) {
					if (song.getName().equals(currentSong)) {
						File file = new File(song.getPath());
						
						try {
							FileInputStream songFile = new FileInputStream(file);
							player = new PausablePlayer(songFile);
						} catch (FileNotFoundException | JavaLayerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
						
						
						
					}
				}
			}
			
		});
		
	}
	
	@FXML
	void playMusic() throws JavaLayerException {
		playClicked();
		
		if (playIsClicked) {
			if (player != null) {
				player.play();
			}
		}
		else {
			playPaused();
			if (isPaused) {
				player.resume();
			}
			player.pause();
			
		}
	}
	
	boolean playClicked() {
		File imageFile;
		if (!playIsClicked) {
			imageFile = new File("src/com/project/img/icons/pause-icon.png");
			playerPlay.setImage(new Image(imageFile.getAbsolutePath()));
			playIsClicked = true;
			return playIsClicked;
		}
		playIsClicked = false;
		imageFile = new File("src/com/project/img/icons/play-icon.png");
		playerPlay.setImage(new Image(imageFile.getAbsolutePath()));
		return playIsClicked;
	}
	
	boolean playPaused() {
		if(!isPaused) {
			isPaused = true;
			return isPaused;
		}
		isPaused = false;
		return isPaused;
	}
	
	
	
	
	
	
}

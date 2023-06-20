package com.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	//teste
	@Override
	public void start(Stage stage) throws Exception {
		// caminho da Tela Principal
		Parent root = FXMLLoader.load(getClass().getResource("view/LoginScreen.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
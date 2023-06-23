package com.project;



import com.project.controller.LoginScreenController;
import com.project.controller.MainScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {
	//teste
	@Override
	public void start(Stage stage) throws Exception {
		// caminho da Tela Principal
 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LoginScreen.fxml"));
		Parent root = (Parent)loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.setResizable(false);
		
		LoginScreenController controller = (LoginScreenController)loader.getController();
		controller.setLoginStage(stage);
	
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

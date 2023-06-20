package com.project.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.project.dao.UserDAO;


import java.util.HashMap;

public class LoginScreenController {
	private UserDAO usersData = new UserDAO();
	
	@FXML
	private TextField loginUsername;
	
	@FXML
	private PasswordField loginPassword;
	
	@FXML
	private Button loginBtn;
	
	
	@FXML
	void openPrincipalPage(ActionEvent e) throws IOException {
		
		HashMap<String, String> aux = new HashMap<String, String>();
			
		aux.put(loginUsername.getText(), loginPassword.getText());
			
		if ( loginUsername.getText().equals("")|| loginPassword.getText().equals("")) {
			System.out.println("Insira nome de usuário ou senha!");
		}
		else if (usersData.getUsers().contains(aux)) {
		 	System.out.println("Login autorizado");
			 
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(MainScreenController.class.getResource("/com/project/view/MainScreen.fxml"));
	    	Pane page = (Pane) loader.load();
	    	
	    	// Criando um novo Stage
	    	Stage mainStage = new Stage();
	    	mainStage.setTitle("Media Player");
	    	mainStage.setResizable(false);
	    	Scene scene = new Scene(page);
	    	mainStage.setScene(scene);
	    	
	    	// Setando o Controle 
	    	MainScreenController controller = loader.getController();
	    	controller.setMainStage(mainStage);
	    	
	    	mainStage.showAndWait();
		}
		else {
			System.out.println("Usuário não cadastrado");
		}
	}
}


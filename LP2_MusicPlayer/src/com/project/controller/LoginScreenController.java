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
import com.project.model.CurrentUser;
import com.project.model.User;


public class LoginScreenController {
	private UserDAO usersData =  UserDAO.getInstance();
	private Stage loginStage;
	private CurrentUser currentUser;
	
	@FXML
	private TextField loginUsername;
	
	@FXML
	private PasswordField loginPassword;
	
	@FXML
	private Button loginBtn;
	
	@FXML
	private Button registerBtn;
	
	public void setLoginStage(Stage loginStage) {
		this.loginStage = loginStage;
	}
	
	public Stage getLoginStage() {
		return loginStage;
	}
	

	
	
	@FXML
	boolean openPrincipalPage(ActionEvent e) throws IOException {
		
		if (loginUsername.getText().equals("") || loginPassword.getText().equals("")) {
			System.out.println(".:. Preencha os campos antes de logar! .:.");
			return false;
		}
		
		for (User user : usersData.getUsers()) {
			if (user.getUsername().equals(loginUsername.getText()) && user.getPassword().equals(loginPassword.getText())) {
				
				System.out.println(".:. Login autorizado .:.");
				CurrentUser.setUser(user);
				
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
		    	loginStage.close();
		    	mainStage.showAndWait();
		    	
				
				return true;
			}
			
		}
		
		
		System.out.println(".:. Usuario não cadastrado! .:.");
		return false;
		
	}
	
	@FXML
	void openRegisterPage() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(RegisterScreenController.class.getResource("/com/project/view/RegisterScreen.fxml"));
    	Pane page = (Pane) loader.load();
    	
    	// Criando um novo Stage
    	Stage registerStage = new Stage();
    	registerStage.setTitle("Sign up");
    	registerStage.setResizable(false);
    	Scene scene = new Scene(page);
    	registerStage.setScene(scene);
    	
    	RegisterScreenController controller = loader.getController();
    	controller.setRegisterStage(registerStage);
    	registerStage.showAndWait();
	}
	
	@FXML
	void initialize() throws IOException {
	
		if (usersData.getUsers().size() == 0) {
			System.out.println(".:. Não existem usuários cadastrados no banco .:.");
			System.out.println(".:. Redirecionando para tela de cadastro... .:.");
			FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(RegisterScreenController.class.getResource("/com/project/view/RegisterScreen.fxml"));
	    	Pane page = (Pane) loader.load();
	    	
	    	// Criando um novo Stage
	    	Stage registerStage = new Stage();
	    	registerStage.setTitle("Sign up");
	    	registerStage.setResizable(false);
	    	Scene scene = new Scene(page);
	    	registerStage.setScene(scene);
	    	
	    	RegisterScreenController controller = loader.getController();
	    	controller.setRegisterStage(registerStage);
	    	registerStage.showAndWait();
	    	
	    	
	    	
	   
		}
	}

	
	
}


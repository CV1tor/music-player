package com.project.controller;


import java.io.IOException;

import com.project.dao.UserDAO;
import com.project.model.User;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterScreenController {
	private Stage registerStage;
	private UserDAO usersData = UserDAO.getInstance();
	
	@FXML
	private TextField registerUsername;
	
	@FXML
	private PasswordField registerPassword;
	
	@FXML
	private Button registerBtn;
	
	@FXML
	private Button clearBtn;
	
	@FXML
	private CheckBox vipCheck;

	public Stage getRegisterStage() {
		return registerStage;
	}

	public void setRegisterStage(Stage registerStage) {
		this.registerStage = registerStage;
	}
	
	@FXML
	boolean openLogin(ActionEvent e) throws IOException {
	
		if (registerUsername.getText().equals("") || registerPassword.getText().equals("")) {
			System.out.println(".:. Preencha todos os campos para cadastro! .:.");
			return false;
		}
		for (User user : usersData.getUsers()) {
				if (registerUsername.getText().equals(user.getUsername())) {
					System.out.println(".:. Usuário já registrado no banco, tente outro nome! .:.");
					return false;
				}
			}
		
		User newUser = new User();
		newUser.setUsername(registerUsername.getText());
		newUser.setPassword(registerPassword.getText());
		
		if (vipCheck.isSelected()) {
			newUser.setVip(true);
		}
		usersData.addUser(newUser);
		usersData.createFiles(newUser);
		
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(LoginScreenController.class.getResource("/com/project/view/LoginScreen.fxml"));
    	Pane page = (Pane) loader.load();
    	
    	// Criando um novo Stage
    	Stage loginStage = new Stage();
    	loginStage.setTitle("Login");
    	loginStage.setResizable(false);
    	Scene scene = new Scene(page);
    	loginStage.setScene(scene);
    	
    	LoginScreenController controller = loader.getController();
    	controller.setLoginStage(loginStage);
    	registerStage.close();
    	
    
    	
  
    	
	    	
	    return true;	
	}
		
	
	@FXML
	void clearFields(ActionEvent e) {
		registerUsername.setText(null);
		registerPassword.setText(null);
	}
	
	
}

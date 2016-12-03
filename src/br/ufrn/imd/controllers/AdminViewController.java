package br.ufrn.imd.controllers;

import br.ufrn.imd.dao.UserDAO;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;
import br.ufrn.imd.navigation.Navigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminViewController {
	
	@FXML private TextField usernameField;
	@FXML private TextField nameField;
	@FXML private PasswordField pwField;
	@FXML private PasswordField repeatPwField;
	@FXML private CheckBox isVip;
	@FXML private Label registerUserMessage;
	@FXML private Button registerUserButton;
	@FXML public void handleRegisterUser() {
		
		boolean canAddUser = true;
		
		User newUser = new User();
		
		newUser.setUsername(this.usernameField.getText());
		newUser.setName(this.nameField.getText());
		newUser.setVip(this.isVip.selectedProperty().get());
		
		if(!this.pwField.getText().equals(this.repeatPwField.getText()) && canAddUser) {
			canAddUser = false;
		} else {
			newUser.setPassword(this.pwField.getText());
		}
		
		if(canAddUser) {
			UserDAO userDAO = new UserDAO();
			boolean userAdded = true;
			try {
				userDAO.addNewUser(newUser);
				userAdded = true;
			} catch (UserAlreadyExistsException e) {
				userDAO.removeUser(newUser);
				userAdded = false;
				e.printStackTrace();
			} finally {
				
				String message = "";
				message = userAdded ? "Usuário registrado com sucesso!" : "Não foi possível registrar o usuário";
				
				this.registerUserMessage.setText(message);
				this.registerUserMessage.setVisible(true);
			}
		}
		
	}
	
	@FXML
	public void handleLogoffButton() {
		Navigation.goTo("LoginView");
	}
	
	
}

package br.ufrn.imd.controllers;

import br.ufrn.imd.database.DataBase;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.navigation.Navigation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * 
 * Controller class that will handle the actions on the LoginView
 * 
 * @author pdr_m
 *
 */
public class LoginViewController {

	/**
	 * DataBase to get the users to login
	 */
	private DataBase db = DataBase.getInstance();

	/**
	 * The user text input field
	 */
	@FXML
	private TextField userField;

	/**
	 * The password input field
	 */
	@FXML
	private PasswordField passwordField;

	/**
	 * The login button
	 */
	@FXML
	private Button loginButton;

	/**
	 * The label that will show with the success/error message
	 */
	@FXML
	private Label messageLabel;

	/**
	 * Function that will handle the login button
	 */
	@FXML
	private void handleLogin() {
		if (this.userField.getText().equals(db.getAdmin().getUsername())) {// admin
																			// logging
			if (this.passwordField.getText().equals(db.getAdmin().getPassword())) {
				this.showMessageLabel("Success!", "green");
				Navigation.goTo("AdminView");
			} else {
				this.showMessageLabel("Wrong password!", "red");
			}

		} else {
			User user = db.getUserByUsername(this.userField.getText());

			if (user != null) {// found an user with that username
				if (user.getPassword().equals(this.passwordField.getText())) {// correct
																				// password
					this.showMessageLabel("Success!", "green");

					Navigation.goTo("UserView");
				} else {
					this.showMessageLabel("Wrong password!", "red");
				}
			} else {
				this.showMessageLabel("User not found!", "red");
			}

		}
	}

	/**
	 * 
	 * Method to show the messages during the login action
	 * 
	 * @param string
	 *            the text to show
	 * @param color
	 *            the color that the text should be displayed
	 */
	private void showMessageLabel(String string, String color) {
		this.messageLabel.setText(string);
		this.messageLabel.setTextFill(Color.web(color));
		if (!this.messageLabel.isVisible()) {
			this.messageLabel.setVisible(true);
		}
	}

}

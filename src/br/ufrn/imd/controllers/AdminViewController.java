package br.ufrn.imd.controllers;

import br.ufrn.imd.navigation.Navigation;
import javafx.fxml.FXML;

public class AdminViewController {
	
	@FXML
	public void handleLogoffButton() {
		Navigation.goTo("LoginView");
	}
	
	
}

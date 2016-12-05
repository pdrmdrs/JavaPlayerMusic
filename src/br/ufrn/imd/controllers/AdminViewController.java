package br.ufrn.imd.controllers;

import java.io.File;

import br.ufrn.imd.dao.MusicDAO;
import br.ufrn.imd.dao.UserDAO;
import br.ufrn.imd.domain.Music;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.CannotDeleteMusicException;
import br.ufrn.imd.exceptions.CannotDeleteUserException;
import br.ufrn.imd.exceptions.MusicAlreadyExistsException;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;
import br.ufrn.imd.main.Main;
import br.ufrn.imd.navigation.Navigation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class AdminViewController {
	
	/**
	 * Attributes to represent the register user action
	 */
	@FXML private TextField usernameField;
	@FXML private TextField nameField;
	@FXML private PasswordField pwField;
	@FXML private PasswordField repeatPwField;
	@FXML private CheckBox isVip;
	@FXML private Label registerUserMessage;
	
	/**
	 * Attributes to represent the remove user action
	 */
	@FXML private ListView<String> userListView;
	@FXML private Label removeUserMessage;
	
	/**
	 * Attributes to represent the add music action
	 */
	@FXML private Label musicPathLabel;
	@FXML private TextField musicNameField;
	@FXML private TextField artistNameField;
	@FXML private Label addMusicMessage;
	
	@FXML private ListView<String> musicListView;
	@FXML private Label removeMusicMessage;
	
	/**
	 * Initialize the necessary data on the view
	 */
	@FXML
	public void initialize() {
		this.updateUserListView();
		this.updateMusicListView();
	}
	
	/**
	 * Method to handle the register user button
	 */
	@FXML 
	public void handleRegisterUser() {
		
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
				try {
					userDAO.removeUser(newUser);
				} catch (CannotDeleteUserException e1) {
					e1.printStackTrace();
				}
				userAdded = false;
				e.printStackTrace();
			} finally {
				
				String message = "";
				message = userAdded ? "Usuário registrado com sucesso!" : "Já existe um usuário com o mesmo login.";
				
				this.registerUserMessage.setText(message);
				this.registerUserMessage.setVisible(true);
				
				this.updateUserListView();
			}
		}
		
	}
	
	/**
	 * Method to handle the remove user button
	 */
	@FXML
	public void handleRemoveUserButton() {
		UserDAO userDAO = new UserDAO();
		boolean userRemoved = true;
		try {
			userDAO.removeUserByUsername(this.userListView.getSelectionModel().getSelectedItem());
			userRemoved = true;
		} catch (CannotDeleteUserException e) {
			userRemoved = false;
			e.printStackTrace();
		} finally {
			String message = "";
			message = userRemoved ? "Usuário removido com sucesso!" : "Não foi possível remover o usuário";
			
			this.removeUserMessage.setText(message);
			this.removeUserMessage.setVisible(true);
			
			this.updateUserListView();
		}
	}
	
	/**
	 * Method to handle the search music button
	 */
	@FXML
	public void handleSearchMusicButton() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar m�sica");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("mp3", "*.mp3"));
		File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
		this.musicPathLabel.setText(file.getAbsolutePath());
		this.musicPathLabel.setVisible(true);
	}
	
	/**
	 * Method to handle the add music button
	 */
	@FXML
	public void	handleAddMusicButton() {
		Music newMusic = new Music();
		newMusic.setDirectory(this.musicPathLabel.getText());
		newMusic.setName(this.musicNameField.getText());
		newMusic.setArtist(this.artistNameField.getText());
		
		MusicDAO musicDAO = new MusicDAO();
		boolean musicAdded = true;
		
		try {
			musicDAO.addMusic(newMusic);
			musicAdded = true;
		} catch (MusicAlreadyExistsException e) {
			musicAdded = false;
			e.printStackTrace();
		} finally {
			String message = "";
			message = musicAdded ? "Música adiciona com sucesso!" : "Já existe uma música com o mesmo nome/diretório.";
			
			this.addMusicMessage.setText(message);
			this.addMusicMessage.setVisible(true);
			
			this.updateMusicListView();
		}
	}
	
	@FXML
	public void handleRemoveMusicButton() {
		MusicDAO userDAO = new MusicDAO();
		boolean musicRemoved = true;
		try {
			userDAO.removeMusicByName(this.musicListView.getSelectionModel().getSelectedItem());
			musicRemoved = true;
		} catch (CannotDeleteMusicException e) {
			musicRemoved = false;
			e.printStackTrace();
		} finally {
			String message = "";
			message = musicRemoved ? "Música removida com sucesso!" : "Não foi possível remover a música.";
			
			this.removeMusicMessage.setText(message);
			this.removeMusicMessage.setVisible(true);
			
			this.updateMusicListView();
		}
	}
	
	/**
	 * Method to update the music list
	 */
	private void updateMusicListView() {
		this.musicListView.setItems(FXCollections.observableArrayList (new MusicDAO().getMusicNamesList()));
	}

	/**
	 * Method to handle the logoff button
	 */
	@FXML
	public void handleLogoffButton() {
		Navigation.goTo("LoginView");
	}
	
	/**
	 * Method to update the user list
	 */
	private void updateUserListView() {
		this.userListView.setItems(FXCollections.observableArrayList (new UserDAO().getUsernamesList()));
	}
}

package br.ufrn.imd.controllers;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.database.DataBase;
import br.ufrn.imd.navigation.Navigation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * 
 * Controller class that will handle the UserView and the actions on that view
 * 
 * @author pdr_m
 *
 */
public class UserViewController {

	/**
	 * Pane that will show the musics. It will be displayed on the main pane.
	 */
	@FXML
	private Pane musicsPane;

	/**
	 * Pane that will show all the playlists on the app. It will be displayed on
	 * the main pane.
	 */
	@FXML
	private Pane playListsPane;

	/**
	 * Pane that will show the actual playing/selected playlist. It will be
	 * displayed on the main pane.
	 */
	@FXML
	private Pane actualPlayListPane;

	/**
	 * Pane that will show the inputs to register a new user. It will be
	 * displayed on the main pane.
	 */
	@FXML
	private Pane registerUserPane;

	/**
	 * Pane that will show the inputs to create a new playlist. It will be
	 * displayed on the main pane.
	 */
	@FXML
	private Pane createPlayListPane;

	/**
	 * list that will contain all the sub panes that the main pane will display
	 */
	private List<Pane> panesOnMainPane = new ArrayList<>();

	/**
	 * Save the actual displayed pane on the mainPane.
	 */
	private Pane actualPane;

	/**
	 * Button that represents the Register User
	 */
	@FXML
	private Button registerUserButton;

	/**
	 * Button that represents the Create PlayList
	 */
	@FXML
	private Button createPlayListButton;

	/**
	 * Running instance of the database
	 */
	private DataBase db = DataBase.getInstance();

	/**
	 * Initialize function to load all the necessary things for the view work
	 * prorperly
	 */
	@FXML
	public void initialize() {
		this.panesOnMainPane.add(musicsPane);
		this.panesOnMainPane.add(playListsPane);
		this.panesOnMainPane.add(actualPlayListPane);
		this.panesOnMainPane.add(registerUserPane);
		this.panesOnMainPane.add(createPlayListPane);

		this.showPane(musicsPane);
	}

	/**
	 * Method that handles the music button action
	 */
	@FXML
	private void handleMusicsButton() {
		this.showPane(musicsPane);
	}

	/**
	 * Method that handles the playLists button action
	 */
	@FXML
	private void handlePlayListsButton() {
		this.showPane(playListsPane);
	}

	/**
	 * Method that handles the actual playList button action
	 */
	@FXML
	private void handleActualPlayListButton() {
		this.showPane(actualPlayListPane);
	}

	/**
	 * Method that handles the register user button action
	 */
	@FXML
	private void handleRegisterUserButton() {
		this.showPane(registerUserPane);
	}

	/**
	 * Method that handle the create playList button action
	 */
	@FXML
	private void handleCreatePlayListButton() {
		this.showPane(createPlayListPane);
	}
	
	/**
	 * Method that handle the logoff button action
	 */
	@FXML
	private void handleLogoffButton() {
		this.db.setUserLogged(null);
		
		Navigation.goTo("LoginView");
	}

	/**
	 * Method that will show the pane passed as parameter and hide all the
	 * others
	 * 
	 * @param paneToShow
	 *            the pane to show
	 */
	private void showPane(Pane paneToShow) {
		if (actualPane != paneToShow) {
			for (Pane p : this.panesOnMainPane) {
				if (p != paneToShow) {
					// p.setVisible(false);
					p.setDisable(true);
					p.setOpacity(0.0);

					// FadeTransition ft = new
					// FadeTransition(Duration.millis(200), p);
					// ft.setFromValue(1.0);
					// ft.setToValue(0.0);
					// ft.play();

					if (db.getUserLogged().isVip()) {
						this.registerUserButton.setDisable(false);
						this.createPlayListButton.setDisable(false);
					} else {
						this.registerUserButton.setDisable(true);
						this.createPlayListButton.setDisable(true);
					}

				} else if (p == paneToShow) {
					// p.setVisible(true);
					p.setDisable(false);

					FadeTransition ft = new FadeTransition(Duration.millis(200), p);
					ft.setFromValue(0.0);
					ft.setToValue(1.0);
					ft.play();

					this.actualPane = paneToShow;
				}
			}
		}
	}

}

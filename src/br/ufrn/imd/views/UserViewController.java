package br.ufrn.imd.views;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.dao.DataBaseDAO;
import br.ufrn.imd.dao.MusicDAO;
import br.ufrn.imd.dao.UserDAO;
import br.ufrn.imd.domain.Player;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.CannotDeleteUserException;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;
import br.ufrn.imd.navigation.Navigation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer.Status;
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
	 * Attributes of the music pane
	 */
	@FXML
	private ListView<String> musicListView;

	/**
	 * Attributes of the user pane
	 */
	@FXML
	private Label userPaneUserName;
	@FXML
	private Label userPaneUserIsVip;

	/**
	 * Attributes of the player pane
	 */
	@FXML
	private Label playerPaneMusicName;
	@FXML
	private Label playerPaneArtistName;

	/**
	 * Attributes of the register user pane
	 */
	@FXML
	private TextField usernameField;
	@FXML
	private TextField nameField;
	@FXML
	private PasswordField pwField;
	@FXML
	private PasswordField repeatPwField;
	@FXML
	private CheckBox isVip;
	@FXML
	private Label registerUserMessage;

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
	 * DataBaseDAO to access the DataBase
	 */
	private DataBaseDAO dbDAO = new DataBaseDAO();

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

		this.userPaneUserName.setText(dbDAO.getUserLogged().getUsername());
		this.userPaneUserIsVip.setVisible(dbDAO.getUserLogged().isVip());

		this.playerPaneMusicName.setText(Player.getInstance().getMusicPlaying().getName());
		this.playerPaneArtistName.setText(Player.getInstance().getMusicPlaying().getArtist());

		this.updateMusicListView();

		this.showPane(musicsPane);
	}

	/**
	 * update the music list
	 */
	private void updateMusicListView() {
		this.musicListView.setItems(FXCollections.observableArrayList(new MusicDAO().getMusicNamesList()));
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
		this.dbDAO.setUserLogged(null);

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
					p.setDisable(true);
					p.setOpacity(0.0);

					if (dbDAO.getUserLogged().isVip()) {
						this.registerUserButton.setDisable(false);
						this.createPlayListButton.setDisable(false);
					} else {
						this.registerUserButton.setDisable(true);
						this.createPlayListButton.setDisable(true);
					}

				} else if (p == paneToShow) {
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

	/**
	 * handle the play button on the player pane
	 */
	@FXML
	public void handlePlayButton() {
		if (Player.getInstance().getPlayer().getStatus().equals(Status.PLAYING)) {
			Player.getInstance().pause();
		} else {
			Player.getInstance().play();

		}
	}

	/**
	 * handle the register button action on the register user pane
	 */
	@FXML
	public void handleRegisterUserPaneRegisterUserButton() {
		boolean canAddUser = true;

		User newUser = new User();

		newUser.setUsername(this.usernameField.getText());
		newUser.setName(this.nameField.getText());

		if (!this.pwField.getText().equals(this.repeatPwField.getText()) && canAddUser) {
			canAddUser = false;
		} else {
			newUser.setPassword(this.pwField.getText());
		}

		if (canAddUser) {
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
			}
		}
	}
}

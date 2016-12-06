package br.ufrn.imd.views;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.dao.DataBaseDAO;
import br.ufrn.imd.dao.MusicDAO;
import br.ufrn.imd.dao.PlaylistDAO;
import br.ufrn.imd.dao.UserDAO;
import br.ufrn.imd.domain.Music;
import br.ufrn.imd.domain.PlayList;
import br.ufrn.imd.domain.Player;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.CannotDeleteUserException;
import br.ufrn.imd.exceptions.PlayListAlreadyExistsException;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;
import br.ufrn.imd.main.Main;
import br.ufrn.imd.navigation.Navigation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private Label registerUserMessage;

	/**
	 * Pane that will show the inputs to create a new playlist. It will be
	 * displayed on the main pane.
	 */
	@FXML
	private Pane createPlayListPane;
	/**
	 * Attributes of the create playlist pane
	 */
	@FXML
	private TextField playlistPanePlaylistNameField;
	@FXML
	private ListView<String> playlistPaneMusicsList;
	@FXML
	private ListView<String> playlistPaneMusicsAddedList;
	@FXML
	private Label playlistPaneMessage;

	/**
	 * Represents the playlist on the playlists scene
	 */
	@FXML
	private ListView<String> playlistsList;

	/**
	 * Represents the music list on the actual play list scene
	 */
	@FXML
	private ListView<String> actualPlayList;

	/**
	 * Represents the play button on the player
	 */
	@FXML
	private Button playButton;

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

		this.updateMusicListView();

		this.updatePlaylistsList();

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
	public void handleMusicsButton() {
		this.showPane(musicsPane);
	}

	/**
	 * Method that handles the playLists button action
	 */
	@FXML
	public void handlePlayListsButton() {
		this.showPane(playListsPane);
	}

	/**
	 * Method that handles the actual playList button action
	 */
	@FXML
	public void handleActualPlayListButton() {
		this.showPane(actualPlayListPane);
	}

	/**
	 * Method that handles the register user button action
	 */
	@FXML
	public void handleRegisterUserButton() {
		this.showPane(registerUserPane);
	}

	/**
	 * Method that handle the create playList button action
	 */
	@FXML
	public void handleCreatePlayListButton() {
		this.updatePlaylistPaneMusicListView();
		this.showPane(createPlayListPane);
	}

	/**
	 * Method that handle the logoff button action
	 */
	@FXML
	public void handleLogoffButton() {
		this.dbDAO.setUserLogged(null);
		if (Main.player != null)
			Main.player.getPlayer().stop();
		Navigation.goTo("LoginView");
	}

	/**
	 * Method that will show the pane passed as parameter and hide all the
	 * others
	 * 
	 * @param paneToShow
	 *            the pane to show
	 */
	public void showPane(Pane paneToShow) {
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
		if (Main.player.getPlayer().getStatus().equals(Status.PLAYING)) {
			this.playButton.setText("||");
			Main.player.pause();
		} else {
			this.playButton.setText("|>");
			Main.player.play();
		}
	}

	/**
	 * handle the play music button on the musics pane
	 */
	@FXML
	public void handlePlayMusicButton() {
		this.changePlayerMusic(new MusicDAO().getMusicByName(this.musicListView.getSelectionModel().getSelectedItem()));
	}

	/**
	 * change the music playing on the player
	 * 
	 * @param music
	 *            the new music to be played
	 */
	private void changePlayerMusic(Music music) {
		if (Main.player != null) {
			Main.player.stop();
		}

		this.playerPaneMusicName.setText(music.getName());
		this.playerPaneArtistName.setText(music.getArtist());

		Main.player = new Player();

		Main.player.setMusicPlaying(music);

		Main.player.createInstance();

		Main.player.play();
	}

	/**
	 * Handle the select music on the actual play list scene
	 */
	@FXML
	public void handleActualPlaylistPlayMusicButton() {
		this.changePlayerMusic(
				new MusicDAO().getMusicByName(this.actualPlayList.getSelectionModel().getSelectedItem()));
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

	/**
	 * Method to handle the create playlist button
	 */
	@FXML
	public void handlePlaylistPaneRegisterPlaylistButton() {

		PlayList newPlaylist = new PlayList();
		newPlaylist.setName(this.playlistPanePlaylistNameField.getText());

		MusicDAO musicDAO = new MusicDAO();

		List<Music> content = musicDAO.getMusicsByName(this.playlistPaneMusicsAddedList.getItems());

		newPlaylist.setContent(content);

		newPlaylist.setOwner(dbDAO.getUserLogged());

		PlaylistDAO playlistDAO = new PlaylistDAO();

		boolean playlistAdded = true;

		try {
			playlistDAO.addNewPlaylist(newPlaylist);
		} catch (PlayListAlreadyExistsException e) {
			playlistAdded = false;
			e.printStackTrace();
		} finally {
			this.updatePlaylistsList();

			String message = playlistAdded ? "Playlist criada com sucesso!" : "Não foi possível criar a playlist.";

			this.playlistPaneMessage.setText(message);
			this.playlistPaneMessage.setVisible(true);
		}

	}

	/**
	 * handle the add music on the create new play list scene
	 */
	@FXML
	public void handlePlaylistPaneAddMusicButton() {
		List<String> musicsAddeds = this.playlistPaneMusicsAddedList.getItems();

		musicsAddeds.add(this.playlistPaneMusicsList.getSelectionModel().getSelectedItem());

		this.updateMusicAddedListView(musicsAddeds);

	}

	/**
	 * handle the remove music on the create new play list scene
	 */
	@FXML
	public void handlePlaylistPaneRemoveMusicButton() {
		List<String> musicsAddeds = this.playlistPaneMusicsAddedList.getItems();

		musicsAddeds.remove(this.playlistPaneMusicsAddedList.getSelectionModel().getSelectedIndex());

		this.updateMusicAddedListView(musicsAddeds);
	}

	/**
	 * Update the musics added list on the create new play list scene
	 * 
	 * @param items
	 *            the list of items to populate the list
	 */
	private void updateMusicAddedListView(List<String> items) {
		this.playlistPaneMusicsAddedList.setItems(FXCollections.observableArrayList(items));
	}

	/**
	 * Update the musics list on the create new play list scene
	 */
	private void updatePlaylistPaneMusicListView() {
		this.playlistPaneMusicsList.setItems(FXCollections.observableArrayList(new MusicDAO().getMusicNamesList()));
	}

	/**
	 * Update the play list on the playlist scene
	 */
	private void updatePlaylistsList() {
		this.playlistsList.setItems(FXCollections.observableArrayList(new PlaylistDAO().getPlaylistsNames()));
	}

	/**
	 * handle the select play list on the playlist scene
	 */
	@FXML
	public void handlePlaylistPaneSelectPlaylistButton() {
		PlayList selectedPlaylist = new PlaylistDAO()
				.getPlaylistByName(this.playlistsList.getSelectionModel().getSelectedItem());
		List<String> musicsFromSelectedPlaylist = new ArrayList<>();

		for (Music m : selectedPlaylist.getContent()) {
			musicsFromSelectedPlaylist.add(m.getName());
		}

		this.actualPlayList.setItems(FXCollections.observableArrayList(musicsFromSelectedPlaylist));
	}
}

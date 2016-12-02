package br.ufrn.imd.io;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.ufrn.imd.dao.MusicDAO;
import br.ufrn.imd.dao.PlaylistDAO;
import br.ufrn.imd.dao.UserDAO;
import br.ufrn.imd.domain.Music;
import br.ufrn.imd.domain.PlayList;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.utils.Constants;

/**
 * 
 * Class that represents a file reader (JSON)
 * 
 * @author Pedro Paulo
 *
 */
public class ReaderObject {

	/**
	 * The only running instance of the ReaderObject
	 */
	private static ReaderObject instance = new ReaderObject();

	/**
	 * The private constructor
	 */
	private ReaderObject() {
	}

	/**
	 * Get the running instance of the object
	 * @return the instance of this object
	 */
	public static ReaderObject getInstance() {
		return ReaderObject.instance;
	}

	/**
	 * Method that will read the musics json file
	 * @return the list of musics
	 */
	public List<Music> readMusicsFile() {

		List<Music> musicsOnFile = new ArrayList<>();

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(Constants.MUSICS_FILE_PATH));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray musicsArray = (JSONArray) jsonObject.get("musics");

			for(int i = 0; i < musicsArray.size(); i++) {
				JSONObject musicObject = (JSONObject) musicsArray.get(i);
				
				Music music = new Music();
				music.setName(musicObject.get("name").toString());
				music.setArtist(musicObject.get("artist").toString());
				music.setDirectory(musicObject.get("directory").toString());
				
				musicsOnFile.add(music);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return musicsOnFile;
	}

	/**
	 * Method that will read the users json file
	 * @return the list of users
	 */
	public List<User> readUsersFile() {
		List<User> usersOnFile = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(Constants.USERS_FILE_PATH));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray usersArray = (JSONArray) jsonObject.get("users");

			for(int i = 0; i < usersArray.size(); i++) {
				JSONObject userObject = (JSONObject) usersArray.get(i);
				
				User user = new User();
				
				user.setId((long) userObject.get("id"));
				user.setUsername(userObject.get("username").toString());
				user.setPassword(userObject.get("password").toString());
				user.setName(userObject.get("name").toString());
				user.setVip(userObject.get("vip").toString().equals("true"));
				
				usersOnFile.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usersOnFile;
	}
	
	/**
	 * Method that will read the playlists json file
	 * @return the list of playlists
	 */
	public List<PlayList> readPlaylistsFile() {
		List<PlayList> playlistsOnFile = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(Constants.PLAYLISTS_FILE_PATH));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray playlists = (JSONArray) jsonObject.get("playlists");
			
			UserDAO userDAO = new UserDAO();

			for(int i = 0; i < playlists.size(); i++) {
				JSONObject playlistObject = (JSONObject) playlists.get(i);
				
				PlayList playlist = new PlayList();
				
				playlist.setName(playlistObject.get("name").toString());
				playlist.setContent(readPlaylistFile(playlistObject.get("directory").toString()));
				playlist.setOwner(userDAO.getUserById((int) playlistObject.get("userId")));
				
				playlistsOnFile.add(playlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return playlistsOnFile;
	}

	/**
	 * Method that will read the playlist json file
	 * @return the list of musics on one playlist
	 */
	private List<Music> readPlaylistFile(String directory) {
		List<Music> musicsOnFile = new ArrayList<>();

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(directory));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray musicsArray = (JSONArray) jsonObject.get("content");

			for(int i = 0; i < musicsArray.size(); i++) {
				JSONObject musicObject = (JSONObject) musicsArray.get(i);
				
				Music music = new Music();
				music.setName(musicObject.get("name").toString());
				music.setArtist(musicObject.get("artist").toString());
				music.setDirectory(musicObject.get("directory").toString());
				
				musicsOnFile.add(music);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return musicsOnFile;
	}

	/**
	 * Method to read all the files and populate the database
	 */
	public void readFiles() {
		// TODO Auto-generated method stub
		MusicDAO musicDAO = new MusicDAO();
		musicDAO.setMusics(this.readMusicsFile());
		
		PlaylistDAO playlistDAO = new PlaylistDAO();
		playlistDAO.setPlaylists(this.readPlaylistsFile());
		
		UserDAO userDAO = new UserDAO();
		userDAO.setUsers(this.readUsersFile());
	}
}

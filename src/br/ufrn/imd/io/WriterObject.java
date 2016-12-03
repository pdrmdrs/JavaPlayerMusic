package br.ufrn.imd.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.ufrn.imd.dao.MusicDAO;
import br.ufrn.imd.dao.PlaylistDAO;
import br.ufrn.imd.dao.UserDAO;
import br.ufrn.imd.domain.Music;
import br.ufrn.imd.domain.PlayList;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.utils.Constants;
import br.ufrn.imd.utils.ValidatePath;

/**
 * 
 * Class that represents the file writer (JSON)
 * 
 * @author pdr_m
 *
 */
public class WriterObject {
	/**
	 * The only running instance of the WriterObject
	 */
	private static WriterObject instance = new WriterObject();

	/**
	 * The private constructor
	 */
	private WriterObject() {
	}

	/**
	 * Get the running instance of the object
	 * @return the instance of this object
	 */
	public static WriterObject getInstance() {
		return WriterObject.instance;
	}

	/**
	 * Method that write all the musics on a json file
	 */
	@SuppressWarnings("unchecked")
	public void writeMusicsFile() {

		JSONObject jsonObject = new JSONObject();

		JSONArray musicsJsonArray = new JSONArray();

		MusicDAO musicDAO = new MusicDAO();
		
		List<Music> musicsOnDataBase = musicDAO.getMusics();

		for (Music m : musicsOnDataBase) {

			JSONObject musicJsonObject = new JSONObject();

			musicJsonObject.put("name", m.getName());
			musicJsonObject.put("artist", m.getArtist());
			musicJsonObject.put("directory", m.getDirectory());

			musicsJsonArray.add(musicJsonObject);
		}

		jsonObject.put("musics", musicsJsonArray);

		FileWriter writeFile = null;

		try {
			writeFile = new FileWriter(ValidatePath.validate(Constants.MUSICS_FILE_PATH));
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Method that write all the users on a json file
	 */
	@SuppressWarnings("unchecked")
	public void writeUsersFile() {
		
		JSONObject jsonObject = new JSONObject();
		
		JSONArray usersJsonArray = new JSONArray();
		
		UserDAO userDAO = new UserDAO();
		
		List<User> usersOnDataBase = userDAO.getUsers();
		
		for (User u : usersOnDataBase) {
			JSONObject userJsonObject = new JSONObject();
			
			userJsonObject.put("id", u.getId());
			userJsonObject.put("username", u.getUsername());
			userJsonObject.put("password", u.getPassword());
			userJsonObject.put("name", u.getName());
			userJsonObject.put("vip", u.isVip());
			
			usersJsonArray.add(userJsonObject);
		}
		
		jsonObject.put("users", usersJsonArray);
		
		FileWriter writeFile = null;
		
		try {
			writeFile = new FileWriter(ValidatePath.validate(Constants.USERS_FILE_PATH));
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method that write all the playlists on a json file
	 */
	@SuppressWarnings("unchecked")
	public void writePlaylistsFile() {
		JSONObject jsonObject = new JSONObject();

		JSONArray playlistsJsonArray = new JSONArray();
		
		PlaylistDAO playlistDAO = new PlaylistDAO();

		List<PlayList> playlistsOnDataBase = playlistDAO.getPlaylists();

		for (PlayList p : playlistsOnDataBase) {
			JSONObject playlistJsonObject = new JSONObject();

			playlistJsonObject.put("name", p.getName());
			playlistJsonObject.put("userId", p.getOwner().getId());
			playlistJsonObject.put("directory", Constants.USERS_PLAYLISTS_PATH + p.getName() + ".json");
			
			writePlaylistFile(p);
			
			playlistsJsonArray.add(playlistJsonObject);
		}

		jsonObject.put("playlists", playlistsJsonArray);

		FileWriter writeFile = null;

		try {
			writeFile = new FileWriter(ValidatePath.validate(Constants.PLAYLISTS_FILE_PATH));
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that write all the musics on a single playlist on a json file
	 */
	@SuppressWarnings("unchecked")
	private void writePlaylistFile(PlayList playList) {
		JSONObject jsonObject = new JSONObject();
		
		JSONArray musicsJsonArray = new JSONArray();
		
		List<Music> content = playList.getContent();
		
		for (Music m : content) {
			JSONObject musicJsonObject = new JSONObject();

			musicJsonObject.put("name", m.getName());
			musicJsonObject.put("artist", m.getArtist());
			musicJsonObject.put("directory", m.getDirectory());

			musicsJsonArray.add(musicJsonObject);
		}
		
		jsonObject.put("name", playList.getName());
		jsonObject.put("userId", playList.getOwner().getId());
		jsonObject.put("content", musicsJsonArray);

		FileWriter writeFile = null;

		try {
			writeFile = new FileWriter(ValidatePath.validate(Constants.USERS_PLAYLISTS_PATH + playList.getName() + ".json"));
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Method to write all the files
	 */
	public void saveFiles() {
		this.writeMusicsFile();
		this.writePlaylistsFile();
		this.writeUsersFile();
	}
}

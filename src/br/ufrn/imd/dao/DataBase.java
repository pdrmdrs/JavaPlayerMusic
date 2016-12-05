package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.domain.Music;
import br.ufrn.imd.domain.PlayList;
import br.ufrn.imd.domain.User;

/**
 * 
 * Singleton class that will represent the DataBase of the running instance of
 * the app
 * 
 * @author pdr_m
 *
 */
public class DataBase {

	/**
	 * String to represent the
	 */
	private final User ADMIN = new User("admin", "secret");

	/**
	 * List to represents all the users
	 */
	private List<User> users = new ArrayList<>(); // TODO: change this to BST

	/**
	 * List to save all the musics on the app
	 */
	private List<Music> musics = new ArrayList<>();

	/**
	 * List to save all the playlists
	 */
	private List<PlayList> playlists = new ArrayList<>();

	/**
	 * DataBase attribute representing the only running instance of this class
	 */
	private static DataBase instance = new DataBase();

	/**
	 * User that is logged in
	 */
	private User userLogged;

	/**
	 * Private constructor, since this is a singleton class
	 */
	private DataBase() {

	}

	/**
	 * Get the admin user object
	 * 
	 * @return the admin user
	 */
	public User getAdmin() {
		return ADMIN;
	}

	/**
	 * Get the users list
	 * 
	 * @return the users list
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Set the users list
	 * 
	 * @param users
	 *            to the users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Method to get the instance of this class
	 * 
	 * @return the only running instance of this class
	 */
	public static DataBase getInstance() {
		return DataBase.instance;
	}

	/**
	 * Get the logged user
	 * 
	 * @return the logged user
	 */
	public User getUserLogged() {
		return userLogged;
	}

	/**
	 * Set the logged user
	 * 
	 * @param userLogged
	 *            the logged user to set
	 */
	public void setUserLogged(User userLogged) {
		this.userLogged = userLogged;
	}

	/**
	 * Get the musics list
	 * 
	 * @return the musics list
	 */
	public List<Music> getMusics() {
		return this.musics;
	}

	/**
	 * Get the playlists list
	 * 
	 * @return the playlists list
	 */
	public List<PlayList> getPlaylists() {
		return this.playlists;
	}

	/**
	 * Set the musics list
	 * 
	 * @param list
	 *            the musics list to set
	 */
	public void setMusics(List<Music> list) {
		this.musics = list;
	}

	/**
	 * Set the playlists list
	 * 
	 * @param list
	 *            the playlists list to set
	 */
	public void setPlaylists(List<PlayList> list) {
		this.playlists = list;
	}
}

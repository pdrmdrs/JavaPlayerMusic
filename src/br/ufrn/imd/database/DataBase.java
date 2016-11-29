package br.ufrn.imd.database;

import java.util.ArrayList;
import java.util.List;

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
	 * DataBase attribute representing the only running instance of this class
	 */
	private static DataBase instance = new DataBase();

	/**
	 * Private constructor, since this is a singleton class
	 */
	private DataBase() {
		// TODO: remove this !!!!!!!!!!!!!!! only to test the users
		for (int i = 0; i < 10; i++) {
			this.users.add(new User("user" + i, "123"));
		}
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
	 * Find the user by the username
	 * 
	 * @param username
	 *            to find on the list
	 * @return the user found
	 */
	public User findUserByUsername(String username) {
		for (User u : this.users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

}

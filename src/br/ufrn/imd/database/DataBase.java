package br.ufrn.imd.database;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.dao.UserDAO;
import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.CannotDeleteUserException;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;

/**
 * 
 * Singleton class that will represent the DataBase of the running instance of
 * the app
 * 
 * @author pdr_m
 *
 */
public class DataBase implements UserDAO {

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
	 * Get the users list
	 * 
	 * @return the users list
	 */
	@Override
	public List<User> getAllUsers() {
		return users;
	}

	/**
	 * Find the user by the id
	 * 
	 * @param id
	 *            to find on the list
	 * @return the user found
	 */
	@Override
	public User getUserById(int id) {
		for (User u : this.users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	/**
	 * Find the user by the username
	 * 
	 * @param username
	 *            to find on the list
	 * @return the user found
	 */
	@Override
	public User getUserByUsername(String username) {
		for (User u : this.users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * Find the user by the name
	 * 
	 * @param name
	 *            to find on the list
	 * @return the user found
	 */
	@Override
	public User getUserByName(String name) {
		for (User u : this.users) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * Add a new user in the database. If the user already exists, throws
	 * exception
	 * 
	 * @param user
	 *            to add on the database
	 * @throws UserAlreadyExistsException
	 */
	@Override
	public void addUser(User user) throws UserAlreadyExistsException {

		boolean userFound = false;

		for (User u : this.users) {
			if (u.equals(user) && userFound == false) {
				userFound = true;
			}
		}

		if (!userFound) {
			this.users.add(user);
		} else {
			throw new UserAlreadyExistsException();
		}

	}

	/**
	 * Delete a user in the database If the user doesn't exits, throws exception
	 * 
	 * @param user
	 *            to delete on the user
	 * @throws CannotDeleteUserException
	 */
	@Override
	public void deleteUser(User user) throws CannotDeleteUserException {

		boolean userDeleted = false;

		for (User u : this.users) {
			if (u.equals(user)) {
				this.users.remove(user);
				userDeleted = true;
			}
		}

		if (!userDeleted) {
			throw new CannotDeleteUserException();
		}
	}

}

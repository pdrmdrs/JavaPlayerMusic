package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.CannotDeleteUserException;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;

/**
 * 
 * DAO of the User class
 * 
 * @author Pedro Paulo
 *
 */
public class UserDAO {

	/**
	 * An instance of the running DataBase
	 */
	private DataBase db = DataBase.getInstance();

	/**
	 * Method to find an user by the id
	 * 
	 * @param id
	 *            the id of the user
	 * @return the user found
	 */
	public User getUserById(int id) {
		for (User u : this.getUsers()) {
			if (u.getId() == id)
				return u;
		}
		return null;
	}

	/**
	 * Get a list of users
	 * 
	 * @return the list of users on the db
	 */
	public List<User> getUsers() {
		return this.db.getUsers();
	}

	/**
	 * Get the user by username
	 * 
	 * @param username
	 *            the username to find
	 * @return the user found
	 */
	public User getUserByUsername(String username) {
		for (User u : this.getUsers()) {
			if (u.getUsername().equals(username))
				return u;
		}
		return null;
	}

	/**
	 * Add a user in the database. If the user already exists, throws exception
	 * 
	 * @param user
	 *            to add on the database
	 * @throws UserAlreadyExistsException
	 *             exception to show that the user already exists
	 */
	public void addUser(User user) throws UserAlreadyExistsException {
		boolean userFound = false;

		if (this.getUsers().contains(user))
			userFound = true;

		if (!userFound) {
			this.getUsers().add(user);
		} else {
			throw new UserAlreadyExistsException();
		}

	}

	/**
	 * Set the users list
	 * 
	 * @param list
	 *            the list of users
	 */
	public void setUsers(List<User> list) {
		this.db.setUsers(list);
	}

	/**
	 * Add a newly created user in the database. If the user already exists,
	 * throws exception
	 * 
	 * @param user
	 *            to add on the database
	 * @throws UserAlreadyExistsException
	 *             exception to show that the user already exists
	 */
	public void addNewUser(User user) throws UserAlreadyExistsException {
		boolean userFound = false;

		for (Iterator<User> iterator = this.getUsers().iterator(); iterator.hasNext();) {
			User usr = iterator.next();
			if (!userFound) {
				if (usr.getUsername().equals(user.getUsername()) || usr.getId() == user.getId()) {
					userFound = true;
				}
			}
		}

		if (!userFound) {
			this.addUser(user);
		} else {
			throw new UserAlreadyExistsException();
		}

	}

	/**
	 * 
	 * Remove a user. If he not exists on the DataBase, throw exception.
	 * 
	 * @param user
	 *            the user to remove
	 * @throws CannotDeleteUserException
	 *             exception to show that the user couldn't be deleted
	 */
	public void removeUser(User user) throws CannotDeleteUserException {
		boolean userFound = false;

		if (this.getUsers().contains(user))
			userFound = true;

		if (!userFound) {
			this.getUsers().remove(user);
		} else {
			throw new CannotDeleteUserException("Usuário não existe.");
		}

	}

	/**
	 * Remove a user by your username
	 * 
	 * @param username
	 *            the username of the user to delete
	 * @throws CannotDeleteUserException
	 *             exception to show that the user couldn't be deleted
	 */
	public void removeUserByUsername(String username) throws CannotDeleteUserException {

		boolean userRemoved = false;

		for (Iterator<User> iterator = this.getUsers().iterator(); iterator.hasNext();) {
			User usr = iterator.next();
			if (!userRemoved) {
				if (usr.getUsername().equals(username)) {
					iterator.remove();
					userRemoved = true;
				}
			}
		}

		if (!userRemoved)
			throw new CannotDeleteUserException();

	}

	/**
	 * Method to return the username list of the users list
	 * @return the list with usernames of the users
	 */
	public List<String> getUsernamesList() {

		List<String> result = new ArrayList<>();

		for (User u : this.getUsers()) {
			result.add(u.getUsername());
		}

		return result;
	}

}

package br.ufrn.imd.dao;

import java.util.Iterator;
import java.util.List;

import br.ufrn.imd.domain.User;
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
		for (User u : this.db.getUsers()) {
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
		for (User u : this.db.getUsers()) {
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

		for (User u : this.db.getUsers()) {
			if (u.equals(user) && userFound == false) {
				userFound = true;
			}
		}

		if (!userFound) {
			this.db.getUsers().add(user);
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
		
		for(Iterator<User> iterator = this.db.getUsers().iterator(); iterator.hasNext();){
			User usr = iterator.next();
			if (!userFound) {
				if (usr.getUsername().equals(user.getUsername()) || usr.getId() == user.getId()) {
					userFound = true;
				}
			}
		}
		
		if(!userFound) {
			this.addUser(user);
		} else {
			throw new UserAlreadyExistsException();
		}
		
	}

	public void removeUser(User newUser) {
		//TODO: implement remove user funcionality
	}

	// public User getUserByName(String name);
	// public void addUser(User user) throws UserAlreadyExistsException;
	// public void deleteUser(User user) throws CannotDeleteUserException;
}

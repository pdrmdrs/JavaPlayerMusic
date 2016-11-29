package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.domain.User;
import br.ufrn.imd.exceptions.CannotDeleteUserException;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;

/**
 * 
 * DAO interface of the User class
 * 
 * @author Pedro Paulo
 *
 */
public interface UserDAO{
	public List<User> getAllUsers();
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public User getUserByName(String name);
	public void addUser(User user) throws UserAlreadyExistsException;
	public void deleteUser(User user) throws CannotDeleteUserException;
}

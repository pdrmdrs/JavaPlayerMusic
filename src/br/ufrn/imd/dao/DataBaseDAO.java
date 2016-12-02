package br.ufrn.imd.dao;

import br.ufrn.imd.domain.User;

/**
 * 
 * DAO of the DataBase class
 * 
 * @author pdr_m
 *
 */
public class DataBaseDAO {
	/**
	 * DataBase to get the users to login
	 */
	private DataBase db = DataBase.getInstance();
	
	/**
	 * Get the admin user on the DataBase
	 * @return the admin user on the DataBase
	 */
	public User getAdmin() {
		return this.db.getAdmin();
	}

	/**
	 * Set the logged user
	 * @param user the user logged
	 */
	public void setUserLogged(User user) {
		this.db.setUserLogged(user);
	}

	/**
	 * Get the logged user
	 * @return the logged user
	 */
	public User getUserLogged() {
		return this.db.getUserLogged();
	}
	
	
}

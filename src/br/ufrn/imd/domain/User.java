package br.ufrn.imd.domain;

/**
 * 
 * Class that represents an user of the app
 * 
 * @author pdr_m
 *
 */
public class User {

	/**
	 * String that represents the username of the user
	 */
	private String username;

	/**
	 * String that represents the password of the user
	 */
	private String password;

	/**
	 * String that represents the name of the user
	 */
	private String name;

	/**
	 * Boolean that tells if the user is vip or not
	 */
	private boolean Vip;

	/**
	 * Constructor that receives only the username and password Its the basic
	 * constructor, since the user should have at least one username and one
	 * password
	 * 
	 * @param username
	 *            the username of the user
	 * @param password
	 *            the password of the user
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Constructor that receives if the user is vip or not
	 * 
	 * @param username
	 *            the username of the user
	 * @param password
	 *            the password of the user
	 * @param isVip
	 *            the user is vip or not
	 */
	public User(String username, String password, boolean isVip) {
		this(username, password);
		this.Vip = isVip;
	}

	/**
	 * Constructor that receives the name of the user
	 * 
	 * @param username
	 *            the username of the user
	 * @param password
	 *            the password of the user
	 * @param isVip
	 *            the user is vip or not
	 * @param name
	 *            the name of the user
	 */
	public User(String username, String password, boolean isVip, String name) {
		this(username, password, isVip);
		this.name = name;
	}

	
	/**
	 * Get the username of the user
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username of the user
	 * @param username the username of the user
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the password of the user
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password of the user
	 * @param password the password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the name of the user
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the user
	 * @param name the name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Tells if the user is Vip or not
	 * @return the vip status of the user
	 */
	public boolean isVip() {
		return Vip;
	}

	/**
	 * Set the vip status of the user
	 * @param vip the vip status of the user
	 */
	public void setVip(boolean vip) {
		Vip = vip;
	}

}

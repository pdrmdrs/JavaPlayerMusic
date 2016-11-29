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
	 * Int to represent the id counter of the users
	 */
	private static int idCounter = 0;
	
	/**
	 * Int to represent the id of the user
	 */
	private int id;

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
		this.id = User.idCounter;
		User.idCounter++;
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
	 * Get the id counter of the users
	 * @return the id counter
	 */
	public static int getIdCounter() {
		return idCounter;
	}

	/**
	 * Set the id counter to the users
	 * @param idCounter to the idCounter
	 */
	public static void setIdCounter(int idCounter) {
		User.idCounter = idCounter;
	}

	/**
	 * Get the Id of the user
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id of the user
	 * @param id to the id of the user
	 */
	public void setId(int id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (Vip ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Vip != other.Vip)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
}

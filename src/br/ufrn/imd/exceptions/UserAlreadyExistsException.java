package br.ufrn.imd.exceptions;

public class UserAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5857930545947880630L;
	
	public UserAlreadyExistsException() {
		super("User already exists on the DataBase");
	}
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}

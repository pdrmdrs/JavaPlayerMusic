package br.ufrn.imd.exceptions;

public class CannotDeleteUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4167760247861685120L;

	public CannotDeleteUserException() {
		super("Cannot delete the user");
	}
	
	public CannotDeleteUserException(String message) {
		super(message);
	}
}

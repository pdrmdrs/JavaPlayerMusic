package br.ufrn.imd.exceptions;

public class MusicAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021461811088807558L;

	public MusicAlreadyExistsException() {
		super("Music already exists on the DataBase");
	}
	
	public MusicAlreadyExistsException(String message) {
		super(message);
	}
}

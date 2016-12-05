package br.ufrn.imd.exceptions;

public class PlayListAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 708537635308160247L;
	public PlayListAlreadyExistsException() {
		super("This playlist already exists");
	}
	
	public PlayListAlreadyExistsException(String message) {
		super(message);
	}
	
}

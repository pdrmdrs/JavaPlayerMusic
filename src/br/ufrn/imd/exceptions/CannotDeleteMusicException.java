package br.ufrn.imd.exceptions;

public class CannotDeleteMusicException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7354397664708359259L;

	public CannotDeleteMusicException() {
		super("Cannot delete the music");
	}
	
	public CannotDeleteMusicException(String message) {
		super(message);
	}
}

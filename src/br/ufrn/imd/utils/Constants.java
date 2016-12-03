package br.ufrn.imd.utils;

/**
 * 
 * Class that will save all the constant utils used on the app
 * 
 * @author pdr_m
 *
 */
public class Constants {
	
	/**
	 * Constant that will handle the right separator for the right OS
	 */
	public static final String SEPARATOR = System.getProperty("os.name").startsWith("Windows") ? "\\" : "/";
	
	/**
	 * Constant that will handle the main folder to put the auxiliar files
	 */
	public static final String PROJECT_FOLDER_NAME = "JavaMusicPlayer";
	
	/**
	 * Constant that will handle the auxiliar files folder path
	 */
	public static final String FOLDERS_PATH = System.getProperty("user.home") + Constants.SEPARATOR + Constants.PROJECT_FOLDER_NAME + Constants.SEPARATOR + "files" + Constants.SEPARATOR;
	
	/**
	 * Constant that will handle the musics file path
	 */
	public static final String MUSICS_FILE_PATH = Constants.FOLDERS_PATH + "musics.json";
	
	/**
	 * Constant that will handle the list of playlists file path
	 */
	public static final String PLAYLISTS_FILE_PATH = Constants.FOLDERS_PATH + "playlists.json";
	
	/**
	 * Constant that will handle the users file path
	 */
	public static final String USERS_FILE_PATH = Constants.FOLDERS_PATH + "users.json";
	
	/**
	 * Constant that will handle the users playlist prefix path
	 */
	public static final String USERS_PLAYLISTS_PATH = Constants.FOLDERS_PATH + "playlists" + Constants.SEPARATOR;
}

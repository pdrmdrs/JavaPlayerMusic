package br.ufrn.imd.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class that represents the playlist
 * 
 * @author Pedro Paulo
 *
 */
public class PlayList {
	
	/**
	 * String that represents the playlist's name
	 */
	private String name;

	/**
	 * Lists that represents the content of the playlist
	 */
	private List<Music> content = new ArrayList<>();

	/**
	 * The owner user of the playlist
	 */
	private User owner;
	
	/**
	 * Empty constructor
	 */
	public PlayList() {
		
	}
	
	/**
	 * Constructor of the playlist
	 * @param name the name of the playlist
	 * @param content the content of the playlist
	 * @param owner the owner of the playlist
	 */
	public PlayList(String name, List<Music> content, User owner) {
		super();
		this.name = name;
		this.content = content;
		this.owner = owner;
	}

	/**
	 * Get the playlist's name
	 * @return the playlits's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the playlist's name
	 * @param name the playlist's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the playlist's owner
	 * @return the owner of the playlist
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Set the playlist's owner
	 * @param owner the owner of the playlist
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Get the content of the playlist
	 * @return the content of the playlist
	 */
	public List<Music> getContent() {
		return content;
	}

	/**
	 * Set the content of the playlist
	 * @param content the content of the playlist
	 */
	public void setContent(List<Music> content) {
		this.content = content;
	}
}

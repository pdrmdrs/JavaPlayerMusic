package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.domain.Music;
import br.ufrn.imd.exceptions.MusicAlreadyExistsException;
import br.ufrn.imd.exceptions.UserAlreadyExistsException;
import javafx.util.Callback;

/**
 * 
 * DAO of the Music class
 * 
 * @author pdr_m
 *
 */
public class MusicDAO {
	
	/**
	 * An instance of the running DataBase
	 */
	private DataBase db = DataBase.getInstance();

	/**
	 * Get a list of musics
	 * @return the list of musics on the database
	 */
	public List<Music> getMusics() {
		return this.db.getMusics();
	}
	
	/**
	 * Set the list of the musics
	 * @param list the list of the musics
	 */
	public void setMusics(List<Music> list) {
		this.db.setMusics(list);
	}
 
	/**
	 * Method to add a music
	 * @param music
	 * @throws MusicAlreadyExistsException
	 */
	public void addMusic(Music music) throws MusicAlreadyExistsException {
		
		boolean musicFound = false;
		
		if(this.getMusics().contains(music)) 
			musicFound = true;
		
		if (!musicFound) {
			this.getMusics().add(music);
		} else {
			throw new MusicAlreadyExistsException();
		}
		
	}

	public List<String> getMusicNamesList() {
		
		List<String> result = new ArrayList<String>();
		
		for(Music m : this.getMusics())
			result.add(m.getName());
		
		return result;
	}
}

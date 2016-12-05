package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufrn.imd.domain.Music;
import br.ufrn.imd.exceptions.CannotDeleteMusicException;
import br.ufrn.imd.exceptions.MusicAlreadyExistsException;

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
	
	/**
	 * Method to add a new music. Verify is already exists a music with the same name or directory
	 * @param music the music to add
	 * @throws MusicAlreadyExistsException exception to show that the music already exists
	 */
	public void addNewMusic(Music music) throws MusicAlreadyExistsException {
		boolean musicFound = false;

		for (Iterator<Music> iterator = this.getMusics().iterator(); iterator.hasNext();) {
			Music msc = iterator.next();
			if (!musicFound) {
				if (msc.getName().equals(music.getName()) || msc.getDirectory().equals(music.getDirectory())) {
					musicFound = true;
				}
			}
		}

		if (!musicFound) {
			this.addMusic(music);
		} else {
			throw new MusicAlreadyExistsException();
		}
	}

	/**
	 * Method to return the list with all music names
	 * @return the list with all music names
	 */
	public List<String> getMusicNamesList() {
		
		List<String> result = new ArrayList<String>();
		
		for(Music m : this.getMusics())
			result.add(m.getName());
		
		return result;
	}

	/**
	 * Remove the music by name
	 * @param musicName the name of the music to delete
	 * @throws CannotDeleteMusicException exception to show that the music couldn't be deleted
	 */
	public void removeMusicByName(String musicName) throws CannotDeleteMusicException{
		boolean musicRemoved = false;

		for (Iterator<Music> iterator = this.getMusics().iterator(); iterator.hasNext();) {
			Music msc = iterator.next();
			if (!musicRemoved) {
				if (msc.getName().equals(musicName)) {
					iterator.remove();
					musicRemoved = true;
				}
			}
		}

		if (!musicRemoved)
			throw new CannotDeleteMusicException();

	}
}

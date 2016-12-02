package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.domain.Music;

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
 
}

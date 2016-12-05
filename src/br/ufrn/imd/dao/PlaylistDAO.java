package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.domain.PlayList;

/**
 * 
 * DAO of the PlayList class
 * 
 * @author pdr_m
 *
 */
public class PlaylistDAO {

	/**
	 * An instance of the running DataBase
	 */
	private DataBase db = DataBase.getInstance();
	
	/**
	 * Get the playlists on database
	 * @return the playlists on database
	 */
	public List<PlayList> getPlaylists() {
		return this.db.getPlaylists();
	}

	/**
	 * Set the playlists on database
	 * @param list the playlists to set
	 */
	public void setPlaylists(List<PlayList> list) {
		this.db.setPlaylists(list);
	}

	public void addNewPlaylist(PlayList newPlaylist) {
		this.getPlaylists().add(newPlaylist);
	}
	
}

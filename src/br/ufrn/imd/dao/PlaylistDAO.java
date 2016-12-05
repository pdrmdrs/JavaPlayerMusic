package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.domain.PlayList;
import br.ufrn.imd.exceptions.PlayListAlreadyExistsException;

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

	public void addNewPlaylist(PlayList newPlaylist) throws PlayListAlreadyExistsException{
		
		boolean playlistFound = false;
		
		for(PlayList p : this.getPlaylists()){
			if(p.getName().equals(newPlaylist.getName()))
				playlistFound = true;
		}
		
		if(playlistFound)
			throw new PlayListAlreadyExistsException();
		else
			this.getPlaylists().add(newPlaylist);
		
		
	}

	public List<String> getPlaylistsNames() {
		List<String> result = new ArrayList<>();
		
		for(PlayList p : this.getPlaylists())
			result.add(p.getName());
		
		return result;
	}

	public PlayList getPlaylistByName(String selectedItem) {
		
		PlayList result = new PlayList();
		
		for(PlayList p : this.getPlaylists()){
			if(p.getName().equals(selectedItem))
				result = p;
		}
		
		return result;
	}
	
}

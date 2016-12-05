package br.ufrn.imd.domain;

import java.io.File;

import br.ufrn.imd.dao.MusicDAO;
import br.ufrn.imd.interfaces.PlayerInterface;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * Class that represents the player of the app
 * 
 * @author pdr_m
 *
 */
public class Player implements PlayerInterface {
	
	private static Music musicPlaying;
	
	private PlayList actualPlaylist;
	
	private MediaPlayer player;
	
	/**
	 * @return the player
	 */
	public MediaPlayer getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(MediaPlayer player) {
		this.player = player;
	}

//	private static Player instance = new Player();
	
	public Player() {
		//this.musicPlaying = new MusicDAO().getMusics().get(0);
//		this.createInstance();
	}
	
	public void createInstance() {
		if(musicPlaying == null){
			Player.musicPlaying = new MusicDAO().getMusics().get(0);
		}
		File f = new File(musicPlaying.getDirectory());
		Media m = new Media(f.toURI().toString());
		player = new MediaPlayer(m);
		player.setVolume(0.5);
	}
	
	/**
	 * @return the musicPlaying
	 */
	public Music getMusicPlaying() {
		return musicPlaying;
	}

	/**
	 * @param musicPlaying the musicPlaying to set
	 */
	public void setMusicPlaying(Music musicPlaying) {
		Player.musicPlaying = musicPlaying;
	}

	/**
	 * @return the actualPlaylist
	 */
	public PlayList getActualPlaylist() {
		return actualPlaylist;
	}

	/**
	 * @param actualPlaylist the actualPlaylist to set
	 */
	public void setActualPlaylist(PlayList actualPlaylist) {
		this.actualPlaylist = actualPlaylist;
	}

	@Override
	public void play() {
		this.player.play();
	}

	@Override
	public void stop() {
		this.player.stop();
	}

	@Override
	public void pause() {
		this.player.pause();
	}
	
}

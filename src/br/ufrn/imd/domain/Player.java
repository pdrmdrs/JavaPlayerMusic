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
	
	private String status = "paused";
	
	private Music musicPlaying;
	
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

	private static Player instance = new Player();
	
	private Player() {
		this.musicPlaying = new MusicDAO().getMusics().get(0);
		File f = new File(musicPlaying.getDirectory());
		Media m = new Media(f.toURI().toString());
		player = new MediaPlayer(m);
	}
	
	public static Player getInstance() {
		return Player.instance;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
		this.musicPlaying = musicPlaying;
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
	public void play(Music music) {
		// TODO Play only one music
	}

	@Override
	public void play(PlayList playlist) {
		// TODO Start the play of music from a playlist
	}

	@Override
	public void pause() {
		this.player.pause();
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
	}

	@Override
	public void previous() {
		// TODO Auto-generated method stub
	}
	
}

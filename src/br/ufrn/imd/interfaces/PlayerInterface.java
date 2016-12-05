package br.ufrn.imd.interfaces;

import br.ufrn.imd.domain.Music;
import br.ufrn.imd.domain.PlayList;

public interface PlayerInterface {
	public void play();
	public void play(Music music);
	public void play(PlayList playlist);
	
	public void pause();
	public void next();
	public void previous();
}

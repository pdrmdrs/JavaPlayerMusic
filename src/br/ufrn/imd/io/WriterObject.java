package br.ufrn.imd.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.domain.Music;

//TODO: write a class that will write the txt files
public class WriterObject {
	private static WriterObject instance = new WriterObject();
	
	private WriterObject() {}
	
	public static WriterObject getInstance() {
		return WriterObject.instance;
	}
	
	public boolean writeMusicsFile() throws IOException {
		
		List<Music> musics = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			musics.add(new Music("C:\\musics\\dadwa\\musica"+i+".mp3", "Nome da musica" + i, "Nome do Artista" + i));
		}
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\dreamerofpain\\Desktop\\Arquivos\\Playlists\\AllMusicsFile.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(musics);
		oos.close();
		
		return true;
	}
}

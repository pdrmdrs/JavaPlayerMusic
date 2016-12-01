package br.ufrn.imd.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import br.ufrn.imd.domain.Music;

//TODO: write a class that will read the txt files
/**
 * 
 * Class that represents a file reader
 * 
 * @author Pedro Paulo
 *
 */
public class ReaderObject {
	private static ReaderObject instance = new ReaderObject();
	
	private ReaderObject() {}
	
	public static ReaderObject getInstance() {
		return ReaderObject.instance;
	}

	//public List<?> readMusicsFile(Path path) {
	public List<?> readMusicsFile() throws IOException, ClassNotFoundException{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\dreamerofpain\\Desktop\\Arquivos\\Playlists\\AllMusicsFile.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<Music> musics= (List<Music>) ois.readObject();
		ois.close();
		
//		for(Music m : musics) {
//			sysout
//		}
		
		return null;
	}
	
}

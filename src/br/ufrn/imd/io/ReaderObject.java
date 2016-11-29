package br.ufrn.imd.io;
//TODO: write a class that will read the txt files
public class ReaderObject {
	private static ReaderObject instance = new ReaderObject();
	
	private ReaderObject() {}
	
	public static ReaderObject getInstance() {
		return ReaderObject.instance;
	}
}

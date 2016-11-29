package br.ufrn.imd.io;
//TODO: write a class that will write the txt files
public class WriterObject {
	private static WriterObject instance = new WriterObject();
	
	private WriterObject() {}
	
	public static WriterObject getInstance() {
		return WriterObject.instance;
	}
}

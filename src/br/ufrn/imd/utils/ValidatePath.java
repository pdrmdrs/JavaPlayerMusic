package br.ufrn.imd.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * Util class that will validade a path
 * 
 * @author pdr_m
 *
 */
public class ValidatePath {
	
	/**
	 * Method that validade a directory, if does not exist, create it
	 * @param path the path to create
	 * @return the path
	 */
	public static String validate(String path) {
		
		Path folderPath = Paths.get(path);
		
		File folder = new File(folderPath.getParent().toString());
		if(!folder.exists()) {
			if(folder.mkdirs()){
//				System.out.println("folder path created successfully");
			} else {
//				System.out.println("failed to create folder path");
			}
		}
		
		File file = new File(path);
		if(!file.exists()) {
			try {
				if(file.createNewFile()){
//					System.out.println("file created successfully");
				} else {
//					System.out.println("failed to create file");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return path;
	}
}

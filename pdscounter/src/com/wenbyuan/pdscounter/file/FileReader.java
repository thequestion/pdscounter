package com.wenbyuan.pdscounter.file;
/**
 * 
 */


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wenbyuan
 *
 */
public class FileReader extends Reader{
	
	private static Logger logger = Logger.getLogger(FileReader.class.getName());
	private File file = null;
	
	public FileReader(String fileName){
		this.file = new File(fileName);
		try {
			if(!file.exists())
				this.file.createNewFile();
		} catch (IOException e) {
			logger.log( Level.SEVERE, "Creating file faled!");
			e.printStackTrace();
		} 
	}

	@Override
	public List<String> readLines() {
		try {
			Path path = Paths.get(file.getAbsolutePath());
			return Files.readAllLines(path, Charset.defaultCharset());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Reading file failed!");
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public String read() {
		try {
			Path path = Paths.get(file.getAbsolutePath());
			return new String( Files.readAllBytes(path), Charset.defaultCharset());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Reading file failed!");
			e.printStackTrace();
		}
		return null;
	}

}

package concord;

import java.io.*;
import java.util.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class ConcordRepoOp {

	/**
	 * Create a concordance from a textfile
	 * @param textPath
	 * @return
	 */
	public static Parser createConcord(String textPath, String usrDir){
		//Generate an Array of files in the user's Texts Repos
		File[] dir = new File(usrDir).listFiles();
		boolean caseSensExists = false;	//need a case sensitive match for *NIX-based OS -- Windows doesn't care
		for(int i = 0; i < dir.length ; i++){
			if(dir[i].getAbsolutePath().equals(textPath)) caseSensExists = true; //textPath is in the usrDir
		}
		//Case sensitivity was failed, return null for Parser
		if(caseSensExists == false){
			System.out.println("No local text '" + textPath + "' exists.");
			return null;
		}

		//Case sensitivity was passed, continue on for Parser
		File textFile = new File(textPath);
		if(!textFile.exists() || textFile.isDirectory()){
			System.out.println("Error -- Invalid Text File: " + textPath);
			return null;
		}
		//We have valid Texts to parse
		Parser conParse = new Parser();
		try{
			conParse.Parser(textPath);
		}catch(FileNotFoundException fnfe){		
			return null;
		}

		conParse.outputWords();
		return conParse;
	}
	
	/**
	 * Save the currently loaded concordance
	 * @param concParse
	 * @param concPath
	 * @return
	 */
	public static String saveConcord(Parser concParse, String concPath){
		File concFile = new File(concPath); //turn the path into a file

		if(!concFile.exists()){//if the file doesn't exist, try to create a new one in the cordance
				try{
					concFile.createNewFile();
					concParse.parseToFile(concFile);
						
				}catch(IOException ioerr){
					System.out.println("Error -- Error Creating Concordance File: " + concPath);
					return "WHOOPS";
				}
		}
		
		System.out.println("The concfile exists and it is named: ");
		if(concFile.exists()){
			try{
				FileWriter concWrite = new FileWriter(concFile);
				System.out.println("We're writing to " + concFile.getAbsolutePath());
				//concWrite(concParse.parseToFile(concFile));
			}catch(IOException ioerr){
				System.out.println("hit an error writing file");
				return "WHOOPS2";
			}
		}
		//concParse.parseToFile(concFile);
	
		return "Heyo, let's implement some save functionality. K?";
	}
	
	public static String showConcords(){
		return "Guess what this should do. If you guess show the gen'd concords, you win.";
	}
	
	public static String showConcordsKeyword(String keyword){
		return "Show the Concords filtered by a keyword.";
	}
	
	public static String showConcordsAppearance(){
		return "Show the concords with the word appearing, and what not.";
	}
}

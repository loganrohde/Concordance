package concord;

import java.io.*;
import java.util.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class ConcordRepoOp {

	public static String createConcord(String userInput){
		System.out.println("Trim the user input for whitespace");
		System.out.println("Edit trimmed input into CamelCaseName");
		System.out.println("Parse the file");
		return "Hopefully, we've created a concordance by this point in the game";
	}
	
	public static String saveConcord(String concordName){
		return "Heyo, let's implement some save functionality. K?";
	}
	
	public static String showConcords(){
		return "Guess what this should do. If you guess show the gen'd concords, you win.";
	}
	
	public static String showConcordsKeyword(String keyword){
		return "Show the Concords filtered by a keyword.";
	}
}

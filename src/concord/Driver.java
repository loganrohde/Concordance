package concord;

import java.util.*;
import java.io.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class Driver {
	
	
	public static void main(String[] args){
				
		//Start off by declaring environmental variables
		String usrHomDir = System.getProperty("user.home");	//home dir for Concordance folder to be placed in
		String usrOS = System.getProperty("os.name");		//OS info to define path conventions
		String usrConcDir = "";	//String will represent path for ~/Concordance/Concordances
		String usrTextDir = "";	//String will represent path for ~/Concordance/Texts
		String usrSlash = "";	//Slash style to be used in pathnames -- / for *nix/non-windows, \ for Windows
		File rscChk = null;		//Resource Check -- specifies the location of ~/Concordance, which will be check
		boolean isWin = (usrOS.toLowerCase().substring(0, 3).equals("win"));	//Check OS info

		//Based on OS, redefine some environment variables
		if(isWin){
			usrConcDir = (usrHomDir + "\\Concordance\\Concordances");
			usrTextDir = (usrHomDir + "\\Concordance\\Texts");
			usrSlash = "\\";
			rscChk = new File(usrHomDir + "\\Concordance");
		}
		else{
			usrConcDir = (usrHomDir + "/Concordance/Concordances");
			usrTextDir = (usrHomDir + "/Concordance/Texts");
			usrSlash = "/";
			rscChk = new File(usrHomDir + "/Concordance");
		}
		
		//If a directory doesn't exist, create it
		if(!rscChk.isDirectory()){
			rscChk.mkdir();	// ~/Concordance dir
		}
		File concDir = new File(usrConcDir);	// ~/Concordance/Concordances dir
		File textDir = new File(usrTextDir);	// ~/Concordance/Texts dir
		if(!concDir.isDirectory()) concDir.mkdir();
		if(!textDir.isDirectory()) textDir.mkdir();

		String curConcPath = "";	//Path of currently loaded concordance
		String curTextPath = "";	//Path of currently specified text
		Parser curConcParse = null;	//Parser of current concordance
		
		
		//Environment variables specified, directories -- move onto user interaction, after intro
		intro();
		Scanner userInScn = new Scanner(System.in);	//Scanner to handle all user input
		while(userInScn.hasNext()){
			String userCmd = userInScn.nextLine();

			//Show the 'commands' menu
			if(userCmd.equals("commands")){
				commandMenu();
			}
			
			//Show the 'help' menu
			if(userCmd.equals("help")){
				helpMenu();
			}
			
			//Quit the process
			if(userCmd.equals("quit")){
				userInScn.close();
				System.exit(0);
			}
			
			//Show Titles in the Texts Repo
			if(userCmd.equals("show titles")){
				System.out.println(TextRepoOp.showTitles(textDir));
			}
			
			//Show Concordances in the Concordances Repo
			if(userCmd.equals("show concordances")){
				ConcordRepoOp.showConcords();
			}
			
			//Show Currently Loaded Concordance
			if(userCmd.equals("show concordance current")){
				if(!curConcPath.equals("")){
					System.out.println(curConcPath);
				}else System.out.println("No Concordance is currently loaded.");
			}
			
			//Save Currently Loaded Concordance
			if(userCmd.equals("save concordance")){
				System.out.println(userCmd);
				System.out.println(curConcPath);
				System.out.println(ConcordRepoOp.saveConcord(curConcParse, curConcPath));
			}

			
			//***********************
			//Begin Try/Catch Blocks
			//No println's, if statements, variable available between try/catch
			//If it is not above, it is not run
			//***********************
			
			//Show titles of texts filtered by a keyword
			//'show titles keyword <keyword>'
			try{
				String sysCmd = "show titles keyword ";
				int sysCmdLen = sysCmd.length();
				//if the beginning of user command matches the system command...
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					//...strip anything after the match, and pass it as an argument to function
					if(userCmd.length() > sysCmdLen) System.out.println(TextRepoOp.showTitlesKeyword(textDir, userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Show titles of texts filtered by author name
			//'show titles author <name>'
			try{
				String sysCmd = "show titles author ";
				int sysCmdLen = (sysCmd).length();
				//if the beginning of user command matches the system command...
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					//...strip anything after the match, and pass it as an argument to function
					if(userCmd.length() > sysCmdLen) System.out.println(TextRepoOp.showTitlesAuthor(userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Create a Parser object(functions as our concordance) from a .txt file in Texts Repo
			//'create concordance <title>'
			try{
				String sysCmd = "create concordance ";
				int sysCmdLen = sysCmd.length();
				//if the beginning of user command matches the system command...
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen){
						//...strip anything after the match, and use it in creating a concordance path and text path
						String concPath = usrConcDir + usrSlash + userCmd.substring(sysCmdLen).trim() + ".conc";
						String textPath = usrTextDir + usrSlash + userCmd.substring(sysCmdLen).trim() + ".txt";
						//user the paths to generate a Parser object
						curConcParse = ConcordRepoOp.createConcord(textPath, usrTextDir);
						//If the parse was successful, update the currently loaded concordance path and text path
						if(curConcParse != null){
							curConcPath = concPath;
							curTextPath = textPath;
							System.out.println("UPDATE Loaded Concordance: " + userCmd.substring(sysCmdLen));
						}
					}
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Show titles of concordances filtered by a keyword
			//'show concordances keyword <keyword>'
			try{
				String sysCmd = "show concordances keyword ";
				int sysCmdLen = sysCmd.length();
				//if the beginning of user command matches the system command
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					//...strip anything after the match, and pass it as an argument to function
					if(userCmd.length() > sysCmdLen) System.out.println(ConcordRepoOp.showConcordsKeyword(userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Show titles of concordances containing at least N appearances of a keyword
			//'show concordances appearance <int N> <keyword>'
			try{
				String sysCmd = "show concordances appearance ";
				int sysCmdLen = sysCmd.length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen){
						String vars = userCmd.substring(sysCmdLen);
						String varKey = "";
						int varApr = -1;
						if(vars.charAt(0) == '"'){
							varKey = vars.substring(0, vars.lastIndexOf('"'));
						}else varKey = vars.substring(0, 1); //need to fix this shit
						System.out.println(ConcordRepoOp.showConcordsAppearance());
					}
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
		}
	}
	
	/**
	 * Print an introductory message to console
	 */
	public static void intro(){
		System.out.println("Team A Concordance Manager");
		System.out.println("--------------------------");
		System.out.println("Please type 'help' for help.");
		System.out.println("Please type 'commands' for a list of valid commands.");
		System.out.println("Please note: ALL command and argument syntax is Case-Sensitive");
	}
	
	/**
	 * Print a list of available commands to console
	 */
	public static void commandMenu(){
		//Req 2
		System.out.println("show titles\n -- Display a list of locally stored titles.");
		System.out.println("show titles author <name>\n -- Display a list of locally stored titles by a specified author.");
		System.out.println("show titles keyword <keyword>\n -- Display a list of locally stored titles filtered by a specified keyword.");
		//Req 3
		System.out.println("create concordance <title>\n -- Create a concordance for an existing title.\n -- NOTE: Concordance is not saved to disk upon creation");
		//Req 4
		System.out.println("save concordance\n -- Save the currently loaded concordance to the hard disk.");
		//Req 5
		System.out.println("show concordances\n -- Display a list of locally stored concordances.");
		System.out.println("show concordances keyword <keyword>\n -- Display a list of locally stored concordances whose titles contain a specified keyword.");
		//Req 6
		System.out.println("show concordances appearance <appearances> <keyword>\n -- Display a list of locally stored concordances containing at least N instances of a specified keyword\n -- NOTE: N must be a whole number that is greater than 0");
		//required for determining currently loaded concordance
		System.out.println("show concordance current\n -- Display the currently loaded concordance");
		//Req 7
		System.out.println("load concordance <title>\n -- Load a specified concordance");
		//Req 8
		System.out.println("query concordance <word> lines");
		System.out.println("query concordance <word> numberlines");
		System.out.println("query concordance <word> rank");
		System.out.println("query concordance <word> distance <distance>");
		System.out.println("query concordance <phrase>");
	}
	
	public static void helpMenu(){
		System.out.println("Full help functionality forthcoming. For now, input a command.");
		System.out.println("NOTE: All command and argument syntax is Case-Sensitive");
	}
}

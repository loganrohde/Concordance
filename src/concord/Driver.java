package concord;

import java.util.*;
import java.io.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class Driver {
	
	
	public static void main(String[] args){
		
		/**
		 * Start off by gathering environmental variables. We're going to need directories in order to find files.
		 */
		String usrHomDir = System.getProperty("user.home");
		String usrOS = System.getProperty("os.name");
		String usrConcDir = "";
		String usrTextDir = "";
		String usrSlash = "";
		File rscChk = null;
		boolean isWin = (usrOS.toLowerCase().substring(0, 3).equals("win"));
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
		if(!rscChk.isDirectory()){
			rscChk.mkdir();
		}
		File concDir = new File(usrConcDir);
		File textDir = new File(usrTextDir);
		if(!concDir.isDirectory()) concDir.mkdir();
		if(!textDir.isDirectory()) textDir.mkdir();
		
		System.out.println(usrHomDir + " " + usrOS + " " + isWin);
		System.out.println(usrConcDir +  " " + concDir.isDirectory());
		System.out.println(usrTextDir +  " " + textDir.isDirectory());
		
		/**
		 * Files and directories are in order. We are good to go.
		 */
		intro();
		
		String curConcPath = "";
		String curTextPath = "";
		Parser curConcParse = null;
		
		Scanner userInScn = new Scanner(System.in);
		while(userInScn.hasNext()){
			String userCmd = userInScn.nextLine();

			if(userCmd.equals("commands")){
				commandMenu();
			}
			if(userCmd.equals("help")){
				helpMenu();
			}
			if(userCmd.equals("quit")) System.exit(0);
			
			//Show Titles
			if(userCmd.equals("show titles")){
				System.out.println(TextRepoOp.showTitles(textDir));
			}
			
			//Show Currently Loaded Concordance
			if(userCmd.equals("show concordance current")){
				if(!curConcPath.equals("")){
					System.out.println(curConcPath);
				}else System.out.println("No Concordance is currently loaded.");
			}
			
			//Show Concordances
			if(userCmd.equals("show concordances")){
				ConcordRepoOp.showConcords();
			}
			
			//Save Concordance
			if(userCmd.equals("save concordance")){
				System.out.println(userCmd);
				System.out.println(curConcPath);
				System.out.println(ConcordRepoOp.saveConcord(curConcParse, curConcPath));
			}

			
			//***********************
			//Begin Try/Catch Blocks
			//No println's, if statements, variable decs
			//are availabe between try catch
			//if it is not above, it is not run
			//***********************
			
			//Show Titles Keyword <keyword>
			try{
				String sysCmd = "show titles keyword ";
				int sysCmdLen = sysCmd.length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen) System.out.println(TextRepoOp.showTitlesKeyword(textDir, userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Show Titles Author <keyword>
			try{
				String sysCmd = "show titles author ";
				int sysCmdLen = (sysCmd).length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen) System.out.println(TextRepoOp.showTitlesAuthor(userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Create Concord Parse
			try{
				String sysCmd = "create concordance ";
				int sysCmdLen = sysCmd.length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen){
						String concPath = usrConcDir + usrSlash + userCmd.substring(sysCmdLen).trim() + ".conc";
						String textPath = usrTextDir + usrSlash + userCmd.substring(sysCmdLen).trim() + ".txt";
						curConcParse = ConcordRepoOp.createConcord(textPath, usrTextDir);
						//Parse Successfull
						if(curConcParse != null){	//If Parse was successful, update the currently loaded concordance
							curConcPath = concPath;
							curTextPath = textPath;
							System.out.println("UPDATE Loaded Concordance: " + curConcPath);
						}
					}
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Show Concordances Keyword <keyword>
			try{
				String sysCmd = "show concordances keyword ";
				int sysCmdLen = sysCmd.length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen) System.out.println(ConcordRepoOp.showConcordsKeyword(userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Show Concordances Appearance <keyword> <# appearances>
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
//						System.out.println(ConcordRepoOp.createConcord(userCmd.substring(sysCmdLen)));
						System.out.println(ConcordRepoOp.showConcordsAppearance());
					}
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
		}
	}
	
	public static void intro(){
		System.out.println("Team A Concordance Manager");
		System.out.println("--------------------------");
		System.out.println("Please type 'help' for help, or 'help command' for more info on a command.");
		System.out.println("Please type 'commands' for a list of valid commands.");
	}
	
	public static void commandMenu(){
		//Req 2
		System.out.println("'show titles' -- Display a list of locally stored titles.");
		System.out.println("'show titles author <name>' -- Display a list of locally stored titles by a specified author.");
		System.out.println("'show titles keyword <keyword>' -- Display a list of locally stored titles filtered by a specified keyword");
		//Req 3
		System.out.println("create concordance <title>");
		//Req 4
		System.out.println("save concordance");
		//Req 5
		System.out.println("show soncordances");
		System.out.println("show concordances keyword <keyword>");
		//Req 6
		System.out.println("show concordances appearance <keyword> <appearances>");
		//Req 7
		System.out.println("load concordance <title>");
		//Req 8
		System.out.println("query concordance <word> lines");
		System.out.println("query concordance <word> numberlines");
		System.out.println("query concordance <word> rank");
		System.out.println("query concordance <word> distance <distance>");
		System.out.println("query concordance <phrase>");
	}
	
	public static void helpMenu(){
		System.out.println("Full help functionality forthcoming. For now, input a command.");
		System.out.println("NOTE: All command syntax is Case-Sensitive");
	}
}

package concord;

import java.util.*;
import java.io.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class Driver {
	
	
	public String currentConcord = "";
	
	
	public static void main(String[] args){
		intro();
		Scanner userInScn = new Scanner(System.in);
		while(userInScn.hasNext()){
			String userCmd = userInScn.nextLine();

			if(userCmd.equals("commands")) commandMenu();
			if(userCmd.equals("help")) helpMenu();
			if(userCmd.equals("quit")) System.exit(0);
			
			//Show Titles
			if(userCmd.equals("Show Titles"))System.out.println(TextRepoOp.showTitles());
			
			//Show Titles Keyword <keyword>
			try{
				String sysCmd = "Show Titles Keyword ";
				int sysCmdLen = sysCmd.length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen) System.out.println(TextRepoOp.showTitlesKeyword(userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Show Titles Author <keyword>
			try{
				String sysCmd = "Show Titles Author ";
				int sysCmdLen = (sysCmd).length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen) System.out.println(TextRepoOp.showTitlesAuthor(userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}
			
			//Create Concord
			//NOTE: A Concordance Must Already Be Loaded into Hash Table
			try{
				String sysCmd = "Create Concordance ";
				int sysCmdLen = sysCmd.length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen) System.out.println(ConcordRepoOp.createConcord(userCmd.substring(sysCmdLen)));
				}
			}
			catch(StringIndexOutOfBoundsException strIOB){
				continue;
			}

			//Save Concordance
			//if(userCmd.equals("Save Concordance")) ConcordRepoOp.saveConcord(currentConcord);

			//Show Concordances
			if(userCmd.equals("Show Concordances")) ConcordRepoOp.showConcords();
			
			//Show Concordances Keyword <keyword>
			try{
				String sysCmd = "Show Concordances Keyword ";
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
				String sysCmd = "Show Concordances Appearance ";
				int sysCmdLen = sysCmd.length();
				if(userCmd.substring(0, sysCmdLen).equals(sysCmd)){
					if(userCmd.length() > sysCmdLen){
						String vars = userCmd.substring(sysCmdLen);
						String varKey = "";
						int varApr = -1;
						if(vars.charAt(0) == '"'){
							varKey = vars.substring(0, vars.lastIndexOf('"'));
						}else varKey = vars.substring(0, 1); //need to fix this shit
						System.out.println(ConcordRepoOp.createConcord(userCmd.substring(sysCmdLen)));
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
		System.out.println("'Show Titles' -- Display a list of locally stored titles.");
		System.out.println("'Titles Author <name>' -- Display a list of locally stored titles by a specified author.");
		System.out.println("'Show Titles Keyword <keyword>' -- Display a list of locally stored titles filtered by a specified keyword");
		//Req 3
		System.out.println("Create Concordance <title>");
		//Req 4
		System.out.println("Save Concordance");
		//Req 5
		System.out.println("Show Concordances");
		System.out.println("Show Concordances Keyword <keyword>");
		//Req 6
		System.out.println("Show Concordances Appearance <keyword> <appearances>");
		//Req 7
		System.out.println("Load Concordance <title>");
		//Req 8
		System.out.println("Query Concordance <word> Lines");
		System.out.println("Query Concordance <word> NumberLines");
		System.out.println("Query Concordance <word> Rank");
		System.out.println("Query Concordance <word> Distance <distance>");
		System.out.println("Query Concordance <phrase>");
	}
	
	public static void helpMenu(){
		System.out.println("Full help functionality forthcoming. For now, input a command.");
	}
}

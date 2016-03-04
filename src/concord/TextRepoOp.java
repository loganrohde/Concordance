package concord;

import java.io.*;
import java.util.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class TextRepoOp {

	public static String showTitles(File dir){
		//dir = dir;
		String titleList = "Locally Stored Titles:";
		String title = "";
		File[] dirContents = dir.listFiles();
		int i = 0;
		while(i < dirContents.length){
			title = dirContents[i].toString();
			title = title.substring(title.lastIndexOf('\\') + 1, title.indexOf('.'));
			titleList = titleList + "\n" + title;
			i++;
		}
		return titleList;
	}
	
	public static String showTitlesKeyword(File dir, String keyword){
		keyword = keyword.trim();
		String titleList = "Locally Stored Titles Filtered By Keyword '" + keyword + "':";
		String title = "";
		File[] dirContents = dir.listFiles();
		int i = 0;
		while(i < dirContents.length){
			title = dirContents[i].toString();
			title = title.substring(title.lastIndexOf('\\') + 1, title.lastIndexOf('.'));
			if(title.contains(keyword)){
				titleList = titleList + "\n" + title;
			}
			i++;
		}
		return titleList;
	}
	
	public static String showTitlesAuthor(String author){
		String titleList = "This would show titles by '" + author + "'";
		titleList = titleList + "\n... but we haven't implemented that functionality yet";
		titleList += "\nStripping titles from Gutenberg is on the way, but the current file";
		titleList += "\nlabeling system does not include authors. Then again, there format";
		titleList += "\nuses numbers rather than titles. So it's all clearly obfuscated.";
		return titleList;
	}
}

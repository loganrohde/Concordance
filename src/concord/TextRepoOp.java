package concord;

import java.io.*;
import java.util.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class TextRepoOp {

	public static String showTitles(){
		String titleList = "This would show titles";
		return titleList;
	}
	
	public static String showTitlesKeyword(String keyword){
		keyword = keyword.trim();
		String titleList = "This would show titles with '" + keyword + "' in it";
		return titleList;
	}
	
	public static String showTitlesAuthor(String author){
		String titleList = "This would show titles by '" + author + "'";
		return titleList;
	}
}

package concord;

import java.util.*;
import java.io.*;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class Parser {

	//create the concordance
	//query created concordances

	protected ArrayList<Word> wordList;
	public ArrayList<Word> wordlist;
    /**
     * Prepare a concordance for the given file.
     *
     * @param fileName
     * @throws java.io.FileNotFoundException
     */
    public void Parser(String fileName) throws FileNotFoundException {    	
    	wordList = new ArrayList<Word>();
        System.out.println("created arrayList");
        inputWords(fileName);
        Collections.sort(wordList); //sort the words by frequency
        //outputWords();
    }

    public void inputWords(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        //uses other punctuation to start a new word
        in.useDelimiter("[\\s\"_;.,:!?()-]");

        int lineNumber = 0;
        String tempWord;

        while (in.hasNextLine()) {
            ++lineNumber;
            tempWord = in.nextLine();
            Scanner in2 = new Scanner(tempWord);
            in2.useDelimiter("[\\s\"_;.,:!?()-]");
            while (in2.hasNext()) {
                Word word = new Word(in2.next().toLowerCase());
                
                int index = wordList.indexOf(word);
                if (index == -1) {
                    wordList.add(word);
                    word.addLine(lineNumber);
                } else {
                    wordList.get(index).increment();
                    wordList.get(index).addLine(lineNumber);
                }
            }

        }

    }

    /**
     * output the array list of words with their count
     */
    public void outputWords() {
        for (int i = 0; i < wordList.size(); i++) {
            if(wordList.get(i).toString().equals("")){
                
            }
            else{
            System.out.println("\"" + wordList.get(i) + "\" appears " 
                    + wordList.get(i).getFrequency() + " times");
            }
        }
    }
    
    public void outputLineNumbers(){
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).toString().equals("")){
                
            }
            else{
            System.out.print("\"" + wordList.get(i));
            System.out.print("\" appears on lines ");
            wordList.get(i).getLines();
            System.out.println("");
            }
        }
    }
    
    public int getWordCount(String newWord){
        newWord = newWord.toLowerCase();
        for(int i = 0; i < wordList.size(); i++){
            if(newWord.compareTo(wordList.get(i).toString()) == 0){
                return wordList.get(i).getFrequency();
            }
           
        }
        return 0;
    }
    
    public void getLines(String newWord){
        newWord = newWord.toLowerCase();
        for(int i = 0; i < wordList.size(); i++){
            if(newWord.compareTo(wordList.get(i).toString()) == 0){
                wordList.get(i).getLines();
            }
        }
    }
    
    public void parseToFile(File fileDest){
    	System.out.println("are we touching this?");
    	for(int i = 0; i < this.wordList.size(); i++){
    		Word curr = wordList.get(i);
    		System.out.println(curr);
    	}
    }
    
    public void fileToParse(File fileDest, File fileToParse){
    	
    }
}

# hello-world
concordance


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concord;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author LoganRohde
 */
public class Concord {
    
    /**
     * A set of words that are different with their count in the file.
     */
    private ArrayList<Word> wordList;
    
    public class Word implements Comparable 
    {
        
        private final String word;
        private int frequency;
        
        /**
         * Make a new word with the given string; frequency initialized to 1.
         * @param word
         */
        public Word(String word) {
            this.word = word;
            this.frequency = 1;
        }
        
        
        /**
         * Uses the frequency of each word and compares in order to see if 
         * word is in the concordance already
         * @param other
         * @return difference
         */
        @Override
        public int compareTo(Object other) {
            Word otherWord = (Word) other;
            int difference = otherWord.frequency - this.frequency;
            if (difference == 0)
                difference = this.word.compareTo(otherWord.word);
            return difference;
        }
        
        @Override
        public boolean equals(Object other) {
            return this.word.equals(((Word)other).word);
        }
        
        @Override
        public String toString() {
            return frequency + "\t" + word;
        }
        
        /**
         * increment the frequence of the word by one
         */
        public void increment() {
            frequency++;
        }
    }


    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    public static void main(String[] args) throws FileNotFoundException {
        
        String filenm = "test.txt";
        Scanner in = new Scanner(new File(filenm));
        FileReader fr = new FileReader(filenm);
 
       
        Concord concordance = new Concord();
        concordance.start(filenm);
    }
    
    
    /**
     * Prepare a concordance for the given file.
     * @param fileName
     * @throws java.io.FileNotFoundException
     */
    public void start(String fileName) throws FileNotFoundException
    {
        wordList = new ArrayList<Word>();
        inputWords(fileName);
        Collections.sort(wordList); //sort the words by frequency
        outputWords();
    }
    
    
    public void inputWords(String fileName) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File(fileName));
        //uses other punctuation to start a new word
        in.useDelimiter("[\\s\"_;.,:!?()-]");
        
        while (in.hasNext())
        {
            Word word = new Word(in.next().toLowerCase());
            int index = wordList.indexOf(word);
            if (index == -1)
                wordList.add(word);
            else
                wordList.get(index).increment();
        }    
    }
    
    /**
     * output the array list of words with their count
     */
    public void outputWords()
    {
        for (int i = 0; i < wordList.size(); i++)
        {
            System.out.println(wordList.get(i));
        }        
    }
    
}

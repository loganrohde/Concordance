package concord;

import java.util.ArrayList;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class Word implements Comparable{

	//Hold data as array nodes for each word node
	//    +word
	//    +line occurences
	//    +occurence count
	
    private final String word;
    private int frequency;
    private ArrayList<Integer> lines;

    /**
     * Make a new word with the given string; frequency initialized to 1.
     *
     * @param word
     */
    public Word(String word) {
        lines = new ArrayList<>();
        this.word = word;
        this.frequency = 1;
    }

    /**
     * Uses the frequency of each word and compares in order to see if word is
     * in the concordance already
     *
     * @param other
     * @return difference
     */
    @Override
    public int compareTo(Object other) {
        Word otherWord = (Word) other;
        int difference = otherWord.frequency - this.frequency;
        if (difference == 0) {
            difference = this.word.compareTo(otherWord.word);
        }
        return difference;
    }

    @Override
    public boolean equals(Object other) {
        return this.word.equals(((Word) other).word);
    }

    @Override
    public String toString() {
        return word;
    }

    public void addLine(int line) {
        lines.add(line);
    }

    public void getLines() {
        System.out.print("{");
        for(int i = 0; i < lines.size(); i ++)
        {
            if(i < lines.size() - 1){
                System.out.print(lines.get(i) + ", ");
            }
            else{
                System.out.print(lines.get(i));
            }
            
        }
        System.out.print("}\n");
    }

    public String getWord() {
        return word;
    }
    
    public boolean occursOnLine(int line) {
        for (int i = 0; i < lines.size(); i++) {
            if (line == lines.get(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * increment the frequence of the word by one
     */
    public void increment() {
        frequency++;
    }
    public int getFrequency(){
        return frequency;
    }
}

package concord;

import java.util.ArrayList;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class Parser {

	//create the concordance
	//query created concordances
	
	
	rotected ArrayList<Word> wordList;

    /**
     * Prepare a concordance for the given file.
     *
     * @param fileName
     * @throws java.io.FileNotFoundException
     */
    Concordance(String fileName) throws FileNotFoundException {
        wordList = new ArrayList<>();
        System.out.println("created arrayList");

        inputWords(fileName);
        Collections.sort(wordList); //sort the words by frequency
        //outputWords();
    }

    //Concordance(String filenm) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    public void inputWords(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));
        System.out.println("starting to input words");
        //uses other punctuation to start a new word
        in.useDelimiter("[\\s\"_;.,:!?()-]");
        int lineNumber = 0;
        System.out.println(lineNumber);

        //while (in.hasNext("\n")) {
            ++lineNumber;
            System.out.println(lineNumber);
            
            while (in.hasNext()) {
                Word word = new Word(in.next().toLowerCase());
                System.out.println(word);
                int index = wordList.indexOf(word);
                if (index == -1) {
                    wordList.add(word);
                    word.addLine(lineNumber);
                } else {
                    wordList.get(index).increment();
                    word.addLine(lineNumber);
                }
            }
        //}
    }

    /**
     * output the array list of words with their count
     */
    public void outputWords() {
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }
    }
}

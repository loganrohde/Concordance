package concord;
/*
 * Toss in your author tags as you update classes. Gracias. --W
 * @author Will Forrest
 */
public class Word{

	//Hold data as array nodes for each word node
	//    +word
	//    +line occurences
	//    +occurence count
	
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
}

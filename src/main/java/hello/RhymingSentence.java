package hello;

import java.io.IOException;
import java.util.ArrayList;

public class RhymingSentence
{
	
	/*
	 * 
	 */
	private SentenceUtility sentenceUtility;
	
	/*
	 * 
	 */
	private BookUtility bookUtility;
	
	/*
	 * 
	 */
	private RandomSentence sentenceToRhymeOffOf;
	
	/*
	 * 
	 */
	private String sentence;
	
	/*
	 * 
	 */
	public RhymingSentence(RandomSentence sentenceToRhymeOffOf) throws IOException
	{
		 sentenceUtility = new SentenceUtility();
		 bookUtility = new BookUtility();
		 
		 // the sentence we need to find a rhyming sentence for
		 this.sentenceToRhymeOffOf = sentenceToRhymeOffOf;
		 
		 // find a rhyming sentence from a random book
		 findRhymingSentence(bookUtility.getRandomBook());
		 
		 // If the sentence doesn't match criteria, keep searching until you get one
		 while (!sentenceUtility.checkIfSentenceMatchesCriteria(sentence, true))
		 {
			 findRhymingSentence(bookUtility.getRandomBook());
		 }
	}
	
    /*
     * Finds a corresponding rhyming sentence for the provided sentence in the provided book.
     */
    public void findRhymingSentence(String book) throws IOException
    {
    	// find the rhyming words for the sentence we need to rhyme off of
    	ArrayList<String> wordsThatRhymeWithSentence = 
    			sentenceUtility.getRhymingWordsForSentence(sentenceToRhymeOffOf.getSentence());
    	
    	// create a random new sentence
    	RandomSentence newSentence = new RandomSentence();
    	String newSentenceString = newSentence.getSentence();
		
    	// get the last word from the random new sentence
        String lastWordFromNewSentence = sentenceUtility.getLastWordOfSentence(newSentenceString);
        
        // keep getting a random new sentence until its last word is a rhyming word
        while (!wordsThatRhymeWithSentence.contains(lastWordFromNewSentence))
        {
        	newSentence.findRandomSentence(book);
        	newSentenceString = newSentence.getSentence();
        	
            lastWordFromNewSentence = sentenceUtility.getLastWordOfSentence(newSentenceString);
        }
        
        this.sentence = newSentenceString;
    }
    
    public String getSentence()
    {
    	return sentence;
    }
}

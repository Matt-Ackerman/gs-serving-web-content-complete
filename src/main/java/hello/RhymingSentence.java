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
	private String titleOfBookSentenceIsFrom;
	
	/*
	 * 
	 */
	private String authorOfBookSentenceIsFrom;
	
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
		 findRhymingSentence(bookUtility);
		 
		 // If the sentence doesn't match criteria, keep searching until you get one
		 while (!sentenceUtility.checkIfSentenceMatchesCriteria(sentence, true))
		 {
			 findRhymingSentence(bookUtility);
		 }
	}
	
    /*
     * Finds a corresponding rhyming sentence for the provided sentence in the provided book.
     */
    public void findRhymingSentence(BookUtility bookUtility) throws IOException
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
        	newSentence.findRandomSentence(bookUtility.getRandomBook());
        	newSentenceString = newSentence.getSentence();
        	
            lastWordFromNewSentence = sentenceUtility.getLastWordOfSentence(newSentenceString);
        }
        
        this.sentence = newSentenceString;
        this.authorOfBookSentenceIsFrom = newSentence.getAuthorOfBookSentenceIsFrom();
        this.titleOfBookSentenceIsFrom = newSentence.getAuthorOfBookSentenceIsFrom();
    }
    
    public String getSentence()
    {
    	return sentence;
    }
    
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public SentenceUtility getSentenceUtility() {
		return sentenceUtility;
	}

	public void setSentenceUtility(SentenceUtility sentenceUtility) {
		this.sentenceUtility = sentenceUtility;
	}

	public BookUtility getBookUtility() {
		return bookUtility;
	}

	public void setBookUtility(BookUtility bookUtility) {
		this.bookUtility = bookUtility;
	}

	public RandomSentence getSentenceToRhymeOffOf() {
		return sentenceToRhymeOffOf;
	}

	public void setSentenceToRhymeOffOf(RandomSentence sentenceToRhymeOffOf) {
		this.sentenceToRhymeOffOf = sentenceToRhymeOffOf;
	}

	public String getTitleOfBookSentenceIsFrom() {
		return titleOfBookSentenceIsFrom;
	}

	public void setTitleOfBookSentenceIsFrom(String titleOfBookSentenceIsFrom) {
		this.titleOfBookSentenceIsFrom = titleOfBookSentenceIsFrom;
	}

	public String getAuthorOfBookSentenceIsFrom() {
		return authorOfBookSentenceIsFrom;
	}

	public void setAuthorOfBookSentenceIsFrom(String authorOfBookSentenceIsFrom) {
		this.authorOfBookSentenceIsFrom = authorOfBookSentenceIsFrom;
	}
	
}

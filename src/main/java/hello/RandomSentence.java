package hello;

import java.io.IOException;
import java.util.Random;

public class RandomSentence
{
	
	/*
	 * 
	 */
	private BookUtility bookUtility;
	
	/*
	 * 
	 */
	private SentenceUtility sentenceUtility;
	
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
	public RandomSentence(BookUtility bookUtility) throws IOException
	{
		this.bookUtility = bookUtility;
		sentenceUtility = new SentenceUtility();
		
		// Find the random sentence
		findRandomSentence(bookUtility.getRandomBook());
		
		// If the sentence doesn't match criteria, keep searching until you get one
		while (!sentenceUtility.checkIfSentenceMatchesCriteria(sentence, true))
		{
			findRandomSentence(bookUtility.getRandomBook());
		}
	}
	
    /*
     * Returns random sentence.
     */
    public void findRandomSentence(Book book) throws IOException
    {
    	String bookText = book.getText();
    	String sentence = "";
    	
    	// Checks length, checks if it contains digits, etc.
    	while ((sentence.length() < 25 || sentence.length() > 50) ||
    			sentence.contains("CHAPTER") ||
    			sentence.matches(".*\\d+.*"))
    	{
        	int startingPoint = randomizeStartingPoint(bookText);
        	
        	int firstCharIndex = bookText.indexOf(".", startingPoint);
        	
        	int a = bookText.indexOf(".", firstCharIndex + 1);
        	int b = bookText.indexOf(",", firstCharIndex + 1);
        	int c = bookText.indexOf("!", firstCharIndex + 1);
        	int d = bookText.indexOf("?", firstCharIndex + 1);
        	
        	// Trim the sentence to the next . or , or ! or ?
        	if (a > 0 && b > 0 && c > 0 && d > 0)
        	{
        		sentence = bookText.substring(firstCharIndex + 1, Math.min(Math.min(a,b), Math.min(c, d)));
        	}
        	else
        	{
        		sentence = bookText.substring(firstCharIndex + 1, a);
        	}
        	//System.out.println(". . . trying to add: " + cleanString(sentence));
    	}

    	this.sentence = cleanString(sentence);
    	this.authorOfBookSentenceIsFrom = book.getAuthor();
    	this.titleOfBookSentenceIsFrom = book.getTitle();
    }
    
    /*
     * Generate random integer to start as charAt in book to discover sentences.
     */
    private int randomizeStartingPoint(String book)
    {
    	Random rn = new Random();
    	int bookLength = book.length() - 500;
    	int range = bookLength;
    	int randomNum =  rn.nextInt(range) + 0;
    	return randomNum;
    }
    
    public String cleanString(String sentence)
    {
    	sentence = sentence.replace("\n", "");
    	sentence = sentence.replace("\"", "");
    	sentence = sentence.replace("“", "");
    	sentence = sentence.replace("”", "");
    	sentence = sentence.replace("(", "");
    	sentence = sentence.replace(")", "");
    	sentence = sentence.replace("_", "");
    	sentence = sentence.replace("“", "\"");
    	sentence = sentence.replace("”", "\"");
    	sentence = sentence.replace("�", "");
    	return sentence;
    }
    
	public void setSentence(String sentence)
	{
		this.sentence = sentence;
	}
	
    public String getSentence()
    {
    	return sentence;
    }

	public BookUtility getBookUtility() {
		return bookUtility;
	}

	public void setBookUtility(BookUtility bookUtility) {
		this.bookUtility = bookUtility;
	}

	public SentenceUtility getSentenceUtility() {
		return sentenceUtility;
	}

	public void setSentenceUtility(SentenceUtility sentenceUtility) {
		this.sentenceUtility = sentenceUtility;
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

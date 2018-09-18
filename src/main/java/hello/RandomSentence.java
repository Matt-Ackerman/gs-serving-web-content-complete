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
	public RandomSentence() throws IOException
	{
		bookUtility = new BookUtility();
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
    public void findRandomSentence(String book) throws IOException
    {

    	String sentence = "";
    	
    	// Checks length, checks if it contains digits, etc.
    	while ((sentence.length() < 25 || sentence.length() > 50) ||
    			sentence.contains("CHAPTER") ||
    			sentence.matches(".*\\d+.*"))
    	{
        	int startingPoint = randomizeStartingPoint(book);
        	
        	int firstCharIndex = book.indexOf(".", startingPoint);
        	
        	int a = book.indexOf(".", firstCharIndex + 1);
        	int b = book.indexOf(",", firstCharIndex + 1);
        	int c = book.indexOf("!", firstCharIndex + 1);
        	int d = book.indexOf("?", firstCharIndex + 1);
        	
        	// Trim the sentence to the next . or , or ! or ?
        	if (a > 0 && b > 0 && c > 0 && d > 0)
        	{
        		sentence = book.substring(firstCharIndex + 1, Math.min(Math.min(a,b), Math.min(c, d)));
        	}
        	else
        	{
        		sentence = book.substring(firstCharIndex + 1, a);
        	}
        	System.out.println(". . . trying to add: " + cleanString(sentence));
    	}

    	this.sentence = cleanString(sentence);
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
    	//if (false)//Character.isWhitespace(sentence.charAt(1)))
    	//{
    	//	sentence = sentence.substring(2, sentence.length());
    	//}
    	return sentence;
    }
    
    public String getSentence()
    {
    	return sentence;
    }

}

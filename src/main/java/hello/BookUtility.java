package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ca.rmen.rhymer.RhymeResult;
import ca.rmen.rhymer.Rhymer;
import ca.rmen.rhymer.cmu.CmuDictionary;

public class BookUtility {
	
	public static void main(String[] args) throws IOException {
		
		BookUtility bookUtility = new BookUtility();
		String book = bookUtility.createStringFromBookFile("book.txt");
		String book2 = bookUtility.createStringFromBookFile("book2.txt");
		String book3 = bookUtility.createStringFromBookFile("book3.txt");
		String book4 = bookUtility.createStringFromBookFile("book4.txt");
		String book5 = bookUtility.createStringFromBookFile("book5.txt");
		ArrayList<String> books = new ArrayList<String>();
		books.add(book);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
		
		ArrayList<String> poem = new ArrayList<String>();
		// create and print poem
		for (int i = 0; i < 2; i++)
		{
	    	poem.addAll(bookUtility.createStanza(books));
		}
		
		for (int i = 0; i < poem.size(); i++)
		{
			System.out.println(poem.get(i));
			if (i == 3 || i == 7)
			{
				System.out.println();
			}
		}
		
	}
	
	/*
	 * Creates a String object from a book text file.
	 */
	public String createStringFromBookFile(String fileName) throws IOException {
    	Class clazz = GreetingController.class;
        InputStream inputStream = clazz.getResourceAsStream(fileName);
        String book = readFromInputStream(inputStream);
        return book;
	}
	
	/*
	 * For reading book.
	 */
    private String readFromInputStream(InputStream inputStream) throws IOException {
	    StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append(" \n");
	        }
	    }
	  return resultStringBuilder.toString();
    }
    
    public ArrayList<String> createStanza(ArrayList<String> books) throws IOException
    {
    	ArrayList<String> stanza = new ArrayList<String>();
    	
    	Random rn = new Random();
    	int randomBook = rn.nextInt(books.size()-1);
    	
		for (int i = 0; i < 2; i++)
		{
			String randomSentence = findRandomSentence(books.get(randomBook));
	    	while (!checkIfSentenceMatchesCriteria(randomSentence, true))
	    	{
	    		randomBook = rn.nextInt(books.size());
	    		randomSentence = findRandomSentence(books.get(randomBook));
	    		System.out.println(". . . trying to add" + randomSentence);
	    	}
	    	stanza.add(randomSentence);
	    	System.out.println("!!!!!!!!!! ADDED " + randomSentence);
	    	
	    	String secondSentence = findRhymingSentence(books.get(randomBook), randomSentence);
	    	while (!checkIfSentenceMatchesCriteria(secondSentence, false))
	    	{
	    		randomBook = rn.nextInt(books.size()-1);
	    		secondSentence = findRhymingSentence(books.get(randomBook), randomSentence);
	    		System.out.println(". . . trying to add" + secondSentence);
	    	}
	    	stanza.add(secondSentence);
	    	System.out.println("!!!!!!!!!! ADDED " + secondSentence);
		}
		return stanza;
    }
    
    /*
     * Finds a corresponding rhyming sentence for the provided sentence in the provided book.
     */
    public String findRhymingSentence(String book, String sentence) throws IOException
    {
    	ArrayList<String> wordsThatRhymeWithSentence = getRhymingWordsForSentence(sentence);
    	
		String rhymingSentence = findRandomSentence(book);
		
        String lastWordFromRhymingSentence = getLastWordOfSentence(rhymingSentence);
        
        while (!wordsThatRhymeWithSentence.contains(lastWordFromRhymingSentence))
        {
            rhymingSentence = findRandomSentence(book);
            lastWordFromRhymingSentence = getLastWordOfSentence(rhymingSentence);
        }
        
        return rhymingSentence;
		
    }
    
    public static ArrayList<String> getRhymingWordsForSentence(String sentence) throws IOException
    {
    	String lastWordFromSentence = getLastWordOfSentence(sentence);
    	
    	Rhymer rhymer = CmuDictionary.loadRhymer();
		List<RhymeResult> results = rhymer.getRhymingWords(lastWordFromSentence);
		
		ArrayList<String> rhymingWords = new ArrayList<String>();
		for (RhymeResult result : results)
		{
			rhymingWords.addAll(Arrays.asList(result.strictRhymes));
			rhymingWords.addAll(Arrays.asList(result.oneSyllableRhymes));
			rhymingWords.addAll(Arrays.asList(result.twoSyllableRhymes));
			rhymingWords.addAll(Arrays.asList(result.threeSyllableRhymes));
		}
		
        return rhymingWords;
    }
    
    /*
     * Checks sentence criteria to make sure it will work in a poem.
     */
    private static boolean checkIfSentenceMatchesCriteria(String sentence, boolean isSentenceToBeRhymedOffOf) throws IOException
    {
    	// For any sentence
    	if (sentence.length() < 25 || sentence.length() > 40)
    	{
    		return false;
    	}
    	if (!sentence.contains(" "))
    	{
    		return false;
    	}
    	if (getLastWordOfSentence(sentence).equals("Mr") || getLastWordOfSentence(sentence).equals("Mrs"))
    	{
    		return false;
    	}
    	
    	// For only the first sentence
    	if (isSentenceToBeRhymedOffOf)
    	{
        	ArrayList<String> rhymingWords = getRhymingWordsForSentence(sentence);
        	if (rhymingWords.size() < 3)
        	{
        		return false;
        	}
    	}
    	
    	return true;
    }
    
    /*
     * Returns last word of a provided sentence
     */
    private static String getLastWordOfSentence(String sentence)
    {
    	String lastWord = "";
    	if (sentence.contains(" "))
    	{
    		lastWord = sentence.substring(sentence.lastIndexOf(" ")+1);
    	}

    	return lastWord;
    }
    
    /*
     * Returns random sentence.
     */
    public String findRandomSentence(String book) throws IOException
    {
    	int startingPoint = randomizeStartingPoint(book);
    	
    	int firstCharIndex = book.indexOf(".", startingPoint);
    	
    	int a = book.indexOf(".", firstCharIndex + 1);
    	int b = book.indexOf(",", firstCharIndex + 1);
    	int c = book.indexOf("!", firstCharIndex + 1);
    	
    	String sentence = "";
    	
    	if (a > 0 && b > 0 && c > 0)
    	{
    		sentence = book.substring(firstCharIndex + 1, Math.min(a, Math.min(b, c)));
    	}
    	else
    	{
    		sentence = book.substring(firstCharIndex + 1, a);
    	}

    	sentence = cleanString(sentence);
    	
    	
    	return sentence;
    }
    
    public String cleanString(String sentence)
    {
    	sentence = sentence.replace("\n", "");
    	sentence = sentence.replace("\"", "");
    	sentence = sentence.replace("“", "");
    	sentence = sentence.replace("”", "");
    	sentence = sentence.replace("(", "");
    	sentence = sentence.replace(")", "");
    	
    	//if (false)//Character.isWhitespace(sentence.charAt(1)))
    	//{
    	//	sentence = sentence.substring(2, sentence.length());
    	//}
    	return sentence;
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

}

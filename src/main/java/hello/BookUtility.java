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
		
		BookUtility b = new BookUtility();
		String book = b.createStringFromBookFile();
		
		String randomSentence = b.findRandomSentence(book);
		System.out.println(randomSentence);
		//System.out.println(b.getLastWordOfSentence(randomSentence));
		
		System.out.println(b.findRhymingSentence(book, randomSentence));
		
	}
	
	/*
	 * Creates a String object from a book text file.
	 */
	public String createStringFromBookFile() throws IOException {
    	Class clazz = GreetingController.class;
        InputStream inputStream = clazz.getResourceAsStream("book.txt");
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
	            resultStringBuilder.append(line).append("\n");
	        }
	    }
	  return resultStringBuilder.toString();
    }
    
    /*
     * Finds a corresponding rhyming sentence for the provided sentence in the provided book.
     */
    public String findRhymingSentence(String book, String sentence) throws IOException
    {
		ArrayList<String> wordsThatRhymeWithSentence = getRhymingWordsForSentence(sentence);
		
        // TODO: this is the slowest way ever
        String hopefulRhyme = findRandomSentence(book);
        String lastWordFromHopefulRhyme = getLastWordOfSentence(hopefulRhyme);
        
        while (!wordsThatRhymeWithSentence.contains(lastWordFromHopefulRhyme))
        {
            hopefulRhyme = findRandomSentence(book);
            lastWordFromHopefulRhyme = getLastWordOfSentence(hopefulRhyme);
        }
        
        return hopefulRhyme;
		
    }
    
    private ArrayList<String> getRhymingWordsForSentence(String sentence) throws IOException
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
		
        for (String rhyme : rhymingWords)
        {
        	System.out.println(rhyme);
        }
        
        return rhymingWords;
    }
    
    /*
     * Checks sentence criteria to make sure it will work in a poem.
     */
    private boolean checkIfSentenceMatchesCriteria(String sentence)
    {
    	if (sentence.length() > 20 && sentence.length() < 80)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
//    private boolean checkIfTwoSentencesRhyme(String sentence1, String sentence2)
//    {
//    	String word1 = getLastWordOfSentence(sentence1);
//    	String word2 = getLastWordOfSentence(sentence2);
//    	
//    }
    
    /*
     * Returns last word of a provided sentence
     */
    private String getLastWordOfSentence(String sentence)
    {
    	String lastWord = sentence.substring(sentence.lastIndexOf(" ")+1);
    	return lastWord;
    }
    
    /*
     * Returns random sentence.
     */
    public String findRandomSentence(String book)
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

    	sentence = sentence.replace("\n", "");
    	sentence = sentence.replace("\"", "");
    	sentence = sentence.replace("“", "");
    	sentence = sentence.replace("”", "");
    	
    	if (checkIfSentenceMatchesCriteria(sentence))
    	{
    		return sentence;
    	}
    	else
    	{
    		return findRandomSentence(book);
    	}
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

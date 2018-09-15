package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.rmen.rhymer.RhymeResult;
import ca.rmen.rhymer.Rhymer;
import ca.rmen.rhymer.cmu.CmuDictionary;

public class SentenceUtility
{
	
	/*
	 * 
	 */
	public SentenceUtility()
	{
		
	}
	
	/*
	 * 
	 */
    public ArrayList<String> getRhymingWordsForSentence(String sentence) throws IOException
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
     * Returns last word of a provided sentence
     */
    public String getLastWordOfSentence(String sentence)
    {
    	String lastWord = "";
    	if (sentence.contains(" "))
    	{
    		lastWord = sentence.substring(sentence.lastIndexOf(" ")+1);
    	}

    	return lastWord;
    }
    
    /*
     * Checks sentence criteria to make sure it will work in a poem.
     */
    public boolean checkIfSentenceMatchesCriteria(String sentence, boolean isSentenceToBeRhymedOffOf) throws IOException
    {
    	// For any sentence
    	if (sentence.length() < 20 || sentence.length() > 50)
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
        	if (rhymingWords.size() < 50)
        	{
        		return false;
        	}
    	}
    	
    	return true;
    }

}

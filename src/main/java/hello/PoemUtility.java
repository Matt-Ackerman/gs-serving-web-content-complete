package hello;

import java.io.IOException;
import java.util.ArrayList;

public class PoemUtility
{
	
	/*
	 * 
	 */
	public PoemUtility()
	{
		
	}

	/*
	 * 
	 */
    public ArrayList<String> createStanza() throws IOException
    {
    	ArrayList<String> stanza = new ArrayList<String>();
    	
		for (int i = 0; i < 2; i++)
		{
			RandomSentence randomSentence = new RandomSentence();
	    	stanza.add(randomSentence.getSentence());
	    	System.out.println("!!!!!!!!!! ADDED " + randomSentence.getSentence());
	    	
			RhymingSentence rhymingSentence = new RhymingSentence(randomSentence);
	    	stanza.add(rhymingSentence.getSentence());
	    	System.out.println("!!!!!!!!!! ADDED " + rhymingSentence.getSentence());
		}
		return stanza;
    }
	
}

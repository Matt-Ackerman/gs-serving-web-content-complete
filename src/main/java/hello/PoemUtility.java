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
    public ArrayList<PoemLine> createStanza() throws IOException
    {
    	ArrayList<PoemLine> stanza = new ArrayList<PoemLine>();
    	
		for (int i = 0; i < 2; i++)
		{
			RandomSentence randomSentence = new RandomSentence();
			PoemLine randomLine = new PoemLine(randomSentence.getSentence(),
					randomSentence.getTitleOfBookSentenceIsFrom(),
					randomSentence.getAuthorOfBookSentenceIsFrom());

	    	stanza.add(randomLine);
	    	System.out.println("!!!!!!!!!! ADDED " + randomLine.getSentence());
	    	
			RhymingSentence rhymingSentence = new RhymingSentence(randomSentence);
			PoemLine rhymingLine = new PoemLine(rhymingSentence.getSentence(),
					rhymingSentence.getTitleOfBookSentenceIsFrom(),
					rhymingSentence.getAuthorOfBookSentenceIsFrom());

	    	stanza.add(rhymingLine);
	    	System.out.println("!!!!!!!!!! ADDED " + rhymingLine.getSentence());
		}
		return stanza;
    }
	
}

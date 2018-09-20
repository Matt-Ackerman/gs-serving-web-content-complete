package hello;

import java.io.IOException;
import java.util.ArrayList;

public class Poem {

	private ArrayList<PoemLine> poemLines;
	
	public Poem() throws IOException
	{
		PoemUtility poemUtility = new PoemUtility();
		
		poemLines = new ArrayList<PoemLine>();
		// create and print poem
		for (int i = 0; i < 2; i++)
		{
	    	poemLines.addAll(poemUtility.createStanza());
		}
	}
	
	public ArrayList<PoemLine> getPoemLines()
	{
		return poemLines;
	}
	
}

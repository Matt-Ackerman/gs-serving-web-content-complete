package hello;

import java.io.IOException;
import java.util.ArrayList;

public class Poem {

	private ArrayList<String> poemLines = new ArrayList<String>();
	
	public Poem() throws IOException
	{
		PoemUtility poemUtility = new PoemUtility();

		ArrayList<String> poem = new ArrayList<String>();
		// create and print poem
		for (int i = 0; i < 2; i++)
		{
	    	poemLines.addAll(poemUtility.createStanza());
		}
	}
	
	public ArrayList<String> getPoemLines()
	{
		return poemLines;
	}
	
}

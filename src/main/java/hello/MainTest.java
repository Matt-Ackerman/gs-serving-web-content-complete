package hello;

import java.io.IOException;
import java.util.ArrayList;

public class MainTest
{
	
	public static void main(String[] args) throws IOException
	{
		
		PoemUtility poemUtility = new PoemUtility();

		ArrayList<String> poem = new ArrayList<String>();
		// create and print poem
		for (int i = 0; i < 2; i++)
		{
	    	poem.addAll(poemUtility.createStanza());
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
}

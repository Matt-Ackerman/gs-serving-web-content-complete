package hello;

import java.io.IOException;
import java.util.ArrayList;

public class MainTest
{
	
	public static void main(String[] args) throws IOException
	{
		Poem poem = new Poem();
		for (PoemLine poemLine : poem.getPoemLines())
		{
			System.out.println(poemLine.getTitle() + " - " + poemLine.getSentence());
		}
	}
}

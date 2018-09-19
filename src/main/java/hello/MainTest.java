package hello;

import java.io.IOException;
import java.util.ArrayList;

public class MainTest
{
	
	public static void main(String[] args) throws IOException
	{
		Poem poem = new Poem();
		for (String poemLine : poem.getPoemLines())
		{
			System.out.println(poemLine);
		}
	}
}

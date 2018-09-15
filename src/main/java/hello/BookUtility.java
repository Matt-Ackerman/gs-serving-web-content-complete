package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class BookUtility {
	
	/*
	 * 
	 */
	private ArrayList<String> books;
	
	/*
	 * 
	 */
	public BookUtility() throws IOException
	{
		String book = createStringFromBookFile("book.txt");
		String book2 = createStringFromBookFile("book2.txt");
		String book3 = createStringFromBookFile("book3.txt");
		String book4 = createStringFromBookFile("book4.txt");
		String book5 = createStringFromBookFile("book5.txt");
		
		books = new ArrayList<String>();
		books.add(book);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
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
    
    /*
     * 
     */
    public String getRandomBook()
    {
    	Random rn = new Random();
    	int randomBook = rn.nextInt(books.size()-1);
    	return books.get(randomBook);
    }
    
    /*
     * 
     */
    public ArrayList<String> getBooks()
    {
    	return books;
    }

}

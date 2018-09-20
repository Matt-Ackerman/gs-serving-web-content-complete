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
	private ArrayList<Book> books;
	
	/*
	 * 
	 */
	public BookUtility() throws IOException
	{
		Book book1 = new Book("Pride and Predjudice", "Jane Austen",
				createStringFromBookFile("Pride and Prejudice.txt"));
		Book book2 = new Book("A Tale of Two Cities", "Charles Dickens",
				createStringFromBookFile("A Tale of Two Cities.txt"));
		Book book3 = new Book("The Adventures of Tom Sawyer", "Mark Twain",
				createStringFromBookFile("The Adventures of Tom Sawyer.txt"));
		Book book4 = new Book("The Iliad of Homer", "Homer",
				createStringFromBookFile("The Iliad of Homer.txt"));
		Book book5 = new Book("Treasure Island", "Robert Louis Stevenson",
				createStringFromBookFile("Treasure Island.txt"));
		
		books = new ArrayList<Book>();
		books.add(book1);
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
    public Book getRandomBook()
    {
    	Random rn = new Random();
    	int randomBook = rn.nextInt(books.size()-1);
    	return books.get(randomBook);
    }
    
    /*
     * 
     */
    public ArrayList<Book> getBooks()
    {
    	return books;
    }

}

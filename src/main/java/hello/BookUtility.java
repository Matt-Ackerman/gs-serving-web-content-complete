package hello;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BookUtility {
	
	/*
	 * 
	 */
	private ArrayList<Book> books;
	
	/*
	 * 
	 */
	public BookUtility()
	{
		try {
			books = createBooks();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Creates a String object from a book text file.
	 */
	public ArrayList<Book> createBooks() throws IOException {
		
		ArrayList<Book> books = new ArrayList<>();
		
		Files.list(new File("books/").toPath()).forEach(path -> {
		    File file = path.toFile();
	        try {
				InputStream inputStream = new FileInputStream(file);
				HashMap<String, String> bookInfo = readFromInputStream(inputStream);
				Book book = new Book(bookInfo.get("title"), bookInfo.get("author"), bookInfo.get("text"));
				books.add(book);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println("!!!!!! --    Book Count    -- !!!!!!   " + books.size());
		return books;
	}
	
	/*
	 * For reading book.
	 */

    private HashMap<String, String> readFromInputStream(InputStream inputStream) throws IOException
    {
    	
	    StringBuilder title = new StringBuilder();
	    StringBuilder author = new StringBuilder();
	    StringBuilder text = new StringBuilder();
	    
	    HashMap<String, String> bookData = new HashMap<>();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)))
	    {
	        String line;
	        int i = 0;
	        while ((line = br.readLine()) != null)
	        {
	        	if (i < 50)
	        	{
		        	if (line.contains("Title:"))
		        	{
		        		title.append(line).append(" \n");
		        	}
		        	else if (line.contains("Author:"))
		        	{
		        		author.append(line).append(" \n");
		        	}
	        	}
	        	else
	        	{
	        		text.append(line).append(" \n");	
	        	}
	        	i++;
	        }
	        
	        bookData.put("title", title.substring(title.indexOf(":") + 2));
	        bookData.put("text", text.toString());
	        bookData.put("author", author.substring(author.indexOf(":") + 2));
	    }
	    return bookData;
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

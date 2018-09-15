package hello;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws IOException {
        
		BookUtility bookUtility = new BookUtility();
		//String book = bookUtility.createStringFromBookFile();
		
		ArrayList<String> poem = new ArrayList<String>();
		// create and print poem
		for (int i = 0; i < 2; i++)
		{
	    //	poem.addAll(bookUtility.createStanza(book));
		}
		
		for (int i = 0; i < poem.size(); i++)
		{
			System.out.println(poem.get(i));
			if (i == 3 || i == 7)
			{
				System.out.println();
			}
		}
    	
    	
    	model.addAttribute("poem", poem);
        return "greeting";
    }

}

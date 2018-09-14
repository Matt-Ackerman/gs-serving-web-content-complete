package hello;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws IOException {
        
		BookUtility b = new BookUtility();
		String book = b.createStringFromBookFile();
		
		String randomSentence = b.findRandomSentence(book);
		System.out.println(randomSentence);
		//System.out.println(b.getLastWordOfSentence(randomSentence));
		
		System.out.println(b.findRhymingSentence(book, randomSentence));
    	
    	
    	
    	model.addAttribute("randomSentence", randomSentence);
        model.addAttribute("rhymingSentence", b.findRhymingSentence(book, randomSentence));
        return "greeting";
    }

}

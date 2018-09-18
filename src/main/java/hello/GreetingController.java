package hello;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GreetingController {
	
	private PoemLine poemLine;
	
	public GreetingController()
	{
    	poemLine = new PoemLine();
        poemLine.setBookTitle("book titleeee");
        poemLine.setSentence("sentenceeee");
	}

//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws IOException {
//        
//		PoemUtility poemUtility = new PoemUtility();
//
//		ArrayList<String> poem = new ArrayList<String>();
//		// create and print poem
//		for (int i = 0; i < 2; i++)
//		{
//	    	poem.addAll(poemUtility.createStanza());
//		}
//		
//		for (int i = 0; i < poem.size(); i++)
//		{
//			System.out.println(poem.get(i));
//			if (i == 3 || i == 7)
//			{
//				System.out.println();
//			}
//		}
//
//    	model.addAttribute("poem", poem);
//        return "greeting";
//    }
    
    @RequestMapping(value = "/get-poem", method = RequestMethod.GET)
    public @ResponseBody String send() throws Exception {
    	
		PoemUtility poemUtility = new PoemUtility();

		ArrayList<String> poem = new ArrayList<String>();
		// create and print poem
		for (int i = 0; i < 2; i++)
		{
	    	poem.addAll(poemUtility.createStanza());
		}
        
		String s = poem.toString();
		return s;
    }

}

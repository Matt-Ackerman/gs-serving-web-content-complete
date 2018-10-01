package hello;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
    
    @RequestMapping(value = "/get-poem", method = RequestMethod.GET)
    public @ResponseBody String send(String sentence, int poemRoundNumber) throws Exception {
    	
		if (sentence.equals(""))
		{
			RandomSentence randomSentence = new RandomSentence();
			String jsonString = new JSONObject()
	                  .put("sentence", randomSentence.getSentence())
	                  .put("title", randomSentence.getTitleOfBookSentenceIsFrom())
	                  .put("author", randomSentence.getAuthorOfBookSentenceIsFrom())
	                  .put("poemRoundNumber", poemRoundNumber)
	                  .toString();
			System.out.println(randomSentence.getSentence());
			return jsonString;
		}
		else
		{
			RhymingSentence rhymingSentence = new RhymingSentence(sentence);
			String jsonString = new JSONObject()
	                  .put("sentence", rhymingSentence.getSentence())
	                  .put("title", rhymingSentence.getTitleOfBookSentenceIsFrom())
	                  .put("author", rhymingSentence.getAuthorOfBookSentenceIsFrom())
	                  .put("poemRoundNumber", poemRoundNumber)
	                  .toString();
			System.out.println(rhymingSentence.getSentence() + "..........." + sentence);
			return jsonString;
		}
    }

}

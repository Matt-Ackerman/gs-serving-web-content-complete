package hello;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
    
    @RequestMapping(value = "/get-poem", method = RequestMethod.GET)
    public @ResponseBody String send() throws Exception {
		Poem poem = new Poem();
		JSONArray jsArray = new JSONArray(poem.getPoemLines());
		return jsArray.toString();
    }

}

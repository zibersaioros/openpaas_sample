package openpaas.sample.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class RootController {
	
	@Autowired(required=false)
	RedisTemplate<String, String> redisTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(RootController.class);

	@RequestMapping("/")
	public String root(){
		return "redirect:/login";
	}
	
	@RequestMapping(value="/main/{id}", method=RequestMethod.GET)
	public String manageView(HttpSession session,@PathVariable("id") String params){
		
		if(session.getAttribute("idKey") != null){
			String idKey = session.getAttribute("idKey").toString();
			String passwordKey = session.getAttribute("passwordKey").toString();
			
			String id = null; String password = null;
			
			if(redisTemplate != null){
				id = redisTemplate.opsForValue().get(idKey);
				password = redisTemplate.opsForValue().get(passwordKey);
			} else {
				id = idKey;
				password = passwordKey;
			}
			
			if("admin".equals(id) && "admin".equals(password)){
//				return "redirect:/main/"+params;
				return "main";
			}
		}
		
		session.invalidate();
		return "redirect:/login";
	}
}

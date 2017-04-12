package openpaas.sample.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@Autowired(required=false)
	RedisTemplate<String, String> redisTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(ManageController.class);
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String manageView(HttpSession session){
		
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
				return "manage";
			}
		}
		
		session.invalidate();
		return "redirect:/login";
	}

	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestBody Map<String, String> map, HttpSession session) throws Exception{
		
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String adminId = "admin";
		String adminPassword = "admin";
		String id = map.get("id");
		String password = map.get("password");
		
		if(adminId.equals(id) 
				&& adminPassword.equals(password) ){
			String idKey = null; String passwordKey = null;
			
			if(redisTemplate != null ){
				idKey = RandomStringUtils.random(16);
				passwordKey = RandomStringUtils.random(16);
				
				redisTemplate.opsForValue().set(idKey, id);
				redisTemplate.opsForValue().set(passwordKey, password);				
			} else {
				idKey = id;
				passwordKey = password;
			}
			
			session.setAttribute("idKey", idKey);
			session.setAttribute("passwordKey", passwordKey);
		}
		else{
			session.invalidate();
			throw new Exception("Invalid name and password");
		}
	
		return responseMap;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logout(HttpSession session){
		
		Map<String, Object> responseMap = new HashMap<String, Object>();

		responseMap.put("status", "200 OK");
		responseMap.put("error", "no error");
		
		session.removeAttribute("idKey");
		session.removeAttribute("passwordKey");
		session.invalidate();
	
		return responseMap;
	}
}

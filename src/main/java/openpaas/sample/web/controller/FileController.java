package openpaas.sample.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileController {
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired(required=false)
	private Account account;
	
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") MultipartFile multipartFile) throws Exception{
		logger.info(multipartFile.getClass().getName());
		logger.info("file name : " +multipartFile.getOriginalFilename());
		logger.info("file size : " + multipartFile.getSize());
		logger.info("file type : " + multipartFile.getContentType());
		
		if(account == null)
			throw new Exception("No GlusterFS");
		
		Container container = account.getContainer("images");
		if(!container.exists()){
			container.create();
			container.makePublic();
		}
		
		//upload
        String fileName = System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
        StoredObject object = container.getObject(fileName);
        object.uploadObject(multipartFile.getInputStream());
        
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("thumb_img_path", object.getPublicURL());
		return responseMap;
	}
}

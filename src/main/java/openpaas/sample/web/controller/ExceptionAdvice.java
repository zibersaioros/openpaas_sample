package openpaas.sample.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleHttpMessageNotRedableException(Exception ex){
		ex.printStackTrace();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("error", ex.getMessage());
		return response;
	}
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleException(Exception ex){
		ex.printStackTrace();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("error", ex.getMessage());
		return response;
	}

}

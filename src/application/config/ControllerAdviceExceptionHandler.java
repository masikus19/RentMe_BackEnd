package application.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import application.security.services.BadRequestException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler({BadRequestException.class})
	public ResponseEntity<String> exceptionHandler(RuntimeException ex, HttpServletRequest request){
		String payload = "path: " + request.getRequestURI()+"; msg: " + ex.getMessage();
		return new ResponseEntity<String>(payload, HttpStatus.BAD_REQUEST);
	}
}

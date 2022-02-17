package application.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
//	@ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<String> exceptionHandlerValid(MethodArgumentNotValidException ex) {
//        return new ResponseEntity<String>(ex.getFieldError().getField()+" is does not match requirements ",HttpStatus.BAD_REQUEST);
//    }
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	  public ResponseEntity<List<String>> handleBodyException(MethodArgumentNotValidException exception) {
			List<String> result = exception.getBindingResult().getFieldErrors().stream()
					.map(err -> err.getDefaultMessage() + err.getRejectedValue())
					.collect(Collectors.toList());
			
			return new ResponseEntity<List<String>>(result, HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	  public ResponseEntity<List<String>> handleParamException(ConstraintViolationException exception) {
			
			List<String> result = exception.getConstraintViolations().stream()
							.map((e)-> e.getMessage()+": " + e.getInvalidValue())
							.collect(Collectors.toList());
			
			return new ResponseEntity<List<String>>(result, HttpStatus.BAD_REQUEST);
	  }
	
//	@ExceptionHandler({ConstraintViolationException.class})
//    public ResponseEntity<String> exceptionHandler1(ConstraintViolationException ex) {
//        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
//    }

}

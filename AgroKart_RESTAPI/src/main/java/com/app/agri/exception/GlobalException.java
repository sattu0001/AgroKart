package com.app.agri.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.agri.dto.Response;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ContentNotFound.class)
	public final ResponseEntity<?> handleContentNotFoundException(ContentNotFound exception) {
		return Response.error(exception.getMessage());

	}
	

	@ExceptionHandler(value = InvalidRequestException.class)
	public final ResponseEntity<?> handleInvalidRequestException(InvalidRequestException exception) {
		return Response.error(exception.getMessage());

	}
	
	
	@ExceptionHandler(value = AlreadyExistsException.class)
	public final ResponseEntity<?> handleAlreadyExistsException(ContentNotFound exception) {
		return Response.error(exception.getMessage());

	}

	@ExceptionHandler(value = RuntimeException.class)
	public final ResponseEntity<?> handleRuntimeException(RuntimeException exception) {
		return Response.error(exception.getMessage());

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		errors=ex.getBindingResult().getFieldErrors().stream().map(f->f.getDefaultMessage().concat(", ")).collect(Collectors.toList());
		
		Map<String, Object> map = new HashMap<>();
		map.put("status", "error");
		if(errors != null)
			map.put("error", errors);
		
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

}

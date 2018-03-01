package org.websure.web.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationHelper<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidationHelper.class);
	
	private Validator validator;
	private Set<ConstraintViolation<T>> violations;
	
	public ValidationHelper(Validator v) {
		this.validator = v;
	}
	
	public ErrorResponse validateOncePerProperty(T obj) {
		
		violations = validator.validate(obj);
		
		if (!violations.isEmpty()) {
			
			Map<String, String> errors = new HashMap<>();
			
			for (ConstraintViolation<T> violation : this.violations) {
				
				String key = violation.getPropertyPath().toString();
				
				if (!errors.containsKey(key)) {
					
					errors.put(key, violation.getMessage());
					logger.debug(violation.getMessage());
				}
				
			}
			
			ErrorResponse err = new ErrorResponse(400, errors);
			
			return err;
		}	
		
		return null;
	}
	
	public ErrorResponse validate(T obj) {
		
		violations = validator.validate(obj);
		
		if (!violations.isEmpty()) {
			
			ErrorResponse err = new ErrorResponse(400);
			
			for (ConstraintViolation<T> violation : this.violations) {
				
			    logger.debug(violation.getMessage()); 
			    err.addError(violation.getMessage());
			}
			
			return err;
		}	
		
		return null;
	}	
	
}

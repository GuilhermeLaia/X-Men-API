package com.mercadolivre.xmen.validation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mercadolivre.xmen.exception.ServiceException;
import com.mercadolivre.xmen.request.Request;

@Component
public class Validation {

	public void validateRequest(Request request) throws ServiceException {
		if(StringUtils.isEmpty(request) || StringUtils.isEmpty(request.getDna()) || request.getDna().length == 0) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
		}
		
	} 
}

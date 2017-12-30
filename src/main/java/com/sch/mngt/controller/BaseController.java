package com.sch.mngt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.sch.mngt.dto.ErrorEntity;

public class BaseController {

	protected ErrorEntity setValidationError(BindingResult result) {
		ErrorEntity entity = new ErrorEntity();
		List<String> errorsMsg = new ArrayList<String>();
		for (ObjectError error : result.getAllErrors()) {
			errorsMsg.add(error.getDefaultMessage());
		}
		entity.setErrors(errorsMsg);
		entity.setHttpCode("400");
		entity.setUserMessage("Invalid data");
		entity.setDeveloperMessage("Invalid data");
		return entity;

	}

	protected ErrorEntity setErrorEntity(String message, String httpCode,Exception e) {
		ErrorEntity entity = new ErrorEntity();
		entity.setDeveloperMessage(message);
		entity.setUserMessage(message);
		entity.setHttpCode(httpCode);
	//	entity.setStackTraceElement(e.getStackTrace());
		return entity;

	}

}

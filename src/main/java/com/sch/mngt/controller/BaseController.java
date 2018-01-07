package com.sch.mngt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
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

	protected ErrorEntity setErrorEntity(String message, String httpCode, Exception e) {
		ErrorEntity entity = new ErrorEntity();
		entity.setDeveloperMessage(message);
		entity.setUserMessage(message);
		entity.setHttpCode(httpCode);
		// entity.setStackTraceElement(e.getStackTrace());
		return entity;

	}

	protected ErrorEntity validateHeaders(HttpServletRequest request) {
		ErrorEntity entity = new ErrorEntity();
		StringBuilder builder = new StringBuilder();

		String schoolId = request.getHeader("schoolId");
		if (StringUtils.isEmpty(schoolId)) {
			builder.append("Headers missing: " + "schoolId");
		}
		if (!StringUtils.isEmpty(builder.toString())) {
			entity.setDeveloperMessage(builder.toString());
			entity.setUserMessage(builder.toString());
			entity.setHttpCode("400");
			return entity;
		} else {
			return null;
		}
	}

}

package com.sch.mngt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
class AppConfig {

	@Bean("multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		System.out.println("multipartResolver() - start");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		int maxUploadSize = -1;
		multipartResolver.setMaxUploadSize(maxUploadSize);
		System.out.println("multipartResolver() - end");
		return multipartResolver;

	}

}

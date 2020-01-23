//package com.example.demo.config;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//@EnableWebMvc
//@EnableAutoConfiguration(exclude={BatchAutoConfiguration.class})
//public class AppWebConfig implements WebMvcConfigurer{
//	
//	@Bean
//	public MultipartResolver multipartResolver(){
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setMaxInMemorySize(10000000);
//		resolver.setMaxUploadSize(-1);
//		resolver.setDefaultEncoding("UTF-8");
//		return resolver;
//	}
//}

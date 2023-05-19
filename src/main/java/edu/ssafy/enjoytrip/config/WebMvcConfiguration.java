package edu.ssafy.enjoytrip.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.ssafy.enjoytrip.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//	private final List<String> patterns = Arrays.asList(
//				"/information/write", "/information/modify/*", "/information/delete/*", 
//				"/api/information/write", "/api/information/modify/*", "/api/information/delete/*");
//	
//	private LoginInterceptor loginInterceptor;
//	
//	public WebMvcConfiguration(LoginInterceptor loginInterceptor) {
//		this.loginInterceptor = loginInterceptor;
//	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(loginInterceptor)
//				.addPathPatterns(patterns);
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("GET", "POST", "PUT", "DELETE")
		.exposedHeaders("Authorization", "access-token")
		.maxAge(3600);
	}

	
}

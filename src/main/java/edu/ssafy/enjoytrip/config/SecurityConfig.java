package edu.ssafy.enjoytrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import edu.ssafy.enjoytrip.jwt.JwtAuthenticationEntryPoint;
import edu.ssafy.enjoytrip.jwt.JwtAuthenticationFilter;
import edu.ssafy.enjoytrip.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.formLogin().disable()
		.authorizeRequests()
		.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
		.antMatchers(HttpMethod.GET,"/api/information/**", "/attraction/**", "/api/plan/**", "/api/review/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/member/login", "/api/member/join").permitAll()
        .antMatchers("/", "/images/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .cors()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint);
		
		return http.build();
	}

}

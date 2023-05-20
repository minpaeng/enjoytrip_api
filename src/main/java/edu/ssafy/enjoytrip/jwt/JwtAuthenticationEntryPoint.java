package edu.ssafy.enjoytrip.jwt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ssafy.enjoytrip.dto.BasicDto;
import edu.ssafy.enjoytrip.dto.StatusEnum;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
	private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        setResponse(response);
        BasicDto exceptionDto = BasicDto.builder()
        		.message("forbidden")
        		.status(StatusEnum.FORBIDDEN)
        		.build();
        response.getWriter().print(convertObjectToJson(exceptionDto));
    }

    private void setResponse(HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        return mapper.writeValueAsString(object);
    }
}

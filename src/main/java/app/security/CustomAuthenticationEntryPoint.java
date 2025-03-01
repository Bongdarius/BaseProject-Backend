package app.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "UNAUTHORIZATION_EXCEPTION_HANDLER")
@AllArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        log.error("오류발생?", authException);

        // ErrorResponseDto errorResponseDto = new
        // ErrorResponseDto(HttpStatus.UNAUTHORIZED.value(),
        // authException.getMessage(), LocalDateTime.now());

        // String responseBody = objectMapper.writeValueAsString(errorResponseDto);
        // response.setContentType(MediaType.APPLICATION_JSON);
        // response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // response.setCharacterEncoding("UTF-8");
        // response.getWriter().write(responseBody);
    }
}
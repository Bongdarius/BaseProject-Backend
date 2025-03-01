package app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
                .allowedOrigins("http://localhost:3001") // 허용할 출처
                // .allowedOrigins("http://192.168.205.231:31080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowCredentials(true) // 자격 증명 허용
                .allowedHeaders("*") // 모든 헤더 허용
                .exposedHeaders("Content-Disposition"); // 노출할 헤더
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
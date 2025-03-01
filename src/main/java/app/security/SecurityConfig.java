package app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import app.security.service.CustomUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

        private final JwtUtil jwtUtil;
        private final CustomUserDetailsService customUserDetailsService;

        private static final String[] AUTH_WHITELIST = {
                        "/api/v1/member/**", "/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
                        "/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html", "/api/v1/auth/**"
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManagerBuilder auth)
                        throws Exception {

                // http.authorizeRequests(requests ->
                // requests.antMatchers("/swagger-resources/**").permitAll()
                // .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // .anyRequest().permitAll());

                // http.headers(headers -> headers.frameOptions().sameOrigin());

                // http.httpBasic(basic -> basic.disable());
                // http.csrf(csrf -> csrf.disable());
                // http.sessionManagement(management ->
                // management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                // http.cors(withDefaults());
                // http.formLogin(login -> login.disable());

                http.csrf((csrf) -> csrf.disable());
                http.cors(withDefaults());

                // 세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 X
                http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS));

                // FormLogin, BasicHttp 비활성화
                http.formLogin((form) -> form.disable());
                http.httpBasic(AbstractHttpConfigurer::disable);

                // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
                http.addFilterBefore(new JwtAuthFilter(customUserDetailsService, jwtUtil),
                                UsernamePasswordAuthenticationFilter.class);

                // http.exceptionHandling((exceptionHandling) -> exceptionHandling
                // .authenticationEntryPoint(authenticationEntryPoint)
                // .accessDeniedHandler(accessDeniedHandler));

                // 권한 규칙 작성
                http.authorizeHttpRequests(authorize -> authorize
                                .antMatchers(AUTH_WHITELIST).permitAll()
                                // @PreAuthrization을 사용할 것이기 때문에 모든 경로에 대한 인증처리는 Pass
                                .anyRequest().permitAll()
                // .anyRequest().authenticated()
                );

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}

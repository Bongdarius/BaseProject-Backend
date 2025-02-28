package app.auth.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import app.auth.dto.LoginDto;
import app.auth.dto.SignupDto;
import app.auth.entity.User;
import app.auth.repository.UserRepository;
import app.auth.service.AuthService;
import app.security.JwtToken;
import app.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory queryFactory;
    private final UserRepository repository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public String signup(SignupDto signupDto) throws Exception {

        String password = signupDto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        signupDto.setPassword(encodedPassword);

        User user = signupDto.toUser();

        repository.save(user);

        return null;
    }

    @Override
    public String login(LoginDto loginDto) throws Exception {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        User user = repository.findByUsername(username).orElseThrow(() -> new Exception("사용자 없음"));
        String encodedPassword = user.getPassword();

        boolean isMatch = passwordEncoder.matches(password, encodedPassword);

        if (!isMatch)
            throw new Exception("비밀번호 불일치");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken jwtToken = jwtTokenProvider.generateToken(username, authentication);

        System.out.println(jwtToken.getAccessToken());
        System.out.println(jwtToken.getGrantType());
        System.out.println(jwtToken.getRefreshToken());

        return "로그인 성공";
    }
}

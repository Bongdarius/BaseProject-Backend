package app.auth.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.auth.dto.ChangePasswordDto;
import app.auth.dto.LoginDto;
import app.auth.dto.SignupDto;
import app.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Validated
public class AuthController {

    private final AuthService service;

    @PostMapping(value = "/signup")
    public String singup(@RequestBody @Valid SignupDto signupDto) throws Exception {
        service.signup(signupDto);
        return "회원가입이 완료되었습니다.";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response)
            throws Exception {

        String token = service.login(loginDto);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping(value = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto)
            throws Exception {

        String msg = service.changePassword(changePasswordDto);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
package app.auth.service;

import app.auth.dto.ChangePasswordDto;
import app.auth.dto.LoginDto;
import app.auth.dto.SignupDto;

public interface AuthService {
    String signup(SignupDto signupDto) throws Exception;

    String login(LoginDto loginDto) throws Exception;

    String changePassword(ChangePasswordDto changePasswordDto) throws Exception;
}

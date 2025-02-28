package app.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import app.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotBlank(message = "사용자 아이디는 필수로 입력되어야 합니다.")
    @Size(min = 4, max = 20, message = "사용자 아이디는 반드시 4자 이상 20자 미만이어야 합니다.")
    String username;

    @NotBlank(message = "사용자 비밀번호는 필수로 입력되어야 합니다.")
    @Size(min = 7, max = 20, message = "비밀번호는 반드시 7자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{7,}$", message = "비밀번호는 영어, 숫자, 특수문자가 1자 이상 포함되어야 합니다.")
    String password;

    public User toUser() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}

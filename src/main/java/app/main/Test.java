package app.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/test")
public class Test {

    @GetMapping
    public String hello(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies(); // 모든 쿠키 가져오기

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();

                if (cookieName.equals("myCookieName")) { // 원하는 쿠키 이름으로 값 가져오기
                    return "쿠키 값 (myCookieName): " + cookieValue;
                }
            }
        }

        return "Hello, World!";
    }
}

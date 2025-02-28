package app.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/test")
public class Test {

    @GetMapping
    public String hello() {
        return "Hello, World!";
    }
}

package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Value("${welcome.message}")
    private String hello;

    public WelcomeController() {

    }

    public WelcomeController(String hello) {
        this.hello = hello;
    }

    @GetMapping("/")
    public String sayHello() {
        return hello;
    }
}
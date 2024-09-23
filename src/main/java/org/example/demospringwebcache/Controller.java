package org.example.demospringwebcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final MyService service;

    @Autowired
    public Controller(MyService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam int number) {
        System.out.println("Controller.hello " + number);

        long result = service.computePublic(number);

        return "hello: " + result;
    }

    @GetMapping("/bye")
    public String bye() {
        System.out.println("Controller.bye");

        service.clearCache();

        return "bye";
    }

    @GetMapping("/check-cache")
    public void checkCache() {
        service.checkCache();
    }
}

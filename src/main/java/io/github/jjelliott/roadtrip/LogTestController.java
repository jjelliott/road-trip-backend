package io.github.jjelliott.roadtrip;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    @GetMapping
    public String blah(){
        return "got it";
    }

    @GetMapping("/haha")
    public String bloop(){
        throw new RuntimeException("Haha");
     }
}

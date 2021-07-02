package com.example.eurekapost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Post/")
@Slf4j
public class EurekaPostController {
    @GetMapping("/test")
    public String test() {
        return "Post test";
    }
}

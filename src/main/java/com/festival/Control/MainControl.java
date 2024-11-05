package com.festival.Control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainControl {

    @GetMapping("/")
    public String head() {
        return "index";
    }
}

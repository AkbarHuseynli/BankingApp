package org.example.gateway.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/gateway")
public class Controller {

    @GetMapping
    public String getMap(){
        return "Gateway";
    }
}

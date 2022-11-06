package com.lqs.demospringsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class helloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('dev:code:pull')")
    public String hello() {
        return "hello";
    }
}

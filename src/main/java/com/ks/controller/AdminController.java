package com.ks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

    /**
     *
     * @return
     */
    @RequestMapping("/home")
    public String home() {
        return "Hello World";
    }


}
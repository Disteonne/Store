package com.netcracker.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @GetMapping("/auth/login")
    public String getLoginPage(){
        return "login";
    }
    /*
    @GetMapping("/")
    public String getStore(){
        return "index";
    }

    @PostMapping("/logout")
    public RedirectView logout(){
        return new RedirectView("../login.html");
    }
     */

}

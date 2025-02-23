package com.misc.note.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {

    @GetMapping("/generate")
    public String generate() {
        return "1234";
    }

    @GetMapping("/check")
    public boolean check() {
        return true;
    }

}

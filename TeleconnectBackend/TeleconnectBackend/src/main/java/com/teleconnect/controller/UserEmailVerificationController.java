package com.teleconnect.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.teleconnect.service.EmailVerificationService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {
	    "http://user-registration-portal-pune.s3-website-us-east-1.amazonaws.com", // Frontend URL
	    "http://3.92.50.211"
	})
@RequestMapping("/api/user/verification")
public class UserEmailVerificationController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/email")
    public String verifyEmail(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        return emailVerificationService.verifyEmail(email, session);
    }

    @PostMapping("/otp")
    public String validateOtp(@RequestBody Map<String, String> payload, HttpSession session) {
        String otp = payload.get("otp");
        return emailVerificationService.validateOtp(otp, session);
    }
}

package com.teleconnect.controller;

import com.teleconnect.dto.AdminActivatedUserDTO;
import com.teleconnect.dto.AdminDeactivatedUserDTO;
import com.teleconnect.dto.AdminDeleteUserRequestDTO;
import com.teleconnect.dto.AdminLoginRequestDTO;
import com.teleconnect.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
	    "http://user-registration-portal-pune.s3-website-us-east-1.amazonaws.com", // Frontend URL
	    "http://3.92.50.211"
	})// EC2 Backend IP
@RequestMapping("/api/admin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequestDTO loginRequest) {
        try {
            String result = adminLoginService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody AdminDeleteUserRequestDTO deleteUserRequest) {
        try {
            adminLoginService.deleteUserByEmail(deleteUserRequest.getEmail());
            return ResponseEntity.ok("User deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // New endpoint for deactivated users
    @GetMapping("/deactivatedUsers")
    public ResponseEntity<List<AdminDeactivatedUserDTO>> getDeactivatedUsers() {
        List<AdminDeactivatedUserDTO> deactivatedUsers = adminLoginService.getDeactivatedUsers();
        return ResponseEntity.ok(deactivatedUsers);
    }

    // New endpoint for activated users
    @GetMapping("/activatedUsers")
    public ResponseEntity<List<AdminActivatedUserDTO>> getActivatedUsers() {
        List<AdminActivatedUserDTO> activatedUsers = adminLoginService.getActivatedUsers();
        return ResponseEntity.ok(activatedUsers);
    }
}


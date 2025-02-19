package com.teleconnect.controller;

import com.teleconnect.model.ChangePlanRequest; // Import your request model
import com.teleconnect.service.UserPlanChangeService; // Import your service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
	    "http://user-registration-portal-pune.s3-website-us-east-1.amazonaws.com", // Frontend URL
	    "http://3.92.50.211"
	})
@RequestMapping("/api/user/plan")
public class UserPlanChangeController {

    @Autowired
    private UserPlanChangeService userPlanChangeService;

    @PutMapping("/change")
    public ResponseEntity<String> changeUserPlan(@RequestBody ChangePlanRequest request) {
        String resultMessage = userPlanChangeService.changePlan(request.getEmail(), request.getPlanId());
        return ResponseEntity.ok(resultMessage);
    }
}

package com.teleconnect.controller;

import com.teleconnect.entity.UserPlanAssignment;
import com.teleconnect.model.AssignPlanRequest;
import com.teleconnect.service.UserPlanAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
	    "http://user-registration-portal-pune.s3-website-us-east-1.amazonaws.com", // Frontend URL
	    "http://3.92.50.211"
	})
@RequestMapping("/api/user/plan")
public class UserPlanAssignmentController {

    @Autowired
    private UserPlanAssignmentService userPlanAssignmentService;

    @PostMapping("/assign")
    public ResponseEntity<?> assignPlan(@RequestBody AssignPlanRequest request) { // Change ResponseEntity<UserPlanAssignment> to ResponseEntity<?>
        try {
            UserPlanAssignment assignment = userPlanAssignmentService.assignMobileNumber(
                    request.getEmail(), 
                    request.getEid(), 
                    request.getPlanId()
            );
            return ResponseEntity.ok(assignment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // This will return a String message
        }
    }
}

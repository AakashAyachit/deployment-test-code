package com.teleconnect.controller;

import com.teleconnect.model.RechargeRequest;
import com.teleconnect.service.RechargeUserPlanAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
	    "http://user-registration-portal-pune.s3-website-us-east-1.amazonaws.com", // Frontend URL
	    "http://3.92.50.211"
	})
@RequestMapping("/api/user/plan")
public class UserMobileNumberRechargeController {

    @Autowired
    private RechargeUserPlanAssignmentService rechargeUserPlanAssignmentService;

    @PutMapping("/recharge")
    public ResponseEntity<String> updateValidityStatus(@RequestBody RechargeRequest rechargeRequest) {
        String resultMessage = rechargeUserPlanAssignmentService.updateValidityStatus(rechargeRequest.getEmail(), rechargeRequest.getPlanId());
        if (resultMessage.contains("not found")) {
            return ResponseEntity.status(404).body(resultMessage);
        }
        return ResponseEntity.ok(resultMessage);
    }
}

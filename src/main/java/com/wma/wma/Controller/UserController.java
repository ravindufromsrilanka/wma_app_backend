package com.wma.wma.Controller;

import com.wma.wma.Payload.Request.OtpVerificationRequest;
import com.wma.wma.Payload.Request.UserLoginRequest;
import com.wma.wma.Payload.Request.UserRegistrationRequest;
import com.wma.wma.Payload.Response.SuccessResponse;
import com.wma.wma.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public SuccessResponse userRegistration(UserRegistrationRequest request){
        return userService.userRegistration(request);
    }

    @PostMapping("/otp/verification")
    public SuccessResponse otpVerification(OtpVerificationRequest request){
        return userService.otpVerification(request);
    }

    @PostMapping("/login")
    public SuccessResponse userLogin(UserLoginRequest request){
        return userService.userLogin(request);
    }
}

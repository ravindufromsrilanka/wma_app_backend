package com.wma.wma.Service;

import com.wma.wma.Payload.Request.OtpVerificationRequest;
import com.wma.wma.Payload.Request.UserLoginRequest;
import com.wma.wma.Payload.Request.UserRegistrationRequest;
import com.wma.wma.Payload.Response.SuccessResponse;

public interface UserService {
    SuccessResponse userRegistration(UserRegistrationRequest request);

    SuccessResponse otpVerification(OtpVerificationRequest request);

    SuccessResponse userLogin(UserLoginRequest request);
}

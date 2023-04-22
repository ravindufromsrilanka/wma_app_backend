package com.wma.wma.Payload.Request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OtpVerificationRequest {
    private String telephone;
    private String otp;
}

package com.wma.wma.Payload.Request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRegistrationRequest {

    private String name;
    private String email;
    private String telephone;
    private String memberType;
    private String password;
}

package com.wma.wma.Payload.Request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginRequest {

    private String username;
    private String password;

}

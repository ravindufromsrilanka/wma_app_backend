package com.wma.wma.Payload.Response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SuccessResponse {

    private int responseCode;
    private String response;

}

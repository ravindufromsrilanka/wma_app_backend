package com.wma.wma.Payload.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private final String message;

    private final String variable;

}
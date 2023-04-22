package com.wma.wma.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorException extends RuntimeException{
    private final String Message;
    private final String variable;
}

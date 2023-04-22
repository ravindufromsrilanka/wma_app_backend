package com.wma.wma.Configuration;

public class Configuration {
    public static final String VALID_PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
    public static final String VALIDATE_TELEPHONE_NUMBER = "^\\941\\d{7,8}$";
    public static final String VALID_EMAIL_ADDRESS_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
}

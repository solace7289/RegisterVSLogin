package com.RegisterLogin.RegisterLogin.Exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error!"),
    USER_EXISTED(1001, "User existed!"),
    USERNAME_INVALID(1002, "Username must be at least 5 characters!"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters!"),
    INVALID_KEY(9998, "Invalid key");
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

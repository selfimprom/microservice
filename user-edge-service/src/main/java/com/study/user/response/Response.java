package com.study.user.response;

import java.io.Serializable;

public class Response implements Serializable {

    public static final Response USERNAME_PASSWORD_INVALID = new Response("10001", "user name or password is invalid!");
    public static final Response MOBILE_OR_EMAIL_REQUIRE = new Response("10002", "mobile or email require!");
    public static final Response SEND_VERIFYCODE_FAILD = new Response("10003", "send verifycode failed!");
    public static final Response VERIFY_CODE_INVALID = new Response("10004", "verify code invalid!");

    public static final Response SUCCESS = new Response();

    private String code;
    private String message;

    public Response() {
        this.code = "0";
        this.message = "success";
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response exception(Exception e){
        return new Response("9999",e.getMessage());
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

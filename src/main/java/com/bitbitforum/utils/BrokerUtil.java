package com.bitbitforum.utils;

public class BrokerUtil {
    public String email;
    public String name;
    public String token;
    public String templateType;

    public BrokerUtil(String email, String name, String token, String templateType) {
        this.email = email;
        this.name = name;
        this.token = token;
        this.templateType = templateType;
    }
}

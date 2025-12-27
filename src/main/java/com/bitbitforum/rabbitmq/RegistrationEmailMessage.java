package com.bitbitforum.rabbitmq;

public class RegistrationEmailMessage {
    public String email;
    public String name;
    public String token;
    public String templateType;

    public RegistrationEmailMessage(String email, String name, String token, String templateType) {
        this.email = email;
        this.name = name;
        this.token = token;
        this.templateType = templateType;
    }
}

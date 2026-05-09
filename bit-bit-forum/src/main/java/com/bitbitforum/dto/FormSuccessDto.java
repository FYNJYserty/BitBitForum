package com.bitbitforum.dto;

public class FormSuccessDto {
    // Variables
    private String email;
    private String username;
    private String textMessage;
    // Constructor
    public FormSuccessDto(String email, String username, String textMessage) {
        this.email = email;
        this.username = username;
        this.textMessage = textMessage;
    }
    //Setters and getters
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getTextMessage() {return textMessage;}
    public void setTextMessage(String textMessage) {this.textMessage = textMessage;}
}

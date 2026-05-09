package com.bitbitforum.dto;

public class FormMessageDto {

    // Variables
    private String email;
    private String title;
    private String textMessage;

    // Constructor
    public FormMessageDto(String title, String email, String textMessage) {
        this.title = title;
        this.email = email;
        this.textMessage = textMessage;
    }

    // Getters setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {this.title = title;}

    public String getEmail() {return  email;}
    public void setEmail(String email) {this.email = email;}

    public String getTextMessage() {return textMessage;}
    public void setTextMessage(String textMessage) {this.textMessage = textMessage;}
}

package com.bitbitforum.dto;

public class UserUpdateDto {
    private String username;
    private String email;
    private Integer ageUsr;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    // Constructors
    public UserUpdateDto() {}

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAgeUsr() {
        return ageUsr;
    }

    public void setAgeUsr(Integer ageUsr) {
        this.ageUsr = ageUsr;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

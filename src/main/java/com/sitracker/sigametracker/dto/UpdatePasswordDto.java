package com.sitracker.sigametracker.dto;

public class UpdatePasswordDto {

    private String oldPassword;

    private String newPassword;

    public String getOldPassword() { return oldPassword; }

    public String getNewPassword() { return newPassword; }

    public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

}

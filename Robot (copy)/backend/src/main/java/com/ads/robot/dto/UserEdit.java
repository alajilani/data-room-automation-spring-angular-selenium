package com.ads.robot.dto;

import com.ads.robot.entities.User;

public class UserEdit {
    private User user;
    private boolean updatePassword;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(boolean updatePassword) {
        this.updatePassword = updatePassword;
    }
}

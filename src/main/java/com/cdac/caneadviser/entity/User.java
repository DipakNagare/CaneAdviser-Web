package com.cdac.caneadviser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private String userId;
    private String password;
    private int roleId;

    
    public User(String userId, String password, int roleId) {
        this.userId = userId;
        this.password = password;
        this.roleId = roleId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }  

    
}
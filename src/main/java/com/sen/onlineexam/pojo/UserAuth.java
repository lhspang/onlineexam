package com.sen.onlineexam.pojo;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.Serializable;

/**
 * @author sen
 * 
 */
public class UserAuth implements Serializable {
    private Integer authId;

    private String identityType;

    private String identifier;

    private String credential;

    private String salt;

    private User user;

    private static final long serialVersionUID = 1L;

    public UserAuth() {
    }

    public UserAuth(Integer authId, String identityType, String identifier, String credential, String salt, User user) {
        this.authId = authId;
        this.identityType = identityType;
        this.identifier = identifier;
        this.credential = credential;
        this.salt = salt;
        this.user = user;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "authId=" + authId +
                ", identityType='" + identityType + '\'' +
                ", identifier='" + identifier + '\'' +
                ", credential='" + credential + '\'' +
                ", user=" + user +
                '}';
    }
}
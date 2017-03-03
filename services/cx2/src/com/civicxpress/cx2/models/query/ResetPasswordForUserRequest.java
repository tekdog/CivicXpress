/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetPasswordForUserRequest implements Serializable {

    @JsonProperty("newPassword")
    private String newPassword;
    @JsonProperty("token")
    private String token;

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResetPasswordForUserRequest)) return false;
        final ResetPasswordForUserRequest resetPasswordForUserRequest = (ResetPasswordForUserRequest) o;
        return Objects.equals(getNewPassword(), resetPasswordForUserRequest.getNewPassword()) &&
                Objects.equals(getToken(), resetPasswordForUserRequest.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNewPassword(),
                getToken());
    }
}

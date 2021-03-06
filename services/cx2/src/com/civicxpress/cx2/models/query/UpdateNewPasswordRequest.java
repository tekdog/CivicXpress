/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateNewPasswordRequest implements Serializable {

    @JsonProperty("password")
    private String password;
    @JsonProperty("newUser")
    private Integer newUser;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNewUser() {
        return this.newUser;
    }

    public void setNewUser(Integer newUser) {
        this.newUser = newUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateNewPasswordRequest)) return false;
        final UpdateNewPasswordRequest updateNewPasswordRequest = (UpdateNewPasswordRequest) o;
        return Objects.equals(getPassword(), updateNewPasswordRequest.getPassword()) &&
                Objects.equals(getNewUser(), updateNewPasswordRequest.getNewUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassword(),
                getNewUser());
    }
}

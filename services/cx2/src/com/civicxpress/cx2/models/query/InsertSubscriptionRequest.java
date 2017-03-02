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

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertSubscriptionRequest implements Serializable {

    @JsonProperty("UserId")
    private Integer userId;
    @JsonProperty("MunicipalityId")
    private Integer municipalityId;
    @JsonProperty("DateSubscribed")
    private String dateSubscribed;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getDateSubscribed() {
        return this.dateSubscribed;
    }

    public void setDateSubscribed(String dateSubscribed) {
        this.dateSubscribed = dateSubscribed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertSubscriptionRequest)) return false;
        final InsertSubscriptionRequest insertSubscriptionRequest = (InsertSubscriptionRequest) o;
        return Objects.equals(getUserId(), insertSubscriptionRequest.getUserId()) &&
                Objects.equals(getMunicipalityId(), insertSubscriptionRequest.getMunicipalityId()) &&
                Objects.equals(getDateSubscribed(), insertSubscriptionRequest.getDateSubscribed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(),
                getMunicipalityId(),
                getDateSubscribed());
    }
}

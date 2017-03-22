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

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertFormMessageRequest implements Serializable {

    @NotNull
    @JsonProperty("UserId")
    private Integer userId;
    @NotNull
    @JsonProperty("RelatedFormGUID")
    private String relatedFormGuid;
    @NotNull
    @JsonProperty("Message")
    private String message;
    @JsonProperty("PostedAt")
    private Timestamp postedAt;
    @NotNull
    @JsonProperty("MunicipalityMessage")
    private Boolean municipalityMessage;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getPostedAt() {
        return this.postedAt;
    }

    public void setPostedAt(Timestamp postedAt) {
        this.postedAt = postedAt;
    }

    public Boolean getMunicipalityMessage() {
        return this.municipalityMessage;
    }

    public void setMunicipalityMessage(Boolean municipalityMessage) {
        this.municipalityMessage = municipalityMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertFormMessageRequest)) return false;
        final InsertFormMessageRequest insertFormMessageRequest = (InsertFormMessageRequest) o;
        return Objects.equals(getUserId(), insertFormMessageRequest.getUserId()) &&
                Objects.equals(getRelatedFormGuid(), insertFormMessageRequest.getRelatedFormGuid()) &&
                Objects.equals(getMessage(), insertFormMessageRequest.getMessage()) &&
                Objects.equals(getPostedAt(), insertFormMessageRequest.getPostedAt()) &&
                Objects.equals(getMunicipalityMessage(), insertFormMessageRequest.getMunicipalityMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(),
                getRelatedFormGuid(),
                getMessage(),
                getPostedAt(),
                getMunicipalityMessage());
    }
}

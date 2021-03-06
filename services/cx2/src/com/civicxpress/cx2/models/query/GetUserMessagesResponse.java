/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetUserMessagesResponse implements Serializable {

    @ColumnAlias("ID")
    private Integer id;
    @ColumnAlias("MessageRead")
    private Boolean messageRead;
    @ColumnAlias("Message")
    private String message;
    @ColumnAlias("PostedAt")
    private LocalDateTime postedAt;
    @ColumnAlias("MunicipalityMessage")
    private Boolean municipalityMessage;
    @ColumnAlias("PostedByEmail")
    private String postedByEmail;
    @ColumnAlias("PostedByFullName")
    private String postedByFullName;
    @ColumnAlias("PostedByUserId")
    private Integer postedByUserId;
    @ColumnAlias("SourceGuid")
    private String sourceGuid;
    @ColumnAlias("SourceTitle")
    private String sourceTitle;
    @ColumnAlias("SourceType")
    private String sourceType;
    @ColumnAlias("SourceCategory")
    private String sourceCategory;
    @ColumnAlias("MunicipalityName")
    private String municipalityName;
    @ColumnAlias("MunicipalityGlobalEmailSig")
    private String municipalityGlobalEmailSig;
    @ColumnAlias("MunicipalityId")
    private Integer municipalityId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getMessageRead() {
        return this.messageRead;
    }

    public void setMessageRead(Boolean messageRead) {
        this.messageRead = messageRead;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getPostedAt() {
        return this.postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public Boolean getMunicipalityMessage() {
        return this.municipalityMessage;
    }

    public void setMunicipalityMessage(Boolean municipalityMessage) {
        this.municipalityMessage = municipalityMessage;
    }

    public String getPostedByEmail() {
        return this.postedByEmail;
    }

    public void setPostedByEmail(String postedByEmail) {
        this.postedByEmail = postedByEmail;
    }

    public String getPostedByFullName() {
        return this.postedByFullName;
    }

    public void setPostedByFullName(String postedByFullName) {
        this.postedByFullName = postedByFullName;
    }

    public Integer getPostedByUserId() {
        return this.postedByUserId;
    }

    public void setPostedByUserId(Integer postedByUserId) {
        this.postedByUserId = postedByUserId;
    }

    public String getSourceGuid() {
        return this.sourceGuid;
    }

    public void setSourceGuid(String sourceGuid) {
        this.sourceGuid = sourceGuid;
    }

    public String getSourceTitle() {
        return this.sourceTitle;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceCategory() {
        return this.sourceCategory;
    }

    public void setSourceCategory(String sourceCategory) {
        this.sourceCategory = sourceCategory;
    }

    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getMunicipalityGlobalEmailSig() {
        return this.municipalityGlobalEmailSig;
    }

    public void setMunicipalityGlobalEmailSig(String municipalityGlobalEmailSig) {
        this.municipalityGlobalEmailSig = municipalityGlobalEmailSig;
    }

    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetUserMessagesResponse)) return false;
        final GetUserMessagesResponse getUserMessagesResponse = (GetUserMessagesResponse) o;
        return Objects.equals(getId(), getUserMessagesResponse.getId()) &&
                Objects.equals(getMessageRead(), getUserMessagesResponse.getMessageRead()) &&
                Objects.equals(getMessage(), getUserMessagesResponse.getMessage()) &&
                Objects.equals(getPostedAt(), getUserMessagesResponse.getPostedAt()) &&
                Objects.equals(getMunicipalityMessage(), getUserMessagesResponse.getMunicipalityMessage()) &&
                Objects.equals(getPostedByEmail(), getUserMessagesResponse.getPostedByEmail()) &&
                Objects.equals(getPostedByFullName(), getUserMessagesResponse.getPostedByFullName()) &&
                Objects.equals(getPostedByUserId(), getUserMessagesResponse.getPostedByUserId()) &&
                Objects.equals(getSourceGuid(), getUserMessagesResponse.getSourceGuid()) &&
                Objects.equals(getSourceTitle(), getUserMessagesResponse.getSourceTitle()) &&
                Objects.equals(getSourceType(), getUserMessagesResponse.getSourceType()) &&
                Objects.equals(getSourceCategory(), getUserMessagesResponse.getSourceCategory()) &&
                Objects.equals(getMunicipalityName(), getUserMessagesResponse.getMunicipalityName()) &&
                Objects.equals(getMunicipalityGlobalEmailSig(), getUserMessagesResponse.getMunicipalityGlobalEmailSig()) &&
                Objects.equals(getMunicipalityId(), getUserMessagesResponse.getMunicipalityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getMessageRead(),
                getMessage(),
                getPostedAt(),
                getMunicipalityMessage(),
                getPostedByEmail(),
                getPostedByFullName(),
                getPostedByUserId(),
                getSourceGuid(),
                getSourceTitle(),
                getSourceType(),
                getSourceCategory(),
                getMunicipalityName(),
                getMunicipalityGlobalEmailSig(),
                getMunicipalityId());
    }
}

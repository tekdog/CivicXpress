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

public class UpdateVendorStatusRequest implements Serializable {

    @JsonProperty("DateApproved")
    private Timestamp dateApproved;
    @JsonProperty("ApprovedBy")
    private String approvedBy;
    @JsonProperty("ExpiresDate")
    private Timestamp expiresDate;
    @JsonProperty("ApprovalStatus")
    private String approvalStatus;
    @JsonProperty("Reviewer")
    private String reviewer;
    @JsonProperty("municipality")
    private Integer municipality;
    @JsonProperty("vendor")
    private Integer vendor;

    public Timestamp getDateApproved() {
        return this.dateApproved;
    }

    public void setDateApproved(Timestamp dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getExpiresDate() {
        return this.expiresDate;
    }

    public void setExpiresDate(Timestamp expiresDate) {
        this.expiresDate = expiresDate;
    }

    public String getApprovalStatus() {
        return this.approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getReviewer() {
        return this.reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Integer getMunicipality() {
        return this.municipality;
    }

    public void setMunicipality(Integer municipality) {
        this.municipality = municipality;
    }

    public Integer getVendor() {
        return this.vendor;
    }

    public void setVendor(Integer vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateVendorStatusRequest)) return false;
        final UpdateVendorStatusRequest updateVendorStatusRequest = (UpdateVendorStatusRequest) o;
        return Objects.equals(getDateApproved(), updateVendorStatusRequest.getDateApproved()) &&
                Objects.equals(getApprovedBy(), updateVendorStatusRequest.getApprovedBy()) &&
                Objects.equals(getExpiresDate(), updateVendorStatusRequest.getExpiresDate()) &&
                Objects.equals(getApprovalStatus(), updateVendorStatusRequest.getApprovalStatus()) &&
                Objects.equals(getReviewer(), updateVendorStatusRequest.getReviewer()) &&
                Objects.equals(getMunicipality(), updateVendorStatusRequest.getMunicipality()) &&
                Objects.equals(getVendor(), updateVendorStatusRequest.getVendor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateApproved(),
                getApprovedBy(),
                getExpiresDate(),
                getApprovalStatus(),
                getReviewer(),
                getMunicipality(),
                getVendor());
    }
}

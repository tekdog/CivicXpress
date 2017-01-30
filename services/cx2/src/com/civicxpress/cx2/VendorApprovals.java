/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * VendorApprovals generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`VendorApprovals`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`MunicipalityId`", "`VendorId`"})})
public class VendorApprovals implements Serializable {

    private Integer id;
    private Integer municipalityId;
    private Integer vendorId;
    private String approvedBy;
    private Boolean active;
    private String approvalStatus;
    private String reviewer;
    private Date dateApproved;
    private Date expiresDate;
    private Municipalities municipalities;
    private Vendor vendor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`VendorId`", nullable = true, scale = 0, precision = 10)
    public Integer getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    @Column(name = "`ApprovedBy`", nullable = true, length = 255)
    public String getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Column(name = "`Active`", nullable = true)
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "`ApprovalStatus`", nullable = true, length = 255)
    public String getApprovalStatus() {
        return this.approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Column(name = "`Reviewer`", nullable = true, length = 255)
    public String getReviewer() {
        return this.reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`DateApproved`", nullable = true)
    public Date getDateApproved() {
        return this.dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`ExpiresDate`", nullable = true)
    public Date getExpiresDate() {
        return this.expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`MunicipalityId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Municipalities getMunicipalities() {
        return this.municipalities;
    }

    public void setMunicipalities(Municipalities municipalities) {
        if(municipalities != null) {
            this.municipalityId = municipalities.getId();
        }

        this.municipalities = municipalities;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`VendorId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Vendor getVendor() {
        return this.vendor;
    }

    public void setVendor(Vendor vendor) {
        if(vendor != null) {
            this.vendorId = vendor.getId();
        }

        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorApprovals)) return false;
        final VendorApprovals vendorApprovals = (VendorApprovals) o;
        return Objects.equals(getId(), vendorApprovals.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.UniqueConstraint;

/**
 * SharedWith generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`SharedWith`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`RelatedGUID`", "`SharedWithUser`", "`CreatedBy`"})})
public class SharedWith implements Serializable {

    private Integer id;
    private String relatedGuid;
    private Integer sharedWithUser;
    private Timestamp createdOn;
    private Integer createdBy;
    private MasterForms masterForms;
    private Users usersByCreatedBy;
    private Users usersBySharedWithUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`RelatedGUID`", nullable = true, length = 255)
    public String getRelatedGuid() {
        return this.relatedGuid;
    }

    public void setRelatedGuid(String relatedGuid) {
        this.relatedGuid = relatedGuid;
    }

    @Column(name = "`SharedWithUser`", nullable = true, scale = 0, precision = 10)
    public Integer getSharedWithUser() {
        return this.sharedWithUser;
    }

    public void setSharedWithUser(Integer sharedWithUser) {
        this.sharedWithUser = sharedWithUser;
    }

    @Column(name = "`CreatedOn`", nullable = true)
    public Timestamp getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "`CreatedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`RelatedGUID`", referencedColumnName = "`FormGUID`", insertable = false, updatable = false)
    public MasterForms getMasterForms() {
        return this.masterForms;
    }

    public void setMasterForms(MasterForms masterForms) {
        if(masterForms != null) {
            this.relatedGuid = masterForms.getFormGuid();
        }

        this.masterForms = masterForms;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CreatedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersByCreatedBy() {
        return this.usersByCreatedBy;
    }

    public void setUsersByCreatedBy(Users usersByCreatedBy) {
        if(usersByCreatedBy != null) {
            this.createdBy = usersByCreatedBy.getId();
        }

        this.usersByCreatedBy = usersByCreatedBy;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`SharedWithUser`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersBySharedWithUser() {
        return this.usersBySharedWithUser;
    }

    public void setUsersBySharedWithUser(Users usersBySharedWithUser) {
        if(usersBySharedWithUser != null) {
            this.sharedWithUser = usersBySharedWithUser.getId();
        }

        this.usersBySharedWithUser = usersBySharedWithUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SharedWith)) return false;
        final SharedWith sharedWith = (SharedWith) o;
        return Objects.equals(getId(), sharedWith.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


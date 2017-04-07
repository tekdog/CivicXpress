/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
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

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * FormsToInspections generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FormsToInspections`")
public class FormsToInspections implements Serializable {

    private Integer id;
    private String relatedFormGuid;
    private String relatedInspectionGuid;
    private Integer addedBy;
    @Type(type = "DateTime")
    private LocalDateTime addedAt;
    private MasterInspections masterInspections;
    private MasterForms masterForms;
    private Users users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`RelatedFormGUID`", nullable = true, length = 32)
    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    @Column(name = "`RelatedInspectionGUID`", nullable = false, length = 32)
    public String getRelatedInspectionGuid() {
        return this.relatedInspectionGuid;
    }

    public void setRelatedInspectionGuid(String relatedInspectionGuid) {
        this.relatedInspectionGuid = relatedInspectionGuid;
    }

    @Column(name = "`AddedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getAddedBy() {
        return this.addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    @Column(name = "`AddedAt`", nullable = false, insertable = false, updatable = false)
    public LocalDateTime getAddedAt() {
        return this.addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`RelatedInspectionGUID`", referencedColumnName = "`InspectionGuid`", insertable = false, updatable = false)
    public MasterInspections getMasterInspections() {
        return this.masterInspections;
    }

    public void setMasterInspections(MasterInspections masterInspections) {
        if(masterInspections != null) {
            this.relatedInspectionGuid = masterInspections.getInspectionGuid();
        }

        this.masterInspections = masterInspections;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`RelatedFormGUID`", referencedColumnName = "`FormGUID`", insertable = false, updatable = false)
    public MasterForms getMasterForms() {
        return this.masterForms;
    }

    public void setMasterForms(MasterForms masterForms) {
        if(masterForms != null) {
            this.relatedFormGuid = masterForms.getFormGuid();
        }

        this.masterForms = masterForms;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`AddedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.addedBy = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormsToInspections)) return false;
        final FormsToInspections formsToInspections = (FormsToInspections) o;
        return Objects.equals(getId(), formsToInspections.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


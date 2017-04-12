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
 * InspectionHistory generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`InspectionHistory`")
public class InspectionHistory implements Serializable {

    private Integer id;
    private String inspectionGuid;
    private Integer newOutcomeId;
    private Integer oldOutcomeId;
    private String comments;
    private int createdBy;
    @Type(type = "DateTime")
    private LocalDateTime dateCreated;
    private MasterInspections masterInspections;
    private InspectionOutcome inspectionOutcomeByNewOutcomeId;
    private InspectionOutcome inspectionOutcomeByOldOutcomeId;
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

    @Column(name = "`InspectionGUID`", nullable = false, length = 32)
    public String getInspectionGuid() {
        return this.inspectionGuid;
    }

    public void setInspectionGuid(String inspectionGuid) {
        this.inspectionGuid = inspectionGuid;
    }

    @Column(name = "`NewOutcomeId`", nullable = true, scale = 0, precision = 10)
    public Integer getNewOutcomeId() {
        return this.newOutcomeId;
    }

    public void setNewOutcomeId(Integer newOutcomeId) {
        this.newOutcomeId = newOutcomeId;
    }

    @Column(name = "`OldOutcomeId`", nullable = true, scale = 0, precision = 10)
    public Integer getOldOutcomeId() {
        return this.oldOutcomeId;
    }

    public void setOldOutcomeId(Integer oldOutcomeId) {
        this.oldOutcomeId = oldOutcomeId;
    }

    @Column(name = "`Comments`", nullable = true, length = 1000)
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "`CreatedBy`", nullable = false, scale = 0, precision = 10)
    public int getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`DateCreated`", nullable = false)
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectionGUID`", referencedColumnName = "`InspectionGuid`", insertable = false, updatable = false)
    public MasterInspections getMasterInspections() {
        return this.masterInspections;
    }

    public void setMasterInspections(MasterInspections masterInspections) {
        if(masterInspections != null) {
            this.inspectionGuid = masterInspections.getInspectionGuid();
        }

        this.masterInspections = masterInspections;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`NewOutcomeId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionOutcome getInspectionOutcomeByNewOutcomeId() {
        return this.inspectionOutcomeByNewOutcomeId;
    }

    public void setInspectionOutcomeByNewOutcomeId(InspectionOutcome inspectionOutcomeByNewOutcomeId) {
        if(inspectionOutcomeByNewOutcomeId != null) {
            this.newOutcomeId = inspectionOutcomeByNewOutcomeId.getId();
        }

        this.inspectionOutcomeByNewOutcomeId = inspectionOutcomeByNewOutcomeId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`OldOutcomeId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionOutcome getInspectionOutcomeByOldOutcomeId() {
        return this.inspectionOutcomeByOldOutcomeId;
    }

    public void setInspectionOutcomeByOldOutcomeId(InspectionOutcome inspectionOutcomeByOldOutcomeId) {
        if(inspectionOutcomeByOldOutcomeId != null) {
            this.oldOutcomeId = inspectionOutcomeByOldOutcomeId.getId();
        }

        this.inspectionOutcomeByOldOutcomeId = inspectionOutcomeByOldOutcomeId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CreatedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.createdBy = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InspectionHistory)) return false;
        final InspectionHistory inspectionHistory = (InspectionHistory) o;
        return Objects.equals(getId(), inspectionHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * MasterCases generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`MasterCases`")
public class MasterCases implements Serializable {

    private String caseGuid;
    private String caseTitle;
    private String relatedInspectionGuid;
    private String relatedFormGuid;
    private String relatedProjectGuid;
    @ServerDefinedProperty( value = VariableType.DATE, scopes = { Scope.INSERT })
    private Date dateCreated;
    private Integer createdBy;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.UPDATE, Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime dateModified;
    private Integer modifiedBy;
    private Integer caseType;
    private Integer caseStatus;
    private CaseStatuses caseStatuses;
    private CaseTypes caseTypes;
    private MasterInspections masterInspections;
    private Projects projects;
    private Users usersByCreatedBy;
    private Users usersByModifiedBy;

    @Id
    @Column(name = "`CaseGUID`", nullable = false, length = 255)
    public String getCaseGuid() {
        return this.caseGuid;
    }

    public void setCaseGuid(String caseGuid) {
        this.caseGuid = caseGuid;
    }

    @Column(name = "`CaseTitle`", nullable = true, length = 255)
    public String getCaseTitle() {
        return this.caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    @Column(name = "`RelatedInspectionGuid`", nullable = true, length = 32)
    public String getRelatedInspectionGuid() {
        return this.relatedInspectionGuid;
    }

    public void setRelatedInspectionGuid(String relatedInspectionGuid) {
        this.relatedInspectionGuid = relatedInspectionGuid;
    }

    @Column(name = "`RelatedFormGuid`", nullable = true, length = 255)
    public String getRelatedFormGuid() {
        return this.relatedFormGuid;
    }

    public void setRelatedFormGuid(String relatedFormGuid) {
        this.relatedFormGuid = relatedFormGuid;
    }

    @Column(name = "`RelatedProjectGuid`", nullable = true, length = 32)
    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    @Column(name = "`DateCreated`", nullable = true, updatable = false)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "`CreatedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`DateModified`", nullable = true)
    public LocalDateTime getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Column(name = "`ModifiedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(name = "`CaseType`", nullable = true, scale = 0, precision = 10)
    public Integer getCaseType() {
        return this.caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    @Column(name = "`CaseStatus`", nullable = true, scale = 0, precision = 10)
    public Integer getCaseStatus() {
        return this.caseStatus;
    }

    public void setCaseStatus(Integer caseStatus) {
        this.caseStatus = caseStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CaseStatus`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public CaseStatuses getCaseStatuses() {
        return this.caseStatuses;
    }

    public void setCaseStatuses(CaseStatuses caseStatuses) {
        if(caseStatuses != null) {
            this.caseStatus = caseStatuses.getId();
        }

        this.caseStatuses = caseStatuses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CaseType`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public CaseTypes getCaseTypes() {
        return this.caseTypes;
    }

    public void setCaseTypes(CaseTypes caseTypes) {
        if(caseTypes != null) {
            this.caseType = caseTypes.getId();
        }

        this.caseTypes = caseTypes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`RelatedInspectionGuid`", referencedColumnName = "`InspectionGuid`", insertable = false, updatable = false)
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
    @JoinColumn(name = "`RelatedProjectGuid`", referencedColumnName = "`ProjectGUID`", insertable = false, updatable = false)
    public Projects getProjects() {
        return this.projects;
    }

    public void setProjects(Projects projects) {
        if(projects != null) {
            this.relatedProjectGuid = projects.getProjectGuid();
        }

        this.projects = projects;
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
    @JoinColumn(name = "`ModifiedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersByModifiedBy() {
        return this.usersByModifiedBy;
    }

    public void setUsersByModifiedBy(Users usersByModifiedBy) {
        if(usersByModifiedBy != null) {
            this.modifiedBy = usersByModifiedBy.getId();
        }

        this.usersByModifiedBy = usersByModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MasterCases)) return false;
        final MasterCases masterCases = (MasterCases) o;
        return Objects.equals(getCaseGuid(), masterCases.getCaseGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCaseGuid());
    }
}


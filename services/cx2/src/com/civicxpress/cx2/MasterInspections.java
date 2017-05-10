/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * MasterInspections generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`MasterInspections`")
public class MasterInspections implements Serializable {

    private String inspectionGuid;
    private String inspectionTitle;
    private String caseGuid;
    private String projectGuid;
    private Integer inspectionDesignId;
    private Integer gisId;
    private Integer inspectionOutcomeId;
    private Integer inspectionCatId;
    private String formGuid;
    private Integer requestedBy;
    private Integer modifiedBy;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime dateRequested;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.UPDATE, Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime dateModified;
    private Integer assignedTo;
    private boolean closed;
    @Type(type = "DateTime")
    private LocalDateTime dateAssigned;
    private String inspectionZone;
    @Type(type = "DateTime")
    private LocalDateTime requestedFor;
    private int id;
    private InspectionOutcome inspectionOutcome;
    private MasterForms masterForms;
    private Gisrecords gisrecords;
    private Users usersByAssignedTo;
    private InspectionCategories inspectionCategories;
    private InspectionDesign inspectionDesign;
    private Users usersByRequestedBy;
    private Users usersByModifiedBy;
    private Projects projects;
    private List<Fees> feeses;
    private List<FormMessages> formMessageses;
    private List<FormsToInspections> formsToInspectionses;
    private List<InspectionHistory> inspectionHistories;
    private List<MasterCases> masterCaseses;
    private List<Violations> violationses;

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "`InspectionGuid`", nullable = false, length = 32)
    public String getInspectionGuid() {
        return this.inspectionGuid;
    }

    public void setInspectionGuid(String inspectionGuid) {
        this.inspectionGuid = inspectionGuid;
    }

    @Column(name = "`InspectionTitle`", nullable = true, length = 255)
    public String getInspectionTitle() {
        return this.inspectionTitle;
    }

    public void setInspectionTitle(String inspectionTitle) {
        this.inspectionTitle = inspectionTitle;
    }

    @Column(name = "`CaseGuid`", nullable = true, length = 255)
    public String getCaseGuid() {
        return this.caseGuid;
    }

    public void setCaseGuid(String caseGuid) {
        this.caseGuid = caseGuid;
    }

    @Column(name = "`ProjectGuid`", nullable = true, length = 32)
    public String getProjectGuid() {
        return this.projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    @Column(name = "`InspectionDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectionDesignId() {
        return this.inspectionDesignId;
    }

    public void setInspectionDesignId(Integer inspectionDesignId) {
        this.inspectionDesignId = inspectionDesignId;
    }

    @Column(name = "`GisId`", nullable = true, scale = 0, precision = 10)
    public Integer getGisId() {
        return this.gisId;
    }

    public void setGisId(Integer gisId) {
        this.gisId = gisId;
    }

    @Column(name = "`InspectionOutcomeId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectionOutcomeId() {
        return this.inspectionOutcomeId;
    }

    public void setInspectionOutcomeId(Integer inspectionOutcomeId) {
        this.inspectionOutcomeId = inspectionOutcomeId;
    }

    @Column(name = "`InspectionCatId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectionCatId() {
        return this.inspectionCatId;
    }

    public void setInspectionCatId(Integer inspectionCatId) {
        this.inspectionCatId = inspectionCatId;
    }

    @Column(name = "`FormGuid`", nullable = true, length = 32)
    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    @Column(name = "`RequestedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getRequestedBy() {
        return this.requestedBy;
    }

    public void setRequestedBy(Integer requestedBy) {
        this.requestedBy = requestedBy;
    }

    @Column(name = "`ModifiedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(name = "`DateRequested`", nullable = true, updatable = false)
    public LocalDateTime getDateRequested() {
        return this.dateRequested;
    }

    public void setDateRequested(LocalDateTime dateRequested) {
        this.dateRequested = dateRequested;
    }

    @Column(name = "`DateModified`", nullable = true)
    public LocalDateTime getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Column(name = "`AssignedTo`", nullable = true, scale = 0, precision = 10)
    public Integer getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Column(name = "`Closed`", nullable = false)
    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Column(name = "`DateAssigned`", nullable = true)
    public LocalDateTime getDateAssigned() {
        return this.dateAssigned;
    }

    public void setDateAssigned(LocalDateTime dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    @Column(name = "`InspectionZone`", nullable = true, length = 255)
    public String getInspectionZone() {
        return this.inspectionZone;
    }

    public void setInspectionZone(String inspectionZone) {
        this.inspectionZone = inspectionZone;
    }

    @Column(name = "`RequestedFor`", nullable = true)
    public LocalDateTime getRequestedFor() {
        return this.requestedFor;
    }

    public void setRequestedFor(LocalDateTime requestedFor) {
        this.requestedFor = requestedFor;
    }

    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectionOutcomeId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionOutcome getInspectionOutcome() {
        return this.inspectionOutcome;
    }

    public void setInspectionOutcome(InspectionOutcome inspectionOutcome) {
        if(inspectionOutcome != null) {
            this.inspectionOutcomeId = inspectionOutcome.getId();
        }

        this.inspectionOutcome = inspectionOutcome;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormGuid`", referencedColumnName = "`FormGUID`", insertable = false, updatable = false)
    public MasterForms getMasterForms() {
        return this.masterForms;
    }

    public void setMasterForms(MasterForms masterForms) {
        if(masterForms != null) {
            this.formGuid = masterForms.getFormGuid();
        }

        this.masterForms = masterForms;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`GisId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Gisrecords getGisrecords() {
        return this.gisrecords;
    }

    public void setGisrecords(Gisrecords gisrecords) {
        if(gisrecords != null) {
            this.gisId = gisrecords.getId();
        }

        this.gisrecords = gisrecords;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`AssignedTo`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersByAssignedTo() {
        return this.usersByAssignedTo;
    }

    public void setUsersByAssignedTo(Users usersByAssignedTo) {
        if(usersByAssignedTo != null) {
            this.assignedTo = usersByAssignedTo.getId();
        }

        this.usersByAssignedTo = usersByAssignedTo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectionCatId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionCategories getInspectionCategories() {
        return this.inspectionCategories;
    }

    public void setInspectionCategories(InspectionCategories inspectionCategories) {
        if(inspectionCategories != null) {
            this.inspectionCatId = inspectionCategories.getId();
        }

        this.inspectionCategories = inspectionCategories;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectionDesignId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionDesign getInspectionDesign() {
        return this.inspectionDesign;
    }

    public void setInspectionDesign(InspectionDesign inspectionDesign) {
        if(inspectionDesign != null) {
            this.inspectionDesignId = inspectionDesign.getId();
        }

        this.inspectionDesign = inspectionDesign;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`RequestedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsersByRequestedBy() {
        return this.usersByRequestedBy;
    }

    public void setUsersByRequestedBy(Users usersByRequestedBy) {
        if(usersByRequestedBy != null) {
            this.requestedBy = usersByRequestedBy.getId();
        }

        this.usersByRequestedBy = usersByRequestedBy;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ProjectGuid`", referencedColumnName = "`ProjectGUID`", insertable = false, updatable = false)
    public Projects getProjects() {
        return this.projects;
    }

    public void setProjects(Projects projects) {
        if(projects != null) {
            this.projectGuid = projects.getProjectGuid();
        }

        this.projects = projects;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterInspections")
    public List<Fees> getFeeses() {
        return this.feeses;
    }

    public void setFeeses(List<Fees> feeses) {
        this.feeses = feeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterInspections")
    public List<FormMessages> getFormMessageses() {
        return this.formMessageses;
    }

    public void setFormMessageses(List<FormMessages> formMessageses) {
        this.formMessageses = formMessageses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterInspections")
    public List<FormsToInspections> getFormsToInspectionses() {
        return this.formsToInspectionses;
    }

    public void setFormsToInspectionses(List<FormsToInspections> formsToInspectionses) {
        this.formsToInspectionses = formsToInspectionses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterInspections")
    public List<InspectionHistory> getInspectionHistories() {
        return this.inspectionHistories;
    }

    public void setInspectionHistories(List<InspectionHistory> inspectionHistories) {
        this.inspectionHistories = inspectionHistories;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterInspections")
    public List<MasterCases> getMasterCaseses() {
        return this.masterCaseses;
    }

    public void setMasterCaseses(List<MasterCases> masterCaseses) {
        this.masterCaseses = masterCaseses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "masterInspections")
    public List<Violations> getViolationses() {
        return this.violationses;
    }

    public void setViolationses(List<Violations> violationses) {
        this.violationses = violationses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MasterInspections)) return false;
        final MasterInspections masterInspections = (MasterInspections) o;
        return Objects.equals(getInspectionGuid(), masterInspections.getInspectionGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInspectionGuid());
    }
}


/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * InspectionOutcome generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`InspectionOutcome`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`InspectDesignId`", "`OutcomeOrder`"})})
public class InspectionOutcome implements Serializable {

    private Integer id;
    private Integer inspectDesignId;
    private String outcome;
    private Boolean assessFeeYn;
    private Boolean considerClosed;
    private Integer outcomeOrder;
    private InspectionDesign inspectionDesign;
    private List<InspectionOutcomeFee> inspectionOutcomeFees = new ArrayList<>();
    private List<MasterInspections> masterInspectionses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`InspectDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectDesignId() {
        return this.inspectDesignId;
    }

    public void setInspectDesignId(Integer inspectDesignId) {
        this.inspectDesignId = inspectDesignId;
    }

    @Column(name = "`Outcome`", nullable = true, length = 255)
    public String getOutcome() {
        return this.outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Column(name = "`AssessFeeYN`", nullable = true)
    public Boolean getAssessFeeYn() {
        return this.assessFeeYn;
    }

    public void setAssessFeeYn(Boolean assessFeeYn) {
        this.assessFeeYn = assessFeeYn;
    }

    @Column(name = "`ConsiderClosed`", nullable = true)
    public Boolean getConsiderClosed() {
        return this.considerClosed;
    }

    public void setConsiderClosed(Boolean considerClosed) {
        this.considerClosed = considerClosed;
    }

    @Column(name = "`OutcomeOrder`", nullable = true, scale = 0, precision = 10)
    public Integer getOutcomeOrder() {
        return this.outcomeOrder;
    }

    public void setOutcomeOrder(Integer outcomeOrder) {
        this.outcomeOrder = outcomeOrder;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectDesignId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionDesign getInspectionDesign() {
        return this.inspectionDesign;
    }

    public void setInspectionDesign(InspectionDesign inspectionDesign) {
        if(inspectionDesign != null) {
            this.inspectDesignId = inspectionDesign.getId();
        }

        this.inspectionDesign = inspectionDesign;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionOutcome")
    public List<InspectionOutcomeFee> getInspectionOutcomeFees() {
        return this.inspectionOutcomeFees;
    }

    public void setInspectionOutcomeFees(List<InspectionOutcomeFee> inspectionOutcomeFees) {
        this.inspectionOutcomeFees = inspectionOutcomeFees;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "inspectionOutcome")
    public List<MasterInspections> getMasterInspectionses() {
        return this.masterInspectionses;
    }

    public void setMasterInspectionses(List<MasterInspections> masterInspectionses) {
        this.masterInspectionses = masterInspectionses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InspectionOutcome)) return false;
        final InspectionOutcome inspectionOutcome = (InspectionOutcome) o;
        return Objects.equals(getId(), inspectionOutcome.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


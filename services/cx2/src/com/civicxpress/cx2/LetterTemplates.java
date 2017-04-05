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

import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * LetterTemplates generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`LetterTemplates`")
public class LetterTemplates implements Serializable {

    private Integer letterId;
    private String letterTitle;
    private String letterBody;
    private Integer formDesignId;
    private Integer inspectionDesignId;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.INSERT })
    private Integer createdBy;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT, Scope.UPDATE })
    @Type(type = "DateTime")
    private LocalDateTime createdDate;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.INSERT, Scope.UPDATE })
    private Integer modifiedBy;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT, Scope.UPDATE })
    @Type(type = "DateTime")
    private LocalDateTime modifiedDate;
    private FormTypes formTypes;
    private InspectionDesign inspectionDesign;
    private Users usersByCreatedBy;
    private Users usersByModifiedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`LetterId`", nullable = false, scale = 0, precision = 10)
    public Integer getLetterId() {
        return this.letterId;
    }

    public void setLetterId(Integer letterId) {
        this.letterId = letterId;
    }

    @Column(name = "`LetterTitle`", nullable = true, length = 255)
    public String getLetterTitle() {
        return this.letterTitle;
    }

    public void setLetterTitle(String letterTitle) {
        this.letterTitle = letterTitle;
    }

    @Column(name = "`LetterBody`", nullable = true, length = 2147483647)
    public String getLetterBody() {
        return this.letterBody;
    }

    public void setLetterBody(String letterBody) {
        this.letterBody = letterBody;
    }

    @Column(name = "`FormDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormDesignId() {
        return this.formDesignId;
    }

    public void setFormDesignId(Integer formDesignId) {
        this.formDesignId = formDesignId;
    }

    @Column(name = "`InspectionDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectionDesignId() {
        return this.inspectionDesignId;
    }

    public void setInspectionDesignId(Integer inspectionDesignId) {
        this.inspectionDesignId = inspectionDesignId;
    }

    @Column(name = "`CreatedBy`", nullable = true, updatable = false, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`CreatedDate`", nullable = true)
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "`ModifiedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(name = "`ModifiedDate`", nullable = true)
    public LocalDateTime getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormDesignId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormTypes getFormTypes() {
        return this.formTypes;
    }

    public void setFormTypes(FormTypes formTypes) {
        if(formTypes != null) {
            this.formDesignId = formTypes.getId();
        }

        this.formTypes = formTypes;
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
        if (!(o instanceof LetterTemplates)) return false;
        final LetterTemplates letterTemplates = (LetterTemplates) o;
        return Objects.equals(getLetterId(), letterTemplates.getLetterId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLetterId());
    }
}


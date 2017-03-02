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

/**
 * InspectionCategoryMapping generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`InspectionCategoryMapping`")
public class InspectionCategoryMapping implements Serializable {

    private Integer id;
    private Integer inspectionCategoryId;
    private Integer inspectionDesignId;
    private InspectionCategories inspectionCategories;
    private InspectionDesign inspectionDesign;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`InspectionCategoryId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectionCategoryId() {
        return this.inspectionCategoryId;
    }

    public void setInspectionCategoryId(Integer inspectionCategoryId) {
        this.inspectionCategoryId = inspectionCategoryId;
    }

    @Column(name = "`InspectionDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getInspectionDesignId() {
        return this.inspectionDesignId;
    }

    public void setInspectionDesignId(Integer inspectionDesignId) {
        this.inspectionDesignId = inspectionDesignId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectionCategoryId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public InspectionCategories getInspectionCategories() {
        return this.inspectionCategories;
    }

    public void setInspectionCategories(InspectionCategories inspectionCategories) {
        if(inspectionCategories != null) {
            this.inspectionCategoryId = inspectionCategories.getId();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InspectionCategoryMapping)) return false;
        final InspectionCategoryMapping inspectionCategoryMapping = (InspectionCategoryMapping) o;
        return Objects.equals(getId(), inspectionCategoryMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

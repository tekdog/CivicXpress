/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
 * GisTransaction generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`GisTransaction`")
public class GisTransaction implements Serializable {

    private Integer id;
    private BigDecimal landValue;
    private BigDecimal buildingValue;
    private Date saleDate;
    private String saleAmount;
    private String purchasedBy;
    private int gisRecordId;
    private Gisrecords gisrecords;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`LandValue`", nullable = true, scale = 2, precision = 36)
    public BigDecimal getLandValue() {
        return this.landValue;
    }

    public void setLandValue(BigDecimal landValue) {
        this.landValue = landValue;
    }

    @Column(name = "`BuildingValue`", nullable = true, scale = 2, precision = 36)
    public BigDecimal getBuildingValue() {
        return this.buildingValue;
    }

    public void setBuildingValue(BigDecimal buildingValue) {
        this.buildingValue = buildingValue;
    }

    @Column(name = "`SaleDate`", nullable = true)
    public Date getSaleDate() {
        return this.saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Column(name = "`SaleAmount`", nullable = true, length = 255)
    public String getSaleAmount() {
        return this.saleAmount;
    }

    public void setSaleAmount(String saleAmount) {
        this.saleAmount = saleAmount;
    }

    @Column(name = "`PurchasedBy`", nullable = true, length = 1000)
    public String getPurchasedBy() {
        return this.purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    @Column(name = "`GisRecordId`", nullable = false, scale = 0, precision = 10)
    public int getGisRecordId() {
        return this.gisRecordId;
    }

    public void setGisRecordId(int gisRecordId) {
        this.gisRecordId = gisRecordId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`GisRecordId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Gisrecords getGisrecords() {
        return this.gisrecords;
    }

    public void setGisrecords(Gisrecords gisrecords) {
        if(gisrecords != null) {
            this.gisRecordId = gisrecords.getId();
        }

        this.gisrecords = gisrecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GisTransaction)) return false;
        final GisTransaction gisTransaction = (GisTransaction) o;
        return Objects.equals(getId(), gisTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


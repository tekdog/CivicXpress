/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SearchWithFormTitleResponse implements Serializable {

    @ColumnAlias("formGuid")
    private String formGuid;
    @ColumnAlias("formTitle")
    private String formTitle;
    @ColumnAlias("municipalityName")
    private String municipalityName;
    @ColumnAlias("createdBy")
    private String createdBy;
    @ColumnAlias("formDesign")
    private String formDesign;
    @ColumnAlias("formStatus")
    private String formStatus;
    @ColumnAlias("lot")
    private String lot;
    @ColumnAlias("subdivision")
    private String subdivision;
    @ColumnAlias("address")
    private String address;
    @ColumnAlias("primaryVendor")
    private String primaryVendor;
    @ColumnAlias("balanceDue")
    private BigDecimal balanceDue;
    @ColumnAlias("formTypeID")
    private Integer formTypeId;
    @ColumnAlias("FormCategory")
    private Integer formCategory;
    @ColumnAlias("dateModified")
    private LocalDateTime dateModified;

    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFormDesign() {
        return this.formDesign;
    }

    public void setFormDesign(String formDesign) {
        this.formDesign = formDesign;
    }

    public String getFormStatus() {
        return this.formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    public String getLot() {
        return this.lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getSubdivision() {
        return this.subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrimaryVendor() {
        return this.primaryVendor;
    }

    public void setPrimaryVendor(String primaryVendor) {
        this.primaryVendor = primaryVendor;
    }

    public BigDecimal getBalanceDue() {
        return this.balanceDue;
    }

    public void setBalanceDue(BigDecimal balanceDue) {
        this.balanceDue = balanceDue;
    }

    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    public Integer getFormCategory() {
        return this.formCategory;
    }

    public void setFormCategory(Integer formCategory) {
        this.formCategory = formCategory;
    }

    public LocalDateTime getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchWithFormTitleResponse)) return false;
        final SearchWithFormTitleResponse searchWithFormTitleResponse = (SearchWithFormTitleResponse) o;
        return Objects.equals(getFormGuid(), searchWithFormTitleResponse.getFormGuid()) &&
                Objects.equals(getFormTitle(), searchWithFormTitleResponse.getFormTitle()) &&
                Objects.equals(getMunicipalityName(), searchWithFormTitleResponse.getMunicipalityName()) &&
                Objects.equals(getCreatedBy(), searchWithFormTitleResponse.getCreatedBy()) &&
                Objects.equals(getFormDesign(), searchWithFormTitleResponse.getFormDesign()) &&
                Objects.equals(getFormStatus(), searchWithFormTitleResponse.getFormStatus()) &&
                Objects.equals(getLot(), searchWithFormTitleResponse.getLot()) &&
                Objects.equals(getSubdivision(), searchWithFormTitleResponse.getSubdivision()) &&
                Objects.equals(getAddress(), searchWithFormTitleResponse.getAddress()) &&
                Objects.equals(getPrimaryVendor(), searchWithFormTitleResponse.getPrimaryVendor()) &&
                Objects.equals(getBalanceDue(), searchWithFormTitleResponse.getBalanceDue()) &&
                Objects.equals(getFormTypeId(), searchWithFormTitleResponse.getFormTypeId()) &&
                Objects.equals(getFormCategory(), searchWithFormTitleResponse.getFormCategory()) &&
                Objects.equals(getDateModified(), searchWithFormTitleResponse.getDateModified());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormGuid(),
                getFormTitle(),
                getMunicipalityName(),
                getCreatedBy(),
                getFormDesign(),
                getFormStatus(),
                getLot(),
                getSubdivision(),
                getAddress(),
                getPrimaryVendor(),
                getBalanceDue(),
                getFormTypeId(),
                getFormCategory(),
                getDateModified());
    }
}

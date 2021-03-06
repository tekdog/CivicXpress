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

public class GetFeesForTransactionResponse implements Serializable {

    @ColumnAlias("Amount")
    private BigDecimal amount;
    @ColumnAlias("FeeType")
    private String feeType;
    @ColumnAlias("AccountingCode")
    private String accountingCode;
    @ColumnAlias("FormTitle")
    private String formTitle;
    @ColumnAlias("FormDesign")
    private String formDesign;
    @ColumnAlias("MunicipalityName")
    private String municipalityName;
    @ColumnAlias("FullAddress")
    private String fullAddress;
    @ColumnAlias("Comments")
    private String comments;

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFeeType() {
        return this.feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getAccountingCode() {
        return this.accountingCode;
    }

    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormDesign() {
        return this.formDesign;
    }

    public void setFormDesign(String formDesign) {
        this.formDesign = formDesign;
    }

    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetFeesForTransactionResponse)) return false;
        final GetFeesForTransactionResponse getFeesForTransactionResponse = (GetFeesForTransactionResponse) o;
        return Objects.equals(getAmount(), getFeesForTransactionResponse.getAmount()) &&
                Objects.equals(getFeeType(), getFeesForTransactionResponse.getFeeType()) &&
                Objects.equals(getAccountingCode(), getFeesForTransactionResponse.getAccountingCode()) &&
                Objects.equals(getFormTitle(), getFeesForTransactionResponse.getFormTitle()) &&
                Objects.equals(getFormDesign(), getFeesForTransactionResponse.getFormDesign()) &&
                Objects.equals(getMunicipalityName(), getFeesForTransactionResponse.getMunicipalityName()) &&
                Objects.equals(getFullAddress(), getFeesForTransactionResponse.getFullAddress()) &&
                Objects.equals(getComments(), getFeesForTransactionResponse.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(),
                getFeeType(),
                getAccountingCode(),
                getFormTitle(),
                getFormDesign(),
                getMunicipalityName(),
                getFullAddress(),
                getComments());
    }
}

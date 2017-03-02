/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class FormsCountForMunicipalitiesResponse implements Serializable {

    @JsonProperty("formsCount")
    @ColumnAlias("formsCount")
    private Integer formsCount;

    public Integer getFormsCount() {
        return this.formsCount;
    }

    public void setFormsCount(Integer formsCount) {
        this.formsCount = formsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormsCountForMunicipalitiesResponse)) return false;
        final FormsCountForMunicipalitiesResponse formsCountForMunicipalitiesResponse = (FormsCountForMunicipalitiesResponse) o;
        return Objects.equals(getFormsCount(), formsCountForMunicipalitiesResponse.getFormsCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormsCount());
    }
}

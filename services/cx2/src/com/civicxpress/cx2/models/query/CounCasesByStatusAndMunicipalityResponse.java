/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class CounCasesByStatusAndMunicipalityResponse implements Serializable {

    @ColumnAlias("caseFormsCount")
    private Long caseFormsCount;

    public Long getCaseFormsCount() {
        return this.caseFormsCount;
    }

    public void setCaseFormsCount(Long caseFormsCount) {
        this.caseFormsCount = caseFormsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CounCasesByStatusAndMunicipalityResponse)) return false;
        final CounCasesByStatusAndMunicipalityResponse counCasesByStatusAndMunicipalityResponse = (CounCasesByStatusAndMunicipalityResponse) o;
        return Objects.equals(getCaseFormsCount(), counCasesByStatusAndMunicipalityResponse.getCaseFormsCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCaseFormsCount());
    }
}

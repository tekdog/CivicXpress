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

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class CheckIfCompanyUserIsVendorAdminResponse implements Serializable {

    @JsonProperty("count")
    @ColumnAlias("count")
    private Integer _count;

    public Integer get_count() {
        return this._count;
    }

    public void set_count(Integer _count) {
        this._count = _count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckIfCompanyUserIsVendorAdminResponse)) return false;
        final CheckIfCompanyUserIsVendorAdminResponse checkIfCompanyUserIsVendorAdminResponse = (CheckIfCompanyUserIsVendorAdminResponse) o;
        return Objects.equals(get_count(), checkIfCompanyUserIsVendorAdminResponse.get_count());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_count());
    }
}

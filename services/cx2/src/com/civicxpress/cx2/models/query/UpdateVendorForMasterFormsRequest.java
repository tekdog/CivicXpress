/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateVendorForMasterFormsRequest implements Serializable {

    @JsonProperty("CXVendorId")
    private Integer cxvendorId;
    @JsonProperty("FormGUID")
    private String formGuid;

    public Integer getCxvendorId() {
        return this.cxvendorId;
    }

    public void setCxvendorId(Integer cxvendorId) {
        this.cxvendorId = cxvendorId;
    }

    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateVendorForMasterFormsRequest)) return false;
        final UpdateVendorForMasterFormsRequest updateVendorForMasterFormsRequest = (UpdateVendorForMasterFormsRequest) o;
        return Objects.equals(getCxvendorId(), updateVendorForMasterFormsRequest.getCxvendorId()) &&
                Objects.equals(getFormGuid(), updateVendorForMasterFormsRequest.getFormGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCxvendorId(),
                getFormGuid());
    }
}
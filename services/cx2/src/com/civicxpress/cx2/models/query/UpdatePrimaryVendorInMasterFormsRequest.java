/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePrimaryVendorInMasterFormsRequest implements Serializable {

    @JsonProperty("VendorId")
    private Integer vendorId;
    @JsonProperty("FormGUID")
    private String formGuid;

    public Integer getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
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
        if (!(o instanceof UpdatePrimaryVendorInMasterFormsRequest)) return false;
        final UpdatePrimaryVendorInMasterFormsRequest updatePrimaryVendorInMasterFormsRequest = (UpdatePrimaryVendorInMasterFormsRequest) o;
        return Objects.equals(getVendorId(), updatePrimaryVendorInMasterFormsRequest.getVendorId()) &&
                Objects.equals(getFormGuid(), updatePrimaryVendorInMasterFormsRequest.getFormGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVendorId(),
                getFormGuid());
    }
}

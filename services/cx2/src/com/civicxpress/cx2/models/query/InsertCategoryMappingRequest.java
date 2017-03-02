/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertCategoryMappingRequest implements Serializable {

    @JsonProperty("FormTypeId")
    private Integer formTypeId;
    @JsonProperty("FormCategoryId")
    private Integer formCategoryId;

    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    public Integer getFormCategoryId() {
        return this.formCategoryId;
    }

    public void setFormCategoryId(Integer formCategoryId) {
        this.formCategoryId = formCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertCategoryMappingRequest)) return false;
        final InsertCategoryMappingRequest insertCategoryMappingRequest = (InsertCategoryMappingRequest) o;
        return Objects.equals(getFormTypeId(), insertCategoryMappingRequest.getFormTypeId()) &&
                Objects.equals(getFormCategoryId(), insertCategoryMappingRequest.getFormCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFormTypeId(),
                getFormCategoryId());
    }
}

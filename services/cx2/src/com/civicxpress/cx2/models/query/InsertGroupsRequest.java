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

public class InsertGroupsRequest implements Serializable {

    @JsonProperty("GroupName")
    private String groupName;
    @JsonProperty("GroupDescription")
    private String groupDescription;
    @JsonProperty("MunicipalityId")
    private Integer municipalityId;

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return this.groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertGroupsRequest)) return false;
        final InsertGroupsRequest insertGroupsRequest = (InsertGroupsRequest) o;
        return Objects.equals(getGroupName(), insertGroupsRequest.getGroupName()) &&
                Objects.equals(getGroupDescription(), insertGroupsRequest.getGroupDescription()) &&
                Objects.equals(getMunicipalityId(), insertGroupsRequest.getMunicipalityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName(),
                getGroupDescription(),
                getMunicipalityId());
    }
}

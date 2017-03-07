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

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddGistoProjectsRequest implements Serializable {

    @JsonProperty("GISRecordId")
    private Integer gisrecordId;
    @JsonProperty("RelatedProjectGUID")
    private String relatedProjectGuid;
    @JsonProperty("AddedByUser")
    private Integer addedByUser;
    @JsonProperty("AddedAt")
    private Timestamp addedAt;

    public Integer getGisrecordId() {
        return this.gisrecordId;
    }

    public void setGisrecordId(Integer gisrecordId) {
        this.gisrecordId = gisrecordId;
    }

    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    public Integer getAddedByUser() {
        return this.addedByUser;
    }

    public void setAddedByUser(Integer addedByUser) {
        this.addedByUser = addedByUser;
    }

    public Timestamp getAddedAt() {
        return this.addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddGistoProjectsRequest)) return false;
        final AddGistoProjectsRequest addGistoProjectsRequest = (AddGistoProjectsRequest) o;
        return Objects.equals(getGisrecordId(), addGistoProjectsRequest.getGisrecordId()) &&
                Objects.equals(getRelatedProjectGuid(), addGistoProjectsRequest.getRelatedProjectGuid()) &&
                Objects.equals(getAddedByUser(), addGistoProjectsRequest.getAddedByUser()) &&
                Objects.equals(getAddedAt(), addGistoProjectsRequest.getAddedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGisrecordId(),
                getRelatedProjectGuid(),
                getAddedByUser(),
                getAddedAt());
    }
}

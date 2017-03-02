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
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetProjectGisrecordsResponse implements Serializable {

    @JsonProperty("addedAt")
    @ColumnAlias("addedAt")
    private Date addedAt;
    @JsonProperty("addedByUser")
    @ColumnAlias("addedByUser")
    private Integer addedByUser;
    @JsonProperty("gisrecordId")
    @ColumnAlias("gisrecordId")
    private Integer gisrecordId;
    @JsonProperty("gisrecords")
    @ColumnAlias("gisrecords")
    private Gisrecords gisrecords;
    @JsonProperty("id")
    @ColumnAlias("id")
    private Integer id;
    @JsonProperty("projects")
    @ColumnAlias("projects")
    private Projects projects;
    @JsonProperty("relatedProjectGuid")
    @ColumnAlias("relatedProjectGuid")
    private String relatedProjectGuid;
    @JsonProperty("users")
    @ColumnAlias("users")
    private Users users;

    public Date getAddedAt() {
        return this.addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public Integer getAddedByUser() {
        return this.addedByUser;
    }

    public void setAddedByUser(Integer addedByUser) {
        this.addedByUser = addedByUser;
    }

    public Integer getGisrecordId() {
        return this.gisrecordId;
    }

    public void setGisrecordId(Integer gisrecordId) {
        this.gisrecordId = gisrecordId;
    }

    public Gisrecords getGisrecords() {
        return this.gisrecords;
    }

    public void setGisrecords(Gisrecords gisrecords) {
        this.gisrecords = gisrecords;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Projects getProjects() {
        return this.projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetProjectGisrecordsResponse)) return false;
        final GetProjectGisrecordsResponse getProjectGisrecordsResponse = (GetProjectGisrecordsResponse) o;
        return Objects.equals(getAddedAt(), getProjectGisrecordsResponse.getAddedAt()) &&
                Objects.equals(getAddedByUser(), getProjectGisrecordsResponse.getAddedByUser()) &&
                Objects.equals(getGisrecordId(), getProjectGisrecordsResponse.getGisrecordId()) &&
                Objects.equals(getGisrecords(), getProjectGisrecordsResponse.getGisrecords()) &&
                Objects.equals(getId(), getProjectGisrecordsResponse.getId()) &&
                Objects.equals(getProjects(), getProjectGisrecordsResponse.getProjects()) &&
                Objects.equals(getRelatedProjectGuid(), getProjectGisrecordsResponse.getRelatedProjectGuid()) &&
                Objects.equals(getUsers(), getProjectGisrecordsResponse.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddedAt(),
                getAddedByUser(),
                getGisrecordId(),
                getGisrecords(),
                getId(),
                getProjects(),
                getRelatedProjectGuid(),
                getUsers());
    }
}

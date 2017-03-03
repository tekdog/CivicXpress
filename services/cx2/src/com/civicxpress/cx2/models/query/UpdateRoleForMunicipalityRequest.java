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

public class UpdateRoleForMunicipalityRequest implements Serializable {

    @JsonProperty("role")
    private String role;
    @JsonProperty("municipality")
    private Integer municipality;
    @JsonProperty("user")
    private Integer user;

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getMunicipality() {
        return this.municipality;
    }

    public void setMunicipality(Integer municipality) {
        this.municipality = municipality;
    }

    public Integer getUser() {
        return this.user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateRoleForMunicipalityRequest)) return false;
        final UpdateRoleForMunicipalityRequest updateRoleForMunicipalityRequest = (UpdateRoleForMunicipalityRequest) o;
        return Objects.equals(getRole(), updateRoleForMunicipalityRequest.getRole()) &&
                Objects.equals(getMunicipality(), updateRoleForMunicipalityRequest.getMunicipality()) &&
                Objects.equals(getUser(), updateRoleForMunicipalityRequest.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(),
                getMunicipality(),
                getUser());
    }
}

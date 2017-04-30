/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * Document generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`Document`")
public class Document implements Serializable {

    private BigInteger id;
    private String itemGuid;
    private String filename;
    private String mimetype;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] contents;
    @Type(type = "DateTime")
    private LocalDateTime dateCreated;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.UPDATE, Scope.INSERT })
    private int createdBy;
    private Integer violationId;
    private Integer gisRecordId;
    private Users users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 18)
    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Column(name = "`ItemGUID`", nullable = true, length = 255)
    public String getItemGuid() {
        return this.itemGuid;
    }

    public void setItemGuid(String itemGuid) {
        this.itemGuid = itemGuid;
    }

    @Column(name = "`Filename`", nullable = false, length = 255)
    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(name = "`Mimetype`", nullable = false, length = 255)
    public String getMimetype() {
        return this.mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    @Column(name = "`Contents`", nullable = false)
    public byte[] getContents() {
        return this.contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    @Column(name = "`DateCreated`", nullable = false)
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "`CreatedBy`", nullable = false, scale = 0, precision = 10)
    public int getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`ViolationId`", nullable = true, scale = 0, precision = 10)
    public Integer getViolationId() {
        return this.violationId;
    }

    public void setViolationId(Integer violationId) {
        this.violationId = violationId;
    }

    @Column(name = "`GisRecordId`", nullable = true, scale = 0, precision = 10)
    public Integer getGisRecordId() {
        return this.gisRecordId;
    }

    public void setGisRecordId(Integer gisRecordId) {
        this.gisRecordId = gisRecordId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CreatedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.createdBy = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        final Document document = (Document) o;
        return Objects.equals(getId(), document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


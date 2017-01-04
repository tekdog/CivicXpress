/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
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

/**
 * SfnewResidentialStructure generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`SFNewResidentialStructure`")
public class SfnewResidentialStructure implements Serializable {

    private Integer id;
    private String propertyOwnerName;
    private String propertyOwnerPhNo;
    private String basementSft;
    private String garageSft;
    private String sidingMaterial;
    private String totalSft;
    private String valueOfWork;
    private Integer formTypeId;
    private Integer userId;
    private Integer formStatusId;
    private String guid;
    private String foundationType;
    private FormStatuses formStatuses;
    private FormTypes formTypes;
    private Users users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`PropertyOwnerName`", nullable = true, length = 255)
    public String getPropertyOwnerName() {
        return this.propertyOwnerName;
    }

    public void setPropertyOwnerName(String propertyOwnerName) {
        this.propertyOwnerName = propertyOwnerName;
    }

    @Column(name = "`PropertyOwnerPhNo`", nullable = true, length = 255)
    public String getPropertyOwnerPhNo() {
        return this.propertyOwnerPhNo;
    }

    public void setPropertyOwnerPhNo(String propertyOwnerPhNo) {
        this.propertyOwnerPhNo = propertyOwnerPhNo;
    }

    @Column(name = "`BasementSFT`", nullable = true, length = 255)
    public String getBasementSft() {
        return this.basementSft;
    }

    public void setBasementSft(String basementSft) {
        this.basementSft = basementSft;
    }

    @Column(name = "`GarageSFT`", nullable = true, length = 255)
    public String getGarageSft() {
        return this.garageSft;
    }

    public void setGarageSft(String garageSft) {
        this.garageSft = garageSft;
    }

    @Column(name = "`SidingMaterial`", nullable = true, length = 255)
    public String getSidingMaterial() {
        return this.sidingMaterial;
    }

    public void setSidingMaterial(String sidingMaterial) {
        this.sidingMaterial = sidingMaterial;
    }

    @Column(name = "`TotalSFT`", nullable = true, length = 255)
    public String getTotalSft() {
        return this.totalSft;
    }

    public void setTotalSft(String totalSft) {
        this.totalSft = totalSft;
    }

    @Column(name = "`ValueOfWork`", nullable = true, length = 255)
    public String getValueOfWork() {
        return this.valueOfWork;
    }

    public void setValueOfWork(String valueOfWork) {
        this.valueOfWork = valueOfWork;
    }

    @Column(name = "`FormTypeId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormTypeId() {
        return this.formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    @Column(name = "`UserId`", nullable = true, scale = 0, precision = 10)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "`FormStatusId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormStatusId() {
        return this.formStatusId;
    }

    public void setFormStatusId(Integer formStatusId) {
        this.formStatusId = formStatusId;
    }

    @Column(name = "`GUID`", nullable = true, length = 255)
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Column(name = "`FoundationType`", nullable = true, length = 255)
    public String getFoundationType() {
        return this.foundationType;
    }

    public void setFoundationType(String foundationType) {
        this.foundationType = foundationType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormStatusId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormStatuses getFormStatuses() {
        return this.formStatuses;
    }

    public void setFormStatuses(FormStatuses formStatuses) {
        if(formStatuses != null) {
            this.formStatusId = formStatuses.getId();
        }

        this.formStatuses = formStatuses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormTypeId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormTypes getFormTypes() {
        return this.formTypes;
    }

    public void setFormTypes(FormTypes formTypes) {
        if(formTypes != null) {
            this.formTypeId = formTypes.getId();
        }

        this.formTypes = formTypes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`UserId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.userId = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SfnewResidentialStructure)) return false;
        final SfnewResidentialStructure sfnewResidentialStructure = (SfnewResidentialStructure) o;
        return Objects.equals(getId(), sfnewResidentialStructure.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


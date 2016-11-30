/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ContractorTypes generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ContractorTypes`")
public class ContractorTypes implements Serializable {

    private Integer id;
    private String contractorType;
    private List<Vendor> vendors = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`ContractorType`", nullable = true, length = 255)
    public String getContractorType() {
        return this.contractorType;
    }

    public void setContractorType(String contractorType) {
        this.contractorType = contractorType;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "contractorTypes")
    public List<Vendor> getVendors() {
        return this.vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractorTypes)) return false;
        final ContractorTypes contractorTypes = (ContractorTypes) o;
        return Objects.equals(getId(), contractorTypes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

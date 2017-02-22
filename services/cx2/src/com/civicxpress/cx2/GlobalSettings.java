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
 * GlobalSettings generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`GlobalSettings`")
public class GlobalSettings implements Serializable {

    private Integer id;
    private Float technologyFee;
    private String cxglobalSignature;
    private String cxphoneNumber;
    private String cxaddress1;
    private String cxaddress2;
    private String cxcity;
    private Integer cxstate;
    private String cxwebsite;
    private String cxemail;
    private String cxpostalCode;
    private String cxeula;
    private States states;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`TechnologyFee`", nullable = true, scale = 2, precision = 10)
    public Float getTechnologyFee() {
        return this.technologyFee;
    }

    public void setTechnologyFee(Float technologyFee) {
        this.technologyFee = technologyFee;
    }

    @Column(name = "`CXGlobalSignature`", nullable = true, length = 500)
    public String getCxglobalSignature() {
        return this.cxglobalSignature;
    }

    public void setCxglobalSignature(String cxglobalSignature) {
        this.cxglobalSignature = cxglobalSignature;
    }

    @Column(name = "`CXPhoneNumber`", nullable = true, length = 255)
    public String getCxphoneNumber() {
        return this.cxphoneNumber;
    }

    public void setCxphoneNumber(String cxphoneNumber) {
        this.cxphoneNumber = cxphoneNumber;
    }

    @Column(name = "`CXAddress1`", nullable = true, length = 255)
    public String getCxaddress1() {
        return this.cxaddress1;
    }

    public void setCxaddress1(String cxaddress1) {
        this.cxaddress1 = cxaddress1;
    }

    @Column(name = "`CXAddress2`", nullable = true, length = 255)
    public String getCxaddress2() {
        return this.cxaddress2;
    }

    public void setCxaddress2(String cxaddress2) {
        this.cxaddress2 = cxaddress2;
    }

    @Column(name = "`CXCity`", nullable = true, length = 255)
    public String getCxcity() {
        return this.cxcity;
    }

    public void setCxcity(String cxcity) {
        this.cxcity = cxcity;
    }

    @Column(name = "`CXState`", nullable = true, scale = 0, precision = 10)
    public Integer getCxstate() {
        return this.cxstate;
    }

    public void setCxstate(Integer cxstate) {
        this.cxstate = cxstate;
    }

    @Column(name = "`CXWebsite`", nullable = true, length = 255)
    public String getCxwebsite() {
        return this.cxwebsite;
    }

    public void setCxwebsite(String cxwebsite) {
        this.cxwebsite = cxwebsite;
    }

    @Column(name = "`CXEmail`", nullable = true, length = 255)
    public String getCxemail() {
        return this.cxemail;
    }

    public void setCxemail(String cxemail) {
        this.cxemail = cxemail;
    }

    @Column(name = "`CXPostalCode`", nullable = true, length = 255)
    public String getCxpostalCode() {
        return this.cxpostalCode;
    }

    public void setCxpostalCode(String cxpostalCode) {
        this.cxpostalCode = cxpostalCode;
    }

    @Column(name = "`CXEULA`", nullable = true, length = 2147483647)
    public String getCxeula() {
        return this.cxeula;
    }

    public void setCxeula(String cxeula) {
        this.cxeula = cxeula;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CXState`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public States getStates() {
        return this.states;
    }

    public void setStates(States states) {
        if(states != null) {
            this.cxstate = states.getId();
        }

        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GlobalSettings)) return false;
        final GlobalSettings globalSettings = (GlobalSettings) o;
        return Objects.equals(getId(), globalSettings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


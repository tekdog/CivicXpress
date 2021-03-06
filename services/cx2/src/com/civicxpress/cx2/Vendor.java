/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Vendor generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`Vendor`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`FeinNumber`"})})
public class Vendor implements Serializable {

    private Integer id;
    private String companyName;
    private String address1;
    private String address2;
    private String city;
    private Integer stateId;
    private String country;
    private String postalCode;
    private String faxNumber;
    private String companyPhone;
    private String altPhone;
    private String companyEmail;
    private String companyWebsite;
    private String typeOfBusiness;
    private Integer contractorTypeId;
    private String businessClassification;
    private String stateRegNumber;
    private Integer numberOfEmployees;
    private String feinNumber;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] coi;
    private String insuranceCompany;
    private Date insuranceExpires;
    private Date lastUpdated;
    private boolean active;
    private String fullAddress;
    private ContractorTypes contractorTypes;
    private States states;
    private List<Fees> feeses;
    private List<MasterForms> masterFormses;
    private List<Projects> projectses;
    private List<VendorAdmins> vendorAdminses;
    private List<VendorApprovals> vendorApprovalses;
    private List<VendorLicenses> vendorLicenseses;
    private List<Vendors2form> vendors2forms;
    private List<VendorsToProject> vendorsToProjects;
    private List<VendorUsers> vendorUserses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`CompanyName`", nullable = true, length = 255)
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "`Address1`", nullable = true, length = 255)
    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column(name = "`Address2`", nullable = true, length = 255)
    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "`City`", nullable = true, length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "`StateId`", nullable = true, scale = 0, precision = 10)
    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Column(name = "`Country`", nullable = true, length = 255)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "`PostalCode`", nullable = true, length = 255)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "`FaxNumber`", nullable = true, length = 255)
    public String getFaxNumber() {
        return this.faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    @Column(name = "`CompanyPhone`", nullable = true, length = 255)
    public String getCompanyPhone() {
        return this.companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    @Column(name = "`AltPhone`", nullable = true, length = 255)
    public String getAltPhone() {
        return this.altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    @Column(name = "`CompanyEmail`", nullable = true, length = 255)
    public String getCompanyEmail() {
        return this.companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Column(name = "`CompanyWebsite`", nullable = true, length = 255)
    public String getCompanyWebsite() {
        return this.companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    @Column(name = "`TypeOfBusiness`", nullable = true, length = 255)
    public String getTypeOfBusiness() {
        return this.typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    @Column(name = "`ContractorTypeId`", nullable = true, scale = 0, precision = 10)
    public Integer getContractorTypeId() {
        return this.contractorTypeId;
    }

    public void setContractorTypeId(Integer contractorTypeId) {
        this.contractorTypeId = contractorTypeId;
    }

    @Column(name = "`BusinessClassification`", nullable = true, length = 255)
    public String getBusinessClassification() {
        return this.businessClassification;
    }

    public void setBusinessClassification(String businessClassification) {
        this.businessClassification = businessClassification;
    }

    @Column(name = "`StateRegNumber`", nullable = true, length = 255)
    public String getStateRegNumber() {
        return this.stateRegNumber;
    }

    public void setStateRegNumber(String stateRegNumber) {
        this.stateRegNumber = stateRegNumber;
    }

    @Column(name = "`NumberOfEmployees`", nullable = true, scale = 0, precision = 10)
    public Integer getNumberOfEmployees() {
        return this.numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @Column(name = "`FeinNumber`", nullable = true, length = 255)
    public String getFeinNumber() {
        return this.feinNumber;
    }

    public void setFeinNumber(String feinNumber) {
        this.feinNumber = feinNumber;
    }

    @Column(name = "`COI`", nullable = true)
    public byte[] getCoi() {
        return this.coi;
    }

    public void setCoi(byte[] coi) {
        this.coi = coi;
    }

    @Column(name = "`InsuranceCompany`", nullable = true, length = 255)
    public String getInsuranceCompany() {
        return this.insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Column(name = "`InsuranceExpires`", nullable = true)
    public Date getInsuranceExpires() {
        return this.insuranceExpires;
    }

    public void setInsuranceExpires(Date insuranceExpires) {
        this.insuranceExpires = insuranceExpires;
    }

    @Column(name = "`LastUpdated`", nullable = true)
    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Column(name = "`Active`", nullable = false)
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "`FullAddress`", nullable = false, insertable = false, updatable = false, length = 768)
    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ContractorTypeId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public ContractorTypes getContractorTypes() {
        return this.contractorTypes;
    }

    public void setContractorTypes(ContractorTypes contractorTypes) {
        if(contractorTypes != null) {
            this.contractorTypeId = contractorTypes.getId();
        }

        this.contractorTypes = contractorTypes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`StateId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public States getStates() {
        return this.states;
    }

    public void setStates(States states) {
        if(states != null) {
            this.stateId = states.getId();
        }

        this.states = states;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<Fees> getFeeses() {
        return this.feeses;
    }

    public void setFeeses(List<Fees> feeses) {
        this.feeses = feeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<MasterForms> getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List<MasterForms> masterFormses) {
        this.masterFormses = masterFormses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<Projects> getProjectses() {
        return this.projectses;
    }

    public void setProjectses(List<Projects> projectses) {
        this.projectses = projectses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<VendorAdmins> getVendorAdminses() {
        return this.vendorAdminses;
    }

    public void setVendorAdminses(List<VendorAdmins> vendorAdminses) {
        this.vendorAdminses = vendorAdminses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<VendorApprovals> getVendorApprovalses() {
        return this.vendorApprovalses;
    }

    public void setVendorApprovalses(List<VendorApprovals> vendorApprovalses) {
        this.vendorApprovalses = vendorApprovalses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<VendorLicenses> getVendorLicenseses() {
        return this.vendorLicenseses;
    }

    public void setVendorLicenseses(List<VendorLicenses> vendorLicenseses) {
        this.vendorLicenseses = vendorLicenseses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<Vendors2form> getVendors2forms() {
        return this.vendors2forms;
    }

    public void setVendors2forms(List<Vendors2form> vendors2forms) {
        this.vendors2forms = vendors2forms;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<VendorsToProject> getVendorsToProjects() {
        return this.vendorsToProjects;
    }

    public void setVendorsToProjects(List<VendorsToProject> vendorsToProjects) {
        this.vendorsToProjects = vendorsToProjects;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "vendor")
    public List<VendorUsers> getVendorUserses() {
        return this.vendorUserses;
    }

    public void setVendorUserses(List<VendorUsers> vendorUserses) {
        this.vendorUserses = vendorUserses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendor)) return false;
        final Vendor vendor = (Vendor) o;
        return Objects.equals(getId(), vendor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


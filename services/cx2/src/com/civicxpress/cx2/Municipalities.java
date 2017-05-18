/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Time;
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Municipalities generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`Municipalities`")
public class Municipalities implements Serializable {

    private Integer id;
    private String municipalityGuid;
    private String municipalityName;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private Boolean allowManualPayment;
    private Integer manualPaymentPercent;
    private Boolean active;
    private String globalEmailSig;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] logo;
    private Integer stateId;
    private Boolean mondayYn;
    private Boolean tuesdayYn;
    private Boolean wednesdayYn;
    private Boolean thursdayYn;
    private Boolean fridayYn;
    private Boolean saturdayYn;
    private Boolean sundayYn;
    private String timeZone;
    private Time openTime;
    private Time closeTime;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private int userLimit;
    private int formLimit;
    private boolean allowOfflineCc;
    private States states;
    private List<CodeSets> codeSetses;
    private List<CaseTypes> caseTypeses;
    private List<Fees> feeses;
    private List<FormCategories> formCategorieses;
    private List<FormTypes> formTypeses;
    private List<Gisrecords> gisrecordses;
    private List<InspectionDesign> inspectionDesigns;
    private List<Holidays> holidayses;
    private List<InspectionCategories> inspectionCategorieses;
    private List<ManualFeeTypes> manualFeeTypeses;
    private List<MasterForms> masterFormses;
    private List<MunicipalityGroups> municipalityGroupses;
    private List<Projects> projectses;
    private List<Roles> roleses;
    private List<Subdivisions> subdivisionses;
    private List<UserSubscriptions> userSubscriptionses;
    private List<VendorApprovals> vendorApprovalses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`MunicipalityGuid`", nullable = true, length = 255)
    public String getMunicipalityGuid() {
        return this.municipalityGuid;
    }

    public void setMunicipalityGuid(String municipalityGuid) {
        this.municipalityGuid = municipalityGuid;
    }

    @Column(name = "`MunicipalityName`", nullable = true, length = 255)
    public String getMunicipalityName() {
        return this.municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
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

    @Column(name = "`PostalCode`", nullable = true, length = 255)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "`Phone`", nullable = true, length = 255)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "`Email`", nullable = true, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "`AllowManualPayment`", nullable = true)
    public Boolean getAllowManualPayment() {
        return this.allowManualPayment;
    }

    public void setAllowManualPayment(Boolean allowManualPayment) {
        this.allowManualPayment = allowManualPayment;
    }

    @Column(name = "`ManualPaymentPercent`", nullable = true, scale = 0, precision = 10)
    public Integer getManualPaymentPercent() {
        return this.manualPaymentPercent;
    }

    public void setManualPaymentPercent(Integer manualPaymentPercent) {
        this.manualPaymentPercent = manualPaymentPercent;
    }

    @Column(name = "`Active`", nullable = true)
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "`GlobalEmailSig`", nullable = true, length = 500)
    public String getGlobalEmailSig() {
        return this.globalEmailSig;
    }

    public void setGlobalEmailSig(String globalEmailSig) {
        this.globalEmailSig = globalEmailSig;
    }

    @Column(name = "`Logo`", nullable = true)
    public byte[] getLogo() {
        return this.logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Column(name = "`StateId`", nullable = true, scale = 0, precision = 10)
    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Column(name = "`MondayYN`", nullable = true)
    public Boolean getMondayYn() {
        return this.mondayYn;
    }

    public void setMondayYn(Boolean mondayYn) {
        this.mondayYn = mondayYn;
    }

    @Column(name = "`TuesdayYN`", nullable = true)
    public Boolean getTuesdayYn() {
        return this.tuesdayYn;
    }

    public void setTuesdayYn(Boolean tuesdayYn) {
        this.tuesdayYn = tuesdayYn;
    }

    @Column(name = "`WednesdayYN`", nullable = true)
    public Boolean getWednesdayYn() {
        return this.wednesdayYn;
    }

    public void setWednesdayYn(Boolean wednesdayYn) {
        this.wednesdayYn = wednesdayYn;
    }

    @Column(name = "`ThursdayYN`", nullable = true)
    public Boolean getThursdayYn() {
        return this.thursdayYn;
    }

    public void setThursdayYn(Boolean thursdayYn) {
        this.thursdayYn = thursdayYn;
    }

    @Column(name = "`FridayYN`", nullable = true)
    public Boolean getFridayYn() {
        return this.fridayYn;
    }

    public void setFridayYn(Boolean fridayYn) {
        this.fridayYn = fridayYn;
    }

    @Column(name = "`SaturdayYN`", nullable = true)
    public Boolean getSaturdayYn() {
        return this.saturdayYn;
    }

    public void setSaturdayYn(Boolean saturdayYn) {
        this.saturdayYn = saturdayYn;
    }

    @Column(name = "`SundayYN`", nullable = true)
    public Boolean getSundayYn() {
        return this.sundayYn;
    }

    public void setSundayYn(Boolean sundayYn) {
        this.sundayYn = sundayYn;
    }

    @Column(name = "`TimeZone`", nullable = true, length = 255)
    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Column(name = "`OpenTime`", nullable = true)
    public Time getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    @Column(name = "`CloseTime`", nullable = true)
    public Time getCloseTime() {
        return this.closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    @Column(name = "`DbName`", nullable = true, length = 255)
    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Column(name = "`DbUser`", nullable = true, length = 255)
    public String getDbUser() {
        return this.dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    @Column(name = "`DbPassword`", nullable = true, length = 255)
    public String getDbPassword() {
        return this.dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @Column(name = "`UserLimit`", nullable = false, scale = 0, precision = 10)
    public int getUserLimit() {
        return this.userLimit;
    }

    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    @Column(name = "`FormLimit`", nullable = false, scale = 0, precision = 10)
    public int getFormLimit() {
        return this.formLimit;
    }

    public void setFormLimit(int formLimit) {
        this.formLimit = formLimit;
    }

    @Column(name = "`AllowOfflineCC`", nullable = false)
    public boolean isAllowOfflineCc() {
        return this.allowOfflineCc;
    }

    public void setAllowOfflineCc(boolean allowOfflineCc) {
        this.allowOfflineCc = allowOfflineCc;
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<CodeSets> getCodeSetses() {
        return this.codeSetses;
    }

    public void setCodeSetses(List<CodeSets> codeSetses) {
        this.codeSetses = codeSetses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<CaseTypes> getCaseTypeses() {
        return this.caseTypeses;
    }

    public void setCaseTypeses(List<CaseTypes> caseTypeses) {
        this.caseTypeses = caseTypeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<Fees> getFeeses() {
        return this.feeses;
    }

    public void setFeeses(List<Fees> feeses) {
        this.feeses = feeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<FormCategories> getFormCategorieses() {
        return this.formCategorieses;
    }

    public void setFormCategorieses(List<FormCategories> formCategorieses) {
        this.formCategorieses = formCategorieses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<FormTypes> getFormTypeses() {
        return this.formTypeses;
    }

    public void setFormTypeses(List<FormTypes> formTypeses) {
        this.formTypeses = formTypeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<Gisrecords> getGisrecordses() {
        return this.gisrecordses;
    }

    public void setGisrecordses(List<Gisrecords> gisrecordses) {
        this.gisrecordses = gisrecordses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<InspectionDesign> getInspectionDesigns() {
        return this.inspectionDesigns;
    }

    public void setInspectionDesigns(List<InspectionDesign> inspectionDesigns) {
        this.inspectionDesigns = inspectionDesigns;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<Holidays> getHolidayses() {
        return this.holidayses;
    }

    public void setHolidayses(List<Holidays> holidayses) {
        this.holidayses = holidayses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<InspectionCategories> getInspectionCategorieses() {
        return this.inspectionCategorieses;
    }

    public void setInspectionCategorieses(List<InspectionCategories> inspectionCategorieses) {
        this.inspectionCategorieses = inspectionCategorieses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<ManualFeeTypes> getManualFeeTypeses() {
        return this.manualFeeTypeses;
    }

    public void setManualFeeTypeses(List<ManualFeeTypes> manualFeeTypeses) {
        this.manualFeeTypeses = manualFeeTypeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<MasterForms> getMasterFormses() {
        return this.masterFormses;
    }

    public void setMasterFormses(List<MasterForms> masterFormses) {
        this.masterFormses = masterFormses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<MunicipalityGroups> getMunicipalityGroupses() {
        return this.municipalityGroupses;
    }

    public void setMunicipalityGroupses(List<MunicipalityGroups> municipalityGroupses) {
        this.municipalityGroupses = municipalityGroupses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<Projects> getProjectses() {
        return this.projectses;
    }

    public void setProjectses(List<Projects> projectses) {
        this.projectses = projectses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<Roles> getRoleses() {
        return this.roleses;
    }

    public void setRoleses(List<Roles> roleses) {
        this.roleses = roleses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<Subdivisions> getSubdivisionses() {
        return this.subdivisionses;
    }

    public void setSubdivisionses(List<Subdivisions> subdivisionses) {
        this.subdivisionses = subdivisionses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<UserSubscriptions> getUserSubscriptionses() {
        return this.userSubscriptionses;
    }

    public void setUserSubscriptionses(List<UserSubscriptions> userSubscriptionses) {
        this.userSubscriptionses = userSubscriptionses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "municipalities")
    public List<VendorApprovals> getVendorApprovalses() {
        return this.vendorApprovalses;
    }

    public void setVendorApprovalses(List<VendorApprovals> vendorApprovalses) {
        this.vendorApprovalses = vendorApprovalses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Municipalities)) return false;
        final Municipalities municipalities = (Municipalities) o;
        return Objects.equals(getId(), municipalities.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


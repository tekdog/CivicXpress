/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Fees generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`Fees`")
public class Fees implements Serializable {

    private Integer id;
    private Integer municipalityId;
    private Integer gisid;
    private String feeType;
    private boolean autoFeeYn;
    private String accountingCode;
    private String paidStatus;
    private Date paidDate;
    private Integer paidByUserId;
    private String transactionId;
    private String comments;
    private String inspectionGuid;
    private String caseGuid;
    private String projectGuid;
    private Integer cxvendorId;
    private String itemTitle;
    private String formGuid;
    private BigDecimal amount;
    private String transactionComments;
    private MasterForms masterForms;
    private MasterInspections masterInspections;
    private Projects projects;
    private Vendor vendor;
    private Gisrecords gisrecords;
    private Municipalities municipalities;
    private Users users;
    private List<MyCart> myCarts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`GISID`", nullable = true, scale = 0, precision = 10)
    public Integer getGisid() {
        return this.gisid;
    }

    public void setGisid(Integer gisid) {
        this.gisid = gisid;
    }

    @Column(name = "`FeeType`", nullable = true, length = 255)
    public String getFeeType() {
        return this.feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Column(name = "`AutoFeeYN`", nullable = false)
    public boolean isAutoFeeYn() {
        return this.autoFeeYn;
    }

    public void setAutoFeeYn(boolean autoFeeYn) {
        this.autoFeeYn = autoFeeYn;
    }

    @Column(name = "`AccountingCode`", nullable = true, length = 255)
    public String getAccountingCode() {
        return this.accountingCode;
    }

    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    @Column(name = "`PaidStatus`", nullable = true, length = 255)
    public String getPaidStatus() {
        return this.paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    @Column(name = "`PaidDate`", nullable = true)
    public Date getPaidDate() {
        return this.paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    @Column(name = "`PaidByUserId`", nullable = true, scale = 0, precision = 10)
    public Integer getPaidByUserId() {
        return this.paidByUserId;
    }

    public void setPaidByUserId(Integer paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    @Column(name = "`TransactionId`", nullable = true, length = 255)
    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "`Comments`", nullable = true, length = 500)
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "`InspectionGuid`", nullable = true, length = 32)
    public String getInspectionGuid() {
        return this.inspectionGuid;
    }

    public void setInspectionGuid(String inspectionGuid) {
        this.inspectionGuid = inspectionGuid;
    }

    @Column(name = "`CaseGuid`", nullable = true, length = 255)
    public String getCaseGuid() {
        return this.caseGuid;
    }

    public void setCaseGuid(String caseGuid) {
        this.caseGuid = caseGuid;
    }

    @Column(name = "`ProjectGuid`", nullable = true, length = 32)
    public String getProjectGuid() {
        return this.projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    @Column(name = "`CXVendorId`", nullable = true, scale = 0, precision = 10)
    public Integer getCxvendorId() {
        return this.cxvendorId;
    }

    public void setCxvendorId(Integer cxvendorId) {
        this.cxvendorId = cxvendorId;
    }

    @Column(name = "`ItemTitle`", nullable = true, length = 255)
    public String getItemTitle() {
        return this.itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    @Column(name = "`FormGuid`", nullable = true, length = 32)
    public String getFormGuid() {
        return this.formGuid;
    }

    public void setFormGuid(String formGuid) {
        this.formGuid = formGuid;
    }

    @Column(name = "`Amount`", nullable = false, scale = 2, precision = 36)
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "`TransactionComments`", nullable = true, length = 255)
    public String getTransactionComments() {
        return this.transactionComments;
    }

    public void setTransactionComments(String transactionComments) {
        this.transactionComments = transactionComments;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormGuid`", referencedColumnName = "`FormGUID`", insertable = false, updatable = false)
    public MasterForms getMasterForms() {
        return this.masterForms;
    }

    public void setMasterForms(MasterForms masterForms) {
        if(masterForms != null) {
            this.formGuid = masterForms.getFormGuid();
        }

        this.masterForms = masterForms;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`InspectionGuid`", referencedColumnName = "`InspectionGuid`", insertable = false, updatable = false)
    public MasterInspections getMasterInspections() {
        return this.masterInspections;
    }

    public void setMasterInspections(MasterInspections masterInspections) {
        if(masterInspections != null) {
            this.inspectionGuid = masterInspections.getInspectionGuid();
        }

        this.masterInspections = masterInspections;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ProjectGuid`", referencedColumnName = "`ProjectGUID`", insertable = false, updatable = false)
    public Projects getProjects() {
        return this.projects;
    }

    public void setProjects(Projects projects) {
        if(projects != null) {
            this.projectGuid = projects.getProjectGuid();
        }

        this.projects = projects;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CXVendorId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Vendor getVendor() {
        return this.vendor;
    }

    public void setVendor(Vendor vendor) {
        if(vendor != null) {
            this.cxvendorId = vendor.getId();
        }

        this.vendor = vendor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`GISID`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Gisrecords getGisrecords() {
        return this.gisrecords;
    }

    public void setGisrecords(Gisrecords gisrecords) {
        if(gisrecords != null) {
            this.gisid = gisrecords.getId();
        }

        this.gisrecords = gisrecords;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`MunicipalityId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Municipalities getMunicipalities() {
        return this.municipalities;
    }

    public void setMunicipalities(Municipalities municipalities) {
        if(municipalities != null) {
            this.municipalityId = municipalities.getId();
        }

        this.municipalities = municipalities;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PaidByUserId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.paidByUserId = users.getId();
        }

        this.users = users;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "fees")
    public List<MyCart> getMyCarts() {
        return this.myCarts;
    }

    public void setMyCarts(List<MyCart> myCarts) {
        this.myCarts = myCarts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fees)) return false;
        final Fees fees = (Fees) o;
        return Objects.equals(getId(), fees.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


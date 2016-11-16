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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FormTypes generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`FormTypes`")
public class FormTypes implements Serializable {

    private Integer id;
    private String formTypeGuid;
    private Integer municipalityId;
    private String formType;
    private String permissionsReaders;
    private String permissionsWriters;
    private String tbLocation;
    private String flatFee;
    private String flatFeeAccountingCode;
    private String sfFee;
    private String sfFeeAccountingCode;
    private String unitFee;
    private String unitFeeAccountingCode;
    private String stateFee;
    private String stateFeeAccountingCode;
    private String report;
    private Boolean active;
    private Integer formCategoryId;
    private String pageName;
    private List<SfnewResidentialStructure> sfnewResidentialStructures = new ArrayList<>();
    private FormCategories formCategories;
    private Municipalities municipalities;
    private List<FormStatuses> formStatuseses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`FormTypeGuid`", nullable = true, length = 255)
    public String getFormTypeGuid() {
        return this.formTypeGuid;
    }

    public void setFormTypeGuid(String formTypeGuid) {
        this.formTypeGuid = formTypeGuid;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`FormType`", nullable = true, length = 255)
    public String getFormType() {
        return this.formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Column(name = "`PermissionsReaders`", nullable = true, length = 255)
    public String getPermissionsReaders() {
        return this.permissionsReaders;
    }

    public void setPermissionsReaders(String permissionsReaders) {
        this.permissionsReaders = permissionsReaders;
    }

    @Column(name = "`PermissionsWriters`", nullable = true, length = 255)
    public String getPermissionsWriters() {
        return this.permissionsWriters;
    }

    public void setPermissionsWriters(String permissionsWriters) {
        this.permissionsWriters = permissionsWriters;
    }

    @Column(name = "`TbLocation`", nullable = true, length = 255)
    public String getTbLocation() {
        return this.tbLocation;
    }

    public void setTbLocation(String tbLocation) {
        this.tbLocation = tbLocation;
    }

    @Column(name = "`FlatFee`", nullable = true, length = 255)
    public String getFlatFee() {
        return this.flatFee;
    }

    public void setFlatFee(String flatFee) {
        this.flatFee = flatFee;
    }

    @Column(name = "`FlatFeeAccountingCode`", nullable = true, length = 255)
    public String getFlatFeeAccountingCode() {
        return this.flatFeeAccountingCode;
    }

    public void setFlatFeeAccountingCode(String flatFeeAccountingCode) {
        this.flatFeeAccountingCode = flatFeeAccountingCode;
    }

    @Column(name = "`SfFee`", nullable = true, length = 255)
    public String getSfFee() {
        return this.sfFee;
    }

    public void setSfFee(String sfFee) {
        this.sfFee = sfFee;
    }

    @Column(name = "`SfFeeAccountingCode`", nullable = true, length = 255)
    public String getSfFeeAccountingCode() {
        return this.sfFeeAccountingCode;
    }

    public void setSfFeeAccountingCode(String sfFeeAccountingCode) {
        this.sfFeeAccountingCode = sfFeeAccountingCode;
    }

    @Column(name = "`UnitFee`", nullable = true, length = 255)
    public String getUnitFee() {
        return this.unitFee;
    }

    public void setUnitFee(String unitFee) {
        this.unitFee = unitFee;
    }

    @Column(name = "`UnitFeeAccountingCode`", nullable = true, length = 255)
    public String getUnitFeeAccountingCode() {
        return this.unitFeeAccountingCode;
    }

    public void setUnitFeeAccountingCode(String unitFeeAccountingCode) {
        this.unitFeeAccountingCode = unitFeeAccountingCode;
    }

    @Column(name = "`StateFee`", nullable = true, length = 255)
    public String getStateFee() {
        return this.stateFee;
    }

    public void setStateFee(String stateFee) {
        this.stateFee = stateFee;
    }

    @Column(name = "`StateFeeAccountingCode`", nullable = true, length = 255)
    public String getStateFeeAccountingCode() {
        return this.stateFeeAccountingCode;
    }

    public void setStateFeeAccountingCode(String stateFeeAccountingCode) {
        this.stateFeeAccountingCode = stateFeeAccountingCode;
    }

    @Column(name = "`Report`", nullable = true, length = 255)
    public String getReport() {
        return this.report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Column(name = "`Active`", nullable = true)
    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "`FormCategoryId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormCategoryId() {
        return this.formCategoryId;
    }

    public void setFormCategoryId(Integer formCategoryId) {
        this.formCategoryId = formCategoryId;
    }

    @Column(name = "`PageName`", nullable = true, length = 255)
    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<SfnewResidentialStructure> getSfnewResidentialStructures() {
        return this.sfnewResidentialStructures;
    }

    public void setSfnewResidentialStructures(List<SfnewResidentialStructure> sfnewResidentialStructures) {
        this.sfnewResidentialStructures = sfnewResidentialStructures;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormCategoryId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormCategories getFormCategories() {
        return this.formCategories;
    }

    public void setFormCategories(FormCategories formCategories) {
        if(formCategories != null) {
            this.formCategoryId = formCategories.getId();
        }

        this.formCategories = formCategories;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "formTypes")
    public List<FormStatuses> getFormStatuseses() {
        return this.formStatuseses;
    }

    public void setFormStatuseses(List<FormStatuses> formStatuseses) {
        this.formStatuseses = formStatuseses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormTypes)) return false;
        final FormTypes formTypes = (FormTypes) o;
        return Objects.equals(getId(), formTypes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


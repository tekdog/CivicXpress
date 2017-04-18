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

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * Gisrecords generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`GISRecords`")
public class Gisrecords implements Serializable {

    private Integer id;
    private String landGuid;
    private Integer municipalityId;
    private Integer subdivisionId;
    private String parcel;
    private String lot;
    private String section;
    private String streetNumber;
    private String streetName;
    private String city;
    private Integer stateId;
    private String streetType;
    private String directionalPrefix;
    private String inspectionZone;
    private String latitude;
    private String longitude;
    private String country;
    private String unitNumber;
    private String postalCode;
    private String directionalSuffix;
    private String usedFor;
    @Type(type = "DateTime")
    private LocalDateTime dateModified;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.INSERT, Scope.UPDATE })
    private Integer modifiedBy;
    private String zoningClassification;
    private String floodZone;
    private String floodPlain;
    private Integer floodMapPanel;
    private boolean isHostile;
    private String fullAddress;
    private Users users;
    private Municipalities municipalities;
    private States states;
    private Subdivisions subdivisions;
    private List<Fees> feeses = new ArrayList<>();
    private List<Gis2forms> gis2formses = new ArrayList<>();
    private List<Giscontacts> giscontactses = new ArrayList<>();
    private List<GisTransaction> gisTransactions = new ArrayList<>();
    private List<InspectionGis> inspectionGises = new ArrayList<>();
    private List<MasterInspections> masterInspectionses = new ArrayList<>();
    private List<ProjectGisrecords> projectGisrecordses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`LandGuid`", nullable = true, length = 255)
    public String getLandGuid() {
        return this.landGuid;
    }

    public void setLandGuid(String landGuid) {
        this.landGuid = landGuid;
    }

    @Column(name = "`MunicipalityId`", nullable = true, scale = 0, precision = 10)
    public Integer getMunicipalityId() {
        return this.municipalityId;
    }

    public void setMunicipalityId(Integer municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Column(name = "`SubdivisionId`", nullable = true, scale = 0, precision = 10)
    public Integer getSubdivisionId() {
        return this.subdivisionId;
    }

    public void setSubdivisionId(Integer subdivisionId) {
        this.subdivisionId = subdivisionId;
    }

    @Column(name = "`Parcel`", nullable = true, length = 255)
    public String getParcel() {
        return this.parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    @Column(name = "`Lot`", nullable = true, length = 255)
    public String getLot() {
        return this.lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    @Column(name = "`Section`", nullable = true, length = 255)
    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Column(name = "`StreetNumber`", nullable = true, length = 255)
    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Column(name = "`StreetName`", nullable = true, length = 255)
    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
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

    @Column(name = "`StreetType`", nullable = true, length = 255)
    public String getStreetType() {
        return this.streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    @Column(name = "`DirectionalPrefix`", nullable = true, length = 255)
    public String getDirectionalPrefix() {
        return this.directionalPrefix;
    }

    public void setDirectionalPrefix(String directionalPrefix) {
        this.directionalPrefix = directionalPrefix;
    }

    @Column(name = "`InspectionZone`", nullable = true, length = 255)
    public String getInspectionZone() {
        return this.inspectionZone;
    }

    public void setInspectionZone(String inspectionZone) {
        this.inspectionZone = inspectionZone;
    }

    @Column(name = "`Latitude`", nullable = true, length = 255)
    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name = "`Longitude`", nullable = true, length = 255)
    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Column(name = "`Country`", nullable = true, length = 255)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "`UnitNumber`", nullable = true, length = 25)
    public String getUnitNumber() {
        return this.unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    @Column(name = "`PostalCode`", nullable = true, length = 255)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "`DirectionalSuffix`", nullable = true, length = 255)
    public String getDirectionalSuffix() {
        return this.directionalSuffix;
    }

    public void setDirectionalSuffix(String directionalSuffix) {
        this.directionalSuffix = directionalSuffix;
    }

    @Column(name = "`UsedFor`", nullable = true, length = 1000)
    public String getUsedFor() {
        return this.usedFor;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor;
    }

    @Column(name = "`DateModified`", nullable = true, insertable = false, updatable = false)
    public LocalDateTime getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @Column(name = "`ModifiedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(name = "`ZoningClassification`", nullable = true, length = 255)
    public String getZoningClassification() {
        return this.zoningClassification;
    }

    public void setZoningClassification(String zoningClassification) {
        this.zoningClassification = zoningClassification;
    }

    @Column(name = "`FloodZone`", nullable = true, length = 255)
    public String getFloodZone() {
        return this.floodZone;
    }

    public void setFloodZone(String floodZone) {
        this.floodZone = floodZone;
    }

    @Column(name = "`FloodPlain`", nullable = true, length = 255)
    public String getFloodPlain() {
        return this.floodPlain;
    }

    public void setFloodPlain(String floodPlain) {
        this.floodPlain = floodPlain;
    }

    @Column(name = "`FloodMapPanel`", nullable = true, scale = 0, precision = 10)
    public Integer getFloodMapPanel() {
        return this.floodMapPanel;
    }

    public void setFloodMapPanel(Integer floodMapPanel) {
        this.floodMapPanel = floodMapPanel;
    }

    @Column(name = "`IsHostile`", nullable = false)
    public boolean isIsHostile() {
        return this.isHostile;
    }

    public void setIsHostile(boolean isHostile) {
        this.isHostile = isHostile;
    }

    @Column(name = "`FullAddress`", nullable = false, insertable = false, updatable = false, length = 1563)
    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ModifiedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.modifiedBy = users.getId();
        }

        this.users = users;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`SubdivisionId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Subdivisions getSubdivisions() {
        return this.subdivisions;
    }

    public void setSubdivisions(Subdivisions subdivisions) {
        if(subdivisions != null) {
            this.subdivisionId = subdivisions.getId();
        }

        this.subdivisions = subdivisions;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "gisrecords")
    public List<Fees> getFeeses() {
        return this.feeses;
    }

    public void setFeeses(List<Fees> feeses) {
        this.feeses = feeses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "gisrecords")
    public List<Gis2forms> getGis2formses() {
        return this.gis2formses;
    }

    public void setGis2formses(List<Gis2forms> gis2formses) {
        this.gis2formses = gis2formses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "gisrecords")
    public List<Giscontacts> getGiscontactses() {
        return this.giscontactses;
    }

    public void setGiscontactses(List<Giscontacts> giscontactses) {
        this.giscontactses = giscontactses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "gisrecords")
    public List<GisTransaction> getGisTransactions() {
        return this.gisTransactions;
    }

    public void setGisTransactions(List<GisTransaction> gisTransactions) {
        this.gisTransactions = gisTransactions;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "gisrecords")
    public List<InspectionGis> getInspectionGises() {
        return this.inspectionGises;
    }

    public void setInspectionGises(List<InspectionGis> inspectionGises) {
        this.inspectionGises = inspectionGises;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "gisrecords")
    public List<MasterInspections> getMasterInspectionses() {
        return this.masterInspectionses;
    }

    public void setMasterInspectionses(List<MasterInspections> masterInspectionses) {
        this.masterInspectionses = masterInspectionses;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "gisrecords")
    public List<ProjectGisrecords> getProjectGisrecordses() {
        return this.projectGisrecordses;
    }

    public void setProjectGisrecordses(List<ProjectGisrecords> projectGisrecordses) {
        this.projectGisrecordses = projectGisrecordses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gisrecords)) return false;
        final Gisrecords gisrecords = (Gisrecords) o;
        return Objects.equals(getId(), gisrecords.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


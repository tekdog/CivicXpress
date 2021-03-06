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

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.wavemaker.runtime.data.annotations.ServerDefinedProperty;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * CodesToForm generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`CodesToForm`")
public class CodesToForm implements Serializable {

    private Integer id;
    private Integer codeSetId;
    private Integer formDesignId;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime dateAdded;
    private CodeSets codeSets;
    private FormTypes formTypes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`CodeSetId`", nullable = true, scale = 0, precision = 10)
    public Integer getCodeSetId() {
        return this.codeSetId;
    }

    public void setCodeSetId(Integer codeSetId) {
        this.codeSetId = codeSetId;
    }

    @Column(name = "`FormDesignId`", nullable = true, scale = 0, precision = 10)
    public Integer getFormDesignId() {
        return this.formDesignId;
    }

    public void setFormDesignId(Integer formDesignId) {
        this.formDesignId = formDesignId;
    }

    @Column(name = "`DateAdded`", nullable = true, updatable = false)
    public LocalDateTime getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CodeSetId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public CodeSets getCodeSets() {
        return this.codeSets;
    }

    public void setCodeSets(CodeSets codeSets) {
        if(codeSets != null) {
            this.codeSetId = codeSets.getId();
        }

        this.codeSets = codeSets;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`FormDesignId`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public FormTypes getFormTypes() {
        return this.formTypes;
    }

    public void setFormTypes(FormTypes formTypes) {
        if(formTypes != null) {
            this.formDesignId = formTypes.getId();
        }

        this.formTypes = formTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodesToForm)) return false;
        final CodesToForm codesToForm = (CodesToForm) o;
        return Objects.equals(getId(), codesToForm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


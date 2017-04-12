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
 * PaymentHistory generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`PaymentHistory`")
public class PaymentHistory implements Serializable {

    private Integer transactionId;
    private String paymentMethod;
    private String paymentNumber;
    private Float amountReceived;
    private String comments;
    @ServerDefinedProperty( value = VariableType.USER_ID, scopes = { Scope.INSERT })
    private Integer createdBy;
    @ServerDefinedProperty( value = VariableType.DATE_TIME, scopes = { Scope.INSERT })
    @Type(type = "DateTime")
    private LocalDateTime dateCreated;
    private Users users;
    private List<TransactionToFees> transactionToFeeses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`TransactionId`", nullable = false, scale = 0, precision = 10)
    public Integer getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "`PaymentMethod`", nullable = true, length = 255)
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Column(name = "`PaymentNumber`", nullable = true, length = 255)
    public String getPaymentNumber() {
        return this.paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    @Column(name = "`AmountReceived`", nullable = true, scale = 2, precision = 10)
    public Float getAmountReceived() {
        return this.amountReceived;
    }

    public void setAmountReceived(Float amountReceived) {
        this.amountReceived = amountReceived;
    }

    @Column(name = "`Comments`", nullable = true, length = 255)
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "`CreatedBy`", nullable = true, updatable = false, scale = 0, precision = 10)
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`DateCreated`", nullable = true, updatable = false)
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
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

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "paymentHistory")
    public List<TransactionToFees> getTransactionToFeeses() {
        return this.transactionToFeeses;
    }

    public void setTransactionToFeeses(List<TransactionToFees> transactionToFeeses) {
        this.transactionToFeeses = transactionToFeeses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentHistory)) return false;
        final PaymentHistory paymentHistory = (PaymentHistory) o;
        return Objects.equals(getTransactionId(), paymentHistory.getTransactionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionId());
    }
}

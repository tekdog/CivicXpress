/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserPasswordResetTokens generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`UserPasswordResetTokens`")
public class UserPasswordResetTokens implements Serializable {

    private Integer id;
    private int userId;
    private String token;
    private Date expirationDate;
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

    @Column(name = "`UserId`", nullable = false, scale = 0, precision = 10)
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "`Token`", nullable = false, length = 255)
    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "`ExpirationDate`", nullable = false)
    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
        if (!(o instanceof UserPasswordResetTokens)) return false;
        final UserPasswordResetTokens userPasswordResetTokens = (UserPasswordResetTokens) o;
        return Objects.equals(getId(), userPasswordResetTokens.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class UserCountResponse implements Serializable {

    @JsonProperty("usersCount")
    @ColumnAlias("usersCount")
    private Integer usersCount;

    public Integer getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCountResponse)) return false;
        final UserCountResponse userCountResponse = (UserCountResponse) o;
        return Objects.equals(getUsersCount(), userCountResponse.getUsersCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsersCount());
    }
}

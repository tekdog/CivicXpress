/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.FormFieldTypes;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetTotalPaymentByMethodForPeriodResponse implements Serializable {

    @ColumnAlias("PaymentMethod")
    private String paymentMethod;
    @ColumnAlias("paymentAmount")
    private BigDecimal paymentAmount;

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetTotalPaymentByMethodForPeriodResponse)) return false;
        final GetTotalPaymentByMethodForPeriodResponse getTotalPaymentByMethodForPeriodResponse = (GetTotalPaymentByMethodForPeriodResponse) o;
        return Objects.equals(getPaymentMethod(), getTotalPaymentByMethodForPeriodResponse.getPaymentMethod()) &&
                Objects.equals(getPaymentAmount(), getTotalPaymentByMethodForPeriodResponse.getPaymentAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaymentMethod(),
                getPaymentAmount());
    }
}

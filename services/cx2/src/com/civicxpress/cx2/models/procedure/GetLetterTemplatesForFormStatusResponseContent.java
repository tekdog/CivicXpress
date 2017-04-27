/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.models.procedure;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetLetterTemplatesForFormStatusResponseContent implements Serializable {

    @ColumnAlias("Id")
    private Integer id;
    @ColumnAlias("LetterTemplateId")
    private Integer letterTemplateId;
    @ColumnAlias("LetterTitle")
    private String letterTitle;
    @ColumnAlias("AttachToEmail")
    private Boolean attachToEmail;
    @ColumnAlias("EmailLetterCreator")
    private Boolean emailLetterCreator;
    @ColumnAlias("AttachToItem")
    private Boolean attachToItem;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLetterTemplateId() {
        return this.letterTemplateId;
    }

    public void setLetterTemplateId(Integer letterTemplateId) {
        this.letterTemplateId = letterTemplateId;
    }

    public String getLetterTitle() {
        return this.letterTitle;
    }

    public void setLetterTitle(String letterTitle) {
        this.letterTitle = letterTitle;
    }

    public Boolean getAttachToEmail() {
        return this.attachToEmail;
    }

    public void setAttachToEmail(Boolean attachToEmail) {
        this.attachToEmail = attachToEmail;
    }

    public Boolean getEmailLetterCreator() {
        return this.emailLetterCreator;
    }

    public void setEmailLetterCreator(Boolean emailLetterCreator) {
        this.emailLetterCreator = emailLetterCreator;
    }

    public Boolean getAttachToItem() {
        return this.attachToItem;
    }

    public void setAttachToItem(Boolean attachToItem) {
        this.attachToItem = attachToItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetLetterTemplatesForFormStatusResponseContent)) return false;
        final GetLetterTemplatesForFormStatusResponseContent getLetterTemplatesForFormStatusResponseContent = (GetLetterTemplatesForFormStatusResponseContent) o;
        return Objects.equals(getId(), getLetterTemplatesForFormStatusResponseContent.getId()) &&
                Objects.equals(getLetterTemplateId(), getLetterTemplatesForFormStatusResponseContent.getLetterTemplateId()) &&
                Objects.equals(getLetterTitle(), getLetterTemplatesForFormStatusResponseContent.getLetterTitle()) &&
                Objects.equals(getAttachToEmail(), getLetterTemplatesForFormStatusResponseContent.getAttachToEmail()) &&
                Objects.equals(getEmailLetterCreator(), getLetterTemplatesForFormStatusResponseContent.getEmailLetterCreator()) &&
                Objects.equals(getAttachToItem(), getLetterTemplatesForFormStatusResponseContent.getAttachToItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getLetterTemplateId(),
                getLetterTitle(),
                getAttachToEmail(),
                getEmailLetterCreator(),
                getAttachToItem());
    }
}

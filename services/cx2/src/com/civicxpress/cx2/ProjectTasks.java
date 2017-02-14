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

/**
 * ProjectTasks generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ProjectTasks`")
public class ProjectTasks implements Serializable {

    private Integer id;
    private String taskName;
    private String taskDescription;
    private Integer addedBy;
    @Type(type = "DateTime")
    private LocalDateTime addedAt;
    private String relatedProjectGuid;
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

    @Column(name = "`TaskName`", nullable = true, length = 255)
    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Column(name = "`TaskDescription`", nullable = true, length = 1000)
    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Column(name = "`AddedBy`", nullable = true, scale = 0, precision = 10)
    public Integer getAddedBy() {
        return this.addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    @Column(name = "`AddedAt`", nullable = true)
    public LocalDateTime getAddedAt() {
        return this.addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Column(name = "`RelatedProjectGUID`", nullable = true, length = 32)
    public String getRelatedProjectGuid() {
        return this.relatedProjectGuid;
    }

    public void setRelatedProjectGuid(String relatedProjectGuid) {
        this.relatedProjectGuid = relatedProjectGuid;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`AddedBy`", referencedColumnName = "`ID`", insertable = false, updatable = false)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.addedBy = users.getId();
        }

        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTasks)) return false;
        final ProjectTasks projectTasks = (ProjectTasks) o;
        return Objects.equals(getId(), projectTasks.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


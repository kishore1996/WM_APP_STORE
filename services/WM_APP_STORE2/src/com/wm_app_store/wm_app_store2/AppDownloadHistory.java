/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store2;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppDownloadHistory generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`APP_DOWNLOAD_HISTORY`")
public class AppDownloadHistory implements Serializable {

    private Integer id;
    private Integer appSourceId;
    private String createdBy;
    private Timestamp creationDate;
    private Timestamp lastUpdateDate;
    private String updatedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`APP_SOURCE_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getAppSourceId() {
        return this.appSourceId;
    }

    public void setAppSourceId(Integer appSourceId) {
        this.appSourceId = appSourceId;
    }

    @Column(name = "`CREATED_BY`", nullable = true, length = 255)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`CREATION_DATE`", nullable = false)
    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "`LAST_UPDATE_DATE`", nullable = false)
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "`UPDATED_BY`", nullable = true, length = 255)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppDownloadHistory)) return false;
        final AppDownloadHistory appDownloadHistory = (AppDownloadHistory) o;
        return Objects.equals(getId(), appDownloadHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

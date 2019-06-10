/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * AppSource generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`APP_SOURCE`")
public class AppSource implements Serializable {

    private Integer id;
    private String _desc;
    private Integer appInfoId;
    private String createdBy;
    private Timestamp creationDate;
    private String fileName;
    private Timestamp lastUpdateDate;
    private Integer price;
    private String updatedBy;
    private Integer versionId;
    private AppInfo appInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`DESC`", nullable = true, length = 255)
    public String get_desc() {
        return this._desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    @Column(name = "`APP_INFO_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getAppInfoId() {
        return this.appInfoId;
    }

    public void setAppInfoId(Integer appInfoId) {
        this.appInfoId = appInfoId;
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

    @Column(name = "`FILE_NAME`", nullable = true, length = 255)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "`LAST_UPDATE_DATE`", nullable = false)
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "`PRICE`", nullable = true, scale = 0, precision = 10)
    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name = "`UPDATED_BY`", nullable = true, length = 255)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "`VERSION_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`APP_INFO_ID`", referencedColumnName = "`ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_APP_INFO_TO_APP_SOURCmF1ck`"))
    @Fetch(FetchMode.JOIN)
    public AppInfo getAppInfo() {
        return this.appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        if(appInfo != null) {
            this.appInfoId = appInfo.getId();
        }

        this.appInfo = appInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppSource)) return false;
        final AppSource appSource = (AppSource) o;
        return Objects.equals(getId(), appSource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
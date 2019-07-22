/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
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
 * AppScreenshots generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`APP_SCREENSHOTS`")
public class AppScreenshots implements Serializable {

    private Integer id;
    private Integer appInfoId;
    private String screenshots;
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

    @Column(name = "`APP_INFO_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getAppInfoId() {
        return this.appInfoId;
    }

    public void setAppInfoId(Integer appInfoId) {
        this.appInfoId = appInfoId;
    }

    @Column(name = "`SCREENSHOTS`", nullable = true, length = 1000)
    public String getScreenshots() {
        return this.screenshots;
    }

    public void setScreenshots(String screenshots) {
        this.screenshots = screenshots;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`APP_INFO_ID`", referencedColumnName = "`ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_APP_SCREENSHOTS_TO_APhHjED`"))
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
        if (!(o instanceof AppScreenshots)) return false;
        final AppScreenshots appScreenshots = (AppScreenshots) o;
        return Objects.equals(getId(), appScreenshots.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
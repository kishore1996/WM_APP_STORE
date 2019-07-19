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

import com.wavemaker.runtime.data.annotations.WMValueInject;
import com.wavemaker.runtime.data.replacers.Scope;
import com.wavemaker.runtime.data.replacers.providers.VariableType;

/**
 * AppInfo generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`APP_INFO`")
public class AppInfo implements Serializable {

    private Integer id;
    private String _desc;
    private Integer categoryId;
    private String createdBy;
    private Timestamp creationDate;
    private Timestamp lastUpdateDate;
    private String name;
    @WMValueInject( type = VariableType.SERVER, name = "USER_NAME", scopes = { Scope.INSERT, Scope.UPDATE })
    private String updatedBy;
    private String image;
    private MdCategory mdCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`DESC`", nullable = true, length = 2000)
    public String get_desc() {
        return this._desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    @Column(name = "`CATEGORY_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    @Column(name = "`NAME`", nullable = true, length = 255)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "`UPDATED_BY`", nullable = true, length = 255)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "`IMAGE`", nullable = true, length = 1000)
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CATEGORY_ID`", referencedColumnName = "`ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_APP_INFO_TO_MD_CATEGORNNgP`"))
    @Fetch(FetchMode.JOIN)
    public MdCategory getMdCategory() {
        return this.mdCategory;
    }

    public void setMdCategory(MdCategory mdCategory) {
        if(mdCategory != null) {
            this.categoryId = mdCategory.getId();
        }

        this.mdCategory = mdCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppInfo)) return false;
        final AppInfo appInfo = (AppInfo) o;
        return Objects.equals(getId(), appInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
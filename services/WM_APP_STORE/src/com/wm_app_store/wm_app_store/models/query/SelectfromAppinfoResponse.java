/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SelectfromAppinfoResponse implements Serializable {


    @ColumnAlias("ID")
    private Integer id;

    @ColumnAlias("DESC")
    private String _desc;

    @ColumnAlias("CATEGORY_ID")
    private Integer categoryId;

    @ColumnAlias("CREATED_BY")
    private String createdBy;

    @ColumnAlias("CREATION_DATE")
    private Timestamp creationDate;

    @ColumnAlias("LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @ColumnAlias("NAME")
    private String name;

    @ColumnAlias("UPDATED_BY")
    private String updatedBy;

    @ColumnAlias("IMAGE")
    private String image;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get_desc() {
        return this._desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectfromAppinfoResponse)) return false;
        final SelectfromAppinfoResponse selectfromAppinfoResponse = (SelectfromAppinfoResponse) o;
        return Objects.equals(getId(), selectfromAppinfoResponse.getId()) &&
                Objects.equals(get_desc(), selectfromAppinfoResponse.get_desc()) &&
                Objects.equals(getCategoryId(), selectfromAppinfoResponse.getCategoryId()) &&
                Objects.equals(getCreatedBy(), selectfromAppinfoResponse.getCreatedBy()) &&
                Objects.equals(getCreationDate(), selectfromAppinfoResponse.getCreationDate()) &&
                Objects.equals(getLastUpdateDate(), selectfromAppinfoResponse.getLastUpdateDate()) &&
                Objects.equals(getName(), selectfromAppinfoResponse.getName()) &&
                Objects.equals(getUpdatedBy(), selectfromAppinfoResponse.getUpdatedBy()) &&
                Objects.equals(getImage(), selectfromAppinfoResponse.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                get_desc(),
                getCategoryId(),
                getCreatedBy(),
                getCreationDate(),
                getLastUpdateDate(),
                getName(),
                getUpdatedBy(),
                getImage());
    }
}
/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SelectfromMdCategoryResponse implements Serializable {


    @ColumnAlias("ID")
    private Integer id;

    @ColumnAlias("CREATED_BY")
    private String createdBy;

    @ColumnAlias("CREATION_DATE")
    private Timestamp creationDate;

    @ColumnAlias("LABEL")
    private String label;

    @ColumnAlias("LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @ColumnAlias("UPDATED_BY")
    private String updatedBy;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectfromMdCategoryResponse)) return false;
        final SelectfromMdCategoryResponse selectfromMdCategoryResponse = (SelectfromMdCategoryResponse) o;
        return Objects.equals(getId(), selectfromMdCategoryResponse.getId()) &&
                Objects.equals(getCreatedBy(), selectfromMdCategoryResponse.getCreatedBy()) &&
                Objects.equals(getCreationDate(), selectfromMdCategoryResponse.getCreationDate()) &&
                Objects.equals(getLabel(), selectfromMdCategoryResponse.getLabel()) &&
                Objects.equals(getLastUpdateDate(), selectfromMdCategoryResponse.getLastUpdateDate()) &&
                Objects.equals(getUpdatedBy(), selectfromMdCategoryResponse.getUpdatedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getCreatedBy(),
                getCreationDate(),
                getLabel(),
                getLastUpdateDate(),
                getUpdatedBy());
    }
}

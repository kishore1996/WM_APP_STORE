/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class ViewProfileResponse implements Serializable {


    @ColumnAlias("ID")
    private Integer id;

    @ColumnAlias("USERNAME")
    private String username;

    @ColumnAlias("PASSWORD")
    private String password;

    @ColumnAlias("USER_ROLE")
    private String userRole;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return this.userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewProfileResponse)) return false;
        final ViewProfileResponse viewProfileResponse = (ViewProfileResponse) o;
        return Objects.equals(getId(), viewProfileResponse.getId()) &&
                Objects.equals(getUsername(), viewProfileResponse.getUsername()) &&
                Objects.equals(getPassword(), viewProfileResponse.getPassword()) &&
                Objects.equals(getUserRole(), viewProfileResponse.getUserRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getUsername(),
                getPassword(),
                getUserRole());
    }
}

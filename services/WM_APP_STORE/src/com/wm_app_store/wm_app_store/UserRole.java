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
 * UserRole generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`USER_ROLE`")
public class UserRole implements Serializable {

    private Integer id;
    private String createdBy;
    private String creationDate;
    private Timestamp lastUpdateDate;
    private Integer roleId;
    private String updatedBy;
    private Integer userId;
    private MdRole mdRole;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`CREATED_BY`", nullable = true, length = 255)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "`CREATION_DATE`", nullable = true, length = 255)
    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "`LAST_UPDATE_DATE`", nullable = false)
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "`ROLE_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "`UPDATED_BY`", nullable = true, length = 255)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "`USER_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ROLE_ID`", referencedColumnName = "`ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_MD_ROLE_TO_USER_ROLE_a3lI7`"))
    @Fetch(FetchMode.JOIN)
    public MdRole getMdRole() {
        return this.mdRole;
    }

    public void setMdRole(MdRole mdRole) {
        if(mdRole != null) {
            this.roleId = mdRole.getId();
        }

        this.mdRole = mdRole;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`USER_ID`", referencedColumnName = "`ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_USER_TO_USER_ROLE_ID_RK7R6`"))
    @Fetch(FetchMode.JOIN)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        if(user != null) {
            this.userId = user.getId();
        }

        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        final UserRole userRole = (UserRole) o;
        return Objects.equals(getId(), userRole.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
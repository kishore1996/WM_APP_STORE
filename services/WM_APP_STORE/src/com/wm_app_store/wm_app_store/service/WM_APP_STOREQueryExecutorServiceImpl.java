/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.data.model.QueryProcedureInput;

import com.wm_app_store.wm_app_store.models.query.*;

@Service
public class WM_APP_STOREQueryExecutorServiceImpl implements WM_APP_STOREQueryExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WM_APP_STOREQueryExecutorServiceImpl.class);

    @Autowired
    @Qualifier("WM_APP_STOREWMQueryExecutor")
    private WMQueryExecutor queryExecutor;

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public Integer executeDeleteUser(String uname, Integer id, String loggedinuser) {
        Map<String, Object> params = new HashMap<>(3);

        params.put("uname", uname);
        params.put("id", id);
        params.put("loggedinuser", loggedinuser);

        return queryExecutor.executeNamedQueryForUpdate("deleteUser", params);
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public Integer executeEditUser(EditUserRequest editUserRequest) {
        Map<String, Object> params = new HashMap<>(4);

        params.put("newusername", editUserRequest.getNewusername());
        params.put("password", editUserRequest.getPassword());
        params.put("urole", editUserRequest.getUrole());
        params.put("currentusername", editUserRequest.getCurrentusername());

        return queryExecutor.executeNamedQueryForUpdate("editUser", params);
    }

    @Transactional(value = "WM_APP_STORETransactionManager", readOnly = true)
    @Override
    public Page<ViewProfileResponse> executeViewProfile(String uname, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("uname", uname);

        return queryExecutor.executeNamedQuery("viewProfile", params, ViewProfileResponse.class, pageable);
    }

    @Transactional(value = "WM_APP_STORETransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportViewProfile(String uname, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("uname", uname);

        QueryProcedureInput<ViewProfileResponse> queryInput = new QueryProcedureInput<>("viewProfile", params, ViewProfileResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public Integer executeChangePassword(ChangePasswordRequest changePasswordRequest) {
        Map<String, Object> params = new HashMap<>(3);

        params.put("newpassword", changePasswordRequest.getNewpassword());
        params.put("uname", changePasswordRequest.getUname());
        params.put("oldpassword", changePasswordRequest.getOldpassword());

        return queryExecutor.executeNamedQueryForUpdate("changePassword", params);
    }

}

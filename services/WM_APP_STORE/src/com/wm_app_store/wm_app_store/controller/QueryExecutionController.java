/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.IntegerWrapper;
import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.wm_app_store.wm_app_store.service.WM_APP_STOREQueryExecutorService;
import com.wm_app_store.wm_app_store.models.query.*;

@RestController(value = "WM_APP_STORE.QueryExecutionController")
@RequestMapping("/WM_APP_STORE/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private WM_APP_STOREQueryExecutorService queryService;

    @Autowired
	private ExportedFileManager exportedFileManager;

    @RequestMapping(value = "/queries/deleteUser", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "delete users")
    public IntegerWrapper executeDeleteUser(@RequestParam(value = "uname") String uname, @RequestParam(value = "id") Integer id, @RequestParam(value = "loggedinuser") String loggedinuser, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: deleteUser");
        Integer _result = queryService.executeDeleteUser(uname, id, loggedinuser);
        LOGGER.debug("got the result for named query: deleteUser, result:{}", _result);
        return new IntegerWrapper(_result);
    }

    @RequestMapping(value = "/queries/editUser", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Edit user credentials")
    public IntegerWrapper executeEditUser(@Valid @RequestBody EditUserRequest editUserRequest, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: editUser");
        Integer _result = queryService.executeEditUser(editUserRequest);
        LOGGER.debug("got the result for named query: editUser, result:{}", _result);
        return new IntegerWrapper(_result);
    }

    @RequestMapping(value = "/queries/viewProfile", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "view logged user profile")
    public Page<ViewProfileResponse> executeViewProfile(@RequestParam(value = "uname") String uname, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: viewProfile");
        Page<ViewProfileResponse> _result = queryService.executeViewProfile(uname, pageable);
        LOGGER.debug("got the result for named query: viewProfile, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query viewProfile")
    @RequestMapping(value = "/queries/viewProfile/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportViewProfile(@RequestParam(value = "uname") String uname, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: viewProfile");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "viewProfile";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportViewProfile(uname,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @RequestMapping(value = "/queries/changePassword", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "change user password")
    public IntegerWrapper executeChangePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: changePassword");
        Integer _result = queryService.executeChangePassword(changePasswordRequest);
        LOGGER.debug("got the result for named query: changePassword, result:{}", _result);
        return new IntegerWrapper(_result);
    }

}

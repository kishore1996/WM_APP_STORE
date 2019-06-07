/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wm_app_store.wm_app_store.UserRole;


/**
 * ServiceImpl object for domain model class UserRole.
 *
 * @see UserRole
 */
@Service("WM_APP_STORE.UserRoleService")
@Validated
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);


    @Autowired
    @Qualifier("WM_APP_STORE.UserRoleDao")
    private WMGenericDao<UserRole, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<UserRole, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public UserRole create(UserRole userRole) {
        LOGGER.debug("Creating a new UserRole with information: {}", userRole);

        UserRole userRoleCreated = this.wmGenericDao.create(userRole);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(userRoleCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public UserRole getById(Integer userroleId) {
        LOGGER.debug("Finding UserRole by id: {}", userroleId);
        return this.wmGenericDao.findById(userroleId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public UserRole findById(Integer userroleId) {
        LOGGER.debug("Finding UserRole by id: {}", userroleId);
        try {
            return this.wmGenericDao.findById(userroleId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No UserRole found with id: {}", userroleId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public List<UserRole> findByMultipleIds(List<Integer> userroleIds, boolean orderedReturn) {
        LOGGER.debug("Finding UserRoles by ids: {}", userroleIds);

        return this.wmGenericDao.findByMultipleIds(userroleIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORETransactionManager")
    @Override
    public UserRole update(UserRole userRole) {
        LOGGER.debug("Updating UserRole with information: {}", userRole);

        this.wmGenericDao.update(userRole);
        this.wmGenericDao.refresh(userRole);

        return userRole;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public UserRole partialUpdate(Integer userroleId, Map<String, Object>userRolePatch) {
        LOGGER.debug("Partially Updating the UserRole with id: {}", userroleId);

        UserRole userRole = getById(userroleId);

        try {
            ObjectReader userRoleReader = this.objectMapper.reader().forType(UserRole.class).withValueToUpdate(userRole);
            userRole = userRoleReader.readValue(this.objectMapper.writeValueAsString(userRolePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", userRolePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        userRole = update(userRole);

        return userRole;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public UserRole delete(Integer userroleId) {
        LOGGER.debug("Deleting UserRole with id: {}", userroleId);
        UserRole deleted = this.wmGenericDao.findById(userroleId);
        if (deleted == null) {
            LOGGER.debug("No UserRole found with id: {}", userroleId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), UserRole.class.getSimpleName(), userroleId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public void delete(UserRole userRole) {
        LOGGER.debug("Deleting UserRole with {}", userRole);
        this.wmGenericDao.delete(userRole);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<UserRole> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all UserRoles");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<UserRole> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all UserRoles");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table UserRole to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table UserRole to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}
/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
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

import com.wm_app_store.wm_app_store2.MdRole;
import com.wm_app_store.wm_app_store2.UserRole;


/**
 * ServiceImpl object for domain model class MdRole.
 *
 * @see MdRole
 */
@Service("WM_APP_STORE2.MdRoleService")
@Validated
public class MdRoleServiceImpl implements MdRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MdRoleServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE2.UserRoleService")
    private UserRoleService userRoleService;

    @Autowired
    @Qualifier("WM_APP_STORE2.MdRoleDao")
    private WMGenericDao<MdRole, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<MdRole, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public MdRole create(MdRole mdRole) {
        LOGGER.debug("Creating a new MdRole with information: {}", mdRole);

        MdRole mdRoleCreated = this.wmGenericDao.create(mdRole);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(mdRoleCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public MdRole getById(Integer mdroleId) {
        LOGGER.debug("Finding MdRole by id: {}", mdroleId);
        return this.wmGenericDao.findById(mdroleId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public MdRole findById(Integer mdroleId) {
        LOGGER.debug("Finding MdRole by id: {}", mdroleId);
        try {
            return this.wmGenericDao.findById(mdroleId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No MdRole found with id: {}", mdroleId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public List<MdRole> findByMultipleIds(List<Integer> mdroleIds, boolean orderedReturn) {
        LOGGER.debug("Finding MdRoles by ids: {}", mdroleIds);

        return this.wmGenericDao.findByMultipleIds(mdroleIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORE2TransactionManager")
    @Override
    public MdRole update(MdRole mdRole) {
        LOGGER.debug("Updating MdRole with information: {}", mdRole);

        this.wmGenericDao.update(mdRole);
        this.wmGenericDao.refresh(mdRole);

        return mdRole;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public MdRole partialUpdate(Integer mdroleId, Map<String, Object>mdRolePatch) {
        LOGGER.debug("Partially Updating the MdRole with id: {}", mdroleId);

        MdRole mdRole = getById(mdroleId);

        try {
            ObjectReader mdRoleReader = this.objectMapper.reader().forType(MdRole.class).withValueToUpdate(mdRole);
            mdRole = mdRoleReader.readValue(this.objectMapper.writeValueAsString(mdRolePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", mdRolePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        mdRole = update(mdRole);

        return mdRole;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public MdRole delete(Integer mdroleId) {
        LOGGER.debug("Deleting MdRole with id: {}", mdroleId);
        MdRole deleted = this.wmGenericDao.findById(mdroleId);
        if (deleted == null) {
            LOGGER.debug("No MdRole found with id: {}", mdroleId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), MdRole.class.getSimpleName(), mdroleId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public void delete(MdRole mdRole) {
        LOGGER.debug("Deleting MdRole with {}", mdRole);
        this.wmGenericDao.delete(mdRole);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public Page<MdRole> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all MdRoles");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public Page<MdRole> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all MdRoles");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE2 for table MdRole to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE2 for table MdRole to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public Page<UserRole> findAssociatedUserRoles(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userRoles");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("mdRole.id = '" + id + "'");

        return userRoleService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service UserRoleService instance
     */
    protected void setUserRoleService(UserRoleService service) {
        this.userRoleService = service;
    }

}
/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store1.service;

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

import com.wm_app_store.wm_app_store1.AppD0wnloadHostory;
import com.wm_app_store.wm_app_store1.AppSource;


/**
 * ServiceImpl object for domain model class AppSource.
 *
 * @see AppSource
 */
@Service("WM_APP_STORE1.AppSourceService")
@Validated
public class AppSourceServiceImpl implements AppSourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSourceServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE1.AppD0wnloadHostoryService")
    private AppD0wnloadHostoryService appD0wnloadHostoryService;

    @Autowired
    @Qualifier("WM_APP_STORE1.AppSourceDao")
    private WMGenericDao<AppSource, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AppSource, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppSource create(AppSource appSource) {
        LOGGER.debug("Creating a new AppSource with information: {}", appSource);

        AppSource appSourceCreated = this.wmGenericDao.create(appSource);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(appSourceCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppSource getById(Integer appsourceId) {
        LOGGER.debug("Finding AppSource by id: {}", appsourceId);
        return this.wmGenericDao.findById(appsourceId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppSource findById(Integer appsourceId) {
        LOGGER.debug("Finding AppSource by id: {}", appsourceId);
        try {
            return this.wmGenericDao.findById(appsourceId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AppSource found with id: {}", appsourceId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public List<AppSource> findByMultipleIds(List<Integer> appsourceIds, boolean orderedReturn) {
        LOGGER.debug("Finding AppSources by ids: {}", appsourceIds);

        return this.wmGenericDao.findByMultipleIds(appsourceIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppSource update(AppSource appSource) {
        LOGGER.debug("Updating AppSource with information: {}", appSource);

        this.wmGenericDao.update(appSource);
        this.wmGenericDao.refresh(appSource);

        return appSource;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppSource partialUpdate(Integer appsourceId, Map<String, Object>appSourcePatch) {
        LOGGER.debug("Partially Updating the AppSource with id: {}", appsourceId);

        AppSource appSource = getById(appsourceId);

        try {
            ObjectReader appSourceReader = this.objectMapper.reader().forType(AppSource.class).withValueToUpdate(appSource);
            appSource = appSourceReader.readValue(this.objectMapper.writeValueAsString(appSourcePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", appSourcePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        appSource = update(appSource);

        return appSource;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppSource delete(Integer appsourceId) {
        LOGGER.debug("Deleting AppSource with id: {}", appsourceId);
        AppSource deleted = this.wmGenericDao.findById(appsourceId);
        if (deleted == null) {
            LOGGER.debug("No AppSource found with id: {}", appsourceId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AppSource.class.getSimpleName(), appsourceId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public void delete(AppSource appSource) {
        LOGGER.debug("Deleting AppSource with {}", appSource);
        this.wmGenericDao.delete(appSource);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<AppSource> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AppSources");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<AppSource> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AppSources");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table AppSource to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table AppSource to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<AppD0wnloadHostory> findAssociatedAppD0wnloadHostories(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated appD0wnloadHostories");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("appSource.id = '" + id + "'");

        return appD0wnloadHostoryService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service AppD0wnloadHostoryService instance
     */
    protected void setAppD0wnloadHostoryService(AppD0wnloadHostoryService service) {
        this.appD0wnloadHostoryService = service;
    }

}

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

import com.wm_app_store.wm_app_store.AppD0wnloadHostory;


/**
 * ServiceImpl object for domain model class AppD0wnloadHostory.
 *
 * @see AppD0wnloadHostory
 */
@Service("WM_APP_STORE.AppD0wnloadHostoryService")
@Validated
public class AppD0wnloadHostoryServiceImpl implements AppD0wnloadHostoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppD0wnloadHostoryServiceImpl.class);


    @Autowired
    @Qualifier("WM_APP_STORE.AppD0wnloadHostoryDao")
    private WMGenericDao<AppD0wnloadHostory, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AppD0wnloadHostory, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppD0wnloadHostory create(AppD0wnloadHostory appD0wnloadHostory) {
        LOGGER.debug("Creating a new AppD0wnloadHostory with information: {}", appD0wnloadHostory);

        AppD0wnloadHostory appD0wnloadHostoryCreated = this.wmGenericDao.create(appD0wnloadHostory);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(appD0wnloadHostoryCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppD0wnloadHostory getById(Integer appd0wnloadhostoryId) {
        LOGGER.debug("Finding AppD0wnloadHostory by id: {}", appd0wnloadhostoryId);
        return this.wmGenericDao.findById(appd0wnloadhostoryId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppD0wnloadHostory findById(Integer appd0wnloadhostoryId) {
        LOGGER.debug("Finding AppD0wnloadHostory by id: {}", appd0wnloadhostoryId);
        try {
            return this.wmGenericDao.findById(appd0wnloadhostoryId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AppD0wnloadHostory found with id: {}", appd0wnloadhostoryId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public List<AppD0wnloadHostory> findByMultipleIds(List<Integer> appd0wnloadhostoryIds, boolean orderedReturn) {
        LOGGER.debug("Finding AppD0wnloadHostories by ids: {}", appd0wnloadhostoryIds);

        return this.wmGenericDao.findByMultipleIds(appd0wnloadhostoryIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORETransactionManager")
    @Override
    public AppD0wnloadHostory update(AppD0wnloadHostory appD0wnloadHostory) {
        LOGGER.debug("Updating AppD0wnloadHostory with information: {}", appD0wnloadHostory);

        this.wmGenericDao.update(appD0wnloadHostory);
        this.wmGenericDao.refresh(appD0wnloadHostory);

        return appD0wnloadHostory;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppD0wnloadHostory partialUpdate(Integer appd0wnloadhostoryId, Map<String, Object>appD0wnloadHostoryPatch) {
        LOGGER.debug("Partially Updating the AppD0wnloadHostory with id: {}", appd0wnloadhostoryId);

        AppD0wnloadHostory appD0wnloadHostory = getById(appd0wnloadhostoryId);

        try {
            ObjectReader appD0wnloadHostoryReader = this.objectMapper.reader().forType(AppD0wnloadHostory.class).withValueToUpdate(appD0wnloadHostory);
            appD0wnloadHostory = appD0wnloadHostoryReader.readValue(this.objectMapper.writeValueAsString(appD0wnloadHostoryPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", appD0wnloadHostoryPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        appD0wnloadHostory = update(appD0wnloadHostory);

        return appD0wnloadHostory;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppD0wnloadHostory delete(Integer appd0wnloadhostoryId) {
        LOGGER.debug("Deleting AppD0wnloadHostory with id: {}", appd0wnloadhostoryId);
        AppD0wnloadHostory deleted = this.wmGenericDao.findById(appd0wnloadhostoryId);
        if (deleted == null) {
            LOGGER.debug("No AppD0wnloadHostory found with id: {}", appd0wnloadhostoryId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AppD0wnloadHostory.class.getSimpleName(), appd0wnloadhostoryId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public void delete(AppD0wnloadHostory appD0wnloadHostory) {
        LOGGER.debug("Deleting AppD0wnloadHostory with {}", appD0wnloadHostory);
        this.wmGenericDao.delete(appD0wnloadHostory);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppD0wnloadHostory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AppD0wnloadHostories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppD0wnloadHostory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AppD0wnloadHostories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppD0wnloadHostory to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppD0wnloadHostory to {} format", options.getExportType());
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
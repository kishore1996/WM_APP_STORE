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

import com.wm_app_store.wm_app_store.AppScreenshots;


/**
 * ServiceImpl object for domain model class AppScreenshots.
 *
 * @see AppScreenshots
 */
@Service("WM_APP_STORE.AppScreenshotsService")
@Validated
public class AppScreenshotsServiceImpl implements AppScreenshotsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppScreenshotsServiceImpl.class);


    @Autowired
    @Qualifier("WM_APP_STORE.AppScreenshotsDao")
    private WMGenericDao<AppScreenshots, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AppScreenshots, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppScreenshots create(AppScreenshots appScreenshots) {
        LOGGER.debug("Creating a new AppScreenshots with information: {}", appScreenshots);

        AppScreenshots appScreenshotsCreated = this.wmGenericDao.create(appScreenshots);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(appScreenshotsCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppScreenshots getById(Integer appscreenshotsId) {
        LOGGER.debug("Finding AppScreenshots by id: {}", appscreenshotsId);
        return this.wmGenericDao.findById(appscreenshotsId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppScreenshots findById(Integer appscreenshotsId) {
        LOGGER.debug("Finding AppScreenshots by id: {}", appscreenshotsId);
        try {
            return this.wmGenericDao.findById(appscreenshotsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AppScreenshots found with id: {}", appscreenshotsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public List<AppScreenshots> findByMultipleIds(List<Integer> appscreenshotsIds, boolean orderedReturn) {
        LOGGER.debug("Finding AppScreenshots by ids: {}", appscreenshotsIds);

        return this.wmGenericDao.findByMultipleIds(appscreenshotsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORETransactionManager")
    @Override
    public AppScreenshots update(AppScreenshots appScreenshots) {
        LOGGER.debug("Updating AppScreenshots with information: {}", appScreenshots);

        this.wmGenericDao.update(appScreenshots);
        this.wmGenericDao.refresh(appScreenshots);

        return appScreenshots;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppScreenshots partialUpdate(Integer appscreenshotsId, Map<String, Object>appScreenshotsPatch) {
        LOGGER.debug("Partially Updating the AppScreenshots with id: {}", appscreenshotsId);

        AppScreenshots appScreenshots = getById(appscreenshotsId);

        try {
            ObjectReader appScreenshotsReader = this.objectMapper.reader().forType(AppScreenshots.class).withValueToUpdate(appScreenshots);
            appScreenshots = appScreenshotsReader.readValue(this.objectMapper.writeValueAsString(appScreenshotsPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", appScreenshotsPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        appScreenshots = update(appScreenshots);

        return appScreenshots;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppScreenshots delete(Integer appscreenshotsId) {
        LOGGER.debug("Deleting AppScreenshots with id: {}", appscreenshotsId);
        AppScreenshots deleted = this.wmGenericDao.findById(appscreenshotsId);
        if (deleted == null) {
            LOGGER.debug("No AppScreenshots found with id: {}", appscreenshotsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AppScreenshots.class.getSimpleName(), appscreenshotsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public void delete(AppScreenshots appScreenshots) {
        LOGGER.debug("Deleting AppScreenshots with {}", appScreenshots);
        this.wmGenericDao.delete(appScreenshots);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppScreenshots> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AppScreenshots");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppScreenshots> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AppScreenshots");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppScreenshots to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppScreenshots to {} format", options.getExportType());
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
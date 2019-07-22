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

import com.wm_app_store.wm_app_store1.AppScreen;


/**
 * ServiceImpl object for domain model class AppScreen.
 *
 * @see AppScreen
 */
@Service("WM_APP_STORE1.AppScreenService")
@Validated
public class AppScreenServiceImpl implements AppScreenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppScreenServiceImpl.class);


    @Autowired
    @Qualifier("WM_APP_STORE1.AppScreenDao")
    private WMGenericDao<AppScreen, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AppScreen, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppScreen create(AppScreen appScreen) {
        LOGGER.debug("Creating a new AppScreen with information: {}", appScreen);

        AppScreen appScreenCreated = this.wmGenericDao.create(appScreen);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(appScreenCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppScreen getById(Integer appscreenId) {
        LOGGER.debug("Finding AppScreen by id: {}", appscreenId);
        return this.wmGenericDao.findById(appscreenId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppScreen findById(Integer appscreenId) {
        LOGGER.debug("Finding AppScreen by id: {}", appscreenId);
        try {
            return this.wmGenericDao.findById(appscreenId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AppScreen found with id: {}", appscreenId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public List<AppScreen> findByMultipleIds(List<Integer> appscreenIds, boolean orderedReturn) {
        LOGGER.debug("Finding AppScreens by ids: {}", appscreenIds);

        return this.wmGenericDao.findByMultipleIds(appscreenIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppScreen update(AppScreen appScreen) {
        LOGGER.debug("Updating AppScreen with information: {}", appScreen);

        this.wmGenericDao.update(appScreen);
        this.wmGenericDao.refresh(appScreen);

        return appScreen;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppScreen partialUpdate(Integer appscreenId, Map<String, Object>appScreenPatch) {
        LOGGER.debug("Partially Updating the AppScreen with id: {}", appscreenId);

        AppScreen appScreen = getById(appscreenId);

        try {
            ObjectReader appScreenReader = this.objectMapper.reader().forType(AppScreen.class).withValueToUpdate(appScreen);
            appScreen = appScreenReader.readValue(this.objectMapper.writeValueAsString(appScreenPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", appScreenPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        appScreen = update(appScreen);

        return appScreen;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public AppScreen delete(Integer appscreenId) {
        LOGGER.debug("Deleting AppScreen with id: {}", appscreenId);
        AppScreen deleted = this.wmGenericDao.findById(appscreenId);
        if (deleted == null) {
            LOGGER.debug("No AppScreen found with id: {}", appscreenId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AppScreen.class.getSimpleName(), appscreenId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public void delete(AppScreen appScreen) {
        LOGGER.debug("Deleting AppScreen with {}", appScreen);
        this.wmGenericDao.delete(appScreen);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<AppScreen> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AppScreens");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<AppScreen> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AppScreens");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table AppScreen to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table AppScreen to {} format", options.getExportType());
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



}

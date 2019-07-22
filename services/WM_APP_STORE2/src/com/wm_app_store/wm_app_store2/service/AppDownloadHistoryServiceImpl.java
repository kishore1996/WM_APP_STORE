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

import com.wm_app_store.wm_app_store2.AppDownloadHistory;


/**
 * ServiceImpl object for domain model class AppDownloadHistory.
 *
 * @see AppDownloadHistory
 */
@Service("WM_APP_STORE2.AppDownloadHistoryService")
@Validated
public class AppDownloadHistoryServiceImpl implements AppDownloadHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppDownloadHistoryServiceImpl.class);


    @Autowired
    @Qualifier("WM_APP_STORE2.AppDownloadHistoryDao")
    private WMGenericDao<AppDownloadHistory, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AppDownloadHistory, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public AppDownloadHistory create(AppDownloadHistory appDownloadHistory) {
        LOGGER.debug("Creating a new AppDownloadHistory with information: {}", appDownloadHistory);

        AppDownloadHistory appDownloadHistoryCreated = this.wmGenericDao.create(appDownloadHistory);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(appDownloadHistoryCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public AppDownloadHistory getById(Integer appdownloadhistoryId) {
        LOGGER.debug("Finding AppDownloadHistory by id: {}", appdownloadhistoryId);
        return this.wmGenericDao.findById(appdownloadhistoryId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public AppDownloadHistory findById(Integer appdownloadhistoryId) {
        LOGGER.debug("Finding AppDownloadHistory by id: {}", appdownloadhistoryId);
        try {
            return this.wmGenericDao.findById(appdownloadhistoryId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AppDownloadHistory found with id: {}", appdownloadhistoryId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public List<AppDownloadHistory> findByMultipleIds(List<Integer> appdownloadhistoryIds, boolean orderedReturn) {
        LOGGER.debug("Finding AppDownloadHistories by ids: {}", appdownloadhistoryIds);

        return this.wmGenericDao.findByMultipleIds(appdownloadhistoryIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORE2TransactionManager")
    @Override
    public AppDownloadHistory update(AppDownloadHistory appDownloadHistory) {
        LOGGER.debug("Updating AppDownloadHistory with information: {}", appDownloadHistory);

        this.wmGenericDao.update(appDownloadHistory);
        this.wmGenericDao.refresh(appDownloadHistory);

        return appDownloadHistory;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public AppDownloadHistory partialUpdate(Integer appdownloadhistoryId, Map<String, Object>appDownloadHistoryPatch) {
        LOGGER.debug("Partially Updating the AppDownloadHistory with id: {}", appdownloadhistoryId);

        AppDownloadHistory appDownloadHistory = getById(appdownloadhistoryId);

        try {
            ObjectReader appDownloadHistoryReader = this.objectMapper.reader().forType(AppDownloadHistory.class).withValueToUpdate(appDownloadHistory);
            appDownloadHistory = appDownloadHistoryReader.readValue(this.objectMapper.writeValueAsString(appDownloadHistoryPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", appDownloadHistoryPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        appDownloadHistory = update(appDownloadHistory);

        return appDownloadHistory;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public AppDownloadHistory delete(Integer appdownloadhistoryId) {
        LOGGER.debug("Deleting AppDownloadHistory with id: {}", appdownloadhistoryId);
        AppDownloadHistory deleted = this.wmGenericDao.findById(appdownloadhistoryId);
        if (deleted == null) {
            LOGGER.debug("No AppDownloadHistory found with id: {}", appdownloadhistoryId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AppDownloadHistory.class.getSimpleName(), appdownloadhistoryId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORE2TransactionManager")
    @Override
    public void delete(AppDownloadHistory appDownloadHistory) {
        LOGGER.debug("Deleting AppDownloadHistory with {}", appDownloadHistory);
        this.wmGenericDao.delete(appDownloadHistory);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public Page<AppDownloadHistory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AppDownloadHistories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager")
    @Override
    public Page<AppDownloadHistory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AppDownloadHistories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE2 for table AppDownloadHistory to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE2TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE2 for table AppDownloadHistory to {} format", options.getExportType());
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



}

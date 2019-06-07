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

import com.wm_app_store.wm_app_store.AppRating;


/**
 * ServiceImpl object for domain model class AppRating.
 *
 * @see AppRating
 */
@Service("WM_APP_STORE.AppRatingService")
@Validated
public class AppRatingServiceImpl implements AppRatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRatingServiceImpl.class);


    @Autowired
    @Qualifier("WM_APP_STORE.AppRatingDao")
    private WMGenericDao<AppRating, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AppRating, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppRating create(AppRating appRating) {
        LOGGER.debug("Creating a new AppRating with information: {}", appRating);

        AppRating appRatingCreated = this.wmGenericDao.create(appRating);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(appRatingCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppRating getById(Integer appratingId) {
        LOGGER.debug("Finding AppRating by id: {}", appratingId);
        return this.wmGenericDao.findById(appratingId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppRating findById(Integer appratingId) {
        LOGGER.debug("Finding AppRating by id: {}", appratingId);
        try {
            return this.wmGenericDao.findById(appratingId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AppRating found with id: {}", appratingId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public List<AppRating> findByMultipleIds(List<Integer> appratingIds, boolean orderedReturn) {
        LOGGER.debug("Finding AppRatings by ids: {}", appratingIds);

        return this.wmGenericDao.findByMultipleIds(appratingIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORETransactionManager")
    @Override
    public AppRating update(AppRating appRating) {
        LOGGER.debug("Updating AppRating with information: {}", appRating);

        this.wmGenericDao.update(appRating);
        this.wmGenericDao.refresh(appRating);

        return appRating;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppRating partialUpdate(Integer appratingId, Map<String, Object>appRatingPatch) {
        LOGGER.debug("Partially Updating the AppRating with id: {}", appratingId);

        AppRating appRating = getById(appratingId);

        try {
            ObjectReader appRatingReader = this.objectMapper.reader().forType(AppRating.class).withValueToUpdate(appRating);
            appRating = appRatingReader.readValue(this.objectMapper.writeValueAsString(appRatingPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", appRatingPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        appRating = update(appRating);

        return appRating;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppRating delete(Integer appratingId) {
        LOGGER.debug("Deleting AppRating with id: {}", appratingId);
        AppRating deleted = this.wmGenericDao.findById(appratingId);
        if (deleted == null) {
            LOGGER.debug("No AppRating found with id: {}", appratingId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AppRating.class.getSimpleName(), appratingId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public void delete(AppRating appRating) {
        LOGGER.debug("Deleting AppRating with {}", appRating);
        this.wmGenericDao.delete(appRating);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppRating> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AppRatings");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppRating> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AppRatings");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppRating to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppRating to {} format", options.getExportType());
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
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

import com.wm_app_store.wm_app_store.AppInfo;
import com.wm_app_store.wm_app_store.AppRating;
import com.wm_app_store.wm_app_store.AppScreen;
import com.wm_app_store.wm_app_store.AppScreenshots;
import com.wm_app_store.wm_app_store.AppSource;


/**
 * ServiceImpl object for domain model class AppInfo.
 *
 * @see AppInfo
 */
@Service("WM_APP_STORE.AppInfoService")
@Validated
public class AppInfoServiceImpl implements AppInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppInfoServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE.AppSourceService")
    private AppSourceService appSourceService;

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE.AppScreenshotsService")
    private AppScreenshotsService appScreenshotsService;

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE.AppRatingService")
    private AppRatingService appRatingService;

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE.AppScreenService")
    private AppScreenService appScreenService;

    @Autowired
    @Qualifier("WM_APP_STORE.AppInfoDao")
    private WMGenericDao<AppInfo, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<AppInfo, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppInfo create(AppInfo appInfo) {
        LOGGER.debug("Creating a new AppInfo with information: {}", appInfo);

        AppInfo appInfoCreated = this.wmGenericDao.create(appInfo);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(appInfoCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppInfo getById(Integer appinfoId) {
        LOGGER.debug("Finding AppInfo by id: {}", appinfoId);
        return this.wmGenericDao.findById(appinfoId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public AppInfo findById(Integer appinfoId) {
        LOGGER.debug("Finding AppInfo by id: {}", appinfoId);
        try {
            return this.wmGenericDao.findById(appinfoId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No AppInfo found with id: {}", appinfoId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public List<AppInfo> findByMultipleIds(List<Integer> appinfoIds, boolean orderedReturn) {
        LOGGER.debug("Finding AppInfos by ids: {}", appinfoIds);

        return this.wmGenericDao.findByMultipleIds(appinfoIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORETransactionManager")
    @Override
    public AppInfo update(AppInfo appInfo) {
        LOGGER.debug("Updating AppInfo with information: {}", appInfo);

        this.wmGenericDao.update(appInfo);
        this.wmGenericDao.refresh(appInfo);

        return appInfo;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppInfo partialUpdate(Integer appinfoId, Map<String, Object>appInfoPatch) {
        LOGGER.debug("Partially Updating the AppInfo with id: {}", appinfoId);

        AppInfo appInfo = getById(appinfoId);

        try {
            ObjectReader appInfoReader = this.objectMapper.reader().forType(AppInfo.class).withValueToUpdate(appInfo);
            appInfo = appInfoReader.readValue(this.objectMapper.writeValueAsString(appInfoPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", appInfoPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        appInfo = update(appInfo);

        return appInfo;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public AppInfo delete(Integer appinfoId) {
        LOGGER.debug("Deleting AppInfo with id: {}", appinfoId);
        AppInfo deleted = this.wmGenericDao.findById(appinfoId);
        if (deleted == null) {
            LOGGER.debug("No AppInfo found with id: {}", appinfoId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), AppInfo.class.getSimpleName(), appinfoId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public void delete(AppInfo appInfo) {
        LOGGER.debug("Deleting AppInfo with {}", appInfo);
        this.wmGenericDao.delete(appInfo);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppInfo> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all AppInfos");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppInfo> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all AppInfos");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppInfo to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table AppInfo to {} format", options.getExportType());
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

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppRating> findAssociatedAppRatings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated appRatings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("appInfo.id = '" + id + "'");

        return appRatingService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppScreen> findAssociatedAppScreens(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated appScreens");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("appInfo.id = '" + id + "'");

        return appScreenService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppScreenshots> findAssociatedAppScreenshotses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated appScreenshotses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("appInfo.id = '" + id + "'");

        return appScreenshotsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<AppSource> findAssociatedAppSources(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated appSources");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("appInfo.id = '" + id + "'");

        return appSourceService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service AppSourceService instance
     */
    protected void setAppSourceService(AppSourceService service) {
        this.appSourceService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service AppScreenshotsService instance
     */
    protected void setAppScreenshotsService(AppScreenshotsService service) {
        this.appScreenshotsService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service AppRatingService instance
     */
    protected void setAppRatingService(AppRatingService service) {
        this.appRatingService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service AppScreenService instance
     */
    protected void setAppScreenService(AppScreenService service) {
        this.appScreenService = service;
    }

}
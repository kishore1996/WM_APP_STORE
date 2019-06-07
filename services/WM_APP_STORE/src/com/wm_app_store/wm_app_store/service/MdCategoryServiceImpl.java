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
import com.wm_app_store.wm_app_store.MdCategory;


/**
 * ServiceImpl object for domain model class MdCategory.
 *
 * @see MdCategory
 */
@Service("WM_APP_STORE.MdCategoryService")
@Validated
public class MdCategoryServiceImpl implements MdCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MdCategoryServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE.AppInfoService")
    private AppInfoService appInfoService;

    @Autowired
    @Qualifier("WM_APP_STORE.MdCategoryDao")
    private WMGenericDao<MdCategory, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<MdCategory, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public MdCategory create(MdCategory mdCategory) {
        LOGGER.debug("Creating a new MdCategory with information: {}", mdCategory);

        MdCategory mdCategoryCreated = this.wmGenericDao.create(mdCategory);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(mdCategoryCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public MdCategory getById(Integer mdcategoryId) {
        LOGGER.debug("Finding MdCategory by id: {}", mdcategoryId);
        return this.wmGenericDao.findById(mdcategoryId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public MdCategory findById(Integer mdcategoryId) {
        LOGGER.debug("Finding MdCategory by id: {}", mdcategoryId);
        try {
            return this.wmGenericDao.findById(mdcategoryId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No MdCategory found with id: {}", mdcategoryId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public List<MdCategory> findByMultipleIds(List<Integer> mdcategoryIds, boolean orderedReturn) {
        LOGGER.debug("Finding MdCategories by ids: {}", mdcategoryIds);

        return this.wmGenericDao.findByMultipleIds(mdcategoryIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORETransactionManager")
    @Override
    public MdCategory update(MdCategory mdCategory) {
        LOGGER.debug("Updating MdCategory with information: {}", mdCategory);

        this.wmGenericDao.update(mdCategory);
        this.wmGenericDao.refresh(mdCategory);

        return mdCategory;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public MdCategory partialUpdate(Integer mdcategoryId, Map<String, Object>mdCategoryPatch) {
        LOGGER.debug("Partially Updating the MdCategory with id: {}", mdcategoryId);

        MdCategory mdCategory = getById(mdcategoryId);

        try {
            ObjectReader mdCategoryReader = this.objectMapper.reader().forType(MdCategory.class).withValueToUpdate(mdCategory);
            mdCategory = mdCategoryReader.readValue(this.objectMapper.writeValueAsString(mdCategoryPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", mdCategoryPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        mdCategory = update(mdCategory);

        return mdCategory;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public MdCategory delete(Integer mdcategoryId) {
        LOGGER.debug("Deleting MdCategory with id: {}", mdcategoryId);
        MdCategory deleted = this.wmGenericDao.findById(mdcategoryId);
        if (deleted == null) {
            LOGGER.debug("No MdCategory found with id: {}", mdcategoryId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), MdCategory.class.getSimpleName(), mdcategoryId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORETransactionManager")
    @Override
    public void delete(MdCategory mdCategory) {
        LOGGER.debug("Deleting MdCategory with {}", mdCategory);
        this.wmGenericDao.delete(mdCategory);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<MdCategory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all MdCategories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager")
    @Override
    public Page<MdCategory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all MdCategories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table MdCategory to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORETransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE for table MdCategory to {} format", options.getExportType());
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
    public Page<AppInfo> findAssociatedAppInfos(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated appInfos");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("mdCategory.id = '" + id + "'");

        return appInfoService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service AppInfoService instance
     */
    protected void setAppInfoService(AppInfoService service) {
        this.appInfoService = service;
    }

}
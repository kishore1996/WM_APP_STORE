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

import com.wm_app_store.wm_app_store1.Credentials;


/**
 * ServiceImpl object for domain model class Credentials.
 *
 * @see Credentials
 */
@Service("WM_APP_STORE1.CredentialsService")
@Validated
public class CredentialsServiceImpl implements CredentialsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsServiceImpl.class);


    @Autowired
    @Qualifier("WM_APP_STORE1.CredentialsDao")
    private WMGenericDao<Credentials, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Credentials, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public Credentials create(Credentials credentials) {
        LOGGER.debug("Creating a new Credentials with information: {}", credentials);

        Credentials credentialsCreated = this.wmGenericDao.create(credentials);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(credentialsCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Credentials getById(Integer credentialsId) {
        LOGGER.debug("Finding Credentials by id: {}", credentialsId);
        return this.wmGenericDao.findById(credentialsId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Credentials findById(Integer credentialsId) {
        LOGGER.debug("Finding Credentials by id: {}", credentialsId);
        try {
            return this.wmGenericDao.findById(credentialsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Credentials found with id: {}", credentialsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public List<Credentials> findByMultipleIds(List<Integer> credentialsIds, boolean orderedReturn) {
        LOGGER.debug("Finding Credentials by ids: {}", credentialsIds);

        return this.wmGenericDao.findByMultipleIds(credentialsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Credentials update(Credentials credentials) {
        LOGGER.debug("Updating Credentials with information: {}", credentials);

        this.wmGenericDao.update(credentials);
        this.wmGenericDao.refresh(credentials);

        return credentials;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public Credentials partialUpdate(Integer credentialsId, Map<String, Object>credentialsPatch) {
        LOGGER.debug("Partially Updating the Credentials with id: {}", credentialsId);

        Credentials credentials = getById(credentialsId);

        try {
            ObjectReader credentialsReader = this.objectMapper.reader().forType(Credentials.class).withValueToUpdate(credentials);
            credentials = credentialsReader.readValue(this.objectMapper.writeValueAsString(credentialsPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", credentialsPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        credentials = update(credentials);

        return credentials;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public Credentials delete(Integer credentialsId) {
        LOGGER.debug("Deleting Credentials with id: {}", credentialsId);
        Credentials deleted = this.wmGenericDao.findById(credentialsId);
        if (deleted == null) {
            LOGGER.debug("No Credentials found with id: {}", credentialsId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Credentials.class.getSimpleName(), credentialsId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public void delete(Credentials credentials) {
        LOGGER.debug("Deleting Credentials with {}", credentials);
        this.wmGenericDao.delete(credentials);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<Credentials> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Credentials");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<Credentials> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Credentials");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table Credentials to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table Credentials to {} format", options.getExportType());
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

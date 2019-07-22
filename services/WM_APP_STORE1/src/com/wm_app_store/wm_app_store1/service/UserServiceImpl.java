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

import com.wm_app_store.wm_app_store1.User;
import com.wm_app_store.wm_app_store1.UserRole;


/**
 * ServiceImpl object for domain model class User.
 *
 * @see User
 */
@Service("WM_APP_STORE1.UserService")
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("WM_APP_STORE1.UserRoleService")
    private UserRoleService userRoleService;

    @Autowired
    @Qualifier("WM_APP_STORE1.UserDao")
    private WMGenericDao<User, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<User, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public User create(User user) {
        LOGGER.debug("Creating a new User with information: {}", user);

        User userCreated = this.wmGenericDao.create(user);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(userCreated);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public User getById(Integer userId) {
        LOGGER.debug("Finding User by id: {}", userId);
        return this.wmGenericDao.findById(userId);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public User findById(Integer userId) {
        LOGGER.debug("Finding User by id: {}", userId);
        try {
            return this.wmGenericDao.findById(userId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No User found with id: {}", userId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public List<User> findByMultipleIds(List<Integer> userIds, boolean orderedReturn) {
        LOGGER.debug("Finding Users by ids: {}", userIds);

        return this.wmGenericDao.findByMultipleIds(userIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "WM_APP_STORE1TransactionManager")
    @Override
    public User update(User user) {
        LOGGER.debug("Updating User with information: {}", user);

        this.wmGenericDao.update(user);
        this.wmGenericDao.refresh(user);

        return user;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public User partialUpdate(Integer userId, Map<String, Object>userPatch) {
        LOGGER.debug("Partially Updating the User with id: {}", userId);

        User user = getById(userId);

        try {
            ObjectReader userReader = this.objectMapper.reader().forType(User.class).withValueToUpdate(user);
            user = userReader.readValue(this.objectMapper.writeValueAsString(userPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", userPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        user = update(user);

        return user;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public User delete(Integer userId) {
        LOGGER.debug("Deleting User with id: {}", userId);
        User deleted = this.wmGenericDao.findById(userId);
        if (deleted == null) {
            LOGGER.debug("No User found with id: {}", userId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), User.class.getSimpleName(), userId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "WM_APP_STORE1TransactionManager")
    @Override
    public void delete(User user) {
        LOGGER.debug("Deleting User with {}", user);
        this.wmGenericDao.delete(user);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<User> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager")
    @Override
    public Page<User> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table User to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "WM_APP_STORE1TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service WM_APP_STORE1 for table User to {} format", options.getExportType());
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
    public Page<UserRole> findAssociatedUserRoles(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userRoles");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("user.id = '" + id + "'");

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

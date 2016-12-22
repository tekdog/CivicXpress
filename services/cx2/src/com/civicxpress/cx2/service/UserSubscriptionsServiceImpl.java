/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.UserSubscriptions;


/**
 * ServiceImpl object for domain model class UserSubscriptions.
 *
 * @see UserSubscriptions
 */
@Service("cx2.UserSubscriptionsService")
public class UserSubscriptionsServiceImpl implements UserSubscriptionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSubscriptionsServiceImpl.class);


    @Autowired
    @Qualifier("cx2.UserSubscriptionsDao")
    private WMGenericDao<UserSubscriptions, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<UserSubscriptions, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public UserSubscriptions create(UserSubscriptions userSubscriptions) {
        LOGGER.debug("Creating a new UserSubscriptions with information: {}", userSubscriptions);
        UserSubscriptions userSubscriptionsCreated = this.wmGenericDao.create(userSubscriptions);
        return userSubscriptionsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public UserSubscriptions getById(Integer usersubscriptionsId) throws EntityNotFoundException {
        LOGGER.debug("Finding UserSubscriptions by id: {}", usersubscriptionsId);
        UserSubscriptions userSubscriptions = this.wmGenericDao.findById(usersubscriptionsId);
        if (userSubscriptions == null){
            LOGGER.debug("No UserSubscriptions found with id: {}", usersubscriptionsId);
            throw new EntityNotFoundException(String.valueOf(usersubscriptionsId));
        }
        return userSubscriptions;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public UserSubscriptions findById(Integer usersubscriptionsId) {
        LOGGER.debug("Finding UserSubscriptions by id: {}", usersubscriptionsId);
        return this.wmGenericDao.findById(usersubscriptionsId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public UserSubscriptions getByMunicipalityIdAndUserId(Integer municipalityId, Integer userId) {
        Map<String, Object> municipalityIdAndUserIdMap = new HashMap<>();
        municipalityIdAndUserIdMap.put("municipalityId", municipalityId);
        municipalityIdAndUserIdMap.put("userId", userId);

        LOGGER.debug("Finding UserSubscriptions by unique keys: {}", municipalityIdAndUserIdMap);
        UserSubscriptions userSubscriptions = this.wmGenericDao.findByUniqueKey(municipalityIdAndUserIdMap);

        if (userSubscriptions == null){
            LOGGER.debug("No UserSubscriptions found with given unique key values: {}", municipalityIdAndUserIdMap);
            throw new EntityNotFoundException(String.valueOf(municipalityIdAndUserIdMap));
        }

        return userSubscriptions;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public UserSubscriptions update(UserSubscriptions userSubscriptions) throws EntityNotFoundException {
        LOGGER.debug("Updating UserSubscriptions with information: {}", userSubscriptions);
        this.wmGenericDao.update(userSubscriptions);

        Integer usersubscriptionsId = userSubscriptions.getId();

        return this.wmGenericDao.findById(usersubscriptionsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public UserSubscriptions delete(Integer usersubscriptionsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting UserSubscriptions with id: {}", usersubscriptionsId);
        UserSubscriptions deleted = this.wmGenericDao.findById(usersubscriptionsId);
        if (deleted == null) {
            LOGGER.debug("No UserSubscriptions found with id: {}", usersubscriptionsId);
            throw new EntityNotFoundException(String.valueOf(usersubscriptionsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<UserSubscriptions> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all UserSubscriptions");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserSubscriptions> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all UserSubscriptions");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table UserSubscriptions to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}


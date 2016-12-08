/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


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

import com.civicxpress.cx2.McnewElectricConnection;


/**
 * ServiceImpl object for domain model class McnewElectricConnection.
 *
 * @see McnewElectricConnection
 */
@Service("cx2.McnewElectricConnectionService")
public class McnewElectricConnectionServiceImpl implements McnewElectricConnectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(McnewElectricConnectionServiceImpl.class);


    @Autowired
    @Qualifier("cx2.McnewElectricConnectionDao")
    private WMGenericDao<McnewElectricConnection, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<McnewElectricConnection, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public McnewElectricConnection create(McnewElectricConnection mcnewElectricConnection) {
        LOGGER.debug("Creating a new McnewElectricConnection with information: {}", mcnewElectricConnection);
        McnewElectricConnection mcnewElectricConnectionCreated = this.wmGenericDao.create(mcnewElectricConnection);
        return mcnewElectricConnectionCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public McnewElectricConnection getById(Integer mcnewelectricconnectionId) throws EntityNotFoundException {
        LOGGER.debug("Finding McnewElectricConnection by id: {}", mcnewelectricconnectionId);
        McnewElectricConnection mcnewElectricConnection = this.wmGenericDao.findById(mcnewelectricconnectionId);
        if (mcnewElectricConnection == null){
            LOGGER.debug("No McnewElectricConnection found with id: {}", mcnewelectricconnectionId);
            throw new EntityNotFoundException(String.valueOf(mcnewelectricconnectionId));
        }
        return mcnewElectricConnection;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public McnewElectricConnection findById(Integer mcnewelectricconnectionId) {
        LOGGER.debug("Finding McnewElectricConnection by id: {}", mcnewelectricconnectionId);
        return this.wmGenericDao.findById(mcnewelectricconnectionId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public McnewElectricConnection update(McnewElectricConnection mcnewElectricConnection) throws EntityNotFoundException {
        LOGGER.debug("Updating McnewElectricConnection with information: {}", mcnewElectricConnection);
        this.wmGenericDao.update(mcnewElectricConnection);

        Integer mcnewelectricconnectionId = mcnewElectricConnection.getId();

        return this.wmGenericDao.findById(mcnewelectricconnectionId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public McnewElectricConnection delete(Integer mcnewelectricconnectionId) throws EntityNotFoundException {
        LOGGER.debug("Deleting McnewElectricConnection with id: {}", mcnewelectricconnectionId);
        McnewElectricConnection deleted = this.wmGenericDao.findById(mcnewelectricconnectionId);
        if (deleted == null) {
            LOGGER.debug("No McnewElectricConnection found with id: {}", mcnewelectricconnectionId);
            throw new EntityNotFoundException(String.valueOf(mcnewelectricconnectionId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<McnewElectricConnection> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all McnewElectricConnections");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<McnewElectricConnection> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all McnewElectricConnections");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table McnewElectricConnection to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

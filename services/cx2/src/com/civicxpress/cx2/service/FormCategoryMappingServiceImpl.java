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

import com.civicxpress.cx2.FormCategoryMapping;


/**
 * ServiceImpl object for domain model class FormCategoryMapping.
 *
 * @see FormCategoryMapping
 */
@Service("cx2.FormCategoryMappingService")
public class FormCategoryMappingServiceImpl implements FormCategoryMappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormCategoryMappingServiceImpl.class);


    @Autowired
    @Qualifier("cx2.FormCategoryMappingDao")
    private WMGenericDao<FormCategoryMapping, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormCategoryMapping, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormCategoryMapping create(FormCategoryMapping formCategoryMapping) {
        LOGGER.debug("Creating a new FormCategoryMapping with information: {}", formCategoryMapping);
        FormCategoryMapping formCategoryMappingCreated = this.wmGenericDao.create(formCategoryMapping);
        return formCategoryMappingCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormCategoryMapping getById(Integer formcategorymappingId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormCategoryMapping by id: {}", formcategorymappingId);
        FormCategoryMapping formCategoryMapping = this.wmGenericDao.findById(formcategorymappingId);
        if (formCategoryMapping == null){
            LOGGER.debug("No FormCategoryMapping found with id: {}", formcategorymappingId);
            throw new EntityNotFoundException(String.valueOf(formcategorymappingId));
        }
        return formCategoryMapping;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormCategoryMapping findById(Integer formcategorymappingId) {
        LOGGER.debug("Finding FormCategoryMapping by id: {}", formcategorymappingId);
        return this.wmGenericDao.findById(formcategorymappingId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormCategoryMapping update(FormCategoryMapping formCategoryMapping) throws EntityNotFoundException {
        LOGGER.debug("Updating FormCategoryMapping with information: {}", formCategoryMapping);
        this.wmGenericDao.update(formCategoryMapping);

        Integer formcategorymappingId = formCategoryMapping.getId();

        return this.wmGenericDao.findById(formcategorymappingId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormCategoryMapping delete(Integer formcategorymappingId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormCategoryMapping with id: {}", formcategorymappingId);
        FormCategoryMapping deleted = this.wmGenericDao.findById(formcategorymappingId);
        if (deleted == null) {
            LOGGER.debug("No FormCategoryMapping found with id: {}", formcategorymappingId);
            throw new EntityNotFoundException(String.valueOf(formcategorymappingId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormCategoryMapping> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormCategoryMappings");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormCategoryMapping> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormCategoryMappings");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormCategoryMapping to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

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

import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.MasterForms;


/**
 * ServiceImpl object for domain model class Giscontacts.
 *
 * @see Giscontacts
 */
@Service("cx2.GiscontactsService")
public class GiscontactsServiceImpl implements GiscontactsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiscontactsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
    @Qualifier("cx2.GiscontactsDao")
    private WMGenericDao<Giscontacts, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Giscontacts, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Giscontacts create(Giscontacts giscontacts) {
        LOGGER.debug("Creating a new Giscontacts with information: {}", giscontacts);
        Giscontacts giscontactsCreated = this.wmGenericDao.create(giscontacts);
        if(giscontactsCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : giscontactsCreated.getMasterFormses()) {
                masterFormse.setGiscontacts(giscontactsCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }
        return giscontactsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Giscontacts getById(Integer giscontactsId) throws EntityNotFoundException {
        LOGGER.debug("Finding Giscontacts by id: {}", giscontactsId);
        Giscontacts giscontacts = this.wmGenericDao.findById(giscontactsId);
        if (giscontacts == null){
            LOGGER.debug("No Giscontacts found with id: {}", giscontactsId);
            throw new EntityNotFoundException(String.valueOf(giscontactsId));
        }
        return giscontacts;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Giscontacts findById(Integer giscontactsId) {
        LOGGER.debug("Finding Giscontacts by id: {}", giscontactsId);
        return this.wmGenericDao.findById(giscontactsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Giscontacts update(Giscontacts giscontacts) throws EntityNotFoundException {
        LOGGER.debug("Updating Giscontacts with information: {}", giscontacts);
        this.wmGenericDao.update(giscontacts);

        Integer giscontactsId = giscontacts.getId();

        return this.wmGenericDao.findById(giscontactsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Giscontacts delete(Integer giscontactsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Giscontacts with id: {}", giscontactsId);
        Giscontacts deleted = this.wmGenericDao.findById(giscontactsId);
        if (deleted == null) {
            LOGGER.debug("No Giscontacts found with id: {}", giscontactsId);
            throw new EntityNotFoundException(String.valueOf(giscontactsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Giscontacts> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Giscontacts");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Giscontacts> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Giscontacts");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Giscontacts to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("giscontacts.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterFormsService instance
	 */
	protected void setMasterFormsService(MasterFormsService service) {
        this.masterFormsService = service;
    }

}


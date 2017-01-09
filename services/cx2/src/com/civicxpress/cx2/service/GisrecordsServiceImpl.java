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

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.Gisrecords;


/**
 * ServiceImpl object for domain model class Gisrecords.
 *
 * @see Gisrecords
 */
@Service("cx2.GisrecordsService")
public class GisrecordsServiceImpl implements GisrecordsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GisrecordsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.FeesService")
	private FeesService feesService;

    @Autowired
	@Qualifier("cx2.GiscontactsService")
	private GiscontactsService giscontactsService;

    @Autowired
    @Qualifier("cx2.GisrecordsDao")
    private WMGenericDao<Gisrecords, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Gisrecords, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Gisrecords create(Gisrecords gisrecords) {
        LOGGER.debug("Creating a new Gisrecords with information: {}", gisrecords);
        Gisrecords gisrecordsCreated = this.wmGenericDao.create(gisrecords);
        if(gisrecordsCreated.getFeeses() != null) {
            for(Fees feese : gisrecordsCreated.getFeeses()) {
                feese.setGisrecords(gisrecordsCreated);
                LOGGER.debug("Creating a new child Fees with information: {}", feese);
                feesService.create(feese);
            }
        }

        if(gisrecordsCreated.getGiscontactses() != null) {
            for(Giscontacts giscontactse : gisrecordsCreated.getGiscontactses()) {
                giscontactse.setGisrecords(gisrecordsCreated);
                LOGGER.debug("Creating a new child Giscontacts with information: {}", giscontactse);
                giscontactsService.create(giscontactse);
            }
        }
        return gisrecordsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Gisrecords getById(Integer gisrecordsId) throws EntityNotFoundException {
        LOGGER.debug("Finding Gisrecords by id: {}", gisrecordsId);
        Gisrecords gisrecords = this.wmGenericDao.findById(gisrecordsId);
        if (gisrecords == null){
            LOGGER.debug("No Gisrecords found with id: {}", gisrecordsId);
            throw new EntityNotFoundException(String.valueOf(gisrecordsId));
        }
        return gisrecords;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Gisrecords findById(Integer gisrecordsId) {
        LOGGER.debug("Finding Gisrecords by id: {}", gisrecordsId);
        return this.wmGenericDao.findById(gisrecordsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Gisrecords update(Gisrecords gisrecords) throws EntityNotFoundException {
        LOGGER.debug("Updating Gisrecords with information: {}", gisrecords);
        this.wmGenericDao.update(gisrecords);

        Integer gisrecordsId = gisrecords.getId();

        return this.wmGenericDao.findById(gisrecordsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Gisrecords delete(Integer gisrecordsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Gisrecords with id: {}", gisrecordsId);
        Gisrecords deleted = this.wmGenericDao.findById(gisrecordsId);
        if (deleted == null) {
            LOGGER.debug("No Gisrecords found with id: {}", gisrecordsId);
            throw new EntityNotFoundException(String.valueOf(gisrecordsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Gisrecords> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Gisrecords");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Gisrecords> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Gisrecords");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Gisrecords to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Fees> findAssociatedFeeses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated feeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("gisrecords.id = '" + id + "'");

        return feesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Giscontacts> findAssociatedGiscontactses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated giscontactses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("gisrecords.id = '" + id + "'");

        return giscontactsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FeesService instance
	 */
	protected void setFeesService(FeesService service) {
        this.feesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service GiscontactsService instance
	 */
	protected void setGiscontactsService(GiscontactsService service) {
        this.giscontactsService = service;
    }

}


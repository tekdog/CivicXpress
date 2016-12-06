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

import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;


/**
 * ServiceImpl object for domain model class States.
 *
 * @see States
 */
@Service("cx2.StatesService")
public class StatesServiceImpl implements StatesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.VendorService")
	private VendorService vendorService;

    @Autowired
	@Qualifier("cx2.MunicipalitiesService")
	private MunicipalitiesService municipalitiesService;

    @Autowired
	@Qualifier("cx2.GisrecordsService")
	private GisrecordsService gisrecordsService;

    @Autowired
	@Qualifier("cx2.UsersService")
	private UsersService usersService;

    @Autowired
    @Qualifier("cx2.StatesDao")
    private WMGenericDao<States, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<States, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public States create(States states) {
        LOGGER.debug("Creating a new States with information: {}", states);
        States statesCreated = this.wmGenericDao.create(states);
        if(statesCreated.getUserses() != null) {
            for(Users userse : statesCreated.getUserses()) {
                userse.setStates(statesCreated);
                LOGGER.debug("Creating a new child Users with information: {}", userse);
                usersService.create(userse);
            }
        }

        if(statesCreated.getMunicipalitieses() != null) {
            for(Municipalities municipalitiese : statesCreated.getMunicipalitieses()) {
                municipalitiese.setStates(statesCreated);
                LOGGER.debug("Creating a new child Municipalities with information: {}", municipalitiese);
                municipalitiesService.create(municipalitiese);
            }
        }

        if(statesCreated.getGisrecordses() != null) {
            for(Gisrecords gisrecordse : statesCreated.getGisrecordses()) {
                gisrecordse.setStates(statesCreated);
                LOGGER.debug("Creating a new child Gisrecords with information: {}", gisrecordse);
                gisrecordsService.create(gisrecordse);
            }
        }

        if(statesCreated.getVendors() != null) {
            for(Vendor vendor : statesCreated.getVendors()) {
                vendor.setStates(statesCreated);
                LOGGER.debug("Creating a new child Vendor with information: {}", vendor);
                vendorService.create(vendor);
            }
        }
        return statesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public States getById(Integer statesId) throws EntityNotFoundException {
        LOGGER.debug("Finding States by id: {}", statesId);
        States states = this.wmGenericDao.findById(statesId);
        if (states == null){
            LOGGER.debug("No States found with id: {}", statesId);
            throw new EntityNotFoundException(String.valueOf(statesId));
        }
        return states;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public States findById(Integer statesId) {
        LOGGER.debug("Finding States by id: {}", statesId);
        return this.wmGenericDao.findById(statesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public States update(States states) throws EntityNotFoundException {
        LOGGER.debug("Updating States with information: {}", states);
        this.wmGenericDao.update(states);

        Integer statesId = states.getId();

        return this.wmGenericDao.findById(statesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public States delete(Integer statesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting States with id: {}", statesId);
        States deleted = this.wmGenericDao.findById(statesId);
        if (deleted == null) {
            LOGGER.debug("No States found with id: {}", statesId);
            throw new EntityNotFoundException(String.valueOf(statesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<States> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all States");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<States> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all States");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table States to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Users> findAssociatedUserses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("states.id = '" + id + "'");

        return usersService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Municipalities> findAssociatedMunicipalitieses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalitieses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("states.id = '" + id + "'");

        return municipalitiesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Gisrecords> findAssociatedGisrecordses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated gisrecordses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("states.id = '" + id + "'");

        return gisrecordsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Vendor> findAssociatedVendors(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendors");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("states.id = '" + id + "'");

        return vendorService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorService instance
	 */
	protected void setVendorService(VendorService service) {
        this.vendorService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MunicipalitiesService instance
	 */
	protected void setMunicipalitiesService(MunicipalitiesService service) {
        this.municipalitiesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service GisrecordsService instance
	 */
	protected void setGisrecordsService(GisrecordsService service) {
        this.gisrecordsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UsersService instance
	 */
	protected void setUsersService(UsersService service) {
        this.usersService = service;
    }

}


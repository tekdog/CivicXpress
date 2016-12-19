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

import com.civicxpress.cx2.VendorLicenses;


/**
 * ServiceImpl object for domain model class VendorLicenses.
 *
 * @see VendorLicenses
 */
@Service("cx2.VendorLicensesService")
public class VendorLicensesServiceImpl implements VendorLicensesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorLicensesServiceImpl.class);


    @Autowired
    @Qualifier("cx2.VendorLicensesDao")
    private WMGenericDao<VendorLicenses, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<VendorLicenses, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public VendorLicenses create(VendorLicenses vendorLicenses) {
        LOGGER.debug("Creating a new VendorLicenses with information: {}", vendorLicenses);
        VendorLicenses vendorLicensesCreated = this.wmGenericDao.create(vendorLicenses);
        return vendorLicensesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public VendorLicenses getById(Integer vendorlicensesId) throws EntityNotFoundException {
        LOGGER.debug("Finding VendorLicenses by id: {}", vendorlicensesId);
        VendorLicenses vendorLicenses = this.wmGenericDao.findById(vendorlicensesId);
        if (vendorLicenses == null){
            LOGGER.debug("No VendorLicenses found with id: {}", vendorlicensesId);
            throw new EntityNotFoundException(String.valueOf(vendorlicensesId));
        }
        return vendorLicenses;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public VendorLicenses findById(Integer vendorlicensesId) {
        LOGGER.debug("Finding VendorLicenses by id: {}", vendorlicensesId);
        return this.wmGenericDao.findById(vendorlicensesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public VendorLicenses update(VendorLicenses vendorLicenses) throws EntityNotFoundException {
        LOGGER.debug("Updating VendorLicenses with information: {}", vendorLicenses);
        this.wmGenericDao.update(vendorLicenses);

        Integer vendorlicensesId = vendorLicenses.getId();

        return this.wmGenericDao.findById(vendorlicensesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public VendorLicenses delete(Integer vendorlicensesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting VendorLicenses with id: {}", vendorlicensesId);
        VendorLicenses deleted = this.wmGenericDao.findById(vendorlicensesId);
        if (deleted == null) {
            LOGGER.debug("No VendorLicenses found with id: {}", vendorlicensesId);
            throw new EntityNotFoundException(String.valueOf(vendorlicensesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<VendorLicenses> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all VendorLicenses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorLicenses> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all VendorLicenses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table VendorLicenses to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

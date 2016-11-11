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

import com.civicxpress.cx2.ContractorApprovals;
import com.civicxpress.cx2.Contractors;


/**
 * ServiceImpl object for domain model class Contractors.
 *
 * @see Contractors
 */
@Service("cx2.ContractorsService")
public class ContractorsServiceImpl implements ContractorsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractorsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.ContractorApprovalsService")
	private ContractorApprovalsService contractorApprovalsService;

    @Autowired
    @Qualifier("cx2.ContractorsDao")
    private WMGenericDao<Contractors, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Contractors, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Contractors create(Contractors contractors) {
        LOGGER.debug("Creating a new Contractors with information: {}", contractors);
        Contractors contractorsCreated = this.wmGenericDao.create(contractors);
        if(contractorsCreated.getContractorApprovalses() != null) {
            for(ContractorApprovals contractorApprovalse : contractorsCreated.getContractorApprovalses()) {
                contractorApprovalse.setContractors(contractorsCreated);
                LOGGER.debug("Creating a new child ContractorApprovals with information: {}", contractorApprovalse);
                contractorApprovalsService.create(contractorApprovalse);
            }
        }
        return contractorsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Contractors getById(Integer contractorsId) throws EntityNotFoundException {
        LOGGER.debug("Finding Contractors by id: {}", contractorsId);
        Contractors contractors = this.wmGenericDao.findById(contractorsId);
        if (contractors == null){
            LOGGER.debug("No Contractors found with id: {}", contractorsId);
            throw new EntityNotFoundException(String.valueOf(contractorsId));
        }
        return contractors;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Contractors findById(Integer contractorsId) {
        LOGGER.debug("Finding Contractors by id: {}", contractorsId);
        return this.wmGenericDao.findById(contractorsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Contractors update(Contractors contractors) throws EntityNotFoundException {
        LOGGER.debug("Updating Contractors with information: {}", contractors);
        this.wmGenericDao.update(contractors);

        Integer contractorsId = contractors.getId();

        return this.wmGenericDao.findById(contractorsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Contractors delete(Integer contractorsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Contractors with id: {}", contractorsId);
        Contractors deleted = this.wmGenericDao.findById(contractorsId);
        if (deleted == null) {
            LOGGER.debug("No Contractors found with id: {}", contractorsId);
            throw new EntityNotFoundException(String.valueOf(contractorsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Contractors> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Contractors");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Contractors> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Contractors");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Contractors to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ContractorApprovals> findAssociatedContractorApprovalses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated contractorApprovalses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("contractors.id = '" + id + "'");

        return contractorApprovalsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ContractorApprovalsService instance
	 */
	protected void setContractorApprovalsService(ContractorApprovalsService service) {
        this.contractorApprovalsService = service;
    }

}


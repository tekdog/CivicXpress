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

import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.McnewElectricConnection;
import com.civicxpress.cx2.McnewResidentialStructure;
import com.civicxpress.cx2.Pudapplication;
import com.civicxpress.cx2.SfnewElectricConnection;
import com.civicxpress.cx2.SfnewResidentialStructure;


/**
 * ServiceImpl object for domain model class FormStatuses.
 *
 * @see FormStatuses
 */
@Service("cx2.FormStatusesService")
public class FormStatusesServiceImpl implements FormStatusesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormStatusesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.PudapplicationService")
	private PudapplicationService pudapplicationService;

    @Autowired
	@Qualifier("cx2.McnewResidentialStructureService")
	private McnewResidentialStructureService mcnewResidentialStructureService;

    @Autowired
	@Qualifier("cx2.McnewElectricConnectionService")
	private McnewElectricConnectionService mcnewElectricConnectionService;

    @Autowired
	@Qualifier("cx2.SfnewElectricConnectionService")
	private SfnewElectricConnectionService sfnewElectricConnectionService;

    @Autowired
	@Qualifier("cx2.SfnewResidentialStructureService")
	private SfnewResidentialStructureService sfnewResidentialStructureService;

    @Autowired
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
    @Qualifier("cx2.FormStatusesDao")
    private WMGenericDao<FormStatuses, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormStatuses, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormStatuses create(FormStatuses formStatuses) {
        LOGGER.debug("Creating a new FormStatuses with information: {}", formStatuses);
        FormStatuses formStatusesCreated = this.wmGenericDao.create(formStatuses);
        if(formStatusesCreated.getSfnewResidentialStructures() != null) {
            for(SfnewResidentialStructure sfnewResidentialStructure : formStatusesCreated.getSfnewResidentialStructures()) {
                sfnewResidentialStructure.setFormStatuses(formStatusesCreated);
                LOGGER.debug("Creating a new child SfnewResidentialStructure with information: {}", sfnewResidentialStructure);
                sfnewResidentialStructureService.create(sfnewResidentialStructure);
            }
        }

        if(formStatusesCreated.getSfnewElectricConnections() != null) {
            for(SfnewElectricConnection sfnewElectricConnection : formStatusesCreated.getSfnewElectricConnections()) {
                sfnewElectricConnection.setFormStatuses(formStatusesCreated);
                LOGGER.debug("Creating a new child SfnewElectricConnection with information: {}", sfnewElectricConnection);
                sfnewElectricConnectionService.create(sfnewElectricConnection);
            }
        }

        if(formStatusesCreated.getMcnewElectricConnections() != null) {
            for(McnewElectricConnection mcnewElectricConnection : formStatusesCreated.getMcnewElectricConnections()) {
                mcnewElectricConnection.setFormStatuses(formStatusesCreated);
                LOGGER.debug("Creating a new child McnewElectricConnection with information: {}", mcnewElectricConnection);
                mcnewElectricConnectionService.create(mcnewElectricConnection);
            }
        }

        if(formStatusesCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : formStatusesCreated.getMasterFormses()) {
                masterFormse.setFormStatuses(formStatusesCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }

        if(formStatusesCreated.getMcnewResidentialStructures() != null) {
            for(McnewResidentialStructure mcnewResidentialStructure : formStatusesCreated.getMcnewResidentialStructures()) {
                mcnewResidentialStructure.setFormStatuses(formStatusesCreated);
                LOGGER.debug("Creating a new child McnewResidentialStructure with information: {}", mcnewResidentialStructure);
                mcnewResidentialStructureService.create(mcnewResidentialStructure);
            }
        }

        if(formStatusesCreated.getPudapplications() != null) {
            for(Pudapplication pudapplication : formStatusesCreated.getPudapplications()) {
                pudapplication.setFormStatuses(formStatusesCreated);
                LOGGER.debug("Creating a new child Pudapplication with information: {}", pudapplication);
                pudapplicationService.create(pudapplication);
            }
        }
        return formStatusesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormStatuses getById(Integer formstatusesId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormStatuses by id: {}", formstatusesId);
        FormStatuses formStatuses = this.wmGenericDao.findById(formstatusesId);
        if (formStatuses == null){
            LOGGER.debug("No FormStatuses found with id: {}", formstatusesId);
            throw new EntityNotFoundException(String.valueOf(formstatusesId));
        }
        return formStatuses;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormStatuses findById(Integer formstatusesId) {
        LOGGER.debug("Finding FormStatuses by id: {}", formstatusesId);
        return this.wmGenericDao.findById(formstatusesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormStatuses update(FormStatuses formStatuses) throws EntityNotFoundException {
        LOGGER.debug("Updating FormStatuses with information: {}", formStatuses);
        this.wmGenericDao.update(formStatuses);

        Integer formstatusesId = formStatuses.getId();

        return this.wmGenericDao.findById(formstatusesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormStatuses delete(Integer formstatusesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormStatuses with id: {}", formstatusesId);
        FormStatuses deleted = this.wmGenericDao.findById(formstatusesId);
        if (deleted == null) {
            LOGGER.debug("No FormStatuses found with id: {}", formstatusesId);
            throw new EntityNotFoundException(String.valueOf(formstatusesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormStatuses> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormStatuses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormStatuses> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormStatuses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormStatuses to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SfnewResidentialStructure> findAssociatedSfnewResidentialStructures(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sfnewResidentialStructures");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formStatuses.id = '" + id + "'");

        return sfnewResidentialStructureService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SfnewElectricConnection> findAssociatedSfnewElectricConnections(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sfnewElectricConnections");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formStatuses.id = '" + id + "'");

        return sfnewElectricConnectionService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<McnewElectricConnection> findAssociatedMcnewElectricConnections(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated mcnewElectricConnections");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formStatuses.id = '" + id + "'");

        return mcnewElectricConnectionService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formStatuses.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<McnewResidentialStructure> findAssociatedMcnewResidentialStructures(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated mcnewResidentialStructures");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formStatuses.id = '" + id + "'");

        return mcnewResidentialStructureService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Pudapplication> findAssociatedPudapplications(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated pudapplications");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formStatuses.id = '" + id + "'");

        return pudapplicationService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PudapplicationService instance
	 */
	protected void setPudapplicationService(PudapplicationService service) {
        this.pudapplicationService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service McnewResidentialStructureService instance
	 */
	protected void setMcnewResidentialStructureService(McnewResidentialStructureService service) {
        this.mcnewResidentialStructureService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service McnewElectricConnectionService instance
	 */
	protected void setMcnewElectricConnectionService(McnewElectricConnectionService service) {
        this.mcnewElectricConnectionService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SfnewElectricConnectionService instance
	 */
	protected void setSfnewElectricConnectionService(SfnewElectricConnectionService service) {
        this.sfnewElectricConnectionService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SfnewResidentialStructureService instance
	 */
	protected void setSfnewResidentialStructureService(SfnewResidentialStructureService service) {
        this.sfnewResidentialStructureService = service;
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


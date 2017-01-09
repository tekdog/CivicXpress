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
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.McnewElectricConnection;
import com.civicxpress.cx2.McnewResidentialStructure;
import com.civicxpress.cx2.Pudapplication;
import com.civicxpress.cx2.SfnewElectricConnection;
import com.civicxpress.cx2.SfnewResidentialStructure;


/**
 * ServiceImpl object for domain model class FormTypes.
 *
 * @see FormTypes
 */
@Service("cx2.FormTypesService")
public class FormTypesServiceImpl implements FormTypesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormTypesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.FormStatusesService")
	private FormStatusesService formStatusesService;

    @Autowired
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
	@Qualifier("cx2.PudapplicationService")
	private PudapplicationService pudapplicationService;

    @Autowired
	@Qualifier("cx2.SfnewResidentialStructureService")
	private SfnewResidentialStructureService sfnewResidentialStructureService;

    @Autowired
	@Qualifier("cx2.McnewElectricConnectionService")
	private McnewElectricConnectionService mcnewElectricConnectionService;

    @Autowired
	@Qualifier("cx2.SfnewElectricConnectionService")
	private SfnewElectricConnectionService sfnewElectricConnectionService;

    @Autowired
	@Qualifier("cx2.McnewResidentialStructureService")
	private McnewResidentialStructureService mcnewResidentialStructureService;

    @Autowired
	@Qualifier("cx2.FormCategoryMappingService")
	private FormCategoryMappingService formCategoryMappingService;

    @Autowired
    @Qualifier("cx2.FormTypesDao")
    private WMGenericDao<FormTypes, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormTypes, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormTypes create(FormTypes formTypes) {
        LOGGER.debug("Creating a new FormTypes with information: {}", formTypes);
        FormTypes formTypesCreated = this.wmGenericDao.create(formTypes);
        if(formTypesCreated.getFormStatuseses() != null) {
            for(FormStatuses formStatusese : formTypesCreated.getFormStatuseses()) {
                formStatusese.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormStatuses with information: {}", formStatusese);
                formStatusesService.create(formStatusese);
            }
        }

        if(formTypesCreated.getFormCategoryMappings() != null) {
            for(FormCategoryMapping formCategoryMapping : formTypesCreated.getFormCategoryMappings()) {
                formCategoryMapping.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormCategoryMapping with information: {}", formCategoryMapping);
                formCategoryMappingService.create(formCategoryMapping);
            }
        }

        if(formTypesCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : formTypesCreated.getMasterFormses()) {
                masterFormse.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }

        if(formTypesCreated.getMcnewElectricConnections() != null) {
            for(McnewElectricConnection mcnewElectricConnection : formTypesCreated.getMcnewElectricConnections()) {
                mcnewElectricConnection.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child McnewElectricConnection with information: {}", mcnewElectricConnection);
                mcnewElectricConnectionService.create(mcnewElectricConnection);
            }
        }

        if(formTypesCreated.getMcnewResidentialStructures() != null) {
            for(McnewResidentialStructure mcnewResidentialStructure : formTypesCreated.getMcnewResidentialStructures()) {
                mcnewResidentialStructure.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child McnewResidentialStructure with information: {}", mcnewResidentialStructure);
                mcnewResidentialStructureService.create(mcnewResidentialStructure);
            }
        }

        if(formTypesCreated.getPudapplications() != null) {
            for(Pudapplication pudapplication : formTypesCreated.getPudapplications()) {
                pudapplication.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child Pudapplication with information: {}", pudapplication);
                pudapplicationService.create(pudapplication);
            }
        }

        if(formTypesCreated.getSfnewElectricConnections() != null) {
            for(SfnewElectricConnection sfnewElectricConnection : formTypesCreated.getSfnewElectricConnections()) {
                sfnewElectricConnection.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child SfnewElectricConnection with information: {}", sfnewElectricConnection);
                sfnewElectricConnectionService.create(sfnewElectricConnection);
            }
        }

        if(formTypesCreated.getSfnewResidentialStructures() != null) {
            for(SfnewResidentialStructure sfnewResidentialStructure : formTypesCreated.getSfnewResidentialStructures()) {
                sfnewResidentialStructure.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child SfnewResidentialStructure with information: {}", sfnewResidentialStructure);
                sfnewResidentialStructureService.create(sfnewResidentialStructure);
            }
        }
        return formTypesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormTypes getById(Integer formtypesId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormTypes by id: {}", formtypesId);
        FormTypes formTypes = this.wmGenericDao.findById(formtypesId);
        if (formTypes == null){
            LOGGER.debug("No FormTypes found with id: {}", formtypesId);
            throw new EntityNotFoundException(String.valueOf(formtypesId));
        }
        return formTypes;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormTypes findById(Integer formtypesId) {
        LOGGER.debug("Finding FormTypes by id: {}", formtypesId);
        return this.wmGenericDao.findById(formtypesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormTypes update(FormTypes formTypes) throws EntityNotFoundException {
        LOGGER.debug("Updating FormTypes with information: {}", formTypes);
        this.wmGenericDao.update(formTypes);

        Integer formtypesId = formTypes.getId();

        return this.wmGenericDao.findById(formtypesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormTypes delete(Integer formtypesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormTypes with id: {}", formtypesId);
        FormTypes deleted = this.wmGenericDao.findById(formtypesId);
        if (deleted == null) {
            LOGGER.debug("No FormTypes found with id: {}", formtypesId);
            throw new EntityNotFoundException(String.valueOf(formtypesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormTypes> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormTypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormTypes> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormTypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormTypes to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormStatuses> findAssociatedFormStatuseses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formStatuseses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formStatusesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormCategoryMapping> findAssociatedFormCategoryMappings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formCategoryMappings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formCategoryMappingService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<McnewElectricConnection> findAssociatedMcnewElectricConnections(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated mcnewElectricConnections");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return mcnewElectricConnectionService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<McnewResidentialStructure> findAssociatedMcnewResidentialStructures(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated mcnewResidentialStructures");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return mcnewResidentialStructureService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Pudapplication> findAssociatedPudapplications(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated pudapplications");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return pudapplicationService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SfnewElectricConnection> findAssociatedSfnewElectricConnections(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sfnewElectricConnections");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return sfnewElectricConnectionService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SfnewResidentialStructure> findAssociatedSfnewResidentialStructures(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sfnewResidentialStructures");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return sfnewResidentialStructureService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormStatusesService instance
	 */
	protected void setFormStatusesService(FormStatusesService service) {
        this.formStatusesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterFormsService instance
	 */
	protected void setMasterFormsService(MasterFormsService service) {
        this.masterFormsService = service;
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
	 * @param service SfnewResidentialStructureService instance
	 */
	protected void setSfnewResidentialStructureService(SfnewResidentialStructureService service) {
        this.sfnewResidentialStructureService = service;
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
	 * @param service McnewResidentialStructureService instance
	 */
	protected void setMcnewResidentialStructureService(McnewResidentialStructureService service) {
        this.mcnewResidentialStructureService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormCategoryMappingService instance
	 */
	protected void setFormCategoryMappingService(FormCategoryMappingService service) {
        this.formCategoryMappingService = service;
    }

}


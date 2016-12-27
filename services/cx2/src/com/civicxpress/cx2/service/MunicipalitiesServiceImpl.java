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

import com.civicxpress.cx2.FormCategories;
import com.civicxpress.cx2.FormFee;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.Holidays;
import com.civicxpress.cx2.ManualFeeTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Roles;
import com.civicxpress.cx2.Subdivisions;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.VendorApprovals;


/**
 * ServiceImpl object for domain model class Municipalities.
 *
 * @see Municipalities
 */
@Service("cx2.MunicipalitiesService")
public class MunicipalitiesServiceImpl implements MunicipalitiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MunicipalitiesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.VendorApprovalsService")
	private VendorApprovalsService vendorApprovalsService;

    @Autowired
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
	@Qualifier("cx2.ManualFeeTypesService")
	private ManualFeeTypesService manualFeeTypesService;

    @Autowired
	@Qualifier("cx2.SubdivisionsService")
	private SubdivisionsService subdivisionsService;

    @Autowired
	@Qualifier("cx2.MunicipalityGroupsService")
	private MunicipalityGroupsService municipalityGroupsService;

    @Autowired
	@Qualifier("cx2.FormCategoriesService")
	private FormCategoriesService formCategoriesService;

    @Autowired
	@Qualifier("cx2.RolesService")
	private RolesService rolesService;

    @Autowired
	@Qualifier("cx2.HolidaysService")
	private HolidaysService holidaysService;

    @Autowired
	@Qualifier("cx2.FormFeeService")
	private FormFeeService formFeeService;

    @Autowired
	@Qualifier("cx2.GisrecordsService")
	private GisrecordsService gisrecordsService;

    @Autowired
	@Qualifier("cx2.UserSubscriptionsService")
	private UserSubscriptionsService userSubscriptionsService;

    @Autowired
    @Qualifier("cx2.MunicipalitiesDao")
    private WMGenericDao<Municipalities, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Municipalities, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Municipalities create(Municipalities municipalities) {
        LOGGER.debug("Creating a new Municipalities with information: {}", municipalities);
        Municipalities municipalitiesCreated = this.wmGenericDao.create(municipalities);
        if(municipalitiesCreated.getFormCategorieses() != null) {
            for(FormCategories formCategoriese : municipalitiesCreated.getFormCategorieses()) {
                formCategoriese.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child FormCategories with information: {}", formCategoriese);
                formCategoriesService.create(formCategoriese);
            }
        }

        if(municipalitiesCreated.getFormFees() != null) {
            for(FormFee formFee : municipalitiesCreated.getFormFees()) {
                formFee.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child FormFee with information: {}", formFee);
                formFeeService.create(formFee);
            }
        }

        if(municipalitiesCreated.getGisrecordses() != null) {
            for(Gisrecords gisrecordse : municipalitiesCreated.getGisrecordses()) {
                gisrecordse.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child Gisrecords with information: {}", gisrecordse);
                gisrecordsService.create(gisrecordse);
            }
        }

        if(municipalitiesCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : municipalitiesCreated.getMasterFormses()) {
                masterFormse.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }

        if(municipalitiesCreated.getHolidayses() != null) {
            for(Holidays holidayse : municipalitiesCreated.getHolidayses()) {
                holidayse.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child Holidays with information: {}", holidayse);
                holidaysService.create(holidayse);
            }
        }

        if(municipalitiesCreated.getManualFeeTypeses() != null) {
            for(ManualFeeTypes manualFeeTypese : municipalitiesCreated.getManualFeeTypeses()) {
                manualFeeTypese.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child ManualFeeTypes with information: {}", manualFeeTypese);
                manualFeeTypesService.create(manualFeeTypese);
            }
        }

        if(municipalitiesCreated.getMunicipalityGroupses() != null) {
            for(MunicipalityGroups municipalityGroupse : municipalitiesCreated.getMunicipalityGroupses()) {
                municipalityGroupse.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child MunicipalityGroups with information: {}", municipalityGroupse);
                municipalityGroupsService.create(municipalityGroupse);
            }
        }

        if(municipalitiesCreated.getRoleses() != null) {
            for(Roles rolese : municipalitiesCreated.getRoleses()) {
                rolese.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child Roles with information: {}", rolese);
                rolesService.create(rolese);
            }
        }

        if(municipalitiesCreated.getSubdivisionses() != null) {
            for(Subdivisions subdivisionse : municipalitiesCreated.getSubdivisionses()) {
                subdivisionse.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child Subdivisions with information: {}", subdivisionse);
                subdivisionsService.create(subdivisionse);
            }
        }

        if(municipalitiesCreated.getUserSubscriptionses() != null) {
            for(UserSubscriptions userSubscriptionse : municipalitiesCreated.getUserSubscriptionses()) {
                userSubscriptionse.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child UserSubscriptions with information: {}", userSubscriptionse);
                userSubscriptionsService.create(userSubscriptionse);
            }
        }

        if(municipalitiesCreated.getVendorApprovalses() != null) {
            for(VendorApprovals vendorApprovalse : municipalitiesCreated.getVendorApprovalses()) {
                vendorApprovalse.setMunicipalities(municipalitiesCreated);
                LOGGER.debug("Creating a new child VendorApprovals with information: {}", vendorApprovalse);
                vendorApprovalsService.create(vendorApprovalse);
            }
        }
        return municipalitiesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Municipalities getById(Integer municipalitiesId) throws EntityNotFoundException {
        LOGGER.debug("Finding Municipalities by id: {}", municipalitiesId);
        Municipalities municipalities = this.wmGenericDao.findById(municipalitiesId);
        if (municipalities == null){
            LOGGER.debug("No Municipalities found with id: {}", municipalitiesId);
            throw new EntityNotFoundException(String.valueOf(municipalitiesId));
        }
        return municipalities;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Municipalities findById(Integer municipalitiesId) {
        LOGGER.debug("Finding Municipalities by id: {}", municipalitiesId);
        return this.wmGenericDao.findById(municipalitiesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Municipalities update(Municipalities municipalities) throws EntityNotFoundException {
        LOGGER.debug("Updating Municipalities with information: {}", municipalities);
        this.wmGenericDao.update(municipalities);

        Integer municipalitiesId = municipalities.getId();

        return this.wmGenericDao.findById(municipalitiesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Municipalities delete(Integer municipalitiesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Municipalities with id: {}", municipalitiesId);
        Municipalities deleted = this.wmGenericDao.findById(municipalitiesId);
        if (deleted == null) {
            LOGGER.debug("No Municipalities found with id: {}", municipalitiesId);
            throw new EntityNotFoundException(String.valueOf(municipalitiesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Municipalities> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Municipalities");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Municipalities> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Municipalities");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Municipalities to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormCategories> findAssociatedFormCategorieses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formCategorieses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return formCategoriesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormFee> findAssociatedFormFees(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formFees");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return formFeeService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Gisrecords> findAssociatedGisrecordses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated gisrecordses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return gisrecordsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Holidays> findAssociatedHolidayses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated holidayses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return holidaysService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ManualFeeTypes> findAssociatedManualFeeTypeses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated manualFeeTypeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return manualFeeTypesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MunicipalityGroups> findAssociatedMunicipalityGroupses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalityGroupses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return municipalityGroupsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Roles> findAssociatedRoleses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated roleses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return rolesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Subdivisions> findAssociatedSubdivisionses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated subdivisionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return subdivisionsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserSubscriptions> findAssociatedUserSubscriptionses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userSubscriptionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return userSubscriptionsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorApprovals> findAssociatedVendorApprovalses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorApprovalses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalities.id = '" + id + "'");

        return vendorApprovalsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorApprovalsService instance
	 */
	protected void setVendorApprovalsService(VendorApprovalsService service) {
        this.vendorApprovalsService = service;
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
	 * @param service ManualFeeTypesService instance
	 */
	protected void setManualFeeTypesService(ManualFeeTypesService service) {
        this.manualFeeTypesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SubdivisionsService instance
	 */
	protected void setSubdivisionsService(SubdivisionsService service) {
        this.subdivisionsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MunicipalityGroupsService instance
	 */
	protected void setMunicipalityGroupsService(MunicipalityGroupsService service) {
        this.municipalityGroupsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormCategoriesService instance
	 */
	protected void setFormCategoriesService(FormCategoriesService service) {
        this.formCategoriesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service RolesService instance
	 */
	protected void setRolesService(RolesService service) {
        this.rolesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service HolidaysService instance
	 */
	protected void setHolidaysService(HolidaysService service) {
        this.holidaysService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormFeeService instance
	 */
	protected void setFormFeeService(FormFeeService service) {
        this.formFeeService = service;
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
	 * @param service UserSubscriptionsService instance
	 */
	protected void setUserSubscriptionsService(UserSubscriptionsService service) {
        this.userSubscriptionsService = service;
    }

}


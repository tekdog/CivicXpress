/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

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
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Vendor;
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.VendorApprovals;
import com.civicxpress.cx2.VendorLicenses;
import com.civicxpress.cx2.VendorUsers;
import com.civicxpress.cx2.Vendors2form;
import com.civicxpress.cx2.VendorsToProject;


/**
 * ServiceImpl object for domain model class Vendor.
 *
 * @see Vendor
 */
@Service("cx2.VendorService")
public class VendorServiceImpl implements VendorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorServiceImpl.class);

    @Autowired
	@Qualifier("cx2.VendorApprovalsService")
	private VendorApprovalsService vendorApprovalsService;

    @Autowired
	@Qualifier("cx2.ProjectsService")
	private ProjectsService projectsService;

    @Autowired
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
	@Qualifier("cx2.FeesService")
	private FeesService feesService;

    @Autowired
	@Qualifier("cx2.VendorLicensesService")
	private VendorLicensesService vendorLicensesService;

    @Autowired
	@Qualifier("cx2.VendorsToProjectService")
	private VendorsToProjectService vendorsToProjectService;

    @Autowired
	@Qualifier("cx2.VendorUsersService")
	private VendorUsersService vendorUsersService;

    @Autowired
	@Qualifier("cx2.Vendors2formService")
	private Vendors2formService vendors2formService;

    @Autowired
	@Qualifier("cx2.VendorAdminsService")
	private VendorAdminsService vendorAdminsService;

    @Autowired
    @Qualifier("cx2.VendorDao")
    private WMGenericDao<Vendor, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Vendor, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Vendor create(Vendor vendor) {
        LOGGER.debug("Creating a new Vendor with information: {}", vendor);
        Vendor vendorCreated = this.wmGenericDao.create(vendor);
        if(vendorCreated.getFeeses() != null) {
            for(Fees feese : vendorCreated.getFeeses()) {
                feese.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child Fees with information: {}", feese);
                feesService.create(feese);
            }
        }

        if(vendorCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : vendorCreated.getMasterFormses()) {
                masterFormse.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }

        if(vendorCreated.getProjectses() != null) {
            for(Projects projectse : vendorCreated.getProjectses()) {
                projectse.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child Projects with information: {}", projectse);
                projectsService.create(projectse);
            }
        }

        if(vendorCreated.getVendorApprovalses() != null) {
            for(VendorApprovals vendorApprovalse : vendorCreated.getVendorApprovalses()) {
                vendorApprovalse.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child VendorApprovals with information: {}", vendorApprovalse);
                vendorApprovalsService.create(vendorApprovalse);
            }
        }

        if(vendorCreated.getVendorLicenseses() != null) {
            for(VendorLicenses vendorLicensese : vendorCreated.getVendorLicenseses()) {
                vendorLicensese.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child VendorLicenses with information: {}", vendorLicensese);
                vendorLicensesService.create(vendorLicensese);
            }
        }

        if(vendorCreated.getVendorAdminses() != null) {
            for(VendorAdmins vendorAdminse : vendorCreated.getVendorAdminses()) {
                vendorAdminse.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child VendorAdmins with information: {}", vendorAdminse);
                vendorAdminsService.create(vendorAdminse);
            }
        }

        if(vendorCreated.getVendors2forms() != null) {
            for(Vendors2form vendors2form : vendorCreated.getVendors2forms()) {
                vendors2form.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child Vendors2form with information: {}", vendors2form);
                vendors2formService.create(vendors2form);
            }
        }

        if(vendorCreated.getVendorsToProjects() != null) {
            for(VendorsToProject vendorsToProject : vendorCreated.getVendorsToProjects()) {
                vendorsToProject.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child VendorsToProject with information: {}", vendorsToProject);
                vendorsToProjectService.create(vendorsToProject);
            }
        }

        if(vendorCreated.getVendorUserses() != null) {
            for(VendorUsers vendorUserse : vendorCreated.getVendorUserses()) {
                vendorUserse.setVendor(vendorCreated);
                LOGGER.debug("Creating a new child VendorUsers with information: {}", vendorUserse);
                vendorUsersService.create(vendorUserse);
            }
        }
        return vendorCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Vendor getById(Integer vendorId) throws EntityNotFoundException {
        LOGGER.debug("Finding Vendor by id: {}", vendorId);
        Vendor vendor = this.wmGenericDao.findById(vendorId);
        if (vendor == null){
            LOGGER.debug("No Vendor found with id: {}", vendorId);
            throw new EntityNotFoundException(String.valueOf(vendorId));
        }
        return vendor;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Vendor findById(Integer vendorId) {
        LOGGER.debug("Finding Vendor by id: {}", vendorId);
        return this.wmGenericDao.findById(vendorId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Vendor getByFeinNumber(String feinNumber) {
        Map<String, Object> feinNumberMap = new HashMap<>();
        feinNumberMap.put("feinNumber", feinNumber);

        LOGGER.debug("Finding Vendor by unique keys: {}", feinNumberMap);
        Vendor vendor = this.wmGenericDao.findByUniqueKey(feinNumberMap);

        if (vendor == null){
            LOGGER.debug("No Vendor found with given unique key values: {}", feinNumberMap);
            throw new EntityNotFoundException(String.valueOf(feinNumberMap));
        }

        return vendor;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Vendor update(Vendor vendor) throws EntityNotFoundException {
        LOGGER.debug("Updating Vendor with information: {}", vendor);
        this.wmGenericDao.update(vendor);

        Integer vendorId = vendor.getId();

        return this.wmGenericDao.findById(vendorId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Vendor delete(Integer vendorId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Vendor with id: {}", vendorId);
        Vendor deleted = this.wmGenericDao.findById(vendorId);
        if (deleted == null) {
            LOGGER.debug("No Vendor found with id: {}", vendorId);
            throw new EntityNotFoundException(String.valueOf(vendorId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Vendor> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Vendors");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Vendor> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Vendors");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Vendor to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Fees> findAssociatedFeeses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated feeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return feesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Projects> findAssociatedProjectses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return projectsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorApprovals> findAssociatedVendorApprovalses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorApprovalses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return vendorApprovalsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorLicenses> findAssociatedVendorLicenseses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorLicenseses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return vendorLicensesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorAdmins> findAssociatedVendorAdminses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorAdminses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return vendorAdminsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Vendors2form> findAssociatedVendors2forms(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendors2forms");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return vendors2formService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorsToProject> findAssociatedVendorsToProjects(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorsToProjects");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return vendorsToProjectService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorUsers> findAssociatedVendorUserses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorUserses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("vendor.id = '" + id + "'");

        return vendorUsersService.findAll(queryBuilder.toString(), pageable);
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
	 * @param service ProjectsService instance
	 */
	protected void setProjectsService(ProjectsService service) {
        this.projectsService = service;
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
	 * @param service FeesService instance
	 */
	protected void setFeesService(FeesService service) {
        this.feesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorLicensesService instance
	 */
	protected void setVendorLicensesService(VendorLicensesService service) {
        this.vendorLicensesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorsToProjectService instance
	 */
	protected void setVendorsToProjectService(VendorsToProjectService service) {
        this.vendorsToProjectService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorUsersService instance
	 */
	protected void setVendorUsersService(VendorUsersService service) {
        this.vendorUsersService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Vendors2formService instance
	 */
	protected void setVendors2formService(Vendors2formService service) {
        this.vendors2formService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorAdminsService instance
	 */
	protected void setVendorAdminsService(VendorAdminsService service) {
        this.vendorAdminsService = service;
    }

}


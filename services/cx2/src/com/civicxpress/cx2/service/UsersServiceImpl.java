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
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.FormFee;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.McnewElectricConnection;
import com.civicxpress.cx2.McnewResidentialStructure;
import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.Pudapplication;
import com.civicxpress.cx2.Roles;
import com.civicxpress.cx2.SfnewElectricConnection;
import com.civicxpress.cx2.SfnewResidentialStructure;
import com.civicxpress.cx2.UserPasswordResetTokens;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.VendorUsers;


/**
 * ServiceImpl object for domain model class Users.
 *
 * @see Users
 */
@Service("cx2.UsersService")
public class UsersServiceImpl implements UsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
	@Qualifier("cx2.UserSubscriptionsService")
	private UserSubscriptionsService userSubscriptionsService;

    @Autowired
	@Qualifier("cx2.VendorAdminsService")
	private VendorAdminsService vendorAdminsService;

    @Autowired
	@Qualifier("cx2.UserPasswordResetTokensService")
	private UserPasswordResetTokensService userPasswordResetTokensService;

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
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
	@Qualifier("cx2.SfnewElectricConnectionService")
	private SfnewElectricConnectionService sfnewElectricConnectionService;

    @Autowired
	@Qualifier("cx2.FormFeeService")
	private FormFeeService formFeeService;

    @Autowired
	@Qualifier("cx2.MunicipalityGroupMembersService")
	private MunicipalityGroupMembersService municipalityGroupMembersService;

    @Autowired
	@Qualifier("cx2.McnewResidentialStructureService")
	private McnewResidentialStructureService mcnewResidentialStructureService;

    @Autowired
	@Qualifier("cx2.RolesService")
	private RolesService rolesService;

    @Autowired
	@Qualifier("cx2.VendorUsersService")
	private VendorUsersService vendorUsersService;

    @Autowired
    @Qualifier("cx2.UsersDao")
    private WMGenericDao<Users, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Users, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Users create(Users users) {
        LOGGER.debug("Creating a new Users with information: {}", users);
        Users usersCreated = this.wmGenericDao.create(users);
        if(usersCreated.getSfnewElectricConnections() != null) {
            for(SfnewElectricConnection sfnewElectricConnection : usersCreated.getSfnewElectricConnections()) {
                sfnewElectricConnection.setUsers(usersCreated);
                LOGGER.debug("Creating a new child SfnewElectricConnection with information: {}", sfnewElectricConnection);
                sfnewElectricConnectionService.create(sfnewElectricConnection);
            }
        }

        if(usersCreated.getSfnewResidentialStructures() != null) {
            for(SfnewResidentialStructure sfnewResidentialStructure : usersCreated.getSfnewResidentialStructures()) {
                sfnewResidentialStructure.setUsers(usersCreated);
                LOGGER.debug("Creating a new child SfnewResidentialStructure with information: {}", sfnewResidentialStructure);
                sfnewResidentialStructureService.create(sfnewResidentialStructure);
            }
        }

        if(usersCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : usersCreated.getMasterFormses()) {
                masterFormse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }

        if(usersCreated.getVendorUserses() != null) {
            for(VendorUsers vendorUserse : usersCreated.getVendorUserses()) {
                vendorUserse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child VendorUsers with information: {}", vendorUserse);
                vendorUsersService.create(vendorUserse);
            }
        }

        if(usersCreated.getRoleses() != null) {
            for(Roles rolese : usersCreated.getRoleses()) {
                rolese.setUsers(usersCreated);
                LOGGER.debug("Creating a new child Roles with information: {}", rolese);
                rolesService.create(rolese);
            }
        }

        if(usersCreated.getFormFees() != null) {
            for(FormFee formFee : usersCreated.getFormFees()) {
                formFee.setUsers(usersCreated);
                LOGGER.debug("Creating a new child FormFee with information: {}", formFee);
                formFeeService.create(formFee);
            }
        }

        if(usersCreated.getMcnewElectricConnections() != null) {
            for(McnewElectricConnection mcnewElectricConnection : usersCreated.getMcnewElectricConnections()) {
                mcnewElectricConnection.setUsers(usersCreated);
                LOGGER.debug("Creating a new child McnewElectricConnection with information: {}", mcnewElectricConnection);
                mcnewElectricConnectionService.create(mcnewElectricConnection);
            }
        }

        if(usersCreated.getMunicipalityGroupMemberses() != null) {
            for(MunicipalityGroupMembers municipalityGroupMemberse : usersCreated.getMunicipalityGroupMemberses()) {
                municipalityGroupMemberse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child MunicipalityGroupMembers with information: {}", municipalityGroupMemberse);
                municipalityGroupMembersService.create(municipalityGroupMemberse);
            }
        }

        if(usersCreated.getUserPasswordResetTokenses() != null) {
            for(UserPasswordResetTokens userPasswordResetTokense : usersCreated.getUserPasswordResetTokenses()) {
                userPasswordResetTokense.setUsers(usersCreated);
                LOGGER.debug("Creating a new child UserPasswordResetTokens with information: {}", userPasswordResetTokense);
                userPasswordResetTokensService.create(userPasswordResetTokense);
            }
        }

        if(usersCreated.getMcnewResidentialStructures() != null) {
            for(McnewResidentialStructure mcnewResidentialStructure : usersCreated.getMcnewResidentialStructures()) {
                mcnewResidentialStructure.setUsers(usersCreated);
                LOGGER.debug("Creating a new child McnewResidentialStructure with information: {}", mcnewResidentialStructure);
                mcnewResidentialStructureService.create(mcnewResidentialStructure);
            }
        }

        if(usersCreated.getVendorAdminses() != null) {
            for(VendorAdmins vendorAdminse : usersCreated.getVendorAdminses()) {
                vendorAdminse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child VendorAdmins with information: {}", vendorAdminse);
                vendorAdminsService.create(vendorAdminse);
            }
        }

        if(usersCreated.getPudapplications() != null) {
            for(Pudapplication pudapplication : usersCreated.getPudapplications()) {
                pudapplication.setUsers(usersCreated);
                LOGGER.debug("Creating a new child Pudapplication with information: {}", pudapplication);
                pudapplicationService.create(pudapplication);
            }
        }

        if(usersCreated.getUserSubscriptionses() != null) {
            for(UserSubscriptions userSubscriptionse : usersCreated.getUserSubscriptionses()) {
                userSubscriptionse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child UserSubscriptions with information: {}", userSubscriptionse);
                userSubscriptionsService.create(userSubscriptionse);
            }
        }
        return usersCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Users getById(Integer usersId) throws EntityNotFoundException {
        LOGGER.debug("Finding Users by id: {}", usersId);
        Users users = this.wmGenericDao.findById(usersId);
        if (users == null){
            LOGGER.debug("No Users found with id: {}", usersId);
            throw new EntityNotFoundException(String.valueOf(usersId));
        }
        return users;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Users findById(Integer usersId) {
        LOGGER.debug("Finding Users by id: {}", usersId);
        return this.wmGenericDao.findById(usersId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Users getByEmail(String email) {
        Map<String, Object> emailMap = new HashMap<>();
        emailMap.put("email", email);

        LOGGER.debug("Finding Users by unique keys: {}", emailMap);
        Users users = this.wmGenericDao.findByUniqueKey(emailMap);

        if (users == null){
            LOGGER.debug("No Users found with given unique key values: {}", emailMap);
            throw new EntityNotFoundException(String.valueOf(emailMap));
        }

        return users;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Users update(Users users) throws EntityNotFoundException {
        LOGGER.debug("Updating Users with information: {}", users);
        this.wmGenericDao.update(users);

        Integer usersId = users.getId();

        return this.wmGenericDao.findById(usersId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Users delete(Integer usersId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Users with id: {}", usersId);
        Users deleted = this.wmGenericDao.findById(usersId);
        if (deleted == null) {
            LOGGER.debug("No Users found with id: {}", usersId);
            throw new EntityNotFoundException(String.valueOf(usersId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Users> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Users> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Users to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SfnewElectricConnection> findAssociatedSfnewElectricConnections(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sfnewElectricConnections");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return sfnewElectricConnectionService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SfnewResidentialStructure> findAssociatedSfnewResidentialStructures(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sfnewResidentialStructures");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return sfnewResidentialStructureService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorUsers> findAssociatedVendorUserses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorUserses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return vendorUsersService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Roles> findAssociatedRoleses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated roleses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return rolesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormFee> findAssociatedFormFees(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formFees");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return formFeeService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<McnewElectricConnection> findAssociatedMcnewElectricConnections(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated mcnewElectricConnections");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return mcnewElectricConnectionService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MunicipalityGroupMembers> findAssociatedMunicipalityGroupMemberses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalityGroupMemberses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return municipalityGroupMembersService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserPasswordResetTokens> findAssociatedUserPasswordResetTokenses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userPasswordResetTokenses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return userPasswordResetTokensService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<McnewResidentialStructure> findAssociatedMcnewResidentialStructures(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated mcnewResidentialStructures");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return mcnewResidentialStructureService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorAdmins> findAssociatedVendorAdminses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorAdminses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return vendorAdminsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Pudapplication> findAssociatedPudapplications(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated pudapplications");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return pudapplicationService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserSubscriptions> findAssociatedUserSubscriptionses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userSubscriptionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return userSubscriptionsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserSubscriptionsService instance
	 */
	protected void setUserSubscriptionsService(UserSubscriptionsService service) {
        this.userSubscriptionsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorAdminsService instance
	 */
	protected void setVendorAdminsService(VendorAdminsService service) {
        this.vendorAdminsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserPasswordResetTokensService instance
	 */
	protected void setUserPasswordResetTokensService(UserPasswordResetTokensService service) {
        this.userPasswordResetTokensService = service;
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
	 * @param service MasterFormsService instance
	 */
	protected void setMasterFormsService(MasterFormsService service) {
        this.masterFormsService = service;
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
	 * @param service FormFeeService instance
	 */
	protected void setFormFeeService(FormFeeService service) {
        this.formFeeService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MunicipalityGroupMembersService instance
	 */
	protected void setMunicipalityGroupMembersService(MunicipalityGroupMembersService service) {
        this.municipalityGroupMembersService = service;
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
	 * @param service RolesService instance
	 */
	protected void setRolesService(RolesService service) {
        this.rolesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorUsersService instance
	 */
	protected void setVendorUsersService(VendorUsersService service) {
        this.vendorUsersService = service;
    }

}


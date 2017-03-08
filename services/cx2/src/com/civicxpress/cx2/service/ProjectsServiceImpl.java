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
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.ProjectForms;
import com.civicxpress.cx2.ProjectGisrecords;
import com.civicxpress.cx2.ProjectSharedWith;
import com.civicxpress.cx2.ProjectTasks;
import com.civicxpress.cx2.Projects;


/**
 * ServiceImpl object for domain model class Projects.
 *
 * @see Projects
 */
@Service("cx2.ProjectsService")
public class ProjectsServiceImpl implements ProjectsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.ProjectTasksService")
	private ProjectTasksService projectTasksService;

    @Autowired
	@Qualifier("cx2.ProjectFormsService")
	private ProjectFormsService projectFormsService;

    @Autowired
	@Qualifier("cx2.FeesService")
	private FeesService feesService;

    @Autowired
	@Qualifier("cx2.FormMessagesService")
	private FormMessagesService formMessagesService;

    @Autowired
	@Qualifier("cx2.MasterInspectionsService")
	private MasterInspectionsService masterInspectionsService;

    @Autowired
	@Qualifier("cx2.ProjectSharedWithService")
	private ProjectSharedWithService projectSharedWithService;

    @Autowired
	@Qualifier("cx2.ProjectGisrecordsService")
	private ProjectGisrecordsService projectGisrecordsService;

    @Autowired
    @Qualifier("cx2.ProjectsDao")
    private WMGenericDao<Projects, String> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Projects, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Projects create(Projects projects) {
        LOGGER.debug("Creating a new Projects with information: {}", projects);
        Projects projectsCreated = this.wmGenericDao.create(projects);
        if(projectsCreated.getFeeses() != null) {
            for(Fees feese : projectsCreated.getFeeses()) {
                feese.setProjects(projectsCreated);
                LOGGER.debug("Creating a new child Fees with information: {}", feese);
                feesService.create(feese);
            }
        }

        if(projectsCreated.getFormMessageses() != null) {
            for(FormMessages formMessagese : projectsCreated.getFormMessageses()) {
                formMessagese.setProjects(projectsCreated);
                LOGGER.debug("Creating a new child FormMessages with information: {}", formMessagese);
                formMessagesService.create(formMessagese);
            }
        }

        if(projectsCreated.getMasterInspectionses() != null) {
            for(MasterInspections masterInspectionse : projectsCreated.getMasterInspectionses()) {
                masterInspectionse.setProjects(projectsCreated);
                LOGGER.debug("Creating a new child MasterInspections with information: {}", masterInspectionse);
                masterInspectionsService.create(masterInspectionse);
            }
        }

        if(projectsCreated.getProjectFormses() != null) {
            for(ProjectForms projectFormse : projectsCreated.getProjectFormses()) {
                projectFormse.setProjects(projectsCreated);
                LOGGER.debug("Creating a new child ProjectForms with information: {}", projectFormse);
                projectFormsService.create(projectFormse);
            }
        }

        if(projectsCreated.getProjectGisrecordses() != null) {
            for(ProjectGisrecords projectGisrecordse : projectsCreated.getProjectGisrecordses()) {
                projectGisrecordse.setProjects(projectsCreated);
                LOGGER.debug("Creating a new child ProjectGisrecords with information: {}", projectGisrecordse);
                projectGisrecordsService.create(projectGisrecordse);
            }
        }

        if(projectsCreated.getProjectSharedWiths() != null) {
            for(ProjectSharedWith projectSharedWith : projectsCreated.getProjectSharedWiths()) {
                projectSharedWith.setProjects(projectsCreated);
                LOGGER.debug("Creating a new child ProjectSharedWith with information: {}", projectSharedWith);
                projectSharedWithService.create(projectSharedWith);
            }
        }

        if(projectsCreated.getProjectTaskses() != null) {
            for(ProjectTasks projectTaskse : projectsCreated.getProjectTaskses()) {
                projectTaskse.setProjects(projectsCreated);
                LOGGER.debug("Creating a new child ProjectTasks with information: {}", projectTaskse);
                projectTasksService.create(projectTaskse);
            }
        }
        return projectsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Projects getById(String projectsId) throws EntityNotFoundException {
        LOGGER.debug("Finding Projects by id: {}", projectsId);
        Projects projects = this.wmGenericDao.findById(projectsId);
        if (projects == null){
            LOGGER.debug("No Projects found with id: {}", projectsId);
            throw new EntityNotFoundException(String.valueOf(projectsId));
        }
        return projects;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Projects findById(String projectsId) {
        LOGGER.debug("Finding Projects by id: {}", projectsId);
        return this.wmGenericDao.findById(projectsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Projects update(Projects projects) throws EntityNotFoundException {
        LOGGER.debug("Updating Projects with information: {}", projects);
        this.wmGenericDao.update(projects);

        String projectsId = projects.getProjectGuid();

        return this.wmGenericDao.findById(projectsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Projects delete(String projectsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Projects with id: {}", projectsId);
        Projects deleted = this.wmGenericDao.findById(projectsId);
        if (deleted == null) {
            LOGGER.debug("No Projects found with id: {}", projectsId);
            throw new EntityNotFoundException(String.valueOf(projectsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Projects> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Projects");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Projects> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Projects");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Projects to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Fees> findAssociatedFeeses(String projectGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated feeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projects.projectGuid = '" + projectGuid + "'");

        return feesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormMessages> findAssociatedFormMessageses(String projectGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projects.projectGuid = '" + projectGuid + "'");

        return formMessagesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterInspections> findAssociatedMasterInspectionses(String projectGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterInspectionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projects.projectGuid = '" + projectGuid + "'");

        return masterInspectionsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectForms> findAssociatedProjectFormses(String projectGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projects.projectGuid = '" + projectGuid + "'");

        return projectFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectGisrecords> findAssociatedProjectGisrecordses(String projectGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectGisrecordses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projects.projectGuid = '" + projectGuid + "'");

        return projectGisrecordsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectSharedWith> findAssociatedProjectSharedWiths(String projectGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectSharedWiths");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projects.projectGuid = '" + projectGuid + "'");

        return projectSharedWithService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectTasks> findAssociatedProjectTaskses(String projectGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectTaskses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projects.projectGuid = '" + projectGuid + "'");

        return projectTasksService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectTasksService instance
	 */
	protected void setProjectTasksService(ProjectTasksService service) {
        this.projectTasksService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectFormsService instance
	 */
	protected void setProjectFormsService(ProjectFormsService service) {
        this.projectFormsService = service;
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
	 * @param service FormMessagesService instance
	 */
	protected void setFormMessagesService(FormMessagesService service) {
        this.formMessagesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterInspectionsService instance
	 */
	protected void setMasterInspectionsService(MasterInspectionsService service) {
        this.masterInspectionsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectSharedWithService instance
	 */
	protected void setProjectSharedWithService(ProjectSharedWithService service) {
        this.projectSharedWithService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectGisrecordsService instance
	 */
	protected void setProjectGisrecordsService(ProjectGisrecordsService service) {
        this.projectGisrecordsService = service;
    }

}


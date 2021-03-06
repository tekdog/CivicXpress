/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

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

import com.civicxpress.cx2.ProjectTasks;


/**
 * ServiceImpl object for domain model class ProjectTasks.
 *
 * @see ProjectTasks
 */
@Service("cx2.ProjectTasksService")
public class ProjectTasksServiceImpl implements ProjectTasksService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectTasksServiceImpl.class);


    @Autowired
    @Qualifier("cx2.ProjectTasksDao")
    private WMGenericDao<ProjectTasks, String> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<ProjectTasks, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public ProjectTasks create(ProjectTasks projectTasks) {
        LOGGER.debug("Creating a new ProjectTasks with information: {}", projectTasks);
        ProjectTasks projectTasksCreated = this.wmGenericDao.create(projectTasks);
        if(projectTasksCreated.getProjectTasksesForPredecessor() != null) {
            for(ProjectTasks projectTasksesForPredecessor : projectTasksCreated.getProjectTasksesForPredecessor()) {
                projectTasksesForPredecessor.setProjectTasksByPredecessor(projectTasksCreated);
                LOGGER.debug("Creating a new child ProjectTasks with information: {}", projectTasksesForPredecessor);
                create(projectTasksesForPredecessor);
            }
        }
        return projectTasksCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public ProjectTasks getById(String projecttasksId) throws EntityNotFoundException {
        LOGGER.debug("Finding ProjectTasks by id: {}", projecttasksId);
        ProjectTasks projectTasks = this.wmGenericDao.findById(projecttasksId);
        if (projectTasks == null){
            LOGGER.debug("No ProjectTasks found with id: {}", projecttasksId);
            throw new EntityNotFoundException(String.valueOf(projecttasksId));
        }
        return projectTasks;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public ProjectTasks findById(String projecttasksId) {
        LOGGER.debug("Finding ProjectTasks by id: {}", projecttasksId);
        return this.wmGenericDao.findById(projecttasksId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public ProjectTasks update(ProjectTasks projectTasks) throws EntityNotFoundException {
        LOGGER.debug("Updating ProjectTasks with information: {}", projectTasks);
        this.wmGenericDao.update(projectTasks);

        String projecttasksId = projectTasks.getPmid();

        return this.wmGenericDao.findById(projecttasksId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public ProjectTasks delete(String projecttasksId) throws EntityNotFoundException {
        LOGGER.debug("Deleting ProjectTasks with id: {}", projecttasksId);
        ProjectTasks deleted = this.wmGenericDao.findById(projecttasksId);
        if (deleted == null) {
            LOGGER.debug("No ProjectTasks found with id: {}", projecttasksId);
            throw new EntityNotFoundException(String.valueOf(projecttasksId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<ProjectTasks> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all ProjectTasks");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectTasks> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all ProjectTasks");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table ProjectTasks to {} format", exportType);
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
    public Page<ProjectTasks> findAssociatedProjectTasksesForPredecessor(String pmid, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectTasksesForPredecessor");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("projectTasksByPredecessor.pmid = '" + pmid + "'");

        return findAll(queryBuilder.toString(), pageable);
    }


}


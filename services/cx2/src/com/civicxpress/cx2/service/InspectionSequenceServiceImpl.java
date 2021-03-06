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

import com.civicxpress.cx2.InspectionSequence;


/**
 * ServiceImpl object for domain model class InspectionSequence.
 *
 * @see InspectionSequence
 */
@Service("cx2.InspectionSequenceService")
public class InspectionSequenceServiceImpl implements InspectionSequenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionSequenceServiceImpl.class);


    @Autowired
    @Qualifier("cx2.InspectionSequenceDao")
    private WMGenericDao<InspectionSequence, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<InspectionSequence, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public InspectionSequence create(InspectionSequence inspectionSequence) {
        LOGGER.debug("Creating a new InspectionSequence with information: {}", inspectionSequence);
        InspectionSequence inspectionSequenceCreated = this.wmGenericDao.create(inspectionSequence);
        return inspectionSequenceCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public InspectionSequence getById(Integer inspectionsequenceId) throws EntityNotFoundException {
        LOGGER.debug("Finding InspectionSequence by id: {}", inspectionsequenceId);
        InspectionSequence inspectionSequence = this.wmGenericDao.findById(inspectionsequenceId);
        if (inspectionSequence == null){
            LOGGER.debug("No InspectionSequence found with id: {}", inspectionsequenceId);
            throw new EntityNotFoundException(String.valueOf(inspectionsequenceId));
        }
        return inspectionSequence;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public InspectionSequence findById(Integer inspectionsequenceId) {
        LOGGER.debug("Finding InspectionSequence by id: {}", inspectionsequenceId);
        return this.wmGenericDao.findById(inspectionsequenceId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public InspectionSequence getByFormTypeIdAndSequenceOrderNumber(Integer formTypeId, Integer sequenceOrderNumber) {
        Map<String, Object> formTypeIdAndSequenceOrderNumberMap = new HashMap<>();
        formTypeIdAndSequenceOrderNumberMap.put("formTypeId", formTypeId);
        formTypeIdAndSequenceOrderNumberMap.put("sequenceOrderNumber", sequenceOrderNumber);

        LOGGER.debug("Finding InspectionSequence by unique keys: {}", formTypeIdAndSequenceOrderNumberMap);
        InspectionSequence inspectionSequence = this.wmGenericDao.findByUniqueKey(formTypeIdAndSequenceOrderNumberMap);

        if (inspectionSequence == null){
            LOGGER.debug("No InspectionSequence found with given unique key values: {}", formTypeIdAndSequenceOrderNumberMap);
            throw new EntityNotFoundException(String.valueOf(formTypeIdAndSequenceOrderNumberMap));
        }

        return inspectionSequence;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public InspectionSequence update(InspectionSequence inspectionSequence) throws EntityNotFoundException {
        LOGGER.debug("Updating InspectionSequence with information: {}", inspectionSequence);
        this.wmGenericDao.update(inspectionSequence);

        Integer inspectionsequenceId = inspectionSequence.getId();

        return this.wmGenericDao.findById(inspectionsequenceId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public InspectionSequence delete(Integer inspectionsequenceId) throws EntityNotFoundException {
        LOGGER.debug("Deleting InspectionSequence with id: {}", inspectionsequenceId);
        InspectionSequence deleted = this.wmGenericDao.findById(inspectionsequenceId);
        if (deleted == null) {
            LOGGER.debug("No InspectionSequence found with id: {}", inspectionsequenceId);
            throw new EntityNotFoundException(String.valueOf(inspectionsequenceId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<InspectionSequence> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all InspectionSequences");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionSequence> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all InspectionSequences");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table InspectionSequence to {} format", exportType);
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



}


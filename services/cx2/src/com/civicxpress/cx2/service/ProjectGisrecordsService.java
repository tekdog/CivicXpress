/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.ProjectGisrecords;

/**
 * Service object for domain model class {@link ProjectGisrecords}.
 */
public interface ProjectGisrecordsService {

    /**
     * Creates a new ProjectGisrecords. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ProjectGisrecords if any.
     *
     * @param projectGisrecords Details of the ProjectGisrecords to be created; value cannot be null.
     * @return The newly created ProjectGisrecords.
     */
	ProjectGisrecords create(ProjectGisrecords projectGisrecords);


	/**
	 * Returns ProjectGisrecords by given id if exists.
	 *
	 * @param projectgisrecordsId The id of the ProjectGisrecords to get; value cannot be null.
	 * @return ProjectGisrecords associated with the given projectgisrecordsId.
     * @throws EntityNotFoundException If no ProjectGisrecords is found.
	 */
	ProjectGisrecords getById(Integer projectgisrecordsId) throws EntityNotFoundException;

    /**
	 * Find and return the ProjectGisrecords by given id if exists, returns null otherwise.
	 *
	 * @param projectgisrecordsId The id of the ProjectGisrecords to get; value cannot be null.
	 * @return ProjectGisrecords associated with the given projectgisrecordsId.
	 */
	ProjectGisrecords findById(Integer projectgisrecordsId);


	/**
	 * Updates the details of an existing ProjectGisrecords. It replaces all fields of the existing ProjectGisrecords with the given projectGisrecords.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on ProjectGisrecords if any.
     *
	 * @param projectGisrecords The details of the ProjectGisrecords to be updated; value cannot be null.
	 * @return The updated ProjectGisrecords.
	 * @throws EntityNotFoundException if no ProjectGisrecords is found with given input.
	 */
	ProjectGisrecords update(ProjectGisrecords projectGisrecords) throws EntityNotFoundException;

    /**
	 * Deletes an existing ProjectGisrecords with the given id.
	 *
	 * @param projectgisrecordsId The id of the ProjectGisrecords to be deleted; value cannot be null.
	 * @return The deleted ProjectGisrecords.
	 * @throws EntityNotFoundException if no ProjectGisrecords found with the given id.
	 */
	ProjectGisrecords delete(Integer projectgisrecordsId) throws EntityNotFoundException;

	/**
	 * Find all ProjectGisrecords matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ProjectGisrecords.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<ProjectGisrecords> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all ProjectGisrecords matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ProjectGisrecords.
     *
     * @see Pageable
     * @see Page
	 */
    Page<ProjectGisrecords> findAll(String query, Pageable pageable);

    /**
	 * Exports all ProjectGisrecords matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the ProjectGisrecords in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the ProjectGisrecords.
	 */
	long count(String query);


}


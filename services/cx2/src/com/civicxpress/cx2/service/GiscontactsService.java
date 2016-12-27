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

import com.civicxpress.cx2.Giscontacts;

/**
 * Service object for domain model class {@link Giscontacts}.
 */
public interface GiscontactsService {

    /**
     * Creates a new Giscontacts. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Giscontacts if any.
     *
     * @param giscontacts Details of the Giscontacts to be created; value cannot be null.
     * @return The newly created Giscontacts.
     */
	Giscontacts create(Giscontacts giscontacts);


	/**
	 * Returns Giscontacts by given id if exists.
	 *
	 * @param giscontactsId The id of the Giscontacts to get; value cannot be null.
	 * @return Giscontacts associated with the given giscontactsId.
     * @throws EntityNotFoundException If no Giscontacts is found.
	 */
	Giscontacts getById(Integer giscontactsId) throws EntityNotFoundException;

    /**
	 * Find and return the Giscontacts by given id if exists, returns null otherwise.
	 *
	 * @param giscontactsId The id of the Giscontacts to get; value cannot be null.
	 * @return Giscontacts associated with the given giscontactsId.
	 */
	Giscontacts findById(Integer giscontactsId);


	/**
	 * Updates the details of an existing Giscontacts. It replaces all fields of the existing Giscontacts with the given giscontacts.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Giscontacts if any.
     *
	 * @param giscontacts The details of the Giscontacts to be updated; value cannot be null.
	 * @return The updated Giscontacts.
	 * @throws EntityNotFoundException if no Giscontacts is found with given input.
	 */
	Giscontacts update(Giscontacts giscontacts) throws EntityNotFoundException;

    /**
	 * Deletes an existing Giscontacts with the given id.
	 *
	 * @param giscontactsId The id of the Giscontacts to be deleted; value cannot be null.
	 * @return The deleted Giscontacts.
	 * @throws EntityNotFoundException if no Giscontacts found with the given id.
	 */
	Giscontacts delete(Integer giscontactsId) throws EntityNotFoundException;

	/**
	 * Find all Giscontacts matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Giscontacts.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Giscontacts> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Giscontacts matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Giscontacts.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Giscontacts> findAll(String query, Pageable pageable);

    /**
	 * Exports all Giscontacts matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Giscontacts in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Giscontacts.
	 */
	long count(String query);


}


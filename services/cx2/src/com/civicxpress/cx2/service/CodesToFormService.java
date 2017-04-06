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

import com.civicxpress.cx2.CodesToForm;

/**
 * Service object for domain model class {@link CodesToForm}.
 */
public interface CodesToFormService {

    /**
     * Creates a new CodesToForm. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CodesToForm if any.
     *
     * @param codesToForm Details of the CodesToForm to be created; value cannot be null.
     * @return The newly created CodesToForm.
     */
	CodesToForm create(CodesToForm codesToForm);


	/**
	 * Returns CodesToForm by given id if exists.
	 *
	 * @param codestoformId The id of the CodesToForm to get; value cannot be null.
	 * @return CodesToForm associated with the given codestoformId.
     * @throws EntityNotFoundException If no CodesToForm is found.
	 */
	CodesToForm getById(Integer codestoformId) throws EntityNotFoundException;

    /**
	 * Find and return the CodesToForm by given id if exists, returns null otherwise.
	 *
	 * @param codestoformId The id of the CodesToForm to get; value cannot be null.
	 * @return CodesToForm associated with the given codestoformId.
	 */
	CodesToForm findById(Integer codestoformId);


	/**
	 * Updates the details of an existing CodesToForm. It replaces all fields of the existing CodesToForm with the given codesToForm.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on CodesToForm if any.
     *
	 * @param codesToForm The details of the CodesToForm to be updated; value cannot be null.
	 * @return The updated CodesToForm.
	 * @throws EntityNotFoundException if no CodesToForm is found with given input.
	 */
	CodesToForm update(CodesToForm codesToForm) throws EntityNotFoundException;

    /**
	 * Deletes an existing CodesToForm with the given id.
	 *
	 * @param codestoformId The id of the CodesToForm to be deleted; value cannot be null.
	 * @return The deleted CodesToForm.
	 * @throws EntityNotFoundException if no CodesToForm found with the given id.
	 */
	CodesToForm delete(Integer codestoformId) throws EntityNotFoundException;

	/**
	 * Find all CodesToForms matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CodesToForms.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<CodesToForm> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all CodesToForms matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CodesToForms.
     *
     * @see Pageable
     * @see Page
	 */
    Page<CodesToForm> findAll(String query, Pageable pageable);

    /**
	 * Exports all CodesToForms matching the given input query to the given exportType format.
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
	 * Retrieve the count of the CodesToForms in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the CodesToForm.
	 */
	long count(String query);


}

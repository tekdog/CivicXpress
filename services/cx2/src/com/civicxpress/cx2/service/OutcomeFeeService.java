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

import com.civicxpress.cx2.OutcomeFee;

/**
 * Service object for domain model class {@link OutcomeFee}.
 */
public interface OutcomeFeeService {

    /**
     * Creates a new OutcomeFee. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on OutcomeFee if any.
     *
     * @param outcomeFee Details of the OutcomeFee to be created; value cannot be null.
     * @return The newly created OutcomeFee.
     */
	OutcomeFee create(OutcomeFee outcomeFee);


	/**
	 * Returns OutcomeFee by given id if exists.
	 *
	 * @param outcomefeeId The id of the OutcomeFee to get; value cannot be null.
	 * @return OutcomeFee associated with the given outcomefeeId.
     * @throws EntityNotFoundException If no OutcomeFee is found.
	 */
	OutcomeFee getById(Integer outcomefeeId) throws EntityNotFoundException;

    /**
	 * Find and return the OutcomeFee by given id if exists, returns null otherwise.
	 *
	 * @param outcomefeeId The id of the OutcomeFee to get; value cannot be null.
	 * @return OutcomeFee associated with the given outcomefeeId.
	 */
	OutcomeFee findById(Integer outcomefeeId);


	/**
	 * Updates the details of an existing OutcomeFee. It replaces all fields of the existing OutcomeFee with the given outcomeFee.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on OutcomeFee if any.
     *
	 * @param outcomeFee The details of the OutcomeFee to be updated; value cannot be null.
	 * @return The updated OutcomeFee.
	 * @throws EntityNotFoundException if no OutcomeFee is found with given input.
	 */
	OutcomeFee update(OutcomeFee outcomeFee) throws EntityNotFoundException;

    /**
	 * Deletes an existing OutcomeFee with the given id.
	 *
	 * @param outcomefeeId The id of the OutcomeFee to be deleted; value cannot be null.
	 * @return The deleted OutcomeFee.
	 * @throws EntityNotFoundException if no OutcomeFee found with the given id.
	 */
	OutcomeFee delete(Integer outcomefeeId) throws EntityNotFoundException;

	/**
	 * Find all OutcomeFees matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching OutcomeFees.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<OutcomeFee> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all OutcomeFees matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching OutcomeFees.
     *
     * @see Pageable
     * @see Page
	 */
    Page<OutcomeFee> findAll(String query, Pageable pageable);

    /**
	 * Exports all OutcomeFees matching the given input query to the given exportType format.
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
	 * Retrieve the count of the OutcomeFees in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the OutcomeFee.
	 */
	long count(String query);


}


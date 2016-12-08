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

import com.civicxpress.cx2.FormFee;

/**
 * Service object for domain model class {@link FormFee}.
 */
public interface FormFeeService {

    /**
     * Creates a new FormFee. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FormFee if any.
     *
     * @param formFee Details of the FormFee to be created; value cannot be null.
     * @return The newly created FormFee.
     */
	FormFee create(FormFee formFee);


	/**
	 * Returns FormFee by given id if exists.
	 *
	 * @param formfeeId The id of the FormFee to get; value cannot be null.
	 * @return FormFee associated with the given formfeeId.
     * @throws EntityNotFoundException If no FormFee is found.
	 */
	FormFee getById(Integer formfeeId) throws EntityNotFoundException;

    /**
	 * Find and return the FormFee by given id if exists, returns null otherwise.
	 *
	 * @param formfeeId The id of the FormFee to get; value cannot be null.
	 * @return FormFee associated with the given formfeeId.
	 */
	FormFee findById(Integer formfeeId);


	/**
	 * Updates the details of an existing FormFee. It replaces all fields of the existing FormFee with the given formFee.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on FormFee if any.
     *
	 * @param formFee The details of the FormFee to be updated; value cannot be null.
	 * @return The updated FormFee.
	 * @throws EntityNotFoundException if no FormFee is found with given input.
	 */
	FormFee update(FormFee formFee) throws EntityNotFoundException;

    /**
	 * Deletes an existing FormFee with the given id.
	 *
	 * @param formfeeId The id of the FormFee to be deleted; value cannot be null.
	 * @return The deleted FormFee.
	 * @throws EntityNotFoundException if no FormFee found with the given id.
	 */
	FormFee delete(Integer formfeeId) throws EntityNotFoundException;

	/**
	 * Find all FormFees matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormFees.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<FormFee> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all FormFees matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormFees.
     *
     * @see Pageable
     * @see Page
	 */
    Page<FormFee> findAll(String query, Pageable pageable);

    /**
	 * Exports all FormFees matching the given input query to the given exportType format.
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
	 * Retrieve the count of the FormFees in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the FormFee.
	 */
	long count(String query);


}

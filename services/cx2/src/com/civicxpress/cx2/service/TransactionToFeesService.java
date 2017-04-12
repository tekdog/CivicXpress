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

import com.civicxpress.cx2.TransactionToFees;

/**
 * Service object for domain model class {@link TransactionToFees}.
 */
public interface TransactionToFeesService {

    /**
     * Creates a new TransactionToFees. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on TransactionToFees if any.
     *
     * @param transactionToFees Details of the TransactionToFees to be created; value cannot be null.
     * @return The newly created TransactionToFees.
     */
	TransactionToFees create(TransactionToFees transactionToFees);


	/**
	 * Returns TransactionToFees by given id if exists.
	 *
	 * @param transactiontofeesId The id of the TransactionToFees to get; value cannot be null.
	 * @return TransactionToFees associated with the given transactiontofeesId.
     * @throws EntityNotFoundException If no TransactionToFees is found.
	 */
	TransactionToFees getById(Integer transactiontofeesId) throws EntityNotFoundException;

    /**
	 * Find and return the TransactionToFees by given id if exists, returns null otherwise.
	 *
	 * @param transactiontofeesId The id of the TransactionToFees to get; value cannot be null.
	 * @return TransactionToFees associated with the given transactiontofeesId.
	 */
	TransactionToFees findById(Integer transactiontofeesId);


	/**
	 * Updates the details of an existing TransactionToFees. It replaces all fields of the existing TransactionToFees with the given transactionToFees.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on TransactionToFees if any.
     *
	 * @param transactionToFees The details of the TransactionToFees to be updated; value cannot be null.
	 * @return The updated TransactionToFees.
	 * @throws EntityNotFoundException if no TransactionToFees is found with given input.
	 */
	TransactionToFees update(TransactionToFees transactionToFees) throws EntityNotFoundException;

    /**
	 * Deletes an existing TransactionToFees with the given id.
	 *
	 * @param transactiontofeesId The id of the TransactionToFees to be deleted; value cannot be null.
	 * @return The deleted TransactionToFees.
	 * @throws EntityNotFoundException if no TransactionToFees found with the given id.
	 */
	TransactionToFees delete(Integer transactiontofeesId) throws EntityNotFoundException;

	/**
	 * Find all TransactionToFees matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching TransactionToFees.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<TransactionToFees> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all TransactionToFees matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching TransactionToFees.
     *
     * @see Pageable
     * @see Page
	 */
    Page<TransactionToFees> findAll(String query, Pageable pageable);

    /**
	 * Exports all TransactionToFees matching the given input query to the given exportType format.
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
	 * Retrieve the count of the TransactionToFees in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the TransactionToFees.
	 */
	long count(String query);


}

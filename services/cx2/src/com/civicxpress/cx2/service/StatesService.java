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

import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.GlobalSettings;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;

/**
 * Service object for domain model class {@link States}.
 */
public interface StatesService {

    /**
     * Creates a new States. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on States if any.
     *
     * @param states Details of the States to be created; value cannot be null.
     * @return The newly created States.
     */
	States create(States states);


	/**
	 * Returns States by given id if exists.
	 *
	 * @param statesId The id of the States to get; value cannot be null.
	 * @return States associated with the given statesId.
     * @throws EntityNotFoundException If no States is found.
	 */
	States getById(Integer statesId) throws EntityNotFoundException;

    /**
	 * Find and return the States by given id if exists, returns null otherwise.
	 *
	 * @param statesId The id of the States to get; value cannot be null.
	 * @return States associated with the given statesId.
	 */
	States findById(Integer statesId);


	/**
	 * Updates the details of an existing States. It replaces all fields of the existing States with the given states.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on States if any.
     *
	 * @param states The details of the States to be updated; value cannot be null.
	 * @return The updated States.
	 * @throws EntityNotFoundException if no States is found with given input.
	 */
	States update(States states) throws EntityNotFoundException;

    /**
	 * Deletes an existing States with the given id.
	 *
	 * @param statesId The id of the States to be deleted; value cannot be null.
	 * @return The deleted States.
	 * @throws EntityNotFoundException if no States found with the given id.
	 */
	States delete(Integer statesId) throws EntityNotFoundException;

	/**
	 * Find all States matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching States.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<States> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all States matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching States.
     *
     * @see Pageable
     * @see Page
	 */
    Page<States> findAll(String query, Pageable pageable);

    /**
	 * Exports all States matching the given input query to the given exportType format.
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
	 * Retrieve the count of the States in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the States.
	 */
	long count(String query);

    /*
     * Returns the associated municipalitieses for given States id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Municipalities instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Municipalities> findAssociatedMunicipalitieses(Integer id, Pageable pageable);

    /*
     * Returns the associated gisrecordsesForOwnerState for given States id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Gisrecords instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Gisrecords> findAssociatedGisrecordsesForOwnerState(Integer id, Pageable pageable);

    /*
     * Returns the associated gisrecordsesForStateId for given States id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Gisrecords instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Gisrecords> findAssociatedGisrecordsesForStateId(Integer id, Pageable pageable);

    /*
     * Returns the associated vendors for given States id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Vendor instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Vendor> findAssociatedVendors(Integer id, Pageable pageable);

    /*
     * Returns the associated userses for given States id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Users instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Users> findAssociatedUserses(Integer id, Pageable pageable);

    /*
     * Returns the associated globalSettingses for given States id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated GlobalSettings instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<GlobalSettings> findAssociatedGlobalSettingses(Integer id, Pageable pageable);

}


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

import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.SharedWith;

/**
 * Service object for domain model class {@link MasterForms}.
 */
public interface MasterFormsService {

    /**
     * Creates a new MasterForms. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on MasterForms if any.
     *
     * @param masterForms Details of the MasterForms to be created; value cannot be null.
     * @return The newly created MasterForms.
     */
	MasterForms create(MasterForms masterForms);


	/**
	 * Returns MasterForms by given id if exists.
	 *
	 * @param masterformsId The id of the MasterForms to get; value cannot be null.
	 * @return MasterForms associated with the given masterformsId.
     * @throws EntityNotFoundException If no MasterForms is found.
	 */
	MasterForms getById(String masterformsId) throws EntityNotFoundException;

    /**
	 * Find and return the MasterForms by given id if exists, returns null otherwise.
	 *
	 * @param masterformsId The id of the MasterForms to get; value cannot be null.
	 * @return MasterForms associated with the given masterformsId.
	 */
	MasterForms findById(String masterformsId);

    /**
	 * Find and return the MasterForms for given formGuid  if exists.
	 *
	 * @param formGuid value of formGuid; value cannot be null.
	 * @return MasterForms associated with the given inputs.
     * @throws EntityNotFoundException if no matching MasterForms found.
	 */
    MasterForms getByFormGuid(String formGuid)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing MasterForms. It replaces all fields of the existing MasterForms with the given masterForms.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on MasterForms if any.
     *
	 * @param masterForms The details of the MasterForms to be updated; value cannot be null.
	 * @return The updated MasterForms.
	 * @throws EntityNotFoundException if no MasterForms is found with given input.
	 */
	MasterForms update(MasterForms masterForms) throws EntityNotFoundException;

    /**
	 * Deletes an existing MasterForms with the given id.
	 *
	 * @param masterformsId The id of the MasterForms to be deleted; value cannot be null.
	 * @return The deleted MasterForms.
	 * @throws EntityNotFoundException if no MasterForms found with the given id.
	 */
	MasterForms delete(String masterformsId) throws EntityNotFoundException;

	/**
	 * Find all MasterForms matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching MasterForms.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<MasterForms> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all MasterForms matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching MasterForms.
     *
     * @see Pageable
     * @see Page
	 */
    Page<MasterForms> findAll(String query, Pageable pageable);

    /**
	 * Exports all MasterForms matching the given input query to the given exportType format.
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
	 * Retrieve the count of the MasterForms in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the MasterForms.
	 */
	long count(String query);

    /*
     * Returns the associated sharedWiths for given MasterForms id.
     *
     * @param formGuid value of formGuid; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated SharedWith instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<SharedWith> findAssociatedSharedWiths(String formGuid, Pageable pageable);

}


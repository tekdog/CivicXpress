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

import com.civicxpress.cx2.MunicipalityGroupMembers;

/**
 * Service object for domain model class {@link MunicipalityGroupMembers}.
 */
public interface MunicipalityGroupMembersService {

    /**
     * Creates a new MunicipalityGroupMembers. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on MunicipalityGroupMembers if any.
     *
     * @param municipalityGroupMembers Details of the MunicipalityGroupMembers to be created; value cannot be null.
     * @return The newly created MunicipalityGroupMembers.
     */
	MunicipalityGroupMembers create(MunicipalityGroupMembers municipalityGroupMembers);


	/**
	 * Returns MunicipalityGroupMembers by given id if exists.
	 *
	 * @param municipalitygroupmembersId The id of the MunicipalityGroupMembers to get; value cannot be null.
	 * @return MunicipalityGroupMembers associated with the given municipalitygroupmembersId.
     * @throws EntityNotFoundException If no MunicipalityGroupMembers is found.
	 */
	MunicipalityGroupMembers getById(Integer municipalitygroupmembersId) throws EntityNotFoundException;

    /**
	 * Find and return the MunicipalityGroupMembers by given id if exists, returns null otherwise.
	 *
	 * @param municipalitygroupmembersId The id of the MunicipalityGroupMembers to get; value cannot be null.
	 * @return MunicipalityGroupMembers associated with the given municipalitygroupmembersId.
	 */
	MunicipalityGroupMembers findById(Integer municipalitygroupmembersId);


	/**
	 * Updates the details of an existing MunicipalityGroupMembers. It replaces all fields of the existing MunicipalityGroupMembers with the given municipalityGroupMembers.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on MunicipalityGroupMembers if any.
     *
	 * @param municipalityGroupMembers The details of the MunicipalityGroupMembers to be updated; value cannot be null.
	 * @return The updated MunicipalityGroupMembers.
	 * @throws EntityNotFoundException if no MunicipalityGroupMembers is found with given input.
	 */
	MunicipalityGroupMembers update(MunicipalityGroupMembers municipalityGroupMembers) throws EntityNotFoundException;

    /**
	 * Deletes an existing MunicipalityGroupMembers with the given id.
	 *
	 * @param municipalitygroupmembersId The id of the MunicipalityGroupMembers to be deleted; value cannot be null.
	 * @return The deleted MunicipalityGroupMembers.
	 * @throws EntityNotFoundException if no MunicipalityGroupMembers found with the given id.
	 */
	MunicipalityGroupMembers delete(Integer municipalitygroupmembersId) throws EntityNotFoundException;

	/**
	 * Find all MunicipalityGroupMembers matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching MunicipalityGroupMembers.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<MunicipalityGroupMembers> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all MunicipalityGroupMembers matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching MunicipalityGroupMembers.
     *
     * @see Pageable
     * @see Page
	 */
    Page<MunicipalityGroupMembers> findAll(String query, Pageable pageable);

    /**
	 * Exports all MunicipalityGroupMembers matching the given input query to the given exportType format.
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
	 * Retrieve the count of the MunicipalityGroupMembers in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the MunicipalityGroupMembers.
	 */
	long count(String query);


}

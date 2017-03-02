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

import com.civicxpress.cx2.InspectionCategoryMapping;
import com.civicxpress.cx2.InspectionDesign;
import com.civicxpress.cx2.InspectionOutcome;
import com.civicxpress.cx2.InspectionSequence;
import com.civicxpress.cx2.MasterInspections;

/**
 * Service object for domain model class {@link InspectionDesign}.
 */
public interface InspectionDesignService {

    /**
     * Creates a new InspectionDesign. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionDesign if any.
     *
     * @param inspectionDesign Details of the InspectionDesign to be created; value cannot be null.
     * @return The newly created InspectionDesign.
     */
	InspectionDesign create(InspectionDesign inspectionDesign);


	/**
	 * Returns InspectionDesign by given id if exists.
	 *
	 * @param inspectiondesignId The id of the InspectionDesign to get; value cannot be null.
	 * @return InspectionDesign associated with the given inspectiondesignId.
     * @throws EntityNotFoundException If no InspectionDesign is found.
	 */
	InspectionDesign getById(Integer inspectiondesignId) throws EntityNotFoundException;

    /**
	 * Find and return the InspectionDesign by given id if exists, returns null otherwise.
	 *
	 * @param inspectiondesignId The id of the InspectionDesign to get; value cannot be null.
	 * @return InspectionDesign associated with the given inspectiondesignId.
	 */
	InspectionDesign findById(Integer inspectiondesignId);


	/**
	 * Updates the details of an existing InspectionDesign. It replaces all fields of the existing InspectionDesign with the given inspectionDesign.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionDesign if any.
     *
	 * @param inspectionDesign The details of the InspectionDesign to be updated; value cannot be null.
	 * @return The updated InspectionDesign.
	 * @throws EntityNotFoundException if no InspectionDesign is found with given input.
	 */
	InspectionDesign update(InspectionDesign inspectionDesign) throws EntityNotFoundException;

    /**
	 * Deletes an existing InspectionDesign with the given id.
	 *
	 * @param inspectiondesignId The id of the InspectionDesign to be deleted; value cannot be null.
	 * @return The deleted InspectionDesign.
	 * @throws EntityNotFoundException if no InspectionDesign found with the given id.
	 */
	InspectionDesign delete(Integer inspectiondesignId) throws EntityNotFoundException;

	/**
	 * Find all InspectionDesigns matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionDesigns.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<InspectionDesign> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all InspectionDesigns matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionDesigns.
     *
     * @see Pageable
     * @see Page
	 */
    Page<InspectionDesign> findAll(String query, Pageable pageable);

    /**
	 * Exports all InspectionDesigns matching the given input query to the given exportType format.
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
	 * Retrieve the count of the InspectionDesigns in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the InspectionDesign.
	 */
	long count(String query);

    /*
     * Returns the associated inspectionOutcomes for given InspectionDesign id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated InspectionOutcome instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<InspectionOutcome> findAssociatedInspectionOutcomes(Integer id, Pageable pageable);

    /*
     * Returns the associated inspectionSequences for given InspectionDesign id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated InspectionSequence instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<InspectionSequence> findAssociatedInspectionSequences(Integer id, Pageable pageable);

    /*
     * Returns the associated masterInspectionses for given InspectionDesign id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterInspections instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterInspections> findAssociatedMasterInspectionses(Integer id, Pageable pageable);

    /*
     * Returns the associated inspectionCategoryMappings for given InspectionDesign id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated InspectionCategoryMapping instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<InspectionCategoryMapping> findAssociatedInspectionCategoryMappings(Integer id, Pageable pageable);

}


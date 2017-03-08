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

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.FormCategoryMapping;
import com.civicxpress.cx2.FormHistory;
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormToInspectionCategoryMapping;
import com.civicxpress.cx2.FormTypeFields;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.InspectionSequence;
import com.civicxpress.cx2.MasterForms;

/**
 * Service object for domain model class {@link FormTypes}.
 */
public interface FormTypesService {

    /**
     * Creates a new FormTypes. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FormTypes if any.
     *
     * @param formTypes Details of the FormTypes to be created; value cannot be null.
     * @return The newly created FormTypes.
     */
	FormTypes create(FormTypes formTypes);


	/**
	 * Returns FormTypes by given id if exists.
	 *
	 * @param formtypesId The id of the FormTypes to get; value cannot be null.
	 * @return FormTypes associated with the given formtypesId.
     * @throws EntityNotFoundException If no FormTypes is found.
	 */
	FormTypes getById(Integer formtypesId) throws EntityNotFoundException;

    /**
	 * Find and return the FormTypes by given id if exists, returns null otherwise.
	 *
	 * @param formtypesId The id of the FormTypes to get; value cannot be null.
	 * @return FormTypes associated with the given formtypesId.
	 */
	FormTypes findById(Integer formtypesId);


	/**
	 * Updates the details of an existing FormTypes. It replaces all fields of the existing FormTypes with the given formTypes.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on FormTypes if any.
     *
	 * @param formTypes The details of the FormTypes to be updated; value cannot be null.
	 * @return The updated FormTypes.
	 * @throws EntityNotFoundException if no FormTypes is found with given input.
	 */
	FormTypes update(FormTypes formTypes) throws EntityNotFoundException;

    /**
	 * Deletes an existing FormTypes with the given id.
	 *
	 * @param formtypesId The id of the FormTypes to be deleted; value cannot be null.
	 * @return The deleted FormTypes.
	 * @throws EntityNotFoundException if no FormTypes found with the given id.
	 */
	FormTypes delete(Integer formtypesId) throws EntityNotFoundException;

	/**
	 * Find all FormTypes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormTypes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<FormTypes> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all FormTypes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormTypes.
     *
     * @see Pageable
     * @see Page
	 */
    Page<FormTypes> findAll(String query, Pageable pageable);

    /**
	 * Exports all FormTypes matching the given input query to the given exportType format.
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
	 * Retrieve the count of the FormTypes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the FormTypes.
	 */
	long count(String query);

    /*
     * Returns the associated feeses for given FormTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Fees instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Fees> findAssociatedFeeses(Integer id, Pageable pageable);

    /*
     * Returns the associated formHistories for given FormTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormHistory instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormHistory> findAssociatedFormHistories(Integer id, Pageable pageable);

    /*
     * Returns the associated formStatuseses for given FormTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormStatuses instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormStatuses> findAssociatedFormStatuseses(Integer id, Pageable pageable);

    /*
     * Returns the associated formCategoryMappings for given FormTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormCategoryMapping instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormCategoryMapping> findAssociatedFormCategoryMappings(Integer id, Pageable pageable);

    /*
     * Returns the associated formTypeFieldses for given FormTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormTypeFields instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormTypeFields> findAssociatedFormTypeFieldses(Integer id, Pageable pageable);

    /*
     * Returns the associated formToInspectionCategoryMappings for given FormTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormToInspectionCategoryMapping instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormToInspectionCategoryMapping> findAssociatedFormToInspectionCategoryMappings(Integer id, Pageable pageable);

    /*
     * Returns the associated inspectionSequences for given FormTypes id.
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
     * Returns the associated masterFormses for given FormTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterForms instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable);

}


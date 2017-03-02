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
import com.civicxpress.cx2.FormHistory;
import com.civicxpress.cx2.FormMessageTagging;
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.Gis2forms;
import com.civicxpress.cx2.InspectionGis;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.ProjectForms;
import com.civicxpress.cx2.ProjectGisrecords;
import com.civicxpress.cx2.ProjectSharedWith;
import com.civicxpress.cx2.ProjectTasks;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Roles;
import com.civicxpress.cx2.SharedWith;
import com.civicxpress.cx2.UserPasswordResetTokens;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.UserViewPreferences;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.VendorUsers;

/**
 * Service object for domain model class {@link Users}.
 */
public interface UsersService {

    /**
     * Creates a new Users. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Users if any.
     *
     * @param users Details of the Users to be created; value cannot be null.
     * @return The newly created Users.
     */
	Users create(Users users);


	/**
	 * Returns Users by given id if exists.
	 *
	 * @param usersId The id of the Users to get; value cannot be null.
	 * @return Users associated with the given usersId.
     * @throws EntityNotFoundException If no Users is found.
	 */
	Users getById(Integer usersId) throws EntityNotFoundException;

    /**
	 * Find and return the Users by given id if exists, returns null otherwise.
	 *
	 * @param usersId The id of the Users to get; value cannot be null.
	 * @return Users associated with the given usersId.
	 */
	Users findById(Integer usersId);

    /**
	 * Find and return the Users for given email  if exists.
	 *
	 * @param email value of email; value cannot be null.
	 * @return Users associated with the given inputs.
     * @throws EntityNotFoundException if no matching Users found.
	 */
    Users getByEmail(String email)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Users. It replaces all fields of the existing Users with the given users.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Users if any.
     *
	 * @param users The details of the Users to be updated; value cannot be null.
	 * @return The updated Users.
	 * @throws EntityNotFoundException if no Users is found with given input.
	 */
	Users update(Users users) throws EntityNotFoundException;

    /**
	 * Deletes an existing Users with the given id.
	 *
	 * @param usersId The id of the Users to be deleted; value cannot be null.
	 * @return The deleted Users.
	 * @throws EntityNotFoundException if no Users found with the given id.
	 */
	Users delete(Integer usersId) throws EntityNotFoundException;

	/**
	 * Find all Users matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Users.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Users> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Users matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Users.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Users> findAll(String query, Pageable pageable);

    /**
	 * Exports all Users matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Users in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Users.
	 */
	long count(String query);

    /*
     * Returns the associated feeses for given Users id.
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
     * Returns the associated formHistories for given Users id.
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
     * Returns the associated formMessageses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormMessages instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormMessages> findAssociatedFormMessageses(Integer id, Pageable pageable);

    /*
     * Returns the associated formMessageTaggings for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormMessageTagging instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormMessageTagging> findAssociatedFormMessageTaggings(Integer id, Pageable pageable);

    /*
     * Returns the associated gis2formses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Gis2forms instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Gis2forms> findAssociatedGis2formses(Integer id, Pageable pageable);

    /*
     * Returns the associated inspectionGises for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated InspectionGis instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<InspectionGis> findAssociatedInspectionGises(Integer id, Pageable pageable);

    /*
     * Returns the associated masterFormses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterForms instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable);

    /*
     * Returns the associated masterInspectionsesForRequestedBy for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterInspections instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterInspections> findAssociatedMasterInspectionsesForRequestedBy(Integer id, Pageable pageable);

    /*
     * Returns the associated masterInspectionsesForModifiedBy for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterInspections instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterInspections> findAssociatedMasterInspectionsesForModifiedBy(Integer id, Pageable pageable);

    /*
     * Returns the associated municipalityGroupMemberses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MunicipalityGroupMembers instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MunicipalityGroupMembers> findAssociatedMunicipalityGroupMemberses(Integer id, Pageable pageable);

    /*
     * Returns the associated projectFormses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectForms instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectForms> findAssociatedProjectFormses(Integer id, Pageable pageable);

    /*
     * Returns the associated projectsesForCreatedBy for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Projects instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Projects> findAssociatedProjectsesForCreatedBy(Integer id, Pageable pageable);

    /*
     * Returns the associated projectsesForModifiedBy for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Projects instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Projects> findAssociatedProjectsesForModifiedBy(Integer id, Pageable pageable);

    /*
     * Returns the associated projectGisrecordses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectGisrecords instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectGisrecords> findAssociatedProjectGisrecordses(Integer id, Pageable pageable);

    /*
     * Returns the associated projectSharedWithsForProjectSharedBy for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectSharedWith instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectSharedWith> findAssociatedProjectSharedWithsForProjectSharedBy(Integer id, Pageable pageable);

    /*
     * Returns the associated projectSharedWithsForProjectSharedWithUser for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectSharedWith instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectSharedWith> findAssociatedProjectSharedWithsForProjectSharedWithUser(Integer id, Pageable pageable);

    /*
     * Returns the associated projectTasksesForAssignedTo for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectTasks instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectTasks> findAssociatedProjectTasksesForAssignedTo(Integer id, Pageable pageable);

    /*
     * Returns the associated projectTasksesForCreatedBy for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectTasks instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectTasks> findAssociatedProjectTasksesForCreatedBy(Integer id, Pageable pageable);

    /*
     * Returns the associated roleses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Roles instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Roles> findAssociatedRoleses(Integer id, Pageable pageable);

    /*
     * Returns the associated sharedWithsForCreatedBy for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated SharedWith instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<SharedWith> findAssociatedSharedWithsForCreatedBy(Integer id, Pageable pageable);

    /*
     * Returns the associated sharedWithsForSharedWithUser for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated SharedWith instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<SharedWith> findAssociatedSharedWithsForSharedWithUser(Integer id, Pageable pageable);

    /*
     * Returns the associated userPasswordResetTokenses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated UserPasswordResetTokens instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<UserPasswordResetTokens> findAssociatedUserPasswordResetTokenses(Integer id, Pageable pageable);

    /*
     * Returns the associated userSubscriptionses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated UserSubscriptions instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<UserSubscriptions> findAssociatedUserSubscriptionses(Integer id, Pageable pageable);

    /*
     * Returns the associated userViewPreferenceses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated UserViewPreferences instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<UserViewPreferences> findAssociatedUserViewPreferenceses(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorAdminses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorAdmins instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorAdmins> findAssociatedVendorAdminses(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorUserses for given Users id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorUsers instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorUsers> findAssociatedVendorUserses(Integer id, Pageable pageable);

}


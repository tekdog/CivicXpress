/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.civicxpress.cx2.BillingInformation;
import com.civicxpress.cx2.Code;
import com.civicxpress.cx2.CodeSets;
import com.civicxpress.cx2.Document;
import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.FormHistory;
import com.civicxpress.cx2.FormMessageTagging;
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.FormsToInspections;
import com.civicxpress.cx2.Gis2forms;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.InspectionDesign;
import com.civicxpress.cx2.InspectionGis;
import com.civicxpress.cx2.InspectionHistory;
import com.civicxpress.cx2.LetterTemplates;
import com.civicxpress.cx2.MasterCases;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.MyCart;
import com.civicxpress.cx2.PaymentHistory;
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
import com.civicxpress.cx2.Violations;
import com.civicxpress.cx2.service.UsersService;


/**
 * Controller object for domain model class Users.
 * @see Users
 */
@RestController("cx2.UsersController")
@Api(value = "UsersController", description = "Exposes APIs to work with Users resource.")
@RequestMapping("/cx2/Users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
	@Qualifier("cx2.UsersService")
	private UsersService usersService;

	@ApiOperation(value = "Creates a new Users instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Users createUsers(@RequestBody Users users) {
		LOGGER.debug("Create Users with information: {}" , users);

		users = usersService.create(users);
		LOGGER.debug("Created Users with information: {}" , users);

	    return users;
	}

	@ApiOperation(value = "Creates a new Users instance.This API should be used when the Users instance has fields that requires multipart data.")
	@RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users createUsers(MultipartHttpServletRequest multipartHttpServletRequest) {
    	Users users = WMMultipartUtils.toObject(multipartHttpServletRequest, Users.class, "cx2"); 
        LOGGER.debug("Creating a new Users with information: {}" , users);
        return usersService.create(users);
    }


    @ApiOperation(value = "Returns the Users instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users getUsers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Users with id: {}" , id);

        Users foundUsers = usersService.getById(id);
        LOGGER.debug("Users details with id: {}" , foundUsers);

        return foundUsers;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Users instance" )
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces="application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getUsersBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value="download", defaultValue = "false") boolean download) {

        LOGGER.debug("Retrieves content for the given BLOB field {} in Users instance" , fieldName);

        if(!WMRuntimeUtils.isLob(Users.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        Users users = usersService.getById(id);

        return WMMultipartUtils.buildDownloadResponseForBlob(users, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the Users instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users editUsers(@PathVariable("id") Integer id, @RequestBody Users users) throws EntityNotFoundException {
        LOGGER.debug("Editing Users with id: {}" , users.getId());

        users.setId(id);
        users = usersService.update(users);
        LOGGER.debug("Users details with id: {}" , users);

        return users;
    }

    @ApiOperation(value = "Updates the Users instance associated with the given id.This API should be used when Users instance fields that require multipart data.") 
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users editUsers(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        Users newUsers = WMMultipartUtils.toObject(multipartHttpServletRequest, Users.class, "cx2");
        newUsers.setId(id);

        Users oldUsers = usersService.getById(id);
        WMMultipartUtils.updateLobsContent(oldUsers, newUsers);
        LOGGER.debug("Updating Users with information: {}" , newUsers);

        return usersService.update(newUsers);
    }

    @ApiOperation(value = "Deletes the Users instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteUsers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Users with id: {}" , id);

        Users deletedUsers = usersService.delete(id);

        return deletedUsers != null;
    }
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Users with given unique key values.")
    public Users getByEmail(@PathVariable("email") String email) {
        LOGGER.debug("Getting Users with uniques key Email");
        return usersService.getByEmail(email);
    }

    /**
     * @deprecated Use {@link #findUsers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Users instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Users> searchUsersByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Users list");
        return usersService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Users instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Users> findUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Users list");
        return usersService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Users instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Users> filterUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Users list");
        return usersService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportUsers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return usersService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Users instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countUsers( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Users");
		return usersService.count(query);
	}

    @RequestMapping(value="/{id:.+}/billingInformations", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the billingInformations instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<BillingInformation> findAssociatedBillingInformations(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated billingInformations");
        return usersService.findAssociatedBillingInformations(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/codesForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the codesForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Code> findAssociatedCodesForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated codesForCreatedBy");
        return usersService.findAssociatedCodesForCreatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/codesForUpdatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the codesForUpdatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Code> findAssociatedCodesForUpdatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated codesForUpdatedBy");
        return usersService.findAssociatedCodesForUpdatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/codeSetsesForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the codeSetsesForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodeSets> findAssociatedCodeSetsesForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated codeSetsesForCreatedBy");
        return usersService.findAssociatedCodeSetsesForCreatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/codeSetsesForUpdatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the codeSetsesForUpdatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodeSets> findAssociatedCodeSetsesForUpdatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated codeSetsesForUpdatedBy");
        return usersService.findAssociatedCodeSetsesForUpdatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/documents", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the documents instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Document> findAssociatedDocuments(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated documents");
        return usersService.findAssociatedDocuments(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/feeses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the feeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Fees> findAssociatedFeeses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated feeses");
        return usersService.findAssociatedFeeses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formHistories", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formHistories instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormHistory> findAssociatedFormHistories(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formHistories");
        return usersService.findAssociatedFormHistories(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formMessageses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formMessageses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessages> findAssociatedFormMessageses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formMessageses");
        return usersService.findAssociatedFormMessageses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formMessageTaggings", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formMessageTaggings instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessageTagging> findAssociatedFormMessageTaggings(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formMessageTaggings");
        return usersService.findAssociatedFormMessageTaggings(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formsToInspectionses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formsToInspectionses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormsToInspections> findAssociatedFormsToInspectionses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formsToInspectionses");
        return usersService.findAssociatedFormsToInspectionses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/gisrecordses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the gisrecordses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gisrecords> findAssociatedGisrecordses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated gisrecordses");
        return usersService.findAssociatedGisrecordses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/inspectionDesigns", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the inspectionDesigns instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionDesign> findAssociatedInspectionDesigns(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated inspectionDesigns");
        return usersService.findAssociatedInspectionDesigns(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/gis2formses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the gis2formses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gis2forms> findAssociatedGis2formses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated gis2formses");
        return usersService.findAssociatedGis2formses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/inspectionGises", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the inspectionGises instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionGis> findAssociatedInspectionGises(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated inspectionGises");
        return usersService.findAssociatedInspectionGises(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/inspectionHistories", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the inspectionHistories instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionHistory> findAssociatedInspectionHistories(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated inspectionHistories");
        return usersService.findAssociatedInspectionHistories(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/letterTemplatesesForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the letterTemplatesesForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<LetterTemplates> findAssociatedLetterTemplatesesForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated letterTemplatesesForCreatedBy");
        return usersService.findAssociatedLetterTemplatesesForCreatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/letterTemplatesesForModifiedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the letterTemplatesesForModifiedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<LetterTemplates> findAssociatedLetterTemplatesesForModifiedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated letterTemplatesesForModifiedBy");
        return usersService.findAssociatedLetterTemplatesesForModifiedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterCasesesForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterCasesesForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterCases> findAssociatedMasterCasesesForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterCasesesForCreatedBy");
        return usersService.findAssociatedMasterCasesesForCreatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterCasesesForModifiedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterCasesesForModifiedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterCases> findAssociatedMasterCasesesForModifiedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterCasesesForModifiedBy");
        return usersService.findAssociatedMasterCasesesForModifiedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterFormses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterFormses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterForms> findAssociatedMasterFormses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterFormses");
        return usersService.findAssociatedMasterFormses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterInspectionsesForAssignedTo", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterInspectionsesForAssignedTo instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterInspections> findAssociatedMasterInspectionsesForAssignedTo(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterInspectionsesForAssignedTo");
        return usersService.findAssociatedMasterInspectionsesForAssignedTo(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterInspectionsesForRequestedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterInspectionsesForRequestedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterInspections> findAssociatedMasterInspectionsesForRequestedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterInspectionsesForRequestedBy");
        return usersService.findAssociatedMasterInspectionsesForRequestedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterInspectionsesForModifiedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterInspectionsesForModifiedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterInspections> findAssociatedMasterInspectionsesForModifiedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterInspectionsesForModifiedBy");
        return usersService.findAssociatedMasterInspectionsesForModifiedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/municipalityGroupMemberses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the municipalityGroupMemberses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MunicipalityGroupMembers> findAssociatedMunicipalityGroupMemberses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated municipalityGroupMemberses");
        return usersService.findAssociatedMunicipalityGroupMemberses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/paymentHistories", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the paymentHistories instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PaymentHistory> findAssociatedPaymentHistories(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated paymentHistories");
        return usersService.findAssociatedPaymentHistories(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectFormses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectFormses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectForms> findAssociatedProjectFormses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectFormses");
        return usersService.findAssociatedProjectFormses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectGisrecordses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectGisrecordses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectGisrecords> findAssociatedProjectGisrecordses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectGisrecordses");
        return usersService.findAssociatedProjectGisrecordses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectsesForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectsesForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Projects> findAssociatedProjectsesForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectsesForCreatedBy");
        return usersService.findAssociatedProjectsesForCreatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectsesForModifiedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectsesForModifiedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Projects> findAssociatedProjectsesForModifiedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectsesForModifiedBy");
        return usersService.findAssociatedProjectsesForModifiedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectTasksesForAssignedTo", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectTasksesForAssignedTo instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectTasks> findAssociatedProjectTasksesForAssignedTo(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectTasksesForAssignedTo");
        return usersService.findAssociatedProjectTasksesForAssignedTo(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectTasksesForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectTasksesForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectTasks> findAssociatedProjectTasksesForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectTasksesForCreatedBy");
        return usersService.findAssociatedProjectTasksesForCreatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectSharedWithsForProjectSharedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectSharedWithsForProjectSharedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectSharedWith> findAssociatedProjectSharedWithsForProjectSharedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectSharedWithsForProjectSharedBy");
        return usersService.findAssociatedProjectSharedWithsForProjectSharedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectSharedWithsForProjectSharedWithUser", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectSharedWithsForProjectSharedWithUser instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectSharedWith> findAssociatedProjectSharedWithsForProjectSharedWithUser(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectSharedWithsForProjectSharedWithUser");
        return usersService.findAssociatedProjectSharedWithsForProjectSharedWithUser(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/roleses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the roleses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Roles> findAssociatedRoleses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated roleses");
        return usersService.findAssociatedRoleses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/sharedWithsForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the sharedWithsForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SharedWith> findAssociatedSharedWithsForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated sharedWithsForCreatedBy");
        return usersService.findAssociatedSharedWithsForCreatedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/sharedWithsForSharedWithUser", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the sharedWithsForSharedWithUser instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SharedWith> findAssociatedSharedWithsForSharedWithUser(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated sharedWithsForSharedWithUser");
        return usersService.findAssociatedSharedWithsForSharedWithUser(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/myCarts", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the myCarts instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MyCart> findAssociatedMyCarts(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated myCarts");
        return usersService.findAssociatedMyCarts(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/userPasswordResetTokenses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the userPasswordResetTokenses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserPasswordResetTokens> findAssociatedUserPasswordResetTokenses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated userPasswordResetTokenses");
        return usersService.findAssociatedUserPasswordResetTokenses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/userSubscriptionses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the userSubscriptionses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserSubscriptions> findAssociatedUserSubscriptionses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated userSubscriptionses");
        return usersService.findAssociatedUserSubscriptionses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/userViewPreferenceses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the userViewPreferenceses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserViewPreferences> findAssociatedUserViewPreferenceses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated userViewPreferenceses");
        return usersService.findAssociatedUserViewPreferenceses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorAdminses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorAdminses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorAdmins> findAssociatedVendorAdminses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorAdminses");
        return usersService.findAssociatedVendorAdminses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorUserses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorUserses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorUsers> findAssociatedVendorUserses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorUserses");
        return usersService.findAssociatedVendorUserses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/violationsesForModifiedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the violationsesForModifiedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Violations> findAssociatedViolationsesForModifiedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated violationsesForModifiedBy");
        return usersService.findAssociatedViolationsesForModifiedBy(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/violationsesForCreatedBy", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the violationsesForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Violations> findAssociatedViolationsesForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated violationsesForCreatedBy");
        return usersService.findAssociatedViolationsesForCreatedBy(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UsersService instance
	 */
	protected void setUsersService(UsersService service) {
		this.usersService = service;
	}

}


/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



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

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.civicxpress.cx2.FormCategoryMapping;
import com.civicxpress.cx2.FormHistory;
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormToInspectionCategoryMapping;
import com.civicxpress.cx2.FormTypeFields;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.InspectionSequence;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.service.FormTypesService;


/**
 * Controller object for domain model class FormTypes.
 * @see FormTypes
 */
@RestController("cx2.FormTypesController")
@Api(value = "FormTypesController", description = "Exposes APIs to work with FormTypes resource.")
@RequestMapping("/cx2/FormTypes")
public class FormTypesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormTypesController.class);

    @Autowired
	@Qualifier("cx2.FormTypesService")
	private FormTypesService formTypesService;

	@ApiOperation(value = "Creates a new FormTypes instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public FormTypes createFormTypes(@RequestBody FormTypes formTypes) {
		LOGGER.debug("Create FormTypes with information: {}" , formTypes);

		formTypes = formTypesService.create(formTypes);
		LOGGER.debug("Created FormTypes with information: {}" , formTypes);

	    return formTypes;
	}


    @ApiOperation(value = "Returns the FormTypes instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormTypes getFormTypes(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting FormTypes with id: {}" , id);

        FormTypes foundFormTypes = formTypesService.getById(id);
        LOGGER.debug("FormTypes details with id: {}" , foundFormTypes);

        return foundFormTypes;
    }

    @ApiOperation(value = "Updates the FormTypes instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormTypes editFormTypes(@PathVariable("id") Integer id, @RequestBody FormTypes formTypes) throws EntityNotFoundException {
        LOGGER.debug("Editing FormTypes with id: {}" , formTypes.getId());

        formTypes.setId(id);
        formTypes = formTypesService.update(formTypes);
        LOGGER.debug("FormTypes details with id: {}" , formTypes);

        return formTypes;
    }

    @ApiOperation(value = "Deletes the FormTypes instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteFormTypes(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormTypes with id: {}" , id);

        FormTypes deletedFormTypes = formTypesService.delete(id);

        return deletedFormTypes != null;
    }

    /**
     * @deprecated Use {@link #findFormTypes(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of FormTypes instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypes> searchFormTypesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FormTypes list");
        return formTypesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FormTypes instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypes> findFormTypes(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormTypes list");
        return formTypesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FormTypes instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypes> filterFormTypes(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormTypes list");
        return formTypesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportFormTypes(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return formTypesService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of FormTypes instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countFormTypes( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting FormTypes");
		return formTypesService.count(query);
	}

    @RequestMapping(value="/{id:.+}/formCategoryMappings", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formCategoryMappings instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormCategoryMapping> findAssociatedFormCategoryMappings(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formCategoryMappings");
        return formTypesService.findAssociatedFormCategoryMappings(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formStatuseses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formStatuseses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormStatuses> findAssociatedFormStatuseses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formStatuseses");
        return formTypesService.findAssociatedFormStatuseses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formHistories", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formHistories instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormHistory> findAssociatedFormHistories(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formHistories");
        return formTypesService.findAssociatedFormHistories(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formToInspectionCategoryMappings", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formToInspectionCategoryMappings instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormToInspectionCategoryMapping> findAssociatedFormToInspectionCategoryMappings(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formToInspectionCategoryMappings");
        return formTypesService.findAssociatedFormToInspectionCategoryMappings(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formTypeFieldses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formTypeFieldses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypeFields> findAssociatedFormTypeFieldses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formTypeFieldses");
        return formTypesService.findAssociatedFormTypeFieldses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterFormses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterFormses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterForms> findAssociatedMasterFormses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterFormses");
        return formTypesService.findAssociatedMasterFormses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/inspectionSequences", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the inspectionSequences instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionSequence> findAssociatedInspectionSequences(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated inspectionSequences");
        return formTypesService.findAssociatedInspectionSequences(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormTypesService instance
	 */
	protected void setFormTypesService(FormTypesService service) {
		this.formTypesService = service;
	}

}


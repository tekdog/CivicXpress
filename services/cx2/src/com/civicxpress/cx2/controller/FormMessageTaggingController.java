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

import com.civicxpress.cx2.FormMessageTagging;
import com.civicxpress.cx2.service.FormMessageTaggingService;


/**
 * Controller object for domain model class FormMessageTagging.
 * @see FormMessageTagging
 */
@RestController("cx2.FormMessageTaggingController")
@Api(value = "FormMessageTaggingController", description = "Exposes APIs to work with FormMessageTagging resource.")
@RequestMapping("/cx2/FormMessageTagging")
public class FormMessageTaggingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormMessageTaggingController.class);

    @Autowired
	@Qualifier("cx2.FormMessageTaggingService")
	private FormMessageTaggingService formMessageTaggingService;

	@ApiOperation(value = "Creates a new FormMessageTagging instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public FormMessageTagging createFormMessageTagging(@RequestBody FormMessageTagging formMessageTagging) {
		LOGGER.debug("Create FormMessageTagging with information: {}" , formMessageTagging);

		formMessageTagging = formMessageTaggingService.create(formMessageTagging);
		LOGGER.debug("Created FormMessageTagging with information: {}" , formMessageTagging);

	    return formMessageTagging;
	}


    @ApiOperation(value = "Returns the FormMessageTagging instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormMessageTagging getFormMessageTagging(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting FormMessageTagging with id: {}" , id);

        FormMessageTagging foundFormMessageTagging = formMessageTaggingService.getById(id);
        LOGGER.debug("FormMessageTagging details with id: {}" , foundFormMessageTagging);

        return foundFormMessageTagging;
    }

    @ApiOperation(value = "Updates the FormMessageTagging instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormMessageTagging editFormMessageTagging(@PathVariable("id") Integer id, @RequestBody FormMessageTagging formMessageTagging) throws EntityNotFoundException {
        LOGGER.debug("Editing FormMessageTagging with id: {}" , formMessageTagging.getId());

        formMessageTagging.setId(id);
        formMessageTagging = formMessageTaggingService.update(formMessageTagging);
        LOGGER.debug("FormMessageTagging details with id: {}" , formMessageTagging);

        return formMessageTagging;
    }

    @ApiOperation(value = "Deletes the FormMessageTagging instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteFormMessageTagging(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormMessageTagging with id: {}" , id);

        FormMessageTagging deletedFormMessageTagging = formMessageTaggingService.delete(id);

        return deletedFormMessageTagging != null;
    }

    /**
     * @deprecated Use {@link #findFormMessageTaggings(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of FormMessageTagging instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessageTagging> searchFormMessageTaggingsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FormMessageTaggings list");
        return formMessageTaggingService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FormMessageTagging instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessageTagging> findFormMessageTaggings(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormMessageTaggings list");
        return formMessageTaggingService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FormMessageTagging instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessageTagging> filterFormMessageTaggings(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormMessageTaggings list");
        return formMessageTaggingService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportFormMessageTaggings(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return formMessageTaggingService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of FormMessageTagging instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countFormMessageTaggings( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting FormMessageTaggings");
		return formMessageTaggingService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormMessageTaggingService instance
	 */
	protected void setFormMessageTaggingService(FormMessageTaggingService service) {
		this.formMessageTaggingService = service;
	}

}


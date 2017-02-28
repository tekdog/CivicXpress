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

import com.civicxpress.cx2.InspectionSequence;
import com.civicxpress.cx2.service.InspectionSequenceService;


/**
 * Controller object for domain model class InspectionSequence.
 * @see InspectionSequence
 */
@RestController("cx2.InspectionSequenceController")
@Api(value = "InspectionSequenceController", description = "Exposes APIs to work with InspectionSequence resource.")
@RequestMapping("/cx2/InspectionSequence")
public class InspectionSequenceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionSequenceController.class);

    @Autowired
	@Qualifier("cx2.InspectionSequenceService")
	private InspectionSequenceService inspectionSequenceService;

	@ApiOperation(value = "Creates a new InspectionSequence instance.")
	@RequestMapping(method = RequestMethod.POST)
        @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public InspectionSequence createInspectionSequence(@RequestBody InspectionSequence inspectionSequence) {
		LOGGER.debug("Create InspectionSequence with information: {}" , inspectionSequence);

		inspectionSequence = inspectionSequenceService.create(inspectionSequence);
		LOGGER.debug("Created InspectionSequence with information: {}" , inspectionSequence);

	    return inspectionSequence;
	}


    @ApiOperation(value = "Returns the InspectionSequence instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public InspectionSequence getInspectionSequence(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting InspectionSequence with id: {}" , id);

        InspectionSequence foundInspectionSequence = inspectionSequenceService.getById(id);
        LOGGER.debug("InspectionSequence details with id: {}" , foundInspectionSequence);

        return foundInspectionSequence;
    }

    @ApiOperation(value = "Updates the InspectionSequence instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public InspectionSequence editInspectionSequence(@PathVariable("id") Integer id, @RequestBody InspectionSequence inspectionSequence) throws EntityNotFoundException {
        LOGGER.debug("Editing InspectionSequence with id: {}" , inspectionSequence.getId());

        inspectionSequence.setId(id);
        inspectionSequence = inspectionSequenceService.update(inspectionSequence);
        LOGGER.debug("InspectionSequence details with id: {}" , inspectionSequence);

        return inspectionSequence;
    }

    @ApiOperation(value = "Deletes the InspectionSequence instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteInspectionSequence(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting InspectionSequence with id: {}" , id);

        InspectionSequence deletedInspectionSequence = inspectionSequenceService.delete(id);

        return deletedInspectionSequence != null;
    }

    /**
     * @deprecated Use {@link #findInspectionSequences(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of InspectionSequence instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionSequence> searchInspectionSequencesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering InspectionSequences list");
        return inspectionSequenceService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of InspectionSequence instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionSequence> findInspectionSequences(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering InspectionSequences list");
        return inspectionSequenceService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportInspectionSequences(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return inspectionSequenceService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of InspectionSequence instances.")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countInspectionSequences( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting InspectionSequences");
		return inspectionSequenceService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service InspectionSequenceService instance
	 */
	protected void setInspectionSequenceService(InspectionSequenceService service) {
		this.inspectionSequenceService = service;
	}

}


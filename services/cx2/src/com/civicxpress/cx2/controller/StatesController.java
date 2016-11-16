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
import com.civicxpress.cx2.Contractors;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.States;
import com.civicxpress.cx2.service.StatesService;

/**
 * Controller object for domain model class States.
 * @see States
 */
@RestController("cx2.StatesController")
@Api(value = "StatesController", description = "Exposes APIs to work with States resource.")
@RequestMapping("/cx2/States")
public class StatesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatesController.class);

    @Autowired
    @Qualifier("cx2.StatesService")
    private StatesService statesService;

    @ApiOperation(value = "Creates a new States instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public States createStates(@RequestBody States states) {
        LOGGER.debug("Create States with information: {}", states);
        states = statesService.create(states);
        LOGGER.debug("Created States with information: {}", states);
        return states;
    }

    @ApiOperation(value = "Returns the States instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public States getStates(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting States with id: {}", id);
        States foundStates = statesService.getById(id);
        LOGGER.debug("States details with id: {}", foundStates);
        return foundStates;
    }

    @ApiOperation(value = "Updates the States instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public States editStates(@PathVariable("id") Integer id, @RequestBody States states) throws EntityNotFoundException {
        LOGGER.debug("Editing States with id: {}", states.getId());
        states.setId(id);
        states = statesService.update(states);
        LOGGER.debug("States details with id: {}", states);
        return states;
    }

    @ApiOperation(value = "Deletes the States instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteStates(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting States with id: {}", id);
        States deletedStates = statesService.delete(id);
        return deletedStates != null;
    }

    /**
     * @deprecated Use {@link #findStates(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of States instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<States> findStates(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering States list");
        return statesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of States instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<States> findStates(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering States list");
        return statesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportStates(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return statesService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of States instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countStates(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting States");
        return statesService.count(query);
    }

    @RequestMapping(value = "/{id}/contractorses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the contractorses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Contractors> findAssociatedContractorses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated contractorses");
        return statesService.findAssociatedContractorses(id, pageable);
    }

    @RequestMapping(value = "/{id}/municipalitieses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the municipalitieses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Municipalities> findAssociatedMunicipalitieses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalitieses");
        return statesService.findAssociatedMunicipalitieses(id, pageable);
    }

    @RequestMapping(value = "/{id}/gisrecordses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the gisrecordses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gisrecords> findAssociatedGisrecordses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated gisrecordses");
        return statesService.findAssociatedGisrecordses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StatesService instance
	 */
    protected void setStatesService(StatesService service) {
        this.statesService = service;
    }
}

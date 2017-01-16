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
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.service.MasterInspectionsService;

/**
 * Controller object for domain model class MasterInspections.
 * @see MasterInspections
 */
@RestController("cx2.MasterInspectionsController")
@Api(value = "MasterInspectionsController", description = "Exposes APIs to work with MasterInspections resource.")
@RequestMapping("/cx2/MasterInspections")
public class MasterInspectionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterInspectionsController.class);

    @Autowired
    @Qualifier("cx2.MasterInspectionsService")
    private MasterInspectionsService masterInspectionsService;

    @ApiOperation(value = "Creates a new MasterInspections instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterInspections createMasterInspections(@RequestBody MasterInspections masterInspections) {
        LOGGER.debug("Create MasterInspections with information: {}", masterInspections);
        masterInspections = masterInspectionsService.create(masterInspections);
        LOGGER.debug("Created MasterInspections with information: {}", masterInspections);
        return masterInspections;
    }

    @ApiOperation(value = "Returns the MasterInspections instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterInspections getMasterInspections(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting MasterInspections with id: {}", id);
        MasterInspections foundMasterInspections = masterInspectionsService.getById(id);
        LOGGER.debug("MasterInspections details with id: {}", foundMasterInspections);
        return foundMasterInspections;
    }

    @ApiOperation(value = "Updates the MasterInspections instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterInspections editMasterInspections(@PathVariable("id") Integer id, @RequestBody MasterInspections masterInspections) throws EntityNotFoundException {
        LOGGER.debug("Editing MasterInspections with id: {}", masterInspections.getId());
        masterInspections.setId(id);
        masterInspections = masterInspectionsService.update(masterInspections);
        LOGGER.debug("MasterInspections details with id: {}", masterInspections);
        return masterInspections;
    }

    @ApiOperation(value = "Deletes the MasterInspections instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMasterInspections(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting MasterInspections with id: {}", id);
        MasterInspections deletedMasterInspections = masterInspectionsService.delete(id);
        return deletedMasterInspections != null;
    }

    /**
     * @deprecated Use {@link #findMasterInspections(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of MasterInspections instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterInspections> searchMasterInspectionsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering MasterInspections list");
        return masterInspectionsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of MasterInspections instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterInspections> findMasterInspections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering MasterInspections list");
        return masterInspectionsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMasterInspections(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return masterInspectionsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of MasterInspections instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countMasterInspections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting MasterInspections");
        return masterInspectionsService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterInspectionsService instance
	 */
    protected void setMasterInspectionsService(MasterInspectionsService service) {
        this.masterInspectionsService = service;
    }
}

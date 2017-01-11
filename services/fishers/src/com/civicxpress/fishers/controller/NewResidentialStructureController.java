/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.fishers.controller;

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
import com.civicxpress.fishers.NewResidentialStructure;
import com.civicxpress.fishers.service.NewResidentialStructureService;

/**
 * Controller object for domain model class NewResidentialStructure.
 * @see NewResidentialStructure
 */
@RestController("fishers.NewResidentialStructureController")
@Api(value = "NewResidentialStructureController", description = "Exposes APIs to work with NewResidentialStructure resource.")
@RequestMapping("/fishers/NewResidentialStructure")
public class NewResidentialStructureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewResidentialStructureController.class);

    @Autowired
    @Qualifier("fishers.NewResidentialStructureService")
    private NewResidentialStructureService newResidentialStructureService;

    @ApiOperation(value = "Creates a new NewResidentialStructure instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public NewResidentialStructure createNewResidentialStructure(@RequestBody NewResidentialStructure newResidentialStructure) {
        LOGGER.debug("Create NewResidentialStructure with information: {}", newResidentialStructure);
        newResidentialStructure = newResidentialStructureService.create(newResidentialStructure);
        LOGGER.debug("Created NewResidentialStructure with information: {}", newResidentialStructure);
        return newResidentialStructure;
    }

    @ApiOperation(value = "Returns the NewResidentialStructure instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public NewResidentialStructure getNewResidentialStructure(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting NewResidentialStructure with id: {}", id);
        NewResidentialStructure foundNewResidentialStructure = newResidentialStructureService.getById(id);
        LOGGER.debug("NewResidentialStructure details with id: {}", foundNewResidentialStructure);
        return foundNewResidentialStructure;
    }

    @ApiOperation(value = "Updates the NewResidentialStructure instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public NewResidentialStructure editNewResidentialStructure(@PathVariable("id") Integer id, @RequestBody NewResidentialStructure newResidentialStructure) throws EntityNotFoundException {
        LOGGER.debug("Editing NewResidentialStructure with id: {}", newResidentialStructure.getId());
        newResidentialStructure.setId(id);
        newResidentialStructure = newResidentialStructureService.update(newResidentialStructure);
        LOGGER.debug("NewResidentialStructure details with id: {}", newResidentialStructure);
        return newResidentialStructure;
    }

    @ApiOperation(value = "Deletes the NewResidentialStructure instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteNewResidentialStructure(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting NewResidentialStructure with id: {}", id);
        NewResidentialStructure deletedNewResidentialStructure = newResidentialStructureService.delete(id);
        return deletedNewResidentialStructure != null;
    }

    /**
     * @deprecated Use {@link #findNewResidentialStructures(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of NewResidentialStructure instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<NewResidentialStructure> searchNewResidentialStructuresByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering NewResidentialStructures list");
        return newResidentialStructureService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of NewResidentialStructure instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<NewResidentialStructure> findNewResidentialStructures(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering NewResidentialStructures list");
        return newResidentialStructureService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportNewResidentialStructures(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return newResidentialStructureService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of NewResidentialStructure instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countNewResidentialStructures(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting NewResidentialStructures");
        return newResidentialStructureService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service NewResidentialStructureService instance
	 */
    protected void setNewResidentialStructureService(NewResidentialStructureService service) {
        this.newResidentialStructureService = service;
    }
}

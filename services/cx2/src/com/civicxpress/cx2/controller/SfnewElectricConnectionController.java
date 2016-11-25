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
import com.civicxpress.cx2.SfnewElectricConnection;
import com.civicxpress.cx2.service.SfnewElectricConnectionService;

/**
 * Controller object for domain model class SfnewElectricConnection.
 * @see SfnewElectricConnection
 */
@RestController("cx2.SfnewElectricConnectionController")
@Api(value = "SfnewElectricConnectionController", description = "Exposes APIs to work with SfnewElectricConnection resource.")
@RequestMapping("/cx2/SfnewElectricConnection")
public class SfnewElectricConnectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SfnewElectricConnectionController.class);

    @Autowired
    @Qualifier("cx2.SfnewElectricConnectionService")
    private SfnewElectricConnectionService sfnewElectricConnectionService;

    @ApiOperation(value = "Creates a new SfnewElectricConnection instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SfnewElectricConnection createSfnewElectricConnection(@RequestBody SfnewElectricConnection sfnewElectricConnection) {
        LOGGER.debug("Create SfnewElectricConnection with information: {}", sfnewElectricConnection);
        sfnewElectricConnection = sfnewElectricConnectionService.create(sfnewElectricConnection);
        LOGGER.debug("Created SfnewElectricConnection with information: {}", sfnewElectricConnection);
        return sfnewElectricConnection;
    }

    @ApiOperation(value = "Returns the SfnewElectricConnection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SfnewElectricConnection getSfnewElectricConnection(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting SfnewElectricConnection with id: {}", id);
        SfnewElectricConnection foundSfnewElectricConnection = sfnewElectricConnectionService.getById(id);
        LOGGER.debug("SfnewElectricConnection details with id: {}", foundSfnewElectricConnection);
        return foundSfnewElectricConnection;
    }

    @ApiOperation(value = "Updates the SfnewElectricConnection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SfnewElectricConnection editSfnewElectricConnection(@PathVariable("id") Integer id, @RequestBody SfnewElectricConnection sfnewElectricConnection) throws EntityNotFoundException {
        LOGGER.debug("Editing SfnewElectricConnection with id: {}", sfnewElectricConnection.getId());
        sfnewElectricConnection.setId(id);
        sfnewElectricConnection = sfnewElectricConnectionService.update(sfnewElectricConnection);
        LOGGER.debug("SfnewElectricConnection details with id: {}", sfnewElectricConnection);
        return sfnewElectricConnection;
    }

    @ApiOperation(value = "Deletes the SfnewElectricConnection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteSfnewElectricConnection(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting SfnewElectricConnection with id: {}", id);
        SfnewElectricConnection deletedSfnewElectricConnection = sfnewElectricConnectionService.delete(id);
        return deletedSfnewElectricConnection != null;
    }

    /**
     * @deprecated Use {@link #findSfnewElectricConnections(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of SfnewElectricConnection instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SfnewElectricConnection> findSfnewElectricConnections(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering SfnewElectricConnections list");
        return sfnewElectricConnectionService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of SfnewElectricConnection instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SfnewElectricConnection> findSfnewElectricConnections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering SfnewElectricConnections list");
        return sfnewElectricConnectionService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSfnewElectricConnections(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return sfnewElectricConnectionService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of SfnewElectricConnection instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countSfnewElectricConnections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting SfnewElectricConnections");
        return sfnewElectricConnectionService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SfnewElectricConnectionService instance
	 */
    protected void setSfnewElectricConnectionService(SfnewElectricConnectionService service) {
        this.sfnewElectricConnectionService = service;
    }
}
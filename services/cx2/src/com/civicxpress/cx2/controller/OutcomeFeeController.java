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
import com.civicxpress.cx2.OutcomeFee;
import com.civicxpress.cx2.service.OutcomeFeeService;

/**
 * Controller object for domain model class OutcomeFee.
 * @see OutcomeFee
 */
@RestController("cx2.OutcomeFeeController")
@Api(value = "OutcomeFeeController", description = "Exposes APIs to work with OutcomeFee resource.")
@RequestMapping("/cx2/OutcomeFee")
public class OutcomeFeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutcomeFeeController.class);

    @Autowired
    @Qualifier("cx2.OutcomeFeeService")
    private OutcomeFeeService outcomeFeeService;

    @ApiOperation(value = "Creates a new OutcomeFee instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public OutcomeFee createOutcomeFee(@RequestBody OutcomeFee outcomeFee) {
        LOGGER.debug("Create OutcomeFee with information: {}", outcomeFee);
        outcomeFee = outcomeFeeService.create(outcomeFee);
        LOGGER.debug("Created OutcomeFee with information: {}", outcomeFee);
        return outcomeFee;
    }

    @ApiOperation(value = "Returns the OutcomeFee instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public OutcomeFee getOutcomeFee(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting OutcomeFee with id: {}", id);
        OutcomeFee foundOutcomeFee = outcomeFeeService.getById(id);
        LOGGER.debug("OutcomeFee details with id: {}", foundOutcomeFee);
        return foundOutcomeFee;
    }

    @ApiOperation(value = "Updates the OutcomeFee instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public OutcomeFee editOutcomeFee(@PathVariable("id") Integer id, @RequestBody OutcomeFee outcomeFee) throws EntityNotFoundException {
        LOGGER.debug("Editing OutcomeFee with id: {}", outcomeFee.getId());
        outcomeFee.setId(id);
        outcomeFee = outcomeFeeService.update(outcomeFee);
        LOGGER.debug("OutcomeFee details with id: {}", outcomeFee);
        return outcomeFee;
    }

    @ApiOperation(value = "Deletes the OutcomeFee instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteOutcomeFee(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting OutcomeFee with id: {}", id);
        OutcomeFee deletedOutcomeFee = outcomeFeeService.delete(id);
        return deletedOutcomeFee != null;
    }

    /**
     * @deprecated Use {@link #findOutcomeFees(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of OutcomeFee instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<OutcomeFee> searchOutcomeFeesByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering OutcomeFees list");
        return outcomeFeeService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of OutcomeFee instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<OutcomeFee> findOutcomeFees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering OutcomeFees list");
        return outcomeFeeService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportOutcomeFees(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return outcomeFeeService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of OutcomeFee instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countOutcomeFees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting OutcomeFees");
        return outcomeFeeService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service OutcomeFeeService instance
	 */
    protected void setOutcomeFeeService(OutcomeFeeService service) {
        this.outcomeFeeService = service;
    }
}

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
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.SharedWith;
import com.civicxpress.cx2.service.MasterFormsService;

/**
 * Controller object for domain model class MasterForms.
 * @see MasterForms
 */
@RestController("cx2.MasterFormsController")
@Api(value = "MasterFormsController", description = "Exposes APIs to work with MasterForms resource.")
@RequestMapping("/cx2/MasterForms")
public class MasterFormsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterFormsController.class);

    @Autowired
    @Qualifier("cx2.MasterFormsService")
    private MasterFormsService masterFormsService;

    @ApiOperation(value = "Creates a new MasterForms instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterForms createMasterForms(@RequestBody MasterForms masterForms) {
        LOGGER.debug("Create MasterForms with information: {}", masterForms);
        masterForms = masterFormsService.create(masterForms);
        LOGGER.debug("Created MasterForms with information: {}", masterForms);
        return masterForms;
    }

    @ApiOperation(value = "Returns the MasterForms instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterForms getMasterForms(@PathVariable("id") String id) throws EntityNotFoundException {
        LOGGER.debug("Getting MasterForms with id: {}", id);
        MasterForms foundMasterForms = masterFormsService.getById(id);
        LOGGER.debug("MasterForms details with id: {}", foundMasterForms);
        return foundMasterForms;
    }

    @ApiOperation(value = "Updates the MasterForms instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterForms editMasterForms(@PathVariable("id") String id, @RequestBody MasterForms masterForms) throws EntityNotFoundException {
        LOGGER.debug("Editing MasterForms with id: {}", masterForms.getFormGuid());
        masterForms.setFormGuid(id);
        masterForms = masterFormsService.update(masterForms);
        LOGGER.debug("MasterForms details with id: {}", masterForms);
        return masterForms;
    }

    @ApiOperation(value = "Deletes the MasterForms instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMasterForms(@PathVariable("id") String id) throws EntityNotFoundException {
        LOGGER.debug("Deleting MasterForms with id: {}", id);
        MasterForms deletedMasterForms = masterFormsService.delete(id);
        return deletedMasterForms != null;
    }

    @RequestMapping(value = "/formGuid/{formGuid}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching MasterForms with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterForms getByFormGuid(@PathVariable("formGuid") String formGuid) {
        LOGGER.debug("Getting MasterForms with uniques key FormGuid");
        return masterFormsService.getByFormGuid(formGuid);
    }

    /**
     * @deprecated Use {@link #findMasterForms(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of MasterForms instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterForms> searchMasterFormsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering MasterForms list");
        return masterFormsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of MasterForms instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterForms> findMasterForms(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering MasterForms list");
        return masterFormsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMasterForms(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return masterFormsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of MasterForms instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countMasterForms(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting MasterForms");
        return masterFormsService.count(query);
    }

    @RequestMapping(value = "/{id}/sharedWiths", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the sharedWiths instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SharedWith> findAssociatedSharedWiths(@PathVariable("id") String id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sharedWiths");
        return masterFormsService.findAssociatedSharedWiths(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterFormsService instance
	 */
    protected void setMasterFormsService(MasterFormsService service) {
        this.masterFormsService = service;
    }
}

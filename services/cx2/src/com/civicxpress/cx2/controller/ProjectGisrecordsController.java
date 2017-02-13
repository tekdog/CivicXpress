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
import com.civicxpress.cx2.ProjectGisrecords;
import com.civicxpress.cx2.service.ProjectGisrecordsService;

/**
 * Controller object for domain model class ProjectGisrecords.
 * @see ProjectGisrecords
 */
@RestController("cx2.ProjectGisrecordsController")
@Api(value = "ProjectGisrecordsController", description = "Exposes APIs to work with ProjectGisrecords resource.")
@RequestMapping("/cx2/ProjectGisrecords")
public class ProjectGisrecordsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectGisrecordsController.class);

    @Autowired
    @Qualifier("cx2.ProjectGisrecordsService")
    private ProjectGisrecordsService projectGisrecordsService;

    @ApiOperation(value = "Creates a new ProjectGisrecords instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProjectGisrecords createProjectGisrecords(@RequestBody ProjectGisrecords projectGisrecords) {
        LOGGER.debug("Create ProjectGisrecords with information: {}", projectGisrecords);
        projectGisrecords = projectGisrecordsService.create(projectGisrecords);
        LOGGER.debug("Created ProjectGisrecords with information: {}", projectGisrecords);
        return projectGisrecords;
    }

    @ApiOperation(value = "Returns the ProjectGisrecords instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProjectGisrecords getProjectGisrecords(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting ProjectGisrecords with id: {}", id);
        ProjectGisrecords foundProjectGisrecords = projectGisrecordsService.getById(id);
        LOGGER.debug("ProjectGisrecords details with id: {}", foundProjectGisrecords);
        return foundProjectGisrecords;
    }

    @ApiOperation(value = "Updates the ProjectGisrecords instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProjectGisrecords editProjectGisrecords(@PathVariable("id") Integer id, @RequestBody ProjectGisrecords projectGisrecords) throws EntityNotFoundException {
        LOGGER.debug("Editing ProjectGisrecords with id: {}", projectGisrecords.getId());
        projectGisrecords.setId(id);
        projectGisrecords = projectGisrecordsService.update(projectGisrecords);
        LOGGER.debug("ProjectGisrecords details with id: {}", projectGisrecords);
        return projectGisrecords;
    }

    @ApiOperation(value = "Deletes the ProjectGisrecords instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteProjectGisrecords(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting ProjectGisrecords with id: {}", id);
        ProjectGisrecords deletedProjectGisrecords = projectGisrecordsService.delete(id);
        return deletedProjectGisrecords != null;
    }

    /**
     * @deprecated Use {@link #findProjectGisrecords(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of ProjectGisrecords instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectGisrecords> searchProjectGisrecordsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering ProjectGisrecords list");
        return projectGisrecordsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of ProjectGisrecords instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectGisrecords> findProjectGisrecords(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering ProjectGisrecords list");
        return projectGisrecordsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportProjectGisrecords(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return projectGisrecordsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of ProjectGisrecords instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countProjectGisrecords(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting ProjectGisrecords");
        return projectGisrecordsService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectGisrecordsService instance
	 */
    protected void setProjectGisrecordsService(ProjectGisrecordsService service) {
        this.projectGisrecordsService = service;
    }
}
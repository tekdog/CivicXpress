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

import com.civicxpress.cx2.CodeList;
import com.civicxpress.cx2.CodeSets;
import com.civicxpress.cx2.service.CodeSetsService;


/**
 * Controller object for domain model class CodeSets.
 * @see CodeSets
 */
@RestController("cx2.CodeSetsController")
@Api(value = "CodeSetsController", description = "Exposes APIs to work with CodeSets resource.")
@RequestMapping("/cx2/CodeSets")
public class CodeSetsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeSetsController.class);

    @Autowired
	@Qualifier("cx2.CodeSetsService")
	private CodeSetsService codeSetsService;

	@ApiOperation(value = "Creates a new CodeSets instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public CodeSets createCodeSets(@RequestBody CodeSets codeSets) {
		LOGGER.debug("Create CodeSets with information: {}" , codeSets);

		codeSets = codeSetsService.create(codeSets);
		LOGGER.debug("Created CodeSets with information: {}" , codeSets);

	    return codeSets;
	}


    @ApiOperation(value = "Returns the CodeSets instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CodeSets getCodeSets(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting CodeSets with id: {}" , id);

        CodeSets foundCodeSets = codeSetsService.getById(id);
        LOGGER.debug("CodeSets details with id: {}" , foundCodeSets);

        return foundCodeSets;
    }

    @ApiOperation(value = "Updates the CodeSets instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CodeSets editCodeSets(@PathVariable("id") Integer id, @RequestBody CodeSets codeSets) throws EntityNotFoundException {
        LOGGER.debug("Editing CodeSets with id: {}" , codeSets.getCodeSetId());

        codeSets.setCodeSetId(id);
        codeSets = codeSetsService.update(codeSets);
        LOGGER.debug("CodeSets details with id: {}" , codeSets);

        return codeSets;
    }

    @ApiOperation(value = "Deletes the CodeSets instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteCodeSets(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting CodeSets with id: {}" , id);

        CodeSets deletedCodeSets = codeSetsService.delete(id);

        return deletedCodeSets != null;
    }

    /**
     * @deprecated Use {@link #findCodeSets(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of CodeSets instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodeSets> searchCodeSetsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering CodeSets list");
        return codeSetsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CodeSets instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodeSets> findCodeSets(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CodeSets list");
        return codeSetsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CodeSets instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodeSets> filterCodeSets(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CodeSets list");
        return codeSetsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportCodeSets(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return codeSetsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of CodeSets instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countCodeSets( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting CodeSets");
		return codeSetsService.count(query);
	}

    @RequestMapping(value="/{id:.+}/codeLists", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the codeLists instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodeList> findAssociatedCodeLists(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated codeLists");
        return codeSetsService.findAssociatedCodeLists(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CodeSetsService instance
	 */
	protected void setCodeSetsService(CodeSetsService service) {
		this.codeSetsService = service;
	}

}

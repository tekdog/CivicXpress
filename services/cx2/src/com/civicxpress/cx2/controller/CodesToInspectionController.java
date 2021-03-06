/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

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
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.civicxpress.cx2.CodesToInspection;
import com.civicxpress.cx2.service.CodesToInspectionService;


/**
 * Controller object for domain model class CodesToInspection.
 * @see CodesToInspection
 */
@RestController("cx2.CodesToInspectionController")
@Api(value = "CodesToInspectionController", description = "Exposes APIs to work with CodesToInspection resource.")
@RequestMapping("/cx2/CodesToInspection")
public class CodesToInspectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodesToInspectionController.class);

    @Autowired
	@Qualifier("cx2.CodesToInspectionService")
	private CodesToInspectionService codesToInspectionService;

	@ApiOperation(value = "Creates a new CodesToInspection instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public CodesToInspection createCodesToInspection(@RequestBody CodesToInspection codesToInspection) {
		LOGGER.debug("Create CodesToInspection with information: {}" , codesToInspection);

		codesToInspection = codesToInspectionService.create(codesToInspection);
		LOGGER.debug("Created CodesToInspection with information: {}" , codesToInspection);

	    return codesToInspection;
	}


    @ApiOperation(value = "Returns the CodesToInspection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CodesToInspection getCodesToInspection(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting CodesToInspection with id: {}" , id);

        CodesToInspection foundCodesToInspection = codesToInspectionService.getById(id);
        LOGGER.debug("CodesToInspection details with id: {}" , foundCodesToInspection);

        return foundCodesToInspection;
    }

    @ApiOperation(value = "Updates the CodesToInspection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CodesToInspection editCodesToInspection(@PathVariable("id") Integer id, @RequestBody CodesToInspection codesToInspection) throws EntityNotFoundException {
        LOGGER.debug("Editing CodesToInspection with id: {}" , codesToInspection.getId());

        codesToInspection.setId(id);
        codesToInspection = codesToInspectionService.update(codesToInspection);
        LOGGER.debug("CodesToInspection details with id: {}" , codesToInspection);

        return codesToInspection;
    }

    @ApiOperation(value = "Deletes the CodesToInspection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteCodesToInspection(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting CodesToInspection with id: {}" , id);

        CodesToInspection deletedCodesToInspection = codesToInspectionService.delete(id);

        return deletedCodesToInspection != null;
    }

    /**
     * @deprecated Use {@link #findCodesToInspections(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of CodesToInspection instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodesToInspection> searchCodesToInspectionsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering CodesToInspections list");
        return codesToInspectionService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CodesToInspection instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodesToInspection> findCodesToInspections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CodesToInspections list");
        return codesToInspectionService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CodesToInspection instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CodesToInspection> filterCodesToInspections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CodesToInspections list");
        return codesToInspectionService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportCodesToInspections(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return codesToInspectionService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of CodesToInspection instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countCodesToInspections( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting CodesToInspections");
		return codesToInspectionService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getCodesToInspectionAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return codesToInspectionService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CodesToInspectionService instance
	 */
	protected void setCodesToInspectionService(CodesToInspectionService service) {
		this.codesToInspectionService = service;
	}

}


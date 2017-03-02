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

import com.civicxpress.cx2.Holidays;
import com.civicxpress.cx2.service.HolidaysService;


/**
 * Controller object for domain model class Holidays.
 * @see Holidays
 */
@RestController("cx2.HolidaysController")
@Api(value = "HolidaysController", description = "Exposes APIs to work with Holidays resource.")
@RequestMapping("/cx2/Holidays")
public class HolidaysController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HolidaysController.class);

    @Autowired
	@Qualifier("cx2.HolidaysService")
	private HolidaysService holidaysService;

	@ApiOperation(value = "Creates a new Holidays instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Holidays createHolidays(@RequestBody Holidays holidays) {
		LOGGER.debug("Create Holidays with information: {}" , holidays);

		holidays = holidaysService.create(holidays);
		LOGGER.debug("Created Holidays with information: {}" , holidays);

	    return holidays;
	}


    @ApiOperation(value = "Returns the Holidays instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Holidays getHolidays(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Holidays with id: {}" , id);

        Holidays foundHolidays = holidaysService.getById(id);
        LOGGER.debug("Holidays details with id: {}" , foundHolidays);

        return foundHolidays;
    }

    @ApiOperation(value = "Updates the Holidays instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Holidays editHolidays(@PathVariable("id") Integer id, @RequestBody Holidays holidays) throws EntityNotFoundException {
        LOGGER.debug("Editing Holidays with id: {}" , holidays.getId());

        holidays.setId(id);
        holidays = holidaysService.update(holidays);
        LOGGER.debug("Holidays details with id: {}" , holidays);

        return holidays;
    }

    @ApiOperation(value = "Deletes the Holidays instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteHolidays(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Holidays with id: {}" , id);

        Holidays deletedHolidays = holidaysService.delete(id);

        return deletedHolidays != null;
    }

    /**
     * @deprecated Use {@link #findHolidays(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Holidays instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Holidays> searchHolidaysByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Holidays list");
        return holidaysService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Holidays instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Holidays> findHolidays(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Holidays list");
        return holidaysService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Holidays instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Holidays> filterHolidays(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Holidays list");
        return holidaysService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportHolidays(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return holidaysService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Holidays instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countHolidays( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Holidays");
		return holidaysService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service HolidaysService instance
	 */
	protected void setHolidaysService(HolidaysService service) {
		this.holidaysService = service;
	}

}


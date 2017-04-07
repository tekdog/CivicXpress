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

import com.civicxpress.cx2.TransactionToFees;
import com.civicxpress.cx2.service.TransactionToFeesService;


/**
 * Controller object for domain model class TransactionToFees.
 * @see TransactionToFees
 */
@RestController("cx2.TransactionToFeesController")
@Api(value = "TransactionToFeesController", description = "Exposes APIs to work with TransactionToFees resource.")
@RequestMapping("/cx2/TransactionToFees")
public class TransactionToFeesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionToFeesController.class);

    @Autowired
	@Qualifier("cx2.TransactionToFeesService")
	private TransactionToFeesService transactionToFeesService;

	@ApiOperation(value = "Creates a new TransactionToFees instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public TransactionToFees createTransactionToFees(@RequestBody TransactionToFees transactionToFees) {
		LOGGER.debug("Create TransactionToFees with information: {}" , transactionToFees);

		transactionToFees = transactionToFeesService.create(transactionToFees);
		LOGGER.debug("Created TransactionToFees with information: {}" , transactionToFees);

	    return transactionToFees;
	}


    @ApiOperation(value = "Returns the TransactionToFees instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionToFees getTransactionToFees(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting TransactionToFees with id: {}" , id);

        TransactionToFees foundTransactionToFees = transactionToFeesService.getById(id);
        LOGGER.debug("TransactionToFees details with id: {}" , foundTransactionToFees);

        return foundTransactionToFees;
    }

    @ApiOperation(value = "Updates the TransactionToFees instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionToFees editTransactionToFees(@PathVariable("id") Integer id, @RequestBody TransactionToFees transactionToFees) throws EntityNotFoundException {
        LOGGER.debug("Editing TransactionToFees with id: {}" , transactionToFees.getId());

        transactionToFees.setId(id);
        transactionToFees = transactionToFeesService.update(transactionToFees);
        LOGGER.debug("TransactionToFees details with id: {}" , transactionToFees);

        return transactionToFees;
    }

    @ApiOperation(value = "Deletes the TransactionToFees instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteTransactionToFees(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting TransactionToFees with id: {}" , id);

        TransactionToFees deletedTransactionToFees = transactionToFeesService.delete(id);

        return deletedTransactionToFees != null;
    }

    /**
     * @deprecated Use {@link #findTransactionToFees(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of TransactionToFees instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionToFees> searchTransactionToFeesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering TransactionToFees list");
        return transactionToFeesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TransactionToFees instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionToFees> findTransactionToFees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TransactionToFees list");
        return transactionToFeesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TransactionToFees instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionToFees> filterTransactionToFees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TransactionToFees list");
        return transactionToFeesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTransactionToFees(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return transactionToFeesService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of TransactionToFees instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countTransactionToFees( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting TransactionToFees");
		return transactionToFeesService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service TransactionToFeesService instance
	 */
	protected void setTransactionToFeesService(TransactionToFeesService service) {
		this.transactionToFeesService = service;
	}

}


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

import com.civicxpress.cx2.UserPasswordResetTokens;
import com.civicxpress.cx2.service.UserPasswordResetTokensService;


/**
 * Controller object for domain model class UserPasswordResetTokens.
 * @see UserPasswordResetTokens
 */
@RestController("cx2.UserPasswordResetTokensController")
@Api(value = "UserPasswordResetTokensController", description = "Exposes APIs to work with UserPasswordResetTokens resource.")
@RequestMapping("/cx2/UserPasswordResetTokens")
public class UserPasswordResetTokensController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPasswordResetTokensController.class);

    @Autowired
	@Qualifier("cx2.UserPasswordResetTokensService")
	private UserPasswordResetTokensService userPasswordResetTokensService;

	@ApiOperation(value = "Creates a new UserPasswordResetTokens instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public UserPasswordResetTokens createUserPasswordResetTokens(@RequestBody UserPasswordResetTokens userPasswordResetTokens) {
		LOGGER.debug("Create UserPasswordResetTokens with information: {}" , userPasswordResetTokens);

		userPasswordResetTokens = userPasswordResetTokensService.create(userPasswordResetTokens);
		LOGGER.debug("Created UserPasswordResetTokens with information: {}" , userPasswordResetTokens);

	    return userPasswordResetTokens;
	}


    @ApiOperation(value = "Returns the UserPasswordResetTokens instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserPasswordResetTokens getUserPasswordResetTokens(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting UserPasswordResetTokens with id: {}" , id);

        UserPasswordResetTokens foundUserPasswordResetTokens = userPasswordResetTokensService.getById(id);
        LOGGER.debug("UserPasswordResetTokens details with id: {}" , foundUserPasswordResetTokens);

        return foundUserPasswordResetTokens;
    }

    @ApiOperation(value = "Updates the UserPasswordResetTokens instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserPasswordResetTokens editUserPasswordResetTokens(@PathVariable("id") Integer id, @RequestBody UserPasswordResetTokens userPasswordResetTokens) throws EntityNotFoundException {
        LOGGER.debug("Editing UserPasswordResetTokens with id: {}" , userPasswordResetTokens.getId());

        userPasswordResetTokens.setId(id);
        userPasswordResetTokens = userPasswordResetTokensService.update(userPasswordResetTokens);
        LOGGER.debug("UserPasswordResetTokens details with id: {}" , userPasswordResetTokens);

        return userPasswordResetTokens;
    }

    @ApiOperation(value = "Deletes the UserPasswordResetTokens instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteUserPasswordResetTokens(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting UserPasswordResetTokens with id: {}" , id);

        UserPasswordResetTokens deletedUserPasswordResetTokens = userPasswordResetTokensService.delete(id);

        return deletedUserPasswordResetTokens != null;
    }

    /**
     * @deprecated Use {@link #findUserPasswordResetTokens(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of UserPasswordResetTokens instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserPasswordResetTokens> searchUserPasswordResetTokensByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering UserPasswordResetTokens list");
        return userPasswordResetTokensService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of UserPasswordResetTokens instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserPasswordResetTokens> findUserPasswordResetTokens(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering UserPasswordResetTokens list");
        return userPasswordResetTokensService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of UserPasswordResetTokens instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserPasswordResetTokens> filterUserPasswordResetTokens(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering UserPasswordResetTokens list");
        return userPasswordResetTokensService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportUserPasswordResetTokens(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return userPasswordResetTokensService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of UserPasswordResetTokens instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countUserPasswordResetTokens( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting UserPasswordResetTokens");
		return userPasswordResetTokensService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserPasswordResetTokensService instance
	 */
	protected void setUserPasswordResetTokensService(UserPasswordResetTokensService service) {
		this.userPasswordResetTokensService = service;
	}

}


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

import com.civicxpress.cx2.TestRichText;
import com.civicxpress.cx2.service.TestRichTextService;


/**
 * Controller object for domain model class TestRichText.
 * @see TestRichText
 */
@RestController("cx2.TestRichTextController")
@Api(value = "TestRichTextController", description = "Exposes APIs to work with TestRichText resource.")
@RequestMapping("/cx2/TestRichText")
public class TestRichTextController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRichTextController.class);

    @Autowired
	@Qualifier("cx2.TestRichTextService")
	private TestRichTextService testRichTextService;

	@ApiOperation(value = "Creates a new TestRichText instance.")
	@RequestMapping(method = RequestMethod.POST)
        @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public TestRichText createTestRichText(@RequestBody TestRichText testRichText) {
		LOGGER.debug("Create TestRichText with information: {}" , testRichText);

		testRichText = testRichTextService.create(testRichText);
		LOGGER.debug("Created TestRichText with information: {}" , testRichText);

	    return testRichText;
	}


    @ApiOperation(value = "Returns the TestRichText instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TestRichText getTestRichText(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting TestRichText with id: {}" , id);

        TestRichText foundTestRichText = testRichTextService.getById(id);
        LOGGER.debug("TestRichText details with id: {}" , foundTestRichText);

        return foundTestRichText;
    }

    @ApiOperation(value = "Updates the TestRichText instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TestRichText editTestRichText(@PathVariable("id") Integer id, @RequestBody TestRichText testRichText) throws EntityNotFoundException {
        LOGGER.debug("Editing TestRichText with id: {}" , testRichText.getId());

        testRichText.setId(id);
        testRichText = testRichTextService.update(testRichText);
        LOGGER.debug("TestRichText details with id: {}" , testRichText);

        return testRichText;
    }

    @ApiOperation(value = "Deletes the TestRichText instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteTestRichText(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting TestRichText with id: {}" , id);

        TestRichText deletedTestRichText = testRichTextService.delete(id);

        return deletedTestRichText != null;
    }

    /**
     * @deprecated Use {@link #findTestRichTexts(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of TestRichText instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TestRichText> searchTestRichTextsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering TestRichTexts list");
        return testRichTextService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of TestRichText instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TestRichText> findTestRichTexts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TestRichTexts list");
        return testRichTextService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTestRichTexts(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return testRichTextService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of TestRichText instances.")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countTestRichTexts( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting TestRichTexts");
		return testRichTextService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service TestRichTextService instance
	 */
	protected void setTestRichTextService(TestRichTextService service) {
		this.testRichTextService = service;
	}

}


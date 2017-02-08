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
import com.civicxpress.cx2.FormMessageTagging;
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.service.FormMessagesService;

/**
 * Controller object for domain model class FormMessages.
 * @see FormMessages
 */
@RestController("cx2.FormMessagesController")
@Api(value = "FormMessagesController", description = "Exposes APIs to work with FormMessages resource.")
@RequestMapping("/cx2/FormMessages")
public class FormMessagesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormMessagesController.class);

    @Autowired
    @Qualifier("cx2.FormMessagesService")
    private FormMessagesService formMessagesService;

    @ApiOperation(value = "Creates a new FormMessages instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormMessages createFormMessages(@RequestBody FormMessages formMessages) {
        LOGGER.debug("Create FormMessages with information: {}", formMessages);
        formMessages = formMessagesService.create(formMessages);
        LOGGER.debug("Created FormMessages with information: {}", formMessages);
        return formMessages;
    }

    @ApiOperation(value = "Returns the FormMessages instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormMessages getFormMessages(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting FormMessages with id: {}", id);
        FormMessages foundFormMessages = formMessagesService.getById(id);
        LOGGER.debug("FormMessages details with id: {}", foundFormMessages);
        return foundFormMessages;
    }

    @ApiOperation(value = "Updates the FormMessages instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormMessages editFormMessages(@PathVariable("id") Integer id, @RequestBody FormMessages formMessages) throws EntityNotFoundException {
        LOGGER.debug("Editing FormMessages with id: {}", formMessages.getId());
        formMessages.setId(id);
        formMessages = formMessagesService.update(formMessages);
        LOGGER.debug("FormMessages details with id: {}", formMessages);
        return formMessages;
    }

    @ApiOperation(value = "Deletes the FormMessages instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteFormMessages(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormMessages with id: {}", id);
        FormMessages deletedFormMessages = formMessagesService.delete(id);
        return deletedFormMessages != null;
    }

    /**
     * @deprecated Use {@link #findFormMessages(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of FormMessages instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessages> searchFormMessagesByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FormMessages list");
        return formMessagesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of FormMessages instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessages> findFormMessages(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormMessages list");
        return formMessagesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportFormMessages(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return formMessagesService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of FormMessages instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countFormMessages(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting FormMessages");
        return formMessagesService.count(query);
    }

    @RequestMapping(value = "/{id:.+}/formMessageTaggings", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the formMessageTaggings instance associated with the given id.")
    public Page<FormMessageTagging> findAssociatedFormMessageTaggings(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageTaggings");
        return formMessagesService.findAssociatedFormMessageTaggings(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormMessagesService instance
	 */
    protected void setFormMessagesService(FormMessagesService service) {
        this.formMessagesService = service;
    }
}

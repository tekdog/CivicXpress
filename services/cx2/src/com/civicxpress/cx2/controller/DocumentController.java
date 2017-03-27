/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.TypeMismatchException;
import org.joda.time.LocalDateTime;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.civicxpress.cx2.Document;
import com.civicxpress.cx2.DocumentId;
import com.civicxpress.cx2.service.DocumentService;


/**
 * Controller object for domain model class Document.
 * @see Document
 */
@RestController("cx2.DocumentController")
@Api(value = "DocumentController", description = "Exposes APIs to work with Document resource.")
@RequestMapping("/cx2/Document")
public class DocumentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
	@Qualifier("cx2.DocumentService")
	private DocumentService documentService;

	@ApiOperation(value = "Creates a new Document instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Document createDocument(@RequestBody Document document) {
		LOGGER.debug("Create Document with information: {}" , document);

		document = documentService.create(document);
		LOGGER.debug("Created Document with information: {}" , document);

	    return document;
	}

	@ApiOperation(value = "Creates a new Document instance.This API should be used when the Document instance has fields that requires multipart data.")
	@RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Document createDocument(MultipartHttpServletRequest multipartHttpServletRequest) {
    	Document document = WMMultipartUtils.toObject(multipartHttpServletRequest, Document.class, "cx2"); 
        LOGGER.debug("Creating a new Document with information: {}" , document);
        return documentService.create(document);
    }

    @ApiOperation(value = "Returns the Document instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Document getDocument(@RequestParam("id") BigInteger id,@RequestParam("itemGuid") String itemGuid,@RequestParam("filename") String filename,@RequestParam("mimetype") String mimetype,@RequestParam("contents") byte[] contents,@RequestParam("dateCreated") LocalDateTime dateCreated) throws EntityNotFoundException {

        DocumentId documentId = new DocumentId();
        documentId.setId(id);
        documentId.setItemGuid(itemGuid);
        documentId.setFilename(filename);
        documentId.setMimetype(mimetype);
        documentId.setContents(contents);
        documentId.setDateCreated(dateCreated);

        LOGGER.debug("Getting Document with id: {}" , documentId);
        Document document = documentService.getById(documentId);
        LOGGER.debug("Document details with id: {}" , document);

        return document;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Document instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id/content/{fieldName}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public void getDocumentBLOBContent(@RequestParam("id") BigInteger id,@RequestParam("itemGuid") String itemGuid,@RequestParam("filename") String filename,@RequestParam("mimetype") String mimetype,@RequestParam("contents") byte[] contents,@RequestParam("dateCreated") LocalDateTime dateCreated, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws EntityNotFoundException {

        LOGGER.debug("Retrieves content for the given BLOB field {} in Document instance" , fieldName);

        if(!WMRuntimeUtils.isLob(Document.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName +  " is not a valid BLOB type");
        }

        DocumentId documentId = new DocumentId();
        documentId.setId(id);
        documentId.setItemGuid(itemGuid);
        documentId.setFilename(filename);
        documentId.setMimetype(mimetype);
        documentId.setContents(contents);
        documentId.setDateCreated(dateCreated);

        Document document = documentService.getById(documentId);
        WMMultipartUtils.buildHttpResponseForBlob(document, fieldName, httpServletRequest, httpServletResponse);
    }



    @ApiOperation(value = "Updates the Document instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Document editDocument(@RequestParam("id") BigInteger id,@RequestParam("itemGuid") String itemGuid,@RequestParam("filename") String filename,@RequestParam("mimetype") String mimetype,@RequestParam("contents") byte[] contents,@RequestParam("dateCreated") LocalDateTime dateCreated, @RequestBody Document document) throws EntityNotFoundException {

        document.setId(id);
        document.setItemGuid(itemGuid);
        document.setFilename(filename);
        document.setMimetype(mimetype);
        document.setContents(contents);
        document.setDateCreated(dateCreated);

        LOGGER.debug("Document details with id is updated with: {}" , document);

        return documentService.update(document);
    }

    @ApiOperation(value = "Updates the Document instance associated with the given composite-id.This API should be used when Document instance fields that require multipart data.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.POST, consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Document editDocument(@RequestParam("id") BigInteger id,@RequestParam("itemGuid") String itemGuid,@RequestParam("filename") String filename,@RequestParam("mimetype") String mimetype,@RequestParam("contents") byte[] contents,@RequestParam("dateCreated") LocalDateTime dateCreated, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        return this.editDocumentAndMultiparts(id, itemGuid, filename, mimetype, contents, dateCreated, multipartHttpServletRequest);
    }

    @ApiOperation(value = "Updates the Document instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT,  consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Document editDocumentAndMultiparts(@RequestParam("id") BigInteger id,@RequestParam("itemGuid") String itemGuid,@RequestParam("filename") String filename,@RequestParam("mimetype") String mimetype,@RequestParam("contents") byte[] contents,@RequestParam("dateCreated") LocalDateTime dateCreated, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException { 

        DocumentId documentId = new DocumentId();
        documentId.setId(id);
        documentId.setItemGuid(itemGuid);
        documentId.setFilename(filename);
        documentId.setMimetype(mimetype);
        documentId.setContents(contents);
        documentId.setDateCreated(dateCreated);

        Document newDocument = WMMultipartUtils.toObject(multipartHttpServletRequest, Document.class, "cx2");
        Document oldDocument = documentService.getById(documentId);

        WMMultipartUtils.updateLobsContent(oldDocument, newDocument);

        newDocument.setId(id);
        newDocument.setItemGuid(itemGuid);
        newDocument.setFilename(filename);
        newDocument.setMimetype(mimetype);
        newDocument.setContents(contents);
        newDocument.setDateCreated(dateCreated);

        LOGGER.debug("Document details with id is updated with: {}" , newDocument);

        return documentService.update(newDocument);
    }


    @ApiOperation(value = "Deletes the Document instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteDocument(@RequestParam("id") BigInteger id,@RequestParam("itemGuid") String itemGuid,@RequestParam("filename") String filename,@RequestParam("mimetype") String mimetype,@RequestParam("contents") byte[] contents,@RequestParam("dateCreated") LocalDateTime dateCreated) throws EntityNotFoundException {

        DocumentId documentId = new DocumentId();
        documentId.setId(id);
        documentId.setItemGuid(itemGuid);
        documentId.setFilename(filename);
        documentId.setMimetype(mimetype);
        documentId.setContents(contents);
        documentId.setDateCreated(dateCreated);

        LOGGER.debug("Deleting Document with id: {}" , documentId);
        Document document = documentService.delete(documentId);

        return document != null;
    }


    /**
     * @deprecated Use {@link #findDocuments(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Document instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Document> searchDocumentsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Documents list");
        return documentService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Document instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Document> findDocuments(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Documents list");
        return documentService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Document instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Document> filterDocuments(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Documents list");
        return documentService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportDocuments(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return documentService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Document instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countDocuments( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Documents");
		return documentService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service DocumentService instance
	 */
	protected void setDocumentService(DocumentService service) {
		this.documentService = service;
	}

}


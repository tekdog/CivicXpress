/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.Document;
import com.civicxpress.cx2.DocumentId;


/**
 * ServiceImpl object for domain model class Document.
 *
 * @see Document
 */
@Service("cx2.DocumentService")
public class DocumentServiceImpl implements DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);


    @Autowired
    @Qualifier("cx2.DocumentDao")
    private WMGenericDao<Document, DocumentId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Document, DocumentId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Document create(Document document) {
        LOGGER.debug("Creating a new Document with information: {}", document);
        Document documentCreated = this.wmGenericDao.create(document);
        return documentCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Document getById(DocumentId documentId) throws EntityNotFoundException {
        LOGGER.debug("Finding Document by id: {}", documentId);
        Document document = this.wmGenericDao.findById(documentId);
        if (document == null){
            LOGGER.debug("No Document found with id: {}", documentId);
            throw new EntityNotFoundException(String.valueOf(documentId));
        }
        return document;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Document findById(DocumentId documentId) {
        LOGGER.debug("Finding Document by id: {}", documentId);
        return this.wmGenericDao.findById(documentId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Document update(Document document) throws EntityNotFoundException {
        LOGGER.debug("Updating Document with information: {}", document);
        this.wmGenericDao.update(document);

        DocumentId documentId = new DocumentId();
        documentId.setId(document.getId());
        documentId.setItemGuid(document.getItemGuid());
        documentId.setFilename(document.getFilename());
        documentId.setMimetype(document.getMimetype());
        documentId.setContents(document.getContents());
        documentId.setDateCreated(document.getDateCreated());
        documentId.setCreatedBy(document.getCreatedBy());

        return this.wmGenericDao.findById(documentId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Document delete(DocumentId documentId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Document with id: {}", documentId);
        Document deleted = this.wmGenericDao.findById(documentId);
        if (deleted == null) {
            LOGGER.debug("No Document found with id: {}", documentId);
            throw new EntityNotFoundException(String.valueOf(documentId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Document> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Documents");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Document> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Documents");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Document to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}


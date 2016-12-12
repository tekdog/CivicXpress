/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.TypeMismatchException;
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
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.civicxpress.cx2.FormFee;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.Holidays;
import com.civicxpress.cx2.ManualFeeTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Roles;
import com.civicxpress.cx2.Subdivisions;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.VendorApprovals;
import com.civicxpress.cx2.service.MunicipalitiesService;

/**
 * Controller object for domain model class Municipalities.
 * @see Municipalities
 */
@RestController("cx2.MunicipalitiesController")
@Api(value = "MunicipalitiesController", description = "Exposes APIs to work with Municipalities resource.")
@RequestMapping("/cx2/Municipalities")
public class MunicipalitiesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MunicipalitiesController.class);

    @Autowired
    @Qualifier("cx2.MunicipalitiesService")
    private MunicipalitiesService municipalitiesService;

    @ApiOperation(value = "Creates a new Municipalities instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Municipalities createMunicipalities(@RequestBody Municipalities municipalities) {
        LOGGER.debug("Create Municipalities with information: {}", municipalities);
        municipalities = municipalitiesService.create(municipalities);
        LOGGER.debug("Created Municipalities with information: {}", municipalities);
        return municipalities;
    }

    @ApiOperation(value = "Creates a new Municipalities instance.This API should be used when the Municipalities instance has fields that requires multipart data.")
    @RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Municipalities createMunicipalities(MultipartHttpServletRequest multipartHttpServletRequest) {
        Municipalities municipalities = WMMultipartUtils.toObject(multipartHttpServletRequest, Municipalities.class, "cx2");
        LOGGER.debug("Creating a new Municipalities with information: {}", municipalities);
        return municipalitiesService.create(municipalities);
    }

    @ApiOperation(value = "Returns the Municipalities instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Municipalities getMunicipalities(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Municipalities with id: {}", id);
        Municipalities foundMunicipalities = municipalitiesService.getById(id);
        LOGGER.debug("Municipalities details with id: {}", foundMunicipalities);
        return foundMunicipalities;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Municipalities instance")
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getMunicipalitiesBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value = "download", defaultValue = "false") boolean download) {
        LOGGER.debug("Retrieves content for the given BLOB field {} in Municipalities instance", fieldName);
        if (!WMRuntimeUtils.isLob(Municipalities.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        Municipalities municipalities = municipalitiesService.getById(id);
        return WMMultipartUtils.buildDownloadResponseForBlob(municipalities, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the Municipalities instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Municipalities editMunicipalities(@PathVariable("id") Integer id, @RequestBody Municipalities municipalities) throws EntityNotFoundException {
        LOGGER.debug("Editing Municipalities with id: {}", municipalities.getId());
        municipalities.setId(id);
        municipalities = municipalitiesService.update(municipalities);
        LOGGER.debug("Municipalities details with id: {}", municipalities);
        return municipalities;
    }

    @ApiOperation(value = "Updates the Municipalities instance associated with the given id.This API should be used when Municipalities instance fields that require multipart data.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Municipalities editMunicipalities(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        Municipalities newMunicipalities = WMMultipartUtils.toObject(multipartHttpServletRequest, Municipalities.class, "cx2");
        newMunicipalities.setId(id);
        Municipalities oldMunicipalities = municipalitiesService.getById(id);
        WMMultipartUtils.updateLobsContent(oldMunicipalities, newMunicipalities);
        LOGGER.debug("Updating Municipalities with information: {}", newMunicipalities);
        return municipalitiesService.update(newMunicipalities);
    }

    @ApiOperation(value = "Deletes the Municipalities instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMunicipalities(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Municipalities with id: {}", id);
        Municipalities deletedMunicipalities = municipalitiesService.delete(id);
        return deletedMunicipalities != null;
    }

    /**
     * @deprecated Use {@link #findMunicipalities(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Municipalities instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Municipalities> findMunicipalities(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Municipalities list");
        return municipalitiesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Municipalities instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Municipalities> findMunicipalities(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Municipalities list");
        return municipalitiesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMunicipalities(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return municipalitiesService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Municipalities instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countMunicipalities(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Municipalities");
        return municipalitiesService.count(query);
    }

    @RequestMapping(value = "/{id}/formTypeses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the formTypeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypes> findAssociatedFormTypeses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formTypeses");
        return municipalitiesService.findAssociatedFormTypeses(id, pageable);
    }

    @RequestMapping(value = "/{id}/manualFeeTypeses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the manualFeeTypeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ManualFeeTypes> findAssociatedManualFeeTypeses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated manualFeeTypeses");
        return municipalitiesService.findAssociatedManualFeeTypeses(id, pageable);
    }

    @RequestMapping(value = "/{id}/masterFormses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the masterFormses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterForms> findAssociatedMasterFormses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");
        return municipalitiesService.findAssociatedMasterFormses(id, pageable);
    }

    @RequestMapping(value = "/{id}/vendorApprovalses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorApprovalses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorApprovals> findAssociatedVendorApprovalses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorApprovalses");
        return municipalitiesService.findAssociatedVendorApprovalses(id, pageable);
    }

    @RequestMapping(value = "/{id}/municipalityGroupses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the municipalityGroupses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MunicipalityGroups> findAssociatedMunicipalityGroupses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalityGroupses");
        return municipalitiesService.findAssociatedMunicipalityGroupses(id, pageable);
    }

    @RequestMapping(value = "/{id}/subdivisionses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the subdivisionses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Subdivisions> findAssociatedSubdivisionses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated subdivisionses");
        return municipalitiesService.findAssociatedSubdivisionses(id, pageable);
    }

    @RequestMapping(value = "/{id}/roleses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the roleses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Roles> findAssociatedRoleses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated roleses");
        return municipalitiesService.findAssociatedRoleses(id, pageable);
    }

    @RequestMapping(value = "/{id}/gisrecordses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the gisrecordses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gisrecords> findAssociatedGisrecordses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated gisrecordses");
        return municipalitiesService.findAssociatedGisrecordses(id, pageable);
    }

    @RequestMapping(value = "/{id}/userSubscriptionses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the userSubscriptionses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserSubscriptions> findAssociatedUserSubscriptionses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userSubscriptionses");
        return municipalitiesService.findAssociatedUserSubscriptionses(id, pageable);
    }

    @RequestMapping(value = "/{id}/holidayses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the holidayses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Holidays> findAssociatedHolidayses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated holidayses");
        return municipalitiesService.findAssociatedHolidayses(id, pageable);
    }

    @RequestMapping(value = "/{id}/formFees", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the formFees instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormFee> findAssociatedFormFees(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formFees");
        return municipalitiesService.findAssociatedFormFees(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MunicipalitiesService instance
	 */
    protected void setMunicipalitiesService(MunicipalitiesService service) {
        this.municipalitiesService = service;
    }
}

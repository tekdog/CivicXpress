/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

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
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Vendor;
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.VendorApprovals;
import com.civicxpress.cx2.VendorLicenses;
import com.civicxpress.cx2.VendorUsers;
import com.civicxpress.cx2.Vendors2form;
import com.civicxpress.cx2.VendorsToProject;
import com.civicxpress.cx2.service.VendorService;


/**
 * Controller object for domain model class Vendor.
 * @see Vendor
 */
@RestController("cx2.VendorController")
@Api(value = "VendorController", description = "Exposes APIs to work with Vendor resource.")
@RequestMapping("/cx2/Vendor")
public class VendorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

    @Autowired
	@Qualifier("cx2.VendorService")
	private VendorService vendorService;

	@ApiOperation(value = "Creates a new Vendor instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Vendor createVendor(@RequestBody Vendor vendor) {
		LOGGER.debug("Create Vendor with information: {}" , vendor);

		vendor = vendorService.create(vendor);
		LOGGER.debug("Created Vendor with information: {}" , vendor);

	    return vendor;
	}

	@ApiOperation(value = "Creates a new Vendor instance.This API should be used when the Vendor instance has fields that requires multipart data.")
	@RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor createVendor(MultipartHttpServletRequest multipartHttpServletRequest) {
    	Vendor vendor = WMMultipartUtils.toObject(multipartHttpServletRequest, Vendor.class, "cx2"); 
        LOGGER.debug("Creating a new Vendor with information: {}" , vendor);
        return vendorService.create(vendor);
    }


    @ApiOperation(value = "Returns the Vendor instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor getVendor(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Vendor with id: {}" , id);

        Vendor foundVendor = vendorService.getById(id);
        LOGGER.debug("Vendor details with id: {}" , foundVendor);

        return foundVendor;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Vendor instance" )
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces="application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getVendorBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value="download", defaultValue = "false") boolean download) {

        LOGGER.debug("Retrieves content for the given BLOB field {} in Vendor instance" , fieldName);

        if(!WMRuntimeUtils.isLob(Vendor.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        Vendor vendor = vendorService.getById(id);

        return WMMultipartUtils.buildDownloadResponseForBlob(vendor, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the Vendor instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor editVendor(@PathVariable("id") Integer id, @RequestBody Vendor vendor) throws EntityNotFoundException {
        LOGGER.debug("Editing Vendor with id: {}" , vendor.getId());

        vendor.setId(id);
        vendor = vendorService.update(vendor);
        LOGGER.debug("Vendor details with id: {}" , vendor);

        return vendor;
    }

    @ApiOperation(value = "Updates the Vendor instance associated with the given id.This API should be used when Vendor instance fields that require multipart data.") 
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor editVendor(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        Vendor newVendor = WMMultipartUtils.toObject(multipartHttpServletRequest, Vendor.class, "cx2");
        newVendor.setId(id);

        Vendor oldVendor = vendorService.getById(id);
        WMMultipartUtils.updateLobsContent(oldVendor, newVendor);
        LOGGER.debug("Updating Vendor with information: {}" , newVendor);

        return vendorService.update(newVendor);
    }

    @ApiOperation(value = "Deletes the Vendor instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVendor(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Vendor with id: {}" , id);

        Vendor deletedVendor = vendorService.delete(id);

        return deletedVendor != null;
    }

    @RequestMapping(value = "/feinNumber/{feinNumber}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Vendor with given unique key values.")
    public Vendor getByFeinNumber(@PathVariable("feinNumber") String feinNumber) {
        LOGGER.debug("Getting Vendor with uniques key FeinNumber");
        return vendorService.getByFeinNumber(feinNumber);
    }

    /**
     * @deprecated Use {@link #findVendors(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Vendor instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendor> searchVendorsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Vendors list");
        return vendorService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Vendor instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendor> findVendors(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vendors list");
        return vendorService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Vendor instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendor> filterVendors(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vendors list");
        return vendorService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVendors(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return vendorService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Vendor instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countVendors( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Vendors");
		return vendorService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getVendorAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return vendorService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/feeses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the feeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Fees> findAssociatedFeeses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated feeses");
        return vendorService.findAssociatedFeeses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterFormses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterFormses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterForms> findAssociatedMasterFormses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterFormses");
        return vendorService.findAssociatedMasterFormses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Projects> findAssociatedProjectses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectses");
        return vendorService.findAssociatedProjectses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorAdminses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorAdminses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorAdmins> findAssociatedVendorAdminses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorAdminses");
        return vendorService.findAssociatedVendorAdminses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorApprovalses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorApprovalses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorApprovals> findAssociatedVendorApprovalses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorApprovalses");
        return vendorService.findAssociatedVendorApprovalses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorLicenseses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorLicenseses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorLicenses> findAssociatedVendorLicenseses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorLicenseses");
        return vendorService.findAssociatedVendorLicenseses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendors2forms", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendors2forms instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendors2form> findAssociatedVendors2forms(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendors2forms");
        return vendorService.findAssociatedVendors2forms(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorsToProjects", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorsToProjects instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorsToProject> findAssociatedVendorsToProjects(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorsToProjects");
        return vendorService.findAssociatedVendorsToProjects(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorUserses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorUserses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorUsers> findAssociatedVendorUserses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorUserses");
        return vendorService.findAssociatedVendorUserses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorService instance
	 */
	protected void setVendorService(VendorService service) {
		this.vendorService = service;
	}

}


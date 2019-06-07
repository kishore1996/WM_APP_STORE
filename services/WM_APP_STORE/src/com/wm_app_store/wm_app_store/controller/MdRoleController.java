/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.controller;

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

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.wm_app_store.wm_app_store.MdRole;
import com.wm_app_store.wm_app_store.UserRole;
import com.wm_app_store.wm_app_store.service.MdRoleService;


/**
 * Controller object for domain model class MdRole.
 * @see MdRole
 */
@RestController("WM_APP_STORE.MdRoleController")
@Api(value = "MdRoleController", description = "Exposes APIs to work with MdRole resource.")
@RequestMapping("/WM_APP_STORE/MdRole")
public class MdRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MdRoleController.class);

    @Autowired
	@Qualifier("WM_APP_STORE.MdRoleService")
	private MdRoleService mdRoleService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new MdRole instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MdRole createMdRole(@RequestBody MdRole mdRole) {
		LOGGER.debug("Create MdRole with information: {}" , mdRole);

		mdRole = mdRoleService.create(mdRole);
		LOGGER.debug("Created MdRole with information: {}" , mdRole);

	    return mdRole;
	}

    @ApiOperation(value = "Returns the MdRole instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MdRole getMdRole(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting MdRole with id: {}" , id);

        MdRole foundMdRole = mdRoleService.getById(id);
        LOGGER.debug("MdRole details with id: {}" , foundMdRole);

        return foundMdRole;
    }

    @ApiOperation(value = "Updates the MdRole instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MdRole editMdRole(@PathVariable("id") Integer id, @RequestBody MdRole mdRole) {
        LOGGER.debug("Editing MdRole with id: {}" , mdRole.getId());

        mdRole.setId(id);
        mdRole = mdRoleService.update(mdRole);
        LOGGER.debug("MdRole details with id: {}" , mdRole);

        return mdRole;
    }
    
    @ApiOperation(value = "Partially updates the MdRole instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MdRole patchMdRole(@PathVariable("id") Integer id, @RequestBody @MapTo(MdRole.class) Map<String, Object> mdRolePatch) {
        LOGGER.debug("Partially updating MdRole with id: {}" , id);

        MdRole mdRole = mdRoleService.partialUpdate(id, mdRolePatch);
        LOGGER.debug("MdRole details after partial update: {}" , mdRole);

        return mdRole;
    }

    @ApiOperation(value = "Deletes the MdRole instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMdRole(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting MdRole with id: {}" , id);

        MdRole deletedMdRole = mdRoleService.delete(id);

        return deletedMdRole != null;
    }

    /**
     * @deprecated Use {@link #findMdRoles(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of MdRole instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<MdRole> searchMdRolesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering MdRoles list by query filter:{}", (Object) queryFilters);
        return mdRoleService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of MdRole instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MdRole> findMdRoles(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering MdRoles list by filter:", query);
        return mdRoleService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of MdRole instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<MdRole> filterMdRoles(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering MdRoles list by filter", query);
        return mdRoleService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportMdRoles(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return mdRoleService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportMdRolesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = MdRole.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> mdRoleService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of MdRole instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countMdRoles( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting MdRoles");
		return mdRoleService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getMdRoleAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return mdRoleService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/userRoles", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the userRoles instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserRole> findAssociatedUserRoles(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated userRoles");
        return mdRoleService.findAssociatedUserRoles(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MdRoleService instance
	 */
	protected void setMdRoleService(MdRoleService service) {
		this.mdRoleService = service;
	}

}
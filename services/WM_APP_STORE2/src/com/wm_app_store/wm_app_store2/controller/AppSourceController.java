/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store2.controller;

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

import com.wm_app_store.wm_app_store2.AppD0wnloadHostory;
import com.wm_app_store.wm_app_store2.AppSource;
import com.wm_app_store.wm_app_store2.service.AppSourceService;


/**
 * Controller object for domain model class AppSource.
 * @see AppSource
 */
@RestController("WM_APP_STORE2.AppSourceController")
@Api(value = "AppSourceController", description = "Exposes APIs to work with AppSource resource.")
@RequestMapping("/WM_APP_STORE2/AppSource")
public class AppSourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSourceController.class);

    @Autowired
	@Qualifier("WM_APP_STORE2.AppSourceService")
	private AppSourceService appSourceService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new AppSource instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppSource createAppSource(@RequestBody AppSource appSource) {
		LOGGER.debug("Create AppSource with information: {}" , appSource);

		appSource = appSourceService.create(appSource);
		LOGGER.debug("Created AppSource with information: {}" , appSource);

	    return appSource;
	}

    @ApiOperation(value = "Returns the AppSource instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppSource getAppSource(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting AppSource with id: {}" , id);

        AppSource foundAppSource = appSourceService.getById(id);
        LOGGER.debug("AppSource details with id: {}" , foundAppSource);

        return foundAppSource;
    }

    @ApiOperation(value = "Updates the AppSource instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppSource editAppSource(@PathVariable("id") Integer id, @RequestBody AppSource appSource) {
        LOGGER.debug("Editing AppSource with id: {}" , appSource.getId());

        appSource.setId(id);
        appSource = appSourceService.update(appSource);
        LOGGER.debug("AppSource details with id: {}" , appSource);

        return appSource;
    }
    
    @ApiOperation(value = "Partially updates the AppSource instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppSource patchAppSource(@PathVariable("id") Integer id, @RequestBody @MapTo(AppSource.class) Map<String, Object> appSourcePatch) {
        LOGGER.debug("Partially updating AppSource with id: {}" , id);

        AppSource appSource = appSourceService.partialUpdate(id, appSourcePatch);
        LOGGER.debug("AppSource details after partial update: {}" , appSource);

        return appSource;
    }

    @ApiOperation(value = "Deletes the AppSource instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAppSource(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting AppSource with id: {}" , id);

        AppSource deletedAppSource = appSourceService.delete(id);

        return deletedAppSource != null;
    }

    /**
     * @deprecated Use {@link #findAppSources(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of AppSource instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppSource> searchAppSourcesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering AppSources list by query filter:{}", (Object) queryFilters);
        return appSourceService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppSource instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppSource> findAppSources(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppSources list by filter:", query);
        return appSourceService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppSource instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppSource> filterAppSources(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppSources list by filter", query);
        return appSourceService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportAppSources(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return appSourceService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportAppSourcesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = AppSource.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> appSourceService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of AppSource instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countAppSources( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting AppSources");
		return appSourceService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getAppSourceAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return appSourceService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/appD0wnloadHostories", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the appD0wnloadHostories instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppD0wnloadHostory> findAssociatedAppD0wnloadHostories(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated appD0wnloadHostories");
        return appSourceService.findAssociatedAppD0wnloadHostories(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AppSourceService instance
	 */
	protected void setAppSourceService(AppSourceService service) {
		this.appSourceService = service;
	}

}

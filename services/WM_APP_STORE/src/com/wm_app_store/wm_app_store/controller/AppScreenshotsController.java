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

import com.wm_app_store.wm_app_store.AppScreenshots;
import com.wm_app_store.wm_app_store.service.AppScreenshotsService;


/**
 * Controller object for domain model class AppScreenshots.
 * @see AppScreenshots
 */
@RestController("WM_APP_STORE.AppScreenshotsController")
@Api(value = "AppScreenshotsController", description = "Exposes APIs to work with AppScreenshots resource.")
@RequestMapping("/WM_APP_STORE/AppScreenshots")
public class AppScreenshotsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppScreenshotsController.class);

    @Autowired
	@Qualifier("WM_APP_STORE.AppScreenshotsService")
	private AppScreenshotsService appScreenshotsService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new AppScreenshots instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppScreenshots createAppScreenshots(@RequestBody AppScreenshots appScreenshots) {
		LOGGER.debug("Create AppScreenshots with information: {}" , appScreenshots);

		appScreenshots = appScreenshotsService.create(appScreenshots);
		LOGGER.debug("Created AppScreenshots with information: {}" , appScreenshots);

	    return appScreenshots;
	}

    @ApiOperation(value = "Returns the AppScreenshots instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppScreenshots getAppScreenshots(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting AppScreenshots with id: {}" , id);

        AppScreenshots foundAppScreenshots = appScreenshotsService.getById(id);
        LOGGER.debug("AppScreenshots details with id: {}" , foundAppScreenshots);

        return foundAppScreenshots;
    }

    @ApiOperation(value = "Updates the AppScreenshots instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppScreenshots editAppScreenshots(@PathVariable("id") Integer id, @RequestBody AppScreenshots appScreenshots) {
        LOGGER.debug("Editing AppScreenshots with id: {}" , appScreenshots.getId());

        appScreenshots.setId(id);
        appScreenshots = appScreenshotsService.update(appScreenshots);
        LOGGER.debug("AppScreenshots details with id: {}" , appScreenshots);

        return appScreenshots;
    }
    
    @ApiOperation(value = "Partially updates the AppScreenshots instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppScreenshots patchAppScreenshots(@PathVariable("id") Integer id, @RequestBody @MapTo(AppScreenshots.class) Map<String, Object> appScreenshotsPatch) {
        LOGGER.debug("Partially updating AppScreenshots with id: {}" , id);

        AppScreenshots appScreenshots = appScreenshotsService.partialUpdate(id, appScreenshotsPatch);
        LOGGER.debug("AppScreenshots details after partial update: {}" , appScreenshots);

        return appScreenshots;
    }

    @ApiOperation(value = "Deletes the AppScreenshots instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAppScreenshots(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting AppScreenshots with id: {}" , id);

        AppScreenshots deletedAppScreenshots = appScreenshotsService.delete(id);

        return deletedAppScreenshots != null;
    }

    /**
     * @deprecated Use {@link #findAppScreenshots(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of AppScreenshots instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppScreenshots> searchAppScreenshotsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering AppScreenshots list by query filter:{}", (Object) queryFilters);
        return appScreenshotsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppScreenshots instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppScreenshots> findAppScreenshots(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppScreenshots list by filter:", query);
        return appScreenshotsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppScreenshots instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppScreenshots> filterAppScreenshots(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppScreenshots list by filter", query);
        return appScreenshotsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportAppScreenshots(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return appScreenshotsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportAppScreenshotsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = AppScreenshots.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> appScreenshotsService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of AppScreenshots instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countAppScreenshots( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting AppScreenshots");
		return appScreenshotsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getAppScreenshotsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return appScreenshotsService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AppScreenshotsService instance
	 */
	protected void setAppScreenshotsService(AppScreenshotsService service) {
		this.appScreenshotsService = service;
	}

}
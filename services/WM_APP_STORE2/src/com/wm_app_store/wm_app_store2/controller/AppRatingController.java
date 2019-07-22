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

import com.wm_app_store.wm_app_store2.AppRating;
import com.wm_app_store.wm_app_store2.service.AppRatingService;


/**
 * Controller object for domain model class AppRating.
 * @see AppRating
 */
@RestController("WM_APP_STORE2.AppRatingController")
@Api(value = "AppRatingController", description = "Exposes APIs to work with AppRating resource.")
@RequestMapping("/WM_APP_STORE2/AppRating")
public class AppRatingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRatingController.class);

    @Autowired
	@Qualifier("WM_APP_STORE2.AppRatingService")
	private AppRatingService appRatingService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new AppRating instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppRating createAppRating(@RequestBody AppRating appRating) {
		LOGGER.debug("Create AppRating with information: {}" , appRating);

		appRating = appRatingService.create(appRating);
		LOGGER.debug("Created AppRating with information: {}" , appRating);

	    return appRating;
	}

    @ApiOperation(value = "Returns the AppRating instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppRating getAppRating(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting AppRating with id: {}" , id);

        AppRating foundAppRating = appRatingService.getById(id);
        LOGGER.debug("AppRating details with id: {}" , foundAppRating);

        return foundAppRating;
    }

    @ApiOperation(value = "Updates the AppRating instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppRating editAppRating(@PathVariable("id") Integer id, @RequestBody AppRating appRating) {
        LOGGER.debug("Editing AppRating with id: {}" , appRating.getId());

        appRating.setId(id);
        appRating = appRatingService.update(appRating);
        LOGGER.debug("AppRating details with id: {}" , appRating);

        return appRating;
    }
    
    @ApiOperation(value = "Partially updates the AppRating instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppRating patchAppRating(@PathVariable("id") Integer id, @RequestBody @MapTo(AppRating.class) Map<String, Object> appRatingPatch) {
        LOGGER.debug("Partially updating AppRating with id: {}" , id);

        AppRating appRating = appRatingService.partialUpdate(id, appRatingPatch);
        LOGGER.debug("AppRating details after partial update: {}" , appRating);

        return appRating;
    }

    @ApiOperation(value = "Deletes the AppRating instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAppRating(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting AppRating with id: {}" , id);

        AppRating deletedAppRating = appRatingService.delete(id);

        return deletedAppRating != null;
    }

    /**
     * @deprecated Use {@link #findAppRatings(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of AppRating instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppRating> searchAppRatingsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering AppRatings list by query filter:{}", (Object) queryFilters);
        return appRatingService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppRating instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppRating> findAppRatings(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppRatings list by filter:", query);
        return appRatingService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppRating instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppRating> filterAppRatings(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppRatings list by filter", query);
        return appRatingService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportAppRatings(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return appRatingService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportAppRatingsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = AppRating.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> appRatingService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of AppRating instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countAppRatings( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting AppRatings");
		return appRatingService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getAppRatingAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return appRatingService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AppRatingService instance
	 */
	protected void setAppRatingService(AppRatingService service) {
		this.appRatingService = service;
	}

}

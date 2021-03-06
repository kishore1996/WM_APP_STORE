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

import com.wm_app_store.wm_app_store.AppD0wnloadHostory;
import com.wm_app_store.wm_app_store.service.AppD0wnloadHostoryService;


/**
 * Controller object for domain model class AppD0wnloadHostory.
 * @see AppD0wnloadHostory
 */
@RestController("WM_APP_STORE.AppD0wnloadHostoryController")
@Api(value = "AppD0wnloadHostoryController", description = "Exposes APIs to work with AppD0wnloadHostory resource.")
@RequestMapping("/WM_APP_STORE/AppD0wnloadHostory")
public class AppD0wnloadHostoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppD0wnloadHostoryController.class);

    @Autowired
	@Qualifier("WM_APP_STORE.AppD0wnloadHostoryService")
	private AppD0wnloadHostoryService appD0wnloadHostoryService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new AppD0wnloadHostory instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppD0wnloadHostory createAppD0wnloadHostory(@RequestBody AppD0wnloadHostory appD0wnloadHostory) {
		LOGGER.debug("Create AppD0wnloadHostory with information: {}" , appD0wnloadHostory);

		appD0wnloadHostory = appD0wnloadHostoryService.create(appD0wnloadHostory);
		LOGGER.debug("Created AppD0wnloadHostory with information: {}" , appD0wnloadHostory);

	    return appD0wnloadHostory;
	}

    @ApiOperation(value = "Returns the AppD0wnloadHostory instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppD0wnloadHostory getAppD0wnloadHostory(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting AppD0wnloadHostory with id: {}" , id);

        AppD0wnloadHostory foundAppD0wnloadHostory = appD0wnloadHostoryService.getById(id);
        LOGGER.debug("AppD0wnloadHostory details with id: {}" , foundAppD0wnloadHostory);

        return foundAppD0wnloadHostory;
    }

    @ApiOperation(value = "Updates the AppD0wnloadHostory instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppD0wnloadHostory editAppD0wnloadHostory(@PathVariable("id") Integer id, @RequestBody AppD0wnloadHostory appD0wnloadHostory) {
        LOGGER.debug("Editing AppD0wnloadHostory with id: {}" , appD0wnloadHostory.getId());

        appD0wnloadHostory.setId(id);
        appD0wnloadHostory = appD0wnloadHostoryService.update(appD0wnloadHostory);
        LOGGER.debug("AppD0wnloadHostory details with id: {}" , appD0wnloadHostory);

        return appD0wnloadHostory;
    }
    
    @ApiOperation(value = "Partially updates the AppD0wnloadHostory instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppD0wnloadHostory patchAppD0wnloadHostory(@PathVariable("id") Integer id, @RequestBody @MapTo(AppD0wnloadHostory.class) Map<String, Object> appD0wnloadHostoryPatch) {
        LOGGER.debug("Partially updating AppD0wnloadHostory with id: {}" , id);

        AppD0wnloadHostory appD0wnloadHostory = appD0wnloadHostoryService.partialUpdate(id, appD0wnloadHostoryPatch);
        LOGGER.debug("AppD0wnloadHostory details after partial update: {}" , appD0wnloadHostory);

        return appD0wnloadHostory;
    }

    @ApiOperation(value = "Deletes the AppD0wnloadHostory instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAppD0wnloadHostory(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting AppD0wnloadHostory with id: {}" , id);

        AppD0wnloadHostory deletedAppD0wnloadHostory = appD0wnloadHostoryService.delete(id);

        return deletedAppD0wnloadHostory != null;
    }

    /**
     * @deprecated Use {@link #findAppD0wnloadHostories(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of AppD0wnloadHostory instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppD0wnloadHostory> searchAppD0wnloadHostoriesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering AppD0wnloadHostories list by query filter:{}", (Object) queryFilters);
        return appD0wnloadHostoryService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppD0wnloadHostory instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppD0wnloadHostory> findAppD0wnloadHostories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppD0wnloadHostories list by filter:", query);
        return appD0wnloadHostoryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppD0wnloadHostory instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppD0wnloadHostory> filterAppD0wnloadHostories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppD0wnloadHostories list by filter", query);
        return appD0wnloadHostoryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportAppD0wnloadHostories(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return appD0wnloadHostoryService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportAppD0wnloadHostoriesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = AppD0wnloadHostory.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> appD0wnloadHostoryService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of AppD0wnloadHostory instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countAppD0wnloadHostories( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting AppD0wnloadHostories");
		return appD0wnloadHostoryService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getAppD0wnloadHostoryAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return appD0wnloadHostoryService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AppD0wnloadHostoryService instance
	 */
	protected void setAppD0wnloadHostoryService(AppD0wnloadHostoryService service) {
		this.appD0wnloadHostoryService = service;
	}

}
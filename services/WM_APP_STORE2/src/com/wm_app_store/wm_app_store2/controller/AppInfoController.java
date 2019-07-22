/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store2.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.wm_app_store.wm_app_store2.AppInfo;
import com.wm_app_store.wm_app_store2.AppRating;
import com.wm_app_store.wm_app_store2.AppScreen;
import com.wm_app_store.wm_app_store2.AppScreenshots;
import com.wm_app_store.wm_app_store2.AppSource;
import com.wm_app_store.wm_app_store2.service.AppInfoService;


/**
 * Controller object for domain model class AppInfo.
 * @see AppInfo
 */
@RestController("WM_APP_STORE2.AppInfoController")
@Api(value = "AppInfoController", description = "Exposes APIs to work with AppInfo resource.")
@RequestMapping("/WM_APP_STORE2/AppInfo")
public class AppInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppInfoController.class);

    @Autowired
	@Qualifier("WM_APP_STORE2.AppInfoService")
	private AppInfoService appInfoService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new AppInfo instance.")
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppInfo createAppInfo(@RequestPart("wm_data_json") AppInfo appInfo, @RequestPart(value = "image", required = false) MultipartFile _image) {
		LOGGER.debug("Create AppInfo with information: {}" , appInfo);

    appInfo.setImage(WMMultipartUtils.toByteArray(_image));
		appInfo = appInfoService.create(appInfo);
		LOGGER.debug("Created AppInfo with information: {}" , appInfo);

	    return appInfo;
	}

    @ApiOperation(value = "Returns the AppInfo instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppInfo getAppInfo(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting AppInfo with id: {}" , id);

        AppInfo foundAppInfo = appInfoService.getById(id);
        LOGGER.debug("AppInfo details with id: {}" , foundAppInfo);

        return foundAppInfo;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in AppInfo instance" )
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces="application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getAppInfoBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value="download", defaultValue = "false") boolean download) {

        LOGGER.debug("Retrieves content for the given BLOB field {} in AppInfo instance" , fieldName);

        if(!WMRuntimeUtils.isLob(AppInfo.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        AppInfo appInfo = appInfoService.getById(id);

        return WMMultipartUtils.buildDownloadResponseForBlob(appInfo, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the AppInfo instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppInfo editAppInfo(@PathVariable("id") Integer id, @RequestBody AppInfo appInfo) {
        LOGGER.debug("Editing AppInfo with id: {}" , appInfo.getId());

        appInfo.setId(id);
        appInfo = appInfoService.update(appInfo);
        LOGGER.debug("AppInfo details with id: {}" , appInfo);

        return appInfo;
    }
    
    @ApiOperation(value = "Partially updates the AppInfo instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppInfo patchAppInfo(@PathVariable("id") Integer id, @RequestBody @MapTo(AppInfo.class) Map<String, Object> appInfoPatch) {
        LOGGER.debug("Partially updating AppInfo with id: {}" , id);

        AppInfo appInfo = appInfoService.partialUpdate(id, appInfoPatch);
        LOGGER.debug("AppInfo details after partial update: {}" , appInfo);

        return appInfo;
    }

    @ApiOperation(value = "Updates the AppInfo instance associated with the given id.This API should be used when AppInfo instance fields that require multipart data.") 
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AppInfo editAppInfo(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) {
        AppInfo newAppInfo = WMMultipartUtils.toObject(multipartHttpServletRequest, AppInfo.class, "WM_APP_STORE2");
        newAppInfo.setId(id);

        AppInfo oldAppInfo = appInfoService.getById(id);
        WMMultipartUtils.updateLobsContent(oldAppInfo, newAppInfo);
        LOGGER.debug("Updating AppInfo with information: {}" , newAppInfo);

        return appInfoService.update(newAppInfo);
    }

    @ApiOperation(value = "Deletes the AppInfo instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAppInfo(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting AppInfo with id: {}" , id);

        AppInfo deletedAppInfo = appInfoService.delete(id);

        return deletedAppInfo != null;
    }

    /**
     * @deprecated Use {@link #findAppInfos(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of AppInfo instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppInfo> searchAppInfosByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering AppInfos list by query filter:{}", (Object) queryFilters);
        return appInfoService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppInfo instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppInfo> findAppInfos(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppInfos list by filter:", query);
        return appInfoService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of AppInfo instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<AppInfo> filterAppInfos(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AppInfos list by filter", query);
        return appInfoService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportAppInfos(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return appInfoService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportAppInfosAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = AppInfo.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> appInfoService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of AppInfo instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countAppInfos( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting AppInfos");
		return appInfoService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getAppInfoAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return appInfoService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/appRatings", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the appRatings instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppRating> findAssociatedAppRatings(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated appRatings");
        return appInfoService.findAssociatedAppRatings(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/appScreens", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the appScreens instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppScreen> findAssociatedAppScreens(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated appScreens");
        return appInfoService.findAssociatedAppScreens(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/appSources", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the appSources instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppSource> findAssociatedAppSources(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated appSources");
        return appInfoService.findAssociatedAppSources(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/appScreenshotses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the appScreenshotses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AppScreenshots> findAssociatedAppScreenshotses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated appScreenshotses");
        return appInfoService.findAssociatedAppScreenshotses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AppInfoService instance
	 */
	protected void setAppInfoService(AppInfoService service) {
		this.appInfoService = service;
	}

}

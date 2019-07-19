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

import com.wm_app_store.wm_app_store.Credentials;
import com.wm_app_store.wm_app_store.service.CredentialsService;


/**
 * Controller object for domain model class Credentials.
 * @see Credentials
 */
@RestController("WM_APP_STORE.CredentialsController")
@Api(value = "CredentialsController", description = "Exposes APIs to work with Credentials resource.")
@RequestMapping("/WM_APP_STORE/Credentials")
public class CredentialsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsController.class);

    @Autowired
	@Qualifier("WM_APP_STORE.CredentialsService")
	private CredentialsService credentialsService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Credentials instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Credentials createCredentials(@RequestBody Credentials credentials) {
		LOGGER.debug("Create Credentials with information: {}" , credentials);

		credentials = credentialsService.create(credentials);
		LOGGER.debug("Created Credentials with information: {}" , credentials);

	    return credentials;
	}

    @ApiOperation(value = "Returns the Credentials instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Credentials getCredentials(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting Credentials with id: {}" , id);

        Credentials foundCredentials = credentialsService.getById(id);
        LOGGER.debug("Credentials details with id: {}" , foundCredentials);

        return foundCredentials;
    }

    @ApiOperation(value = "Updates the Credentials instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Credentials editCredentials(@PathVariable("id") Integer id, @RequestBody Credentials credentials) {
        LOGGER.debug("Editing Credentials with id: {}" , credentials.getId());

        credentials.setId(id);
        credentials = credentialsService.update(credentials);
        LOGGER.debug("Credentials details with id: {}" , credentials);

        return credentials;
    }
    
    @ApiOperation(value = "Partially updates the Credentials instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Credentials patchCredentials(@PathVariable("id") Integer id, @RequestBody @MapTo(Credentials.class) Map<String, Object> credentialsPatch) {
        LOGGER.debug("Partially updating Credentials with id: {}" , id);

        Credentials credentials = credentialsService.partialUpdate(id, credentialsPatch);
        LOGGER.debug("Credentials details after partial update: {}" , credentials);

        return credentials;
    }

    @ApiOperation(value = "Deletes the Credentials instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteCredentials(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting Credentials with id: {}" , id);

        Credentials deletedCredentials = credentialsService.delete(id);

        return deletedCredentials != null;
    }

    /**
     * @deprecated Use {@link #findCredentials(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Credentials instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<Credentials> searchCredentialsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Credentials list by query filter:{}", (Object) queryFilters);
        return credentialsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Credentials instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Credentials> findCredentials(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Credentials list by filter:", query);
        return credentialsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Credentials instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<Credentials> filterCredentials(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Credentials list by filter", query);
        return credentialsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportCredentials(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return credentialsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportCredentialsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Credentials.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> credentialsService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Credentials instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countCredentials( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Credentials");
		return credentialsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getCredentialsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return credentialsService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CredentialsService instance
	 */
	protected void setCredentialsService(CredentialsService service) {
		this.credentialsService = service;
	}

}
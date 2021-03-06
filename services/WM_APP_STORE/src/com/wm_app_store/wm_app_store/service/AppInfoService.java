/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wm_app_store.wm_app_store.AppInfo;
import com.wm_app_store.wm_app_store.AppRating;
import com.wm_app_store.wm_app_store.AppScreen;
import com.wm_app_store.wm_app_store.AppScreenshots;
import com.wm_app_store.wm_app_store.AppSource;

/**
 * Service object for domain model class {@link AppInfo}.
 */
public interface AppInfoService {

    /**
     * Creates a new AppInfo. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppInfo if any.
     *
     * @param appInfo Details of the AppInfo to be created; value cannot be null.
     * @return The newly created AppInfo.
     */
    AppInfo create(@Valid AppInfo appInfo);


	/**
     * Returns AppInfo by given id if exists.
     *
     * @param appinfoId The id of the AppInfo to get; value cannot be null.
     * @return AppInfo associated with the given appinfoId.
	 * @throws EntityNotFoundException If no AppInfo is found.
     */
    AppInfo getById(Integer appinfoId);

    /**
     * Find and return the AppInfo by given id if exists, returns null otherwise.
     *
     * @param appinfoId The id of the AppInfo to get; value cannot be null.
     * @return AppInfo associated with the given appinfoId.
     */
    AppInfo findById(Integer appinfoId);

	/**
     * Find and return the list of AppInfos by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param appinfoIds The id's of the AppInfo to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return AppInfos associated with the given appinfoIds.
     */
    List<AppInfo> findByMultipleIds(List<Integer> appinfoIds, boolean orderedReturn);


    /**
     * Updates the details of an existing AppInfo. It replaces all fields of the existing AppInfo with the given appInfo.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppInfo if any.
     *
     * @param appInfo The details of the AppInfo to be updated; value cannot be null.
     * @return The updated AppInfo.
     * @throws EntityNotFoundException if no AppInfo is found with given input.
     */
    AppInfo update(@Valid AppInfo appInfo);


    /**
     * Partially updates the details of an existing AppInfo. It updates only the
     * fields of the existing AppInfo which are passed in the appInfoPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppInfo if any.
     *
     * @param appinfoId The id of the AppInfo to be deleted; value cannot be null.
     * @param appInfoPatch The partial data of AppInfo which is supposed to be updated; value cannot be null.
     * @return The updated AppInfo.
     * @throws EntityNotFoundException if no AppInfo is found with given input.
     */
    AppInfo partialUpdate(Integer appinfoId, Map<String, Object> appInfoPatch);

    /**
     * Deletes an existing AppInfo with the given id.
     *
     * @param appinfoId The id of the AppInfo to be deleted; value cannot be null.
     * @return The deleted AppInfo.
     * @throws EntityNotFoundException if no AppInfo found with the given id.
     */
    AppInfo delete(Integer appinfoId);

    /**
     * Deletes an existing AppInfo with the given object.
     *
     * @param appInfo The instance of the AppInfo to be deleted; value cannot be null.
     */
    void delete(AppInfo appInfo);

    /**
     * Find all AppInfos matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppInfos.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<AppInfo> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all AppInfos matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppInfos.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppInfo> findAll(String query, Pageable pageable);

    /**
     * Exports all AppInfos matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all AppInfos matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the AppInfos in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the AppInfo.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated appRatings for given AppInfo id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated AppRating instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppRating> findAssociatedAppRatings(Integer id, Pageable pageable);

    /*
     * Returns the associated appScreens for given AppInfo id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated AppScreen instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppScreen> findAssociatedAppScreens(Integer id, Pageable pageable);

    /*
     * Returns the associated appScreenshotses for given AppInfo id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated AppScreenshots instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppScreenshots> findAssociatedAppScreenshotses(Integer id, Pageable pageable);

    /*
     * Returns the associated appSources for given AppInfo id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated AppSource instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppSource> findAssociatedAppSources(Integer id, Pageable pageable);

}
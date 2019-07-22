/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store1.service;

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

import com.wm_app_store.wm_app_store1.AppScreen;

/**
 * Service object for domain model class {@link AppScreen}.
 */
public interface AppScreenService {

    /**
     * Creates a new AppScreen. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppScreen if any.
     *
     * @param appScreen Details of the AppScreen to be created; value cannot be null.
     * @return The newly created AppScreen.
     */
    AppScreen create(@Valid AppScreen appScreen);


	/**
     * Returns AppScreen by given id if exists.
     *
     * @param appscreenId The id of the AppScreen to get; value cannot be null.
     * @return AppScreen associated with the given appscreenId.
	 * @throws EntityNotFoundException If no AppScreen is found.
     */
    AppScreen getById(Integer appscreenId);

    /**
     * Find and return the AppScreen by given id if exists, returns null otherwise.
     *
     * @param appscreenId The id of the AppScreen to get; value cannot be null.
     * @return AppScreen associated with the given appscreenId.
     */
    AppScreen findById(Integer appscreenId);

	/**
     * Find and return the list of AppScreens by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param appscreenIds The id's of the AppScreen to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return AppScreens associated with the given appscreenIds.
     */
    List<AppScreen> findByMultipleIds(List<Integer> appscreenIds, boolean orderedReturn);


    /**
     * Updates the details of an existing AppScreen. It replaces all fields of the existing AppScreen with the given appScreen.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppScreen if any.
     *
     * @param appScreen The details of the AppScreen to be updated; value cannot be null.
     * @return The updated AppScreen.
     * @throws EntityNotFoundException if no AppScreen is found with given input.
     */
    AppScreen update(@Valid AppScreen appScreen);


    /**
     * Partially updates the details of an existing AppScreen. It updates only the
     * fields of the existing AppScreen which are passed in the appScreenPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppScreen if any.
     *
     * @param appscreenId The id of the AppScreen to be deleted; value cannot be null.
     * @param appScreenPatch The partial data of AppScreen which is supposed to be updated; value cannot be null.
     * @return The updated AppScreen.
     * @throws EntityNotFoundException if no AppScreen is found with given input.
     */
    AppScreen partialUpdate(Integer appscreenId, Map<String, Object> appScreenPatch);

    /**
     * Deletes an existing AppScreen with the given id.
     *
     * @param appscreenId The id of the AppScreen to be deleted; value cannot be null.
     * @return The deleted AppScreen.
     * @throws EntityNotFoundException if no AppScreen found with the given id.
     */
    AppScreen delete(Integer appscreenId);

    /**
     * Deletes an existing AppScreen with the given object.
     *
     * @param appScreen The instance of the AppScreen to be deleted; value cannot be null.
     */
    void delete(AppScreen appScreen);

    /**
     * Find all AppScreens matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppScreens.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<AppScreen> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all AppScreens matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppScreens.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppScreen> findAll(String query, Pageable pageable);

    /**
     * Exports all AppScreens matching the given input query to the given exportType format.
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
     * Exports all AppScreens matching the given input query to the given exportType format.
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
     * Retrieve the count of the AppScreens in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the AppScreen.
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


}

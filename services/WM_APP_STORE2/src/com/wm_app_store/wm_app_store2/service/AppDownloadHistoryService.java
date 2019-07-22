/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store2.service;

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

import com.wm_app_store.wm_app_store2.AppDownloadHistory;

/**
 * Service object for domain model class {@link AppDownloadHistory}.
 */
public interface AppDownloadHistoryService {

    /**
     * Creates a new AppDownloadHistory. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppDownloadHistory if any.
     *
     * @param appDownloadHistory Details of the AppDownloadHistory to be created; value cannot be null.
     * @return The newly created AppDownloadHistory.
     */
    AppDownloadHistory create(@Valid AppDownloadHistory appDownloadHistory);


	/**
     * Returns AppDownloadHistory by given id if exists.
     *
     * @param appdownloadhistoryId The id of the AppDownloadHistory to get; value cannot be null.
     * @return AppDownloadHistory associated with the given appdownloadhistoryId.
	 * @throws EntityNotFoundException If no AppDownloadHistory is found.
     */
    AppDownloadHistory getById(Integer appdownloadhistoryId);

    /**
     * Find and return the AppDownloadHistory by given id if exists, returns null otherwise.
     *
     * @param appdownloadhistoryId The id of the AppDownloadHistory to get; value cannot be null.
     * @return AppDownloadHistory associated with the given appdownloadhistoryId.
     */
    AppDownloadHistory findById(Integer appdownloadhistoryId);

	/**
     * Find and return the list of AppDownloadHistories by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param appdownloadhistoryIds The id's of the AppDownloadHistory to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return AppDownloadHistories associated with the given appdownloadhistoryIds.
     */
    List<AppDownloadHistory> findByMultipleIds(List<Integer> appdownloadhistoryIds, boolean orderedReturn);


    /**
     * Updates the details of an existing AppDownloadHistory. It replaces all fields of the existing AppDownloadHistory with the given appDownloadHistory.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppDownloadHistory if any.
     *
     * @param appDownloadHistory The details of the AppDownloadHistory to be updated; value cannot be null.
     * @return The updated AppDownloadHistory.
     * @throws EntityNotFoundException if no AppDownloadHistory is found with given input.
     */
    AppDownloadHistory update(@Valid AppDownloadHistory appDownloadHistory);


    /**
     * Partially updates the details of an existing AppDownloadHistory. It updates only the
     * fields of the existing AppDownloadHistory which are passed in the appDownloadHistoryPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppDownloadHistory if any.
     *
     * @param appdownloadhistoryId The id of the AppDownloadHistory to be deleted; value cannot be null.
     * @param appDownloadHistoryPatch The partial data of AppDownloadHistory which is supposed to be updated; value cannot be null.
     * @return The updated AppDownloadHistory.
     * @throws EntityNotFoundException if no AppDownloadHistory is found with given input.
     */
    AppDownloadHistory partialUpdate(Integer appdownloadhistoryId, Map<String, Object> appDownloadHistoryPatch);

    /**
     * Deletes an existing AppDownloadHistory with the given id.
     *
     * @param appdownloadhistoryId The id of the AppDownloadHistory to be deleted; value cannot be null.
     * @return The deleted AppDownloadHistory.
     * @throws EntityNotFoundException if no AppDownloadHistory found with the given id.
     */
    AppDownloadHistory delete(Integer appdownloadhistoryId);

    /**
     * Deletes an existing AppDownloadHistory with the given object.
     *
     * @param appDownloadHistory The instance of the AppDownloadHistory to be deleted; value cannot be null.
     */
    void delete(AppDownloadHistory appDownloadHistory);

    /**
     * Find all AppDownloadHistories matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppDownloadHistories.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<AppDownloadHistory> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all AppDownloadHistories matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppDownloadHistories.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppDownloadHistory> findAll(String query, Pageable pageable);

    /**
     * Exports all AppDownloadHistories matching the given input query to the given exportType format.
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
     * Exports all AppDownloadHistories matching the given input query to the given exportType format.
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
     * Retrieve the count of the AppDownloadHistories in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the AppDownloadHistory.
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

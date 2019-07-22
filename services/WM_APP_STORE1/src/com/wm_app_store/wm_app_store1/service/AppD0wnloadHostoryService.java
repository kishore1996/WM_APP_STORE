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

import com.wm_app_store.wm_app_store1.AppD0wnloadHostory;

/**
 * Service object for domain model class {@link AppD0wnloadHostory}.
 */
public interface AppD0wnloadHostoryService {

    /**
     * Creates a new AppD0wnloadHostory. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppD0wnloadHostory if any.
     *
     * @param appD0wnloadHostory Details of the AppD0wnloadHostory to be created; value cannot be null.
     * @return The newly created AppD0wnloadHostory.
     */
    AppD0wnloadHostory create(@Valid AppD0wnloadHostory appD0wnloadHostory);


	/**
     * Returns AppD0wnloadHostory by given id if exists.
     *
     * @param appd0wnloadhostoryId The id of the AppD0wnloadHostory to get; value cannot be null.
     * @return AppD0wnloadHostory associated with the given appd0wnloadhostoryId.
	 * @throws EntityNotFoundException If no AppD0wnloadHostory is found.
     */
    AppD0wnloadHostory getById(Integer appd0wnloadhostoryId);

    /**
     * Find and return the AppD0wnloadHostory by given id if exists, returns null otherwise.
     *
     * @param appd0wnloadhostoryId The id of the AppD0wnloadHostory to get; value cannot be null.
     * @return AppD0wnloadHostory associated with the given appd0wnloadhostoryId.
     */
    AppD0wnloadHostory findById(Integer appd0wnloadhostoryId);

	/**
     * Find and return the list of AppD0wnloadHostories by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param appd0wnloadhostoryIds The id's of the AppD0wnloadHostory to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return AppD0wnloadHostories associated with the given appd0wnloadhostoryIds.
     */
    List<AppD0wnloadHostory> findByMultipleIds(List<Integer> appd0wnloadhostoryIds, boolean orderedReturn);


    /**
     * Updates the details of an existing AppD0wnloadHostory. It replaces all fields of the existing AppD0wnloadHostory with the given appD0wnloadHostory.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppD0wnloadHostory if any.
     *
     * @param appD0wnloadHostory The details of the AppD0wnloadHostory to be updated; value cannot be null.
     * @return The updated AppD0wnloadHostory.
     * @throws EntityNotFoundException if no AppD0wnloadHostory is found with given input.
     */
    AppD0wnloadHostory update(@Valid AppD0wnloadHostory appD0wnloadHostory);


    /**
     * Partially updates the details of an existing AppD0wnloadHostory. It updates only the
     * fields of the existing AppD0wnloadHostory which are passed in the appD0wnloadHostoryPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppD0wnloadHostory if any.
     *
     * @param appd0wnloadhostoryId The id of the AppD0wnloadHostory to be deleted; value cannot be null.
     * @param appD0wnloadHostoryPatch The partial data of AppD0wnloadHostory which is supposed to be updated; value cannot be null.
     * @return The updated AppD0wnloadHostory.
     * @throws EntityNotFoundException if no AppD0wnloadHostory is found with given input.
     */
    AppD0wnloadHostory partialUpdate(Integer appd0wnloadhostoryId, Map<String, Object> appD0wnloadHostoryPatch);

    /**
     * Deletes an existing AppD0wnloadHostory with the given id.
     *
     * @param appd0wnloadhostoryId The id of the AppD0wnloadHostory to be deleted; value cannot be null.
     * @return The deleted AppD0wnloadHostory.
     * @throws EntityNotFoundException if no AppD0wnloadHostory found with the given id.
     */
    AppD0wnloadHostory delete(Integer appd0wnloadhostoryId);

    /**
     * Deletes an existing AppD0wnloadHostory with the given object.
     *
     * @param appD0wnloadHostory The instance of the AppD0wnloadHostory to be deleted; value cannot be null.
     */
    void delete(AppD0wnloadHostory appD0wnloadHostory);

    /**
     * Find all AppD0wnloadHostories matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppD0wnloadHostories.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<AppD0wnloadHostory> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all AppD0wnloadHostories matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppD0wnloadHostories.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppD0wnloadHostory> findAll(String query, Pageable pageable);

    /**
     * Exports all AppD0wnloadHostories matching the given input query to the given exportType format.
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
     * Exports all AppD0wnloadHostories matching the given input query to the given exportType format.
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
     * Retrieve the count of the AppD0wnloadHostories in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the AppD0wnloadHostory.
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
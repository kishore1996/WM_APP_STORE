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

import com.wm_app_store.wm_app_store1.AppRating;

/**
 * Service object for domain model class {@link AppRating}.
 */
public interface AppRatingService {

    /**
     * Creates a new AppRating. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppRating if any.
     *
     * @param appRating Details of the AppRating to be created; value cannot be null.
     * @return The newly created AppRating.
     */
    AppRating create(@Valid AppRating appRating);


	/**
     * Returns AppRating by given id if exists.
     *
     * @param appratingId The id of the AppRating to get; value cannot be null.
     * @return AppRating associated with the given appratingId.
	 * @throws EntityNotFoundException If no AppRating is found.
     */
    AppRating getById(Integer appratingId);

    /**
     * Find and return the AppRating by given id if exists, returns null otherwise.
     *
     * @param appratingId The id of the AppRating to get; value cannot be null.
     * @return AppRating associated with the given appratingId.
     */
    AppRating findById(Integer appratingId);

	/**
     * Find and return the list of AppRatings by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param appratingIds The id's of the AppRating to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return AppRatings associated with the given appratingIds.
     */
    List<AppRating> findByMultipleIds(List<Integer> appratingIds, boolean orderedReturn);


    /**
     * Updates the details of an existing AppRating. It replaces all fields of the existing AppRating with the given appRating.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppRating if any.
     *
     * @param appRating The details of the AppRating to be updated; value cannot be null.
     * @return The updated AppRating.
     * @throws EntityNotFoundException if no AppRating is found with given input.
     */
    AppRating update(@Valid AppRating appRating);


    /**
     * Partially updates the details of an existing AppRating. It updates only the
     * fields of the existing AppRating which are passed in the appRatingPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on AppRating if any.
     *
     * @param appratingId The id of the AppRating to be deleted; value cannot be null.
     * @param appRatingPatch The partial data of AppRating which is supposed to be updated; value cannot be null.
     * @return The updated AppRating.
     * @throws EntityNotFoundException if no AppRating is found with given input.
     */
    AppRating partialUpdate(Integer appratingId, Map<String, Object> appRatingPatch);

    /**
     * Deletes an existing AppRating with the given id.
     *
     * @param appratingId The id of the AppRating to be deleted; value cannot be null.
     * @return The deleted AppRating.
     * @throws EntityNotFoundException if no AppRating found with the given id.
     */
    AppRating delete(Integer appratingId);

    /**
     * Deletes an existing AppRating with the given object.
     *
     * @param appRating The instance of the AppRating to be deleted; value cannot be null.
     */
    void delete(AppRating appRating);

    /**
     * Find all AppRatings matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppRatings.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<AppRating> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all AppRatings matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching AppRatings.
     *
     * @see Pageable
     * @see Page
     */
    Page<AppRating> findAll(String query, Pageable pageable);

    /**
     * Exports all AppRatings matching the given input query to the given exportType format.
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
     * Exports all AppRatings matching the given input query to the given exportType format.
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
     * Retrieve the count of the AppRatings in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the AppRating.
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

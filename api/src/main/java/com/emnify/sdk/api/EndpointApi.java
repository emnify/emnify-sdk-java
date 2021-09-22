/*-
 * #%L
 * EMnify Java SDK
 * %%
 * Copyright (C) 2021 EMnify
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


package com.emnify.sdk.api;

import com.emnify.sdk.ApiCallback;
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.ApiResponse;
import com.emnify.sdk.Configuration;
import com.emnify.sdk.Pair;
import com.emnify.sdk.ProgressRequestBody;
import com.emnify.sdk.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.emnify.sdk.model.ChangePassword422response;
import com.emnify.sdk.model.ChangeQuota422Response;
import com.emnify.sdk.model.Endpoint;
import com.emnify.sdk.model.EndpointQuota;
import com.emnify.sdk.model.Model40xResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndpointApi {
    private ApiClient localVarApiClient;

    public EndpointApi() {
        this(Configuration.getDefaultApiClient());
    }

    public EndpointApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for createEndpoint
     * @param endpoint  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource successfully created </td><td>  * Location - Relative location of the newly-created resource. <br>  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createEndpointCall(Endpoint endpoint, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = endpoint;

        // create path and map variables
        String localVarPath = "/api/v1/endpoint";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createEndpointValidateBeforeCall(Endpoint endpoint, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'endpoint' is set
        if (endpoint == null) {
            throw new ApiException("Missing the required parameter 'endpoint' when calling createEndpoint(Async)");
        }
        

        okhttp3.Call localVarCall = createEndpointCall(endpoint, _callback);
        return localVarCall;

    }

    /**
     * Create Endpoint
     * If a &#x60;sim&#x60; object is provided, the SIM with the contained ID will be assigned to the endpoint. The &#x60;activate&#x60; property defaults to &#x60;true&#x60; and can be omitted unless the SIM should not be activated with this API call.  The following fields may be provided: * &#x60;name&#x60; (String required) * &#x60;service_profile&#x60; (Object required) * &#x60;tariff_profile&#x60; (Object required) * &#x60;status&#x60; (Object required) - &#x60;0&#x60; &#x3D; __Enabled__, &#x60;1&#x60; &#x3D; __Disabled__! * &#x60;tags&#x60; (String optional) * &#x60;imei&#x60; (String optional) * &#x60;imei_lock&#x60; (Boolean optional) * &#x60;sim&#x60; (Object optional)   - &#x60;id&#x60; (number required) SIM ID to be assigned to this endpoint   - &#x60;activate&#x60; (Boolean, optional, default:true) * &#x60;ip_address&#x60; (String optional) * &#x60;ip_address_space&#x60; (Object, optional if IP address is omitted, mandatory when IP address is set) 
     * @param endpoint  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource successfully created </td><td>  * Location - Relative location of the newly-created resource. <br>  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public void createEndpoint(Endpoint endpoint) throws ApiException {
        createEndpointWithHttpInfo(endpoint);
    }

    /**
     * Create Endpoint
     * If a &#x60;sim&#x60; object is provided, the SIM with the contained ID will be assigned to the endpoint. The &#x60;activate&#x60; property defaults to &#x60;true&#x60; and can be omitted unless the SIM should not be activated with this API call.  The following fields may be provided: * &#x60;name&#x60; (String required) * &#x60;service_profile&#x60; (Object required) * &#x60;tariff_profile&#x60; (Object required) * &#x60;status&#x60; (Object required) - &#x60;0&#x60; &#x3D; __Enabled__, &#x60;1&#x60; &#x3D; __Disabled__! * &#x60;tags&#x60; (String optional) * &#x60;imei&#x60; (String optional) * &#x60;imei_lock&#x60; (Boolean optional) * &#x60;sim&#x60; (Object optional)   - &#x60;id&#x60; (number required) SIM ID to be assigned to this endpoint   - &#x60;activate&#x60; (Boolean, optional, default:true) * &#x60;ip_address&#x60; (String optional) * &#x60;ip_address_space&#x60; (Object, optional if IP address is omitted, mandatory when IP address is set) 
     * @param endpoint  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource successfully created </td><td>  * Location - Relative location of the newly-created resource. <br>  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> createEndpointWithHttpInfo(Endpoint endpoint) throws ApiException {
        okhttp3.Call localVarCall = createEndpointValidateBeforeCall(endpoint, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Create Endpoint (asynchronously)
     * If a &#x60;sim&#x60; object is provided, the SIM with the contained ID will be assigned to the endpoint. The &#x60;activate&#x60; property defaults to &#x60;true&#x60; and can be omitted unless the SIM should not be activated with this API call.  The following fields may be provided: * &#x60;name&#x60; (String required) * &#x60;service_profile&#x60; (Object required) * &#x60;tariff_profile&#x60; (Object required) * &#x60;status&#x60; (Object required) - &#x60;0&#x60; &#x3D; __Enabled__, &#x60;1&#x60; &#x3D; __Disabled__! * &#x60;tags&#x60; (String optional) * &#x60;imei&#x60; (String optional) * &#x60;imei_lock&#x60; (Boolean optional) * &#x60;sim&#x60; (Object optional)   - &#x60;id&#x60; (number required) SIM ID to be assigned to this endpoint   - &#x60;activate&#x60; (Boolean, optional, default:true) * &#x60;ip_address&#x60; (String optional) * &#x60;ip_address_space&#x60; (Object, optional if IP address is omitted, mandatory when IP address is set) 
     * @param endpoint  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource successfully created </td><td>  * Location - Relative location of the newly-created resource. <br>  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createEndpointAsync(Endpoint endpoint, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = createEndpointValidateBeforeCall(endpoint, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteEndpointDataQuotaById
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Can be one of the following reasons: - No Data Quota configured - Endpoint not found - Endpoint not part of this organisation  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteEndpointDataQuotaByIdCall(Integer endpointId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/endpoint/{endpoint_id}/quota/data"
            .replaceAll("\\{" + "endpoint_id" + "\\}", localVarApiClient.escapeString(endpointId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteEndpointDataQuotaByIdValidateBeforeCall(Integer endpointId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'endpointId' is set
        if (endpointId == null) {
            throw new ApiException("Missing the required parameter 'endpointId' when calling deleteEndpointDataQuotaById(Async)");
        }
        

        okhttp3.Call localVarCall = deleteEndpointDataQuotaByIdCall(endpointId, _callback);
        return localVarCall;

    }

    /**
     * Remove Data Quota
     * Will delete the data quota for the endpoint, if any is set. Note that if &#x60;apply_data_quota&#x60; is still set in the service profile, the endpoint will get blocked from data service. 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Can be one of the following reasons: - No Data Quota configured - Endpoint not found - Endpoint not part of this organisation  </td><td>  -  </td></tr>
     </table>
     */
    public void deleteEndpointDataQuotaById(Integer endpointId) throws ApiException {
        deleteEndpointDataQuotaByIdWithHttpInfo(endpointId);
    }

    /**
     * Remove Data Quota
     * Will delete the data quota for the endpoint, if any is set. Note that if &#x60;apply_data_quota&#x60; is still set in the service profile, the endpoint will get blocked from data service. 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Can be one of the following reasons: - No Data Quota configured - Endpoint not found - Endpoint not part of this organisation  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteEndpointDataQuotaByIdWithHttpInfo(Integer endpointId) throws ApiException {
        okhttp3.Call localVarCall = deleteEndpointDataQuotaByIdValidateBeforeCall(endpointId, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Remove Data Quota (asynchronously)
     * Will delete the data quota for the endpoint, if any is set. Note that if &#x60;apply_data_quota&#x60; is still set in the service profile, the endpoint will get blocked from data service. 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 204 </td><td> Success </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Can be one of the following reasons: - No Data Quota configured - Endpoint not found - Endpoint not part of this organisation  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteEndpointDataQuotaByIdAsync(Integer endpointId, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteEndpointDataQuotaByIdValidateBeforeCall(endpointId, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for endpointQuotaDataByEndpointIdGet
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call endpointQuotaDataByEndpointIdGetCall(Integer endpointId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/endpoint/{endpoint_id}/quota/data"
            .replaceAll("\\{" + "endpoint_id" + "\\}", localVarApiClient.escapeString(endpointId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call endpointQuotaDataByEndpointIdGetValidateBeforeCall(Integer endpointId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'endpointId' is set
        if (endpointId == null) {
            throw new ApiException("Missing the required parameter 'endpointId' when calling endpointQuotaDataByEndpointIdGet(Async)");
        }
        

        okhttp3.Call localVarCall = endpointQuotaDataByEndpointIdGetCall(endpointId, _callback);
        return localVarCall;

    }

    /**
     * Retrieve Data Quota details
     * Returns details about the assigned Data Quota for an endpoint. * &#x60;status&#x60;: this indicates the current status of the quota and may contain the following values:     - &#x60;ACTIVE&#x60;: the endpoint can currently connect and has quota left     - &#x60;EXHAUSTED&#x60;: the endpoint has exceeded the quota volume, if it still can use data service depends on the action chosen to be performed on exhaustion     - &#x60;EXPIRED&#x60;: the quota has expired; the endpoint is denied from using data services (until new quota is added) * &#x60;volume&#x60;: returns the volume left on this quota in MB * &#x60;expiry_date&#x60;: timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (regardless if the quota volume was used up or not) * &#x60;peak_throughput&#x60;: The maximum bandwidth in octets per second after the endpoint has been throttled. * &#x60;action_on_exhaustion&#x60;: returns the behaviour defined to be applied when quota volume is used up (exhausted).     - &#x60;Throttle&#x60;: bandwidth will be throttle to the defined peak throughput until quota expires     - &#x60;Block&#x60;: data service will be instantly blocked once volume used up, regardless if the expiry date is already reached or not * &#x60;auto_refill&#x60;: 0 (false) / 1 (true), refill the quota with the last added volume on a daily basis 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @return EndpointQuota
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public EndpointQuota endpointQuotaDataByEndpointIdGet(Integer endpointId) throws ApiException {
        ApiResponse<EndpointQuota> localVarResp = endpointQuotaDataByEndpointIdGetWithHttpInfo(endpointId);
        return localVarResp.getData();
    }

    /**
     * Retrieve Data Quota details
     * Returns details about the assigned Data Quota for an endpoint. * &#x60;status&#x60;: this indicates the current status of the quota and may contain the following values:     - &#x60;ACTIVE&#x60;: the endpoint can currently connect and has quota left     - &#x60;EXHAUSTED&#x60;: the endpoint has exceeded the quota volume, if it still can use data service depends on the action chosen to be performed on exhaustion     - &#x60;EXPIRED&#x60;: the quota has expired; the endpoint is denied from using data services (until new quota is added) * &#x60;volume&#x60;: returns the volume left on this quota in MB * &#x60;expiry_date&#x60;: timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (regardless if the quota volume was used up or not) * &#x60;peak_throughput&#x60;: The maximum bandwidth in octets per second after the endpoint has been throttled. * &#x60;action_on_exhaustion&#x60;: returns the behaviour defined to be applied when quota volume is used up (exhausted).     - &#x60;Throttle&#x60;: bandwidth will be throttle to the defined peak throughput until quota expires     - &#x60;Block&#x60;: data service will be instantly blocked once volume used up, regardless if the expiry date is already reached or not * &#x60;auto_refill&#x60;: 0 (false) / 1 (true), refill the quota with the last added volume on a daily basis 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @return ApiResponse&lt;EndpointQuota&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<EndpointQuota> endpointQuotaDataByEndpointIdGetWithHttpInfo(Integer endpointId) throws ApiException {
        okhttp3.Call localVarCall = endpointQuotaDataByEndpointIdGetValidateBeforeCall(endpointId, null);
        Type localVarReturnType = new TypeToken<EndpointQuota>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve Data Quota details (asynchronously)
     * Returns details about the assigned Data Quota for an endpoint. * &#x60;status&#x60;: this indicates the current status of the quota and may contain the following values:     - &#x60;ACTIVE&#x60;: the endpoint can currently connect and has quota left     - &#x60;EXHAUSTED&#x60;: the endpoint has exceeded the quota volume, if it still can use data service depends on the action chosen to be performed on exhaustion     - &#x60;EXPIRED&#x60;: the quota has expired; the endpoint is denied from using data services (until new quota is added) * &#x60;volume&#x60;: returns the volume left on this quota in MB * &#x60;expiry_date&#x60;: timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (regardless if the quota volume was used up or not) * &#x60;peak_throughput&#x60;: The maximum bandwidth in octets per second after the endpoint has been throttled. * &#x60;action_on_exhaustion&#x60;: returns the behaviour defined to be applied when quota volume is used up (exhausted).     - &#x60;Throttle&#x60;: bandwidth will be throttle to the defined peak throughput until quota expires     - &#x60;Block&#x60;: data service will be instantly blocked once volume used up, regardless if the expiry date is already reached or not * &#x60;auto_refill&#x60;: 0 (false) / 1 (true), refill the quota with the last added volume on a daily basis 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call endpointQuotaDataByEndpointIdGetAsync(Integer endpointId, final ApiCallback<EndpointQuota> _callback) throws ApiException {

        okhttp3.Call localVarCall = endpointQuotaDataByEndpointIdGetValidateBeforeCall(endpointId, _callback);
        Type localVarReturnType = new TypeToken<EndpointQuota>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for endpointQuotaDataByEndpointIdPost
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param endpointQuota  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource Created </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call endpointQuotaDataByEndpointIdPostCall(Integer endpointId, EndpointQuota endpointQuota, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = endpointQuota;

        // create path and map variables
        String localVarPath = "/api/v1/endpoint/{endpoint_id}/quota/data"
            .replaceAll("\\{" + "endpoint_id" + "\\}", localVarApiClient.escapeString(endpointId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call endpointQuotaDataByEndpointIdPostValidateBeforeCall(Integer endpointId, EndpointQuota endpointQuota, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'endpointId' is set
        if (endpointId == null) {
            throw new ApiException("Missing the required parameter 'endpointId' when calling endpointQuotaDataByEndpointIdPost(Async)");
        }
        
        // verify the required parameter 'endpointQuota' is set
        if (endpointQuota == null) {
            throw new ApiException("Missing the required parameter 'endpointQuota' when calling endpointQuotaDataByEndpointIdPost(Async)");
        }
        

        okhttp3.Call localVarCall = endpointQuotaDataByEndpointIdPostCall(endpointId, endpointQuota, _callback);
        return localVarCall;

    }

    /**
     * Set Data Quota
     * At any time, a new data quota can be set for an endpoint. At an initial state when no data quota is set, the endpoint will be denied from using data services. To top-up the data volume you need to retrieve the currently remaining volume, increase it by the top-up volume and set it as the new quota volume.  The following parameters can be configured: * &#x60;status&#x60; - The status of the quota (mandatory):   - 1: &#x60;ACTIVE&#x60;   - 2: &#x60;EXHAUSTED&#x60;   - 3: &#x60;EXPIRED&#x60; * &#x60;volume&#x60;: The volume left on this quota in MB * &#x60;expiry_date&#x60;: Timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (mandatory) * &#x60;auto_refill&#x60;: Wether the quota shall be refilled on a daily basis (defaults to disabled):   - 0: &#x60;disabled&#x60;   - 1: &#x60;enabled&#x60; * &#x60;threshold_percentage&#x60;: The percentage of remaining quota at which the system should generate a &#x60;threshold reached&#x60; event * &#x60;action_on_exhaustion&#x60;: The behaviour of the system after the quota is exhausted:   - id: ID of the action on quota exhaustion (mandatory)     - 1: &#x60;Block&#x60;     - 2: &#x60;Throttle&#x60;   - peak_throughput: The maximum bandwidth in octets per second after the endpoint has been throttled. (mandatory)   Allowed values are 64000, 128000, 256000, 384000. (will not take any effect on &#x60;action_on_exhaustion&#x60; \&quot;Block\&quot;)  #### Events The system generates a \&quot;Quota Used Up\&quot; Event in case the data quota is completely depleted. The endpoint will be blocked from further consumption of data. The quota object will be included in the details of the event. Example events can be found in the Events of an Endpoint section.  #### Notes  The endpoint can instantly use data services after the API call to this entrypoint is successfully made. Any timestamp with a future date can be set, this allows to create data packages (e.g. for 1 hour, 24 hour, 7 days or any other timeframe) as required. 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param endpointQuota  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource Created </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. </td><td>  -  </td></tr>
     </table>
     */
    public void endpointQuotaDataByEndpointIdPost(Integer endpointId, EndpointQuota endpointQuota) throws ApiException {
        endpointQuotaDataByEndpointIdPostWithHttpInfo(endpointId, endpointQuota);
    }

    /**
     * Set Data Quota
     * At any time, a new data quota can be set for an endpoint. At an initial state when no data quota is set, the endpoint will be denied from using data services. To top-up the data volume you need to retrieve the currently remaining volume, increase it by the top-up volume and set it as the new quota volume.  The following parameters can be configured: * &#x60;status&#x60; - The status of the quota (mandatory):   - 1: &#x60;ACTIVE&#x60;   - 2: &#x60;EXHAUSTED&#x60;   - 3: &#x60;EXPIRED&#x60; * &#x60;volume&#x60;: The volume left on this quota in MB * &#x60;expiry_date&#x60;: Timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (mandatory) * &#x60;auto_refill&#x60;: Wether the quota shall be refilled on a daily basis (defaults to disabled):   - 0: &#x60;disabled&#x60;   - 1: &#x60;enabled&#x60; * &#x60;threshold_percentage&#x60;: The percentage of remaining quota at which the system should generate a &#x60;threshold reached&#x60; event * &#x60;action_on_exhaustion&#x60;: The behaviour of the system after the quota is exhausted:   - id: ID of the action on quota exhaustion (mandatory)     - 1: &#x60;Block&#x60;     - 2: &#x60;Throttle&#x60;   - peak_throughput: The maximum bandwidth in octets per second after the endpoint has been throttled. (mandatory)   Allowed values are 64000, 128000, 256000, 384000. (will not take any effect on &#x60;action_on_exhaustion&#x60; \&quot;Block\&quot;)  #### Events The system generates a \&quot;Quota Used Up\&quot; Event in case the data quota is completely depleted. The endpoint will be blocked from further consumption of data. The quota object will be included in the details of the event. Example events can be found in the Events of an Endpoint section.  #### Notes  The endpoint can instantly use data services after the API call to this entrypoint is successfully made. Any timestamp with a future date can be set, this allows to create data packages (e.g. for 1 hour, 24 hour, 7 days or any other timeframe) as required. 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param endpointQuota  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource Created </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> endpointQuotaDataByEndpointIdPostWithHttpInfo(Integer endpointId, EndpointQuota endpointQuota) throws ApiException {
        okhttp3.Call localVarCall = endpointQuotaDataByEndpointIdPostValidateBeforeCall(endpointId, endpointQuota, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Set Data Quota (asynchronously)
     * At any time, a new data quota can be set for an endpoint. At an initial state when no data quota is set, the endpoint will be denied from using data services. To top-up the data volume you need to retrieve the currently remaining volume, increase it by the top-up volume and set it as the new quota volume.  The following parameters can be configured: * &#x60;status&#x60; - The status of the quota (mandatory):   - 1: &#x60;ACTIVE&#x60;   - 2: &#x60;EXHAUSTED&#x60;   - 3: &#x60;EXPIRED&#x60; * &#x60;volume&#x60;: The volume left on this quota in MB * &#x60;expiry_date&#x60;: Timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (mandatory) * &#x60;auto_refill&#x60;: Wether the quota shall be refilled on a daily basis (defaults to disabled):   - 0: &#x60;disabled&#x60;   - 1: &#x60;enabled&#x60; * &#x60;threshold_percentage&#x60;: The percentage of remaining quota at which the system should generate a &#x60;threshold reached&#x60; event * &#x60;action_on_exhaustion&#x60;: The behaviour of the system after the quota is exhausted:   - id: ID of the action on quota exhaustion (mandatory)     - 1: &#x60;Block&#x60;     - 2: &#x60;Throttle&#x60;   - peak_throughput: The maximum bandwidth in octets per second after the endpoint has been throttled. (mandatory)   Allowed values are 64000, 128000, 256000, 384000. (will not take any effect on &#x60;action_on_exhaustion&#x60; \&quot;Block\&quot;)  #### Events The system generates a \&quot;Quota Used Up\&quot; Event in case the data quota is completely depleted. The endpoint will be blocked from further consumption of data. The quota object will be included in the details of the event. Example events can be found in the Events of an Endpoint section.  #### Notes  The endpoint can instantly use data services after the API call to this entrypoint is successfully made. Any timestamp with a future date can be set, this allows to create data packages (e.g. for 1 hour, 24 hour, 7 days or any other timeframe) as required. 
     * @param endpointId The numeric ID of an Endpoint (required)
     * @param endpointQuota  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Resource Created </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Unprocessable Entity. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call endpointQuotaDataByEndpointIdPostAsync(Integer endpointId, EndpointQuota endpointQuota, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = endpointQuotaDataByEndpointIdPostValidateBeforeCall(endpointId, endpointQuota, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getEndpoints
     * @param q Filter parameter in &#x60;&lt;filter&gt;:&lt;value&gt;&#x60; format. Expects comma separated list of filtering criteria out of the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param sort Sort properties in a comma separated list from the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;id&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param page Current page number (optional)
     * @param perPage Paging parameters defining the number of items per page (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully returned a list of Endpoints. </td><td>  * Link - URLs for navigational links used to retrieve first, previous, next and last page in a dataset <br>  * X-Count-Per-Page - The requested &#x60;per_page&#x60; parameter <br>  * X-Current-Page - The current page number within the current data set <br>  * X-Total-Count - The total element count in the current data set <br>  * X-Total-Pages - The total page count in the current data set <br>  * X-Filter - If filtering is applied using &#x60;?q&#x3D;field:criteria&#x60; URL query parameters, the filter criteria is returned here <br>  * X-Sort - If sorting is applied using &#x60;?sort&#x3D;sort_criteria&#x60; URL query parameters, the sort criteria is returned here <br>  </td></tr>
     </table>
     */
    public okhttp3.Call getEndpointsCall(String q, String sort, Integer page, Integer perPage, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/endpoint";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (q != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("q", q));
        }

        if (sort != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("sort", sort));
        }

        if (page != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("page", page));
        }

        if (perPage != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("per_page", perPage));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getEndpointsValidateBeforeCall(String q, String sort, Integer page, Integer perPage, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getEndpointsCall(q, sort, page, perPage, _callback);
        return localVarCall;

    }

    /**
     * List Endpoints
     * Returns the list of endpoints, filtered, sorted and paged according to query parameters.
     * @param q Filter parameter in &#x60;&lt;filter&gt;:&lt;value&gt;&#x60; format. Expects comma separated list of filtering criteria out of the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param sort Sort properties in a comma separated list from the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;id&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param page Current page number (optional)
     * @param perPage Paging parameters defining the number of items per page (optional)
     * @return List&lt;Endpoint&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully returned a list of Endpoints. </td><td>  * Link - URLs for navigational links used to retrieve first, previous, next and last page in a dataset <br>  * X-Count-Per-Page - The requested &#x60;per_page&#x60; parameter <br>  * X-Current-Page - The current page number within the current data set <br>  * X-Total-Count - The total element count in the current data set <br>  * X-Total-Pages - The total page count in the current data set <br>  * X-Filter - If filtering is applied using &#x60;?q&#x3D;field:criteria&#x60; URL query parameters, the filter criteria is returned here <br>  * X-Sort - If sorting is applied using &#x60;?sort&#x3D;sort_criteria&#x60; URL query parameters, the sort criteria is returned here <br>  </td></tr>
     </table>
     */
    public List<Endpoint> getEndpoints(String q, String sort, Integer page, Integer perPage) throws ApiException {
        ApiResponse<List<Endpoint>> localVarResp = getEndpointsWithHttpInfo(q, sort, page, perPage);
        return localVarResp.getData();
    }

    /**
     * List Endpoints
     * Returns the list of endpoints, filtered, sorted and paged according to query parameters.
     * @param q Filter parameter in &#x60;&lt;filter&gt;:&lt;value&gt;&#x60; format. Expects comma separated list of filtering criteria out of the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param sort Sort properties in a comma separated list from the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;id&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param page Current page number (optional)
     * @param perPage Paging parameters defining the number of items per page (optional)
     * @return ApiResponse&lt;List&lt;Endpoint&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully returned a list of Endpoints. </td><td>  * Link - URLs for navigational links used to retrieve first, previous, next and last page in a dataset <br>  * X-Count-Per-Page - The requested &#x60;per_page&#x60; parameter <br>  * X-Current-Page - The current page number within the current data set <br>  * X-Total-Count - The total element count in the current data set <br>  * X-Total-Pages - The total page count in the current data set <br>  * X-Filter - If filtering is applied using &#x60;?q&#x3D;field:criteria&#x60; URL query parameters, the filter criteria is returned here <br>  * X-Sort - If sorting is applied using &#x60;?sort&#x3D;sort_criteria&#x60; URL query parameters, the sort criteria is returned here <br>  </td></tr>
     </table>
     */
    public ApiResponse<List<Endpoint>> getEndpointsWithHttpInfo(String q, String sort, Integer page, Integer perPage) throws ApiException {
        okhttp3.Call localVarCall = getEndpointsValidateBeforeCall(q, sort, page, perPage, null);
        Type localVarReturnType = new TypeToken<List<Endpoint>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List Endpoints (asynchronously)
     * Returns the list of endpoints, filtered, sorted and paged according to query parameters.
     * @param q Filter parameter in &#x60;&lt;filter&gt;:&lt;value&gt;&#x60; format. Expects comma separated list of filtering criteria out of the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param sort Sort properties in a comma separated list from the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;id&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  (optional)
     * @param page Current page number (optional)
     * @param perPage Paging parameters defining the number of items per page (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully returned a list of Endpoints. </td><td>  * Link - URLs for navigational links used to retrieve first, previous, next and last page in a dataset <br>  * X-Count-Per-Page - The requested &#x60;per_page&#x60; parameter <br>  * X-Current-Page - The current page number within the current data set <br>  * X-Total-Count - The total element count in the current data set <br>  * X-Total-Pages - The total page count in the current data set <br>  * X-Filter - If filtering is applied using &#x60;?q&#x3D;field:criteria&#x60; URL query parameters, the filter criteria is returned here <br>  * X-Sort - If sorting is applied using &#x60;?sort&#x3D;sort_criteria&#x60; URL query parameters, the sort criteria is returned here <br>  </td></tr>
     </table>
     */
    public okhttp3.Call getEndpointsAsync(String q, String sort, Integer page, Integer perPage, final ApiCallback<List<Endpoint>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getEndpointsValidateBeforeCall(q, sort, page, perPage, _callback);
        Type localVarReturnType = new TypeToken<List<Endpoint>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}

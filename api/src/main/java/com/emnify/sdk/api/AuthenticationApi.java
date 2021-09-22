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


import com.emnify.sdk.model.Authentication;
import com.emnify.sdk.model.AuthenticationResponse;
import com.emnify.sdk.model.InlineResponse404;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationApi {
    private ApiClient localVarApiClient;

    public AuthenticationApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AuthenticationApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for authenticate
     * @param authentication Authentication using Application Tokens or user/password combination (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Authentication Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call authenticateCall(Authentication authentication, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = authentication;

        // create path and map variables
        String localVarPath = "/api/v1/authenticate";

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

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call authenticateValidateBeforeCall(Authentication authentication, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'authentication' is set
        if (authentication == null) {
            throw new ApiException("Missing the required parameter 'authentication' when calling authenticate(Async)");
        }
        

        okhttp3.Call localVarCall = authenticateCall(authentication, _callback);
        return localVarCall;

    }

    /**
     * Retrieve Authentication Token
     * This entrypoint returns a JWT &#x60;auth_token&#x60; for authenticating further requests to the API. 
     * @param authentication Authentication using Application Tokens or user/password combination (required)
     * @return AuthenticationResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Authentication Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public AuthenticationResponse authenticate(Authentication authentication) throws ApiException {
        ApiResponse<AuthenticationResponse> localVarResp = authenticateWithHttpInfo(authentication);
        return localVarResp.getData();
    }

    /**
     * Retrieve Authentication Token
     * This entrypoint returns a JWT &#x60;auth_token&#x60; for authenticating further requests to the API. 
     * @param authentication Authentication using Application Tokens or user/password combination (required)
     * @return ApiResponse&lt;AuthenticationResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Authentication Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<AuthenticationResponse> authenticateWithHttpInfo(Authentication authentication) throws ApiException {
        okhttp3.Call localVarCall = authenticateValidateBeforeCall(authentication, null);
        Type localVarReturnType = new TypeToken<AuthenticationResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve Authentication Token (asynchronously)
     * This entrypoint returns a JWT &#x60;auth_token&#x60; for authenticating further requests to the API. 
     * @param authentication Authentication using Application Tokens or user/password combination (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Authentication Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Unexpected error in API call. See HTTP response body for details. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call authenticateAsync(Authentication authentication, final ApiCallback<AuthenticationResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = authenticateValidateBeforeCall(authentication, _callback);
        Type localVarReturnType = new TypeToken<AuthenticationResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}

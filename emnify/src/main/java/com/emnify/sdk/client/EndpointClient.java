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

package com.emnify.sdk.client;

import java.util.List;
import java.util.stream.Collectors;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.api.EndpointApi;
import com.emnify.sdk.client.exception.SdkApiException;
import com.emnify.sdk.client.exception.SdkException;
import com.emnify.sdk.client.exception.SdkRetryException;
import com.emnify.sdk.client.model.Endpoint;
import com.emnify.sdk.client.model.QueryParams;
import com.emnify.sdk.client.model.Quota;
import com.emnify.sdk.client.retrier.AuthenticationRetrier;
import com.emnify.sdk.client.retrier.Retriable;
import com.emnify.sdk.client.retrier.RetryLimitExceeded;
import com.emnify.sdk.client.retrier.RetryResult;

public class EndpointClient {

    private final AuthenticationRetrier authRetrier;
    private final EndpointApi endpointApi;

    protected EndpointClient(ApiClient apiClient, AuthenticationRetrier authRetrier) {
        this.authRetrier = authRetrier;
        this.endpointApi = new EndpointApi(apiClient);
    }

    /**
     * Return the list of endpoints without filters, sorting and pagination.
     *
     * @return list of endpoints
     * @throws SdkException
     */
    public List<Endpoint> listEndpoints() throws SdkException {
        return listEndpoints(null);
    }

    /**
     * Returns the list of endpoints, filtered, sorted and paged according to query parameters.
     *
     * @param params parameters of filtering, sorting and pagination
     * @return list of endpoints
     * @throws SdkException
     */
    public List<Endpoint> listEndpoints(final QueryParams params) throws SdkException {
        Retriable<List<Endpoint>> retriable = () -> {
            List<com.emnify.sdk.model.Endpoint> endpoints;
            if (params == null) {
                endpoints = endpointApi.getEndpoints(null, null, null, null);
            } else {
                endpoints = endpointApi.getEndpoints(params.getFilter(), params.getSort(), params.getPage(), params.getPerPage());
            }

            return endpoints.stream().map(Endpoint::toClientModel).collect(Collectors.toList());
        };

        try {
            RetryResult<List<Endpoint>> result = authRetrier.perform(retriable);
            return result.getResult();
        } catch (ApiException e) {
            throw SdkApiException.create("Error getting list of endpoints", e);
        } catch (RetryLimitExceeded e) {
            throw SdkRetryException.create("Error getting list of endpoints", e);
        }
    }

    /**
     * Gets the quota by endpoint identifier or throw exception.
     *
     * @param endpointId
     * @return quota if such a data exists otherwise throw exception
     * @throws SdkException
     */
    public Quota getQuota(int endpointId) throws SdkException {
        Retriable<Quota> retriable = () -> Quota.toClientModel(endpointApi.endpointQuotaDataByEndpointIdGet(endpointId));

        try {
            RetryResult<Quota> result = authRetrier.perform(retriable);
            return result.getResult();
        } catch (ApiException e) {
            throw SdkApiException.create("Error getting quota by endpoint ID: " + endpointId, e);
        } catch (RetryLimitExceeded e) {
            throw SdkRetryException.create("Error getting quota by endpoint ID: " + endpointId, e);
        }
    }

    /**
     * Sets the data quota of the selected endpoint.
     * If the quota already exists, only the provided values will be updated.
     *
     * @param endpointId endpoint identifier
     * @param quota      endpoint quota data
     * @throws SdkException
     */
    public void saveQuota(int endpointId, Quota quota) throws SdkException {
        if (endpointId <= 0 || quota == null) {
            throw new SdkException("Endpoint ID and Quota data are required!");
        }

        Retriable<Void> retriable = () -> {
            endpointApi.endpointQuotaDataByEndpointIdPost(endpointId, Quota.toApiModel(quota));
            return null;
        };

        try {
            authRetrier.perform(retriable);
        } catch (ApiException e) {
            throw SdkApiException.create("Error saving quota for endpoint ID: " + endpointId, e);
        } catch (RetryLimitExceeded e) {
            throw SdkRetryException.create("Error saving quota for endpoint ID: " + endpointId, e);
        }
    }
}

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

package com.emnify.sdk.client.retrier;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.client.exception.ClientException;
import com.emnify.sdk.client.auth.Authentication;
import static com.emnify.sdk.client.ApiExceptionUtils.isUnauthorizedException;

public class AuthenticationRetrier {

    public static final int DEFAULT_MAX_AUTH_ATTEMPTS = 3;

    private final int maxAttempts;
    private Authentication authentication;
    private ApiClient apiClient;

    public AuthenticationRetrier(Authentication authentication, ApiClient apiClient) {
        this.maxAttempts = DEFAULT_MAX_AUTH_ATTEMPTS;
        this.authentication = authentication;
        this.apiClient = apiClient;
    }

    public AuthenticationRetrier(int maxAttempts, Authentication authentication, ApiClient apiClient) {
        this.maxAttempts = maxAttempts;
        this.authentication = authentication;
        this.apiClient = apiClient;
    }

    public <E> RetryResult<E> perform(Retriable<E> retriable) throws RetryLimitExceeded, ClientException, ApiException {
        int count = 0;
        while (!exceedAttempts(count)) {
            count++;
            try {
                return new RetryResult<>(retriable.attempt());
            } catch (ApiException e) {
                if (isUnauthorizedException(e)) {
                    reauthenticate();
                } else {
                    throw e;
                }
            }
        }

        throw new RetryLimitExceeded(maxAttempts);
    }

    private void reauthenticate() throws ClientException {
        authentication.authenticate(apiClient);
    }

    private boolean exceedAttempts(int attempt) {
        return attempt >= maxAttempts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthenticationRetrier{");
        sb.append("maxAttempts=").append(maxAttempts);
        sb.append(", authentication=").append(authentication);
        sb.append(", apiClient=").append(apiClient);
        sb.append('}');
        return sb.toString();
    }
}

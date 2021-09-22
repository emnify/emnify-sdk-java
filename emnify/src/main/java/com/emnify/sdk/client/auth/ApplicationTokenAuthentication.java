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

package com.emnify.sdk.client.auth;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.api.AuthenticationApi;
import com.emnify.sdk.client.exception.SdkApiException;
import com.emnify.sdk.client.exception.SdkException;
import com.emnify.sdk.model.AuthenticationResponse;
import lombok.ToString;

@ToString
public class ApplicationTokenAuthentication extends AbstractAuthentication {

    private final String appToken;

    public ApplicationTokenAuthentication(String appToken) {
        this.appToken = appToken;
    }

    @Override
    public void authenticate(ApiClient apiClient) throws SdkException {
        try {
            if (isExpired()) {
                AuthenticationApi authClient = new AuthenticationApi(apiClient);

                com.emnify.sdk.model.Authentication tokenAuth = new com.emnify.sdk.model.Authentication();
                tokenAuth.setApplicationToken(appToken);

                AuthenticationResponse response = authClient.authenticate(tokenAuth);

                apiClient.setBearerToken(response.getAuthToken());
                tokens = new AuthenticationTokens(response.getAuthToken(), appToken);
            }
        } catch (ApiException e) {
            throw SdkApiException.create("Unable to authenticate by application token: " + appToken, e);
        }
    }

    @Override
    public boolean isRefreshTokenValid() {
        // always true
        return true;
    }
}

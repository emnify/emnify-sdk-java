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

package com.emnify.sdk.client.config;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.client.auth.ApplicationTokenAuthentication;
import com.emnify.sdk.client.auth.Authentication;
import com.emnify.sdk.client.retrier.AuthenticationRetrier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public class Configuration extends com.emnify.sdk.Configuration {

    private static final String DEFAULT_BASE_PATH = "https://cdn.emnify.net";

    public static ApiClient getApiClient() {
        ApiClient apiClient = getDefaultApiClient();
        String basePath = SystemUtils.getEnvironmentVariable("EMNIFY_BASE_PATH", DEFAULT_BASE_PATH);
        apiClient = apiClient.setBasePath(basePath);

        return apiClient;
    }



    public static Authentication createAuthentication(String appToken) {
        if (StringUtils.isNotBlank(appToken)) {
            return new ApplicationTokenAuthentication(appToken);
        }

        return null;
    }

    public static AuthenticationRetrier createAuthenticationRetrier(Authentication authentication, ApiClient apiClient) {
        return new AuthenticationRetrier(authentication, apiClient);
    }

    public static AuthenticationRetrier createAuthenticationRetrier(int maxAttempts, Authentication authentication, ApiClient apiClient) {
        return new AuthenticationRetrier(maxAttempts, authentication, apiClient);
    }
}

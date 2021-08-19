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

package com.emnify.sdk.client.exception;

import com.emnify.sdk.ApiException;
import com.emnify.sdk.client.retrier.RetryLimitExceeded;
import org.apache.commons.lang3.StringUtils;

public class ClientException extends Exception {

    private ApiException apiException;

    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, ApiException apiException) {
        this(String.format("%s, cause: %s %s",
                message,
                StringUtils.defaultString(apiException.getMessage()),
                StringUtils.defaultString(apiException.getResponseBody())));
        this.apiException = apiException;
    }

    public ClientException(String message, RetryLimitExceeded limitExceeded) {
        this(String.format("%s, cause: %s", message, limitExceeded.getMessage()));
    }
}

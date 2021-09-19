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

public class SdkApiException extends SdkException {

    private SdkApiException(String message) {
        super(message);
    }

    private SdkApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public static SdkApiException create(String message, ApiException cause) {
        return builder().message(message).cause(cause).build();
    }

    private static SdkExceptionBuilder builder() {
        return new SdkApiExceptionBuilder();
    }

    protected static class SdkApiExceptionBuilder implements SdkExceptionBuilder {

        private String message;

        private ApiException cause;

        @Override
        public SdkApiException build() {
            String message = this.message;

            if (cause != null) {
                message += String.format(" Cause: %s %s", cause.getMessage(), cause.getResponseBody());
            }

            if (cause == null) {
                return new SdkApiException(message);
            } else {
                return new SdkApiException(message, cause);
            }
        }

        @Override
        public SdkApiExceptionBuilder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public <E extends Throwable> SdkApiExceptionBuilder cause(E cause) {
            this.cause = (ApiException) cause;
            return this;
        }
    }
}

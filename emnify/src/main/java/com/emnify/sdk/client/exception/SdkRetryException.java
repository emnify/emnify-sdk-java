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

import com.emnify.sdk.client.retrier.RetryLimitExceeded;
import lombok.Builder;

@Builder
public class SdkRetryException extends SdkException {

    private SdkRetryException(String message) {
        super(message);
    }
    private SdkRetryException(String message, Throwable cause) {
        super(message, cause);
    }

    public static SdkRetryException create(String message) {
        return builder().message(message).build();
    }

    public static SdkRetryException create(String message, RetryLimitExceeded cause) {
        return builder().message(message).cause(cause).build();
    }

    private static SdkRetryException.SdkRetryExceptionBuilder builder() {
        return new SdkRetryException.SdkRetryExceptionBuilder();
    }

    protected static class SdkRetryExceptionBuilder implements SdkExceptionBuilder {

        private String message;

        private RetryLimitExceeded cause;

        @Override
        public SdkRetryException build() {
            String message = this.message;

            if (cause != null) {
                message += String.format("%s, cause: %s", message, cause.getMessage());
            }

            return new SdkRetryException(message, cause);
        }

        @Override
        public SdkRetryExceptionBuilder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public <E extends Throwable> SdkRetryExceptionBuilder cause(E cause) {
            this.cause = (RetryLimitExceeded) cause;
            return this;
        }
    }
}

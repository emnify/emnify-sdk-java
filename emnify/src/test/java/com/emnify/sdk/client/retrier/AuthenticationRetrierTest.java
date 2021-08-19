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

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.client.auth.Authentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import static com.emnify.sdk.client.ApiExceptionUtils.UNAUTHORIZED_CODE;
import static com.emnify.sdk.client.config.Configuration.createAuthenticationRetrier;
import static com.emnify.sdk.client.util.TestUtils.expectException;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
public class AuthenticationRetrierTest {

    private static final int MAX_ATTEMPTS = 5;

    @Mock
    private Authentication authentication;

    @Mock
    private ApiClient apiClient;

    @Test
    public void test_Perform_Default() throws Exception {
        String expectedValue = "Attempt executed successfully";

        AuthenticationRetrier retrier = createAuthenticationRetrier(MAX_ATTEMPTS, authentication, apiClient);
        Retriable<String> retriable = () -> expectedValue;
        RetryResult<String> performResult = retrier.perform(retriable);

        assertEquals(expectedValue, performResult.getResult());

        verify(authentication, never()).authenticate(any());
    }

    @Test
    public void test_Perform_Unauthorized() throws Exception {
        String expectedValue = "Attempt executed successfully";
        AtomicInteger atomicInteger = new AtomicInteger(1);

        AuthenticationRetrier retrier = createAuthenticationRetrier(MAX_ATTEMPTS, authentication, apiClient);
        Retriable<String> retriable = () -> {
            if (atomicInteger.getAndAdd(1) == 1) {
                throw new ApiException(UNAUTHORIZED_CODE, Collections.emptyMap(), "");
            }
            return expectedValue;
        };

        RetryResult<String> performResult = retrier.perform(retriable);

        assertEquals(expectedValue, performResult.getResult());

        verify(authentication, times(1)).authenticate(any());
    }

    @Test
    public void test_Perform_Unauthorized_ExceedDefaultLimit() throws Exception {
        AuthenticationRetrier retrier = createAuthenticationRetrier(authentication, apiClient);
        Retriable<String> retriable = () -> {
            throw new ApiException(UNAUTHORIZED_CODE, Collections.emptyMap(), "");
        };

        expectException(() -> retrier.perform(retriable), RetryLimitExceeded.class);

        verify(authentication, times(AuthenticationRetrier.DEFAULT_MAX_AUTH_ATTEMPTS)).authenticate(any());
    }

    @Test
    public void test_Perform_Unauthorized_ExceedCustomLimit() throws Exception {
        AuthenticationRetrier retrier = createAuthenticationRetrier(MAX_ATTEMPTS, authentication, apiClient);
        Retriable<String> retriable = () -> {
            throw new ApiException(UNAUTHORIZED_CODE, Collections.emptyMap(), "");
        };

        expectException(() -> retrier.perform(retriable), RetryLimitExceeded.class);

        verify(authentication, times(MAX_ATTEMPTS)).authenticate(any());
    }

    @Test
    public void test_Perform_Exception() throws Exception {
        AuthenticationRetrier retrier = createAuthenticationRetrier(MAX_ATTEMPTS, authentication, apiClient);
        Retriable<String> retriable = () -> { throw new ApiException("Error while perform execute"); };

        expectException(() -> retrier.perform(retriable), ApiException.class, "Error while perform execute");

        verify(authentication, never()).authenticate(any());
    }

}

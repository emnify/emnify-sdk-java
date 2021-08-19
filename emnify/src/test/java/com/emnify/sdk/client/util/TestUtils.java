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

package com.emnify.sdk.client.util;

import java.util.Date;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.api.AuthenticationApi;
import com.emnify.sdk.client.config.Configuration;
import org.apache.commons.lang3.RandomUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

public class TestUtils {

    public static <E extends Exception> void expectException(RunnableWithExceptions function, Class<E> expectedClass) {
        try {
            function.run();
            fail("Test did not generate expected exception of type " + expectedClass.getSimpleName());
        } catch (Exception e) {
            assertTrue(e.getClass().isAssignableFrom(expectedClass));
        }
    }

    public static <E extends Exception> void expectException(RunnableWithExceptions function, Class<E> expectedClass, String expectedMessage) {
        try {
            function.run();
            fail("Test did not generate expected exception of type " + expectedClass.getSimpleName());
        } catch (Exception e) {
            assertTrue(e.getClass().isAssignableFrom(expectedClass));
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    public static void setupApiClientMock(ApiClient apiClientMock) throws Exception {
        whenNew(ApiClient.class)
                .withNoArguments()
                .thenReturn(apiClientMock);

        reset(apiClientMock);

        Configuration.setDefaultApiClient(apiClientMock);
        when(apiClientMock.setBasePath(any())).thenReturn(apiClientMock);
    }

    public static void setupAuthenticationApiMock(AuthenticationApi authApiMock, ApiClient apiClientMock) throws Exception {
        whenNew(AuthenticationApi.class)
                .withArguments(apiClientMock)
                .thenReturn(authApiMock);

        reset(authApiMock);
    }

    public static String getJWTToken(Date expirationDate) {
        return JWT.create()
                .withClaim("exp", expirationDate)
                .sign(Algorithm.none());
    }

    @FunctionalInterface
    public interface RunnableWithExceptions {
        void run() throws Exception;
    }

    public static int generateId() {
        return 1 + RandomUtils.nextInt(0, Integer.MAX_VALUE);
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}

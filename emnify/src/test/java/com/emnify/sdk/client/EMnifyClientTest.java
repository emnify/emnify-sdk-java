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

import java.util.Date;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.client.auth.ApplicationTokenAuthentication;
import com.emnify.sdk.client.auth.BasicAuthentication;
import com.emnify.sdk.client.config.Configuration;
import com.emnify.sdk.client.exception.ClientException;
import com.emnify.sdk.client.auth.AuthenticationTokens;
import com.emnify.sdk.client.util.TestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static com.emnify.sdk.client.util.TestUtils.setupApiClientMock;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Configuration.class, EMnify.class})
public class EMnifyClientTest {

    private static final String USERNAME = TestUtils.generateString();
    private static final String PASSWORD = TestUtils.generateString();
    private static final String APPLICATION_TOKEN = TestUtils.getJWTToken(null);

    private static final Date TOKEN_EXPIRATION_DATE = DateUtils.addMinutes(new Date(), 5);
    private static final Date REFRESH_TOKEN_EXPIRATION_DATE = DateUtils.addMinutes(new Date(), 10);

    private static final String AUTH_TOKEN = TestUtils.getJWTToken(TOKEN_EXPIRATION_DATE);
    private static final String REFRESH_TOKEN = TestUtils.getJWTToken(REFRESH_TOKEN_EXPIRATION_DATE);

    @Mock
    private ApiClient apiClientMock;

    @Before
    public void setUp() throws Exception {
        setupApiClientMock(apiClientMock);
    }

    @Test
    public void test_authenticate_application_token() throws Exception {
        ApplicationTokenAuthentication mock = mock(ApplicationTokenAuthentication.class);

        whenNew(ApplicationTokenAuthentication.class)
                .withArguments(APPLICATION_TOKEN)
                .thenReturn(mock);

        // execute
        EMnify client = EMnify.authenticate(APPLICATION_TOKEN);

        // verify
        verifyNew(ApplicationTokenAuthentication.class).withArguments(APPLICATION_TOKEN);
        verify(mock).authenticate(apiClientMock);

        // assert
        assertNotNull(client);
        assertFalse(client.isAuthenticationExpired());
    }

    @Test
    public void test_authenticate_application_token_expired() throws Exception {
        ApplicationTokenAuthentication mock = mock(ApplicationTokenAuthentication.class);

        whenNew(ApplicationTokenAuthentication.class)
                .withArguments(APPLICATION_TOKEN)
                .thenReturn(mock);

        // execute
        EMnify client = EMnify.authenticate(APPLICATION_TOKEN);

        // verify
        verifyNew(ApplicationTokenAuthentication.class).withArguments(APPLICATION_TOKEN);
        verify(mock).authenticate(apiClientMock);

        // assert
        assertNotNull(client);
    }

    @Test
    public void test_authenticate_application_token_refreshExpired() throws Exception {
        ApplicationTokenAuthentication mock = mock(ApplicationTokenAuthentication.class);
        whenNew(ApplicationTokenAuthentication.class)
                .withArguments(APPLICATION_TOKEN)
                .thenReturn(mock);

        // execute
        EMnify client = EMnify.authenticate(APPLICATION_TOKEN);
        client.refreshAuthIfNecessary();

        // verify
        verifyNew(ApplicationTokenAuthentication.class).withArguments(APPLICATION_TOKEN);
        verify(mock, times(2)).authenticate(apiClientMock);

        // assert
        assertNotNull(client);
    }

    @Test
    public void test_authenticate_application_token_exception() throws Exception {
        ApplicationTokenAuthentication mock = mock(ApplicationTokenAuthentication.class);

        whenNew(ApplicationTokenAuthentication.class)
                .withArguments(APPLICATION_TOKEN)
                .thenReturn(mock);

        doThrow(new ClientException()).when(mock).authenticate(apiClientMock);

        // execute
        TestUtils.expectException(() -> EMnify.authenticate(APPLICATION_TOKEN), ClientException.class);

        // verify
        verifyNew(ApplicationTokenAuthentication.class).withArguments(APPLICATION_TOKEN);
        verify(mock).authenticate(apiClientMock);
    }

    @Test
    public void test_authenticate_basic() throws Exception {
        BasicAuthentication mock = mock(BasicAuthentication.class);

        whenNew(BasicAuthentication.class)
                .withArguments(USERNAME, PASSWORD)
                .thenReturn(mock);

        AuthenticationTokens tokens = new AuthenticationTokens(AUTH_TOKEN, REFRESH_TOKEN);

        // execute
        EMnify client = EMnify.authenticate(USERNAME, PASSWORD);

        // verify
        verifyNew(BasicAuthentication.class).withArguments(USERNAME, PASSWORD);
        verify(mock).authenticate(apiClientMock);

        // assert
        assertNotNull(client);
    }

    @Test
    public void test_authenticate_basic_exception() throws Exception {
        BasicAuthentication mock = mock(BasicAuthentication.class);

        whenNew(BasicAuthentication.class)
                .withArguments(USERNAME, PASSWORD)
                .thenReturn(mock);

        doThrow(new ClientException()).when(mock).authenticate(apiClientMock);

        // execute
        TestUtils.expectException(() -> EMnify.authenticate(USERNAME, PASSWORD), ClientException.class);

        // verify
        verifyNew(BasicAuthentication.class).withArguments(USERNAME, PASSWORD);
        verify(mock).authenticate(apiClientMock);
    }
}

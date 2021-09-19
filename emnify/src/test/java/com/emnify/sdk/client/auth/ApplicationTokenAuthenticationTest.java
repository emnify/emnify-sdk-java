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

import java.util.Date;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.api.AuthenticationApi;
import com.emnify.sdk.client.config.Configuration;
import com.emnify.sdk.client.exception.SdkApiException;
import com.emnify.sdk.client.util.TestUtils;
import com.emnify.sdk.model.Authentication;
import com.emnify.sdk.model.AuthenticationResponse;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static com.emnify.sdk.client.util.TestUtils.setupApiClientMock;
import static com.emnify.sdk.client.util.TestUtils.setupAuthenticationApiMock;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Configuration.class, ApplicationTokenAuthentication.class})
public class ApplicationTokenAuthenticationTest {

    private static final Date TOKEN_EXPIRATION_DATE = DateUtils.addMinutes(new Date(), 5);

    private static final String AUTH_TOKEN = TestUtils.getJWTToken(TOKEN_EXPIRATION_DATE);
    private static final String APPLICATION_TOKEN = TestUtils.getJWTToken(null);

    @Mock
    private ApiClient apiClientMock;

    @Mock
    private AuthenticationApi authApiMock;

    @Before
    public void setUp() throws Exception {
        setupApiClientMock(apiClientMock);
        setupAuthenticationApiMock(authApiMock, apiClientMock);
    }

    @Test
    public void test_Authenticate_Default() throws Exception {
        Authentication requestMock = mock(com.emnify.sdk.model.Authentication.class);
        whenNew(Authentication.class)
                .withNoArguments()
                .thenReturn(requestMock);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAuthToken(AUTH_TOKEN);

        when(authApiMock.authenticate(requestMock)).thenReturn(response);

        // execute
        ApplicationTokenAuthentication authentication = new ApplicationTokenAuthentication(APPLICATION_TOKEN);
        authentication.authenticate(apiClientMock);

        // verify
        verify(requestMock).setApplicationToken(APPLICATION_TOKEN);
        verify(requestMock, never()).setUsername(any());
        verify(requestMock, never()).setPassword(any());

        verify(authApiMock).authenticate(requestMock);
        verify(apiClientMock).setBearerToken(AUTH_TOKEN);

        // assert
        assertFalse(authentication.isExpired());
        assertTrue(authentication.isRefreshTokenValid());
    }

    @Test
    public void test_Authenticate_Exception() throws Exception {
        com.emnify.sdk.model.Authentication request = new com.emnify.sdk.model.Authentication();
        request.setApplicationToken(APPLICATION_TOKEN);

        when(authApiMock.authenticate(request)).thenThrow(new ApiException());

        // execute
        TestUtils.expectException(() -> new ApplicationTokenAuthentication(APPLICATION_TOKEN).authenticate(apiClientMock),
                SdkApiException.class, "Unable to authenticate by application token: " + APPLICATION_TOKEN + " Cause: null null");

        // verify
        verify(authApiMock).authenticate(request);
    }
}

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
import com.emnify.sdk.client.exception.ClientException;
import com.emnify.sdk.client.config.Configuration;
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
import static com.emnify.sdk.client.util.TestUtils.expectException;
import static com.emnify.sdk.client.util.TestUtils.setupApiClientMock;
import static com.emnify.sdk.client.util.TestUtils.setupAuthenticationApiMock;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Configuration.class, BasicAuthentication.class})
public class BasicAuthenticationTest {

    private static final Date TOKEN_EXPIRATION_DATE = DateUtils.addMinutes(new Date(), 5);
    private static final Date REFRESH_TOKEN_EXPIRATION_DATE = DateUtils.addMinutes(new Date(), 10);

    private static final String AUTH_TOKEN = TestUtils.getJWTToken(TOKEN_EXPIRATION_DATE);
    private static final String EXPIRED_AUTH_TOKEN = TestUtils.getJWTToken(DateUtils.addMinutes(new Date(), -10));
    private static final String REFRESH_TOKEN = TestUtils.getJWTToken(REFRESH_TOKEN_EXPIRATION_DATE);

    private static final String USERNAME = TestUtils.generateString();
    private static final String PASSWORD = "secretPassword";
    private static final String HASHED_PASSWORD = "5b41eb73810d06f17bc7585fae9a87ecf677fe5c";

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
        Authentication requestMock = mock(Authentication.class);
        whenNew(Authentication.class)
                .withNoArguments()
                .thenReturn(requestMock);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAuthToken(AUTH_TOKEN);
        response.setRefreshToken(REFRESH_TOKEN);

        when(authApiMock.authenticate(requestMock)).thenReturn(response);

        // execute
        BasicAuthentication authentication = new BasicAuthentication(USERNAME, PASSWORD);
        authentication.authenticate(apiClientMock);

        // assert
        assertFalse(authentication.isExpired());
        assertTrue(authentication.isRefreshTokenValid());

        // verify
        verify(requestMock).setUsername(USERNAME);
        verify(requestMock).setPassword(HASHED_PASSWORD);
        verify(requestMock, never()).setApplicationToken(any());

        verify(authApiMock).authenticate(requestMock);
        verify(apiClientMock).setBearerToken(AUTH_TOKEN);

    }

    @Test
    public void test_Authenticate_InvalidRefreshToken() throws Exception {
        Authentication requestMock = mock(Authentication.class);
        whenNew(Authentication.class)
                .withNoArguments()
                .thenReturn(requestMock);

        AuthenticationResponse expiredTokensResponse = new AuthenticationResponse();
        expiredTokensResponse.setAuthToken(EXPIRED_AUTH_TOKEN);
        expiredTokensResponse.setRefreshToken(EXPIRED_AUTH_TOKEN);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAuthToken(AUTH_TOKEN);
        response.setRefreshToken(REFRESH_TOKEN);

        when(authApiMock.authenticate(requestMock)).thenReturn(expiredTokensResponse).thenReturn(response);

        // execute
        BasicAuthentication authentication = new BasicAuthentication(USERNAME, PASSWORD);
        authentication.authenticate(apiClientMock);

        assertTrue(authentication.isExpired());
        assertFalse(authentication.isRefreshTokenValid());

        // execute one more time when auth token is set as expired
        authentication.authenticate(apiClientMock);

        // assert
        assertFalse(authentication.isExpired());
        assertTrue(authentication.isRefreshTokenValid());

        // verify
        verify(requestMock, times(2)).setUsername(USERNAME);
        verify(requestMock, times(2)).setPassword(HASHED_PASSWORD);
        verify(requestMock, never()).setRefreshToken(EXPIRED_AUTH_TOKEN);
        verify(requestMock, never()).setApplicationToken(any());

        verify(authApiMock, times(2)).authenticate(requestMock);
        verify(apiClientMock).setBearerToken(EXPIRED_AUTH_TOKEN);
        verify(apiClientMock).setBearerToken(AUTH_TOKEN);
    }

    @Test
    public void test_Authenticate_ExpiredToken() throws Exception {
        Authentication requestMock = mock(Authentication.class);
        whenNew(Authentication.class)
                .withNoArguments()
                .thenReturn(requestMock);

        AuthenticationResponse expiredTokenResponse = new AuthenticationResponse();
        expiredTokenResponse.setAuthToken(EXPIRED_AUTH_TOKEN);
        expiredTokenResponse.setRefreshToken(REFRESH_TOKEN);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAuthToken(AUTH_TOKEN);
        response.setRefreshToken(REFRESH_TOKEN);

        when(authApiMock.authenticate(requestMock)).thenReturn(expiredTokenResponse).thenReturn(response);

        // execute
        BasicAuthentication authentication = new BasicAuthentication(USERNAME, PASSWORD);
        authentication.authenticate(apiClientMock);

        // assert
        assertTrue(authentication.isExpired());
        assertTrue(authentication.isRefreshTokenValid());

        // execute one more time when auth token is set as expired
        authentication.authenticate(apiClientMock);

        // assert
        assertFalse(authentication.isExpired());
        assertTrue(authentication.isRefreshTokenValid());

        // verify
        verify(requestMock).setUsername(USERNAME);
        verify(requestMock).setPassword(HASHED_PASSWORD);
        verify(requestMock).setRefreshToken(REFRESH_TOKEN);
        verify(requestMock, never()).setApplicationToken(any());

        verify(authApiMock, times(2)).authenticate(requestMock);
        verify(apiClientMock).setBearerToken(EXPIRED_AUTH_TOKEN);
        verify(apiClientMock).setBearerToken(AUTH_TOKEN);

    }

    @Test
    public void test_Authenticate_EmptyPassword() throws Exception {
        Authentication requestMock = mock(Authentication.class);
        whenNew(Authentication.class)
                .withNoArguments()
                .thenReturn(requestMock);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAuthToken(AUTH_TOKEN);

        when(authApiMock.authenticate(requestMock)).thenReturn(response);

        // execute
        expectException(
                () -> new BasicAuthentication(USERNAME, null).authenticate(apiClientMock),
                ClientException.class, "Unable to authenticate user: " + USERNAME);

        // verify
        verify(requestMock).setUsername(USERNAME);
        verify(requestMock, never()).setPassword("");
        verify(requestMock, never()).setApplicationToken(any());

        verify(authApiMock, never()).authenticate(requestMock);
        verify(apiClientMock, never()).setBearerToken(AUTH_TOKEN);

    }

    @Test
    public void test_Authenticate_Exception() throws Exception {
        Authentication requestMock = mock(Authentication.class);
        whenNew(Authentication.class).withNoArguments().thenReturn(requestMock);

        when(authApiMock.authenticate(requestMock)).thenThrow(new ApiException());

        // execute
        expectException(
                () -> new BasicAuthentication(USERNAME, PASSWORD).authenticate(apiClientMock),
                ClientException.class, "Unable to authenticate user: " + USERNAME);

        // verify
        verify(requestMock).setUsername(USERNAME);
        verify(requestMock).setPassword(HASHED_PASSWORD);
        verify(requestMock, never()).setApplicationToken(any());

        verify(authApiMock).authenticate(requestMock);
        verify(apiClientMock, never()).setBearerToken(AUTH_TOKEN);
    }
}

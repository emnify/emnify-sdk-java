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

import java.security.MessageDigest;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.api.AuthenticationApi;
import com.emnify.sdk.client.exception.SdkApiException;
import com.emnify.sdk.client.exception.SdkException;
import com.emnify.sdk.model.AuthenticationResponse;
import lombok.ToString;

@ToString
public class BasicAuthentication extends AbstractAuthentication {

    private final String username;
    private final String password;

    public BasicAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void authenticate(ApiClient apiClient) throws SdkException {
        try {
            if (isExpired()) {
                AuthenticationApi authClient = new AuthenticationApi(apiClient);

                com.emnify.sdk.model.Authentication userAuth = new com.emnify.sdk.model.Authentication();
                if (isRefreshTokenValid()) {
                    userAuth.setRefreshToken(tokens.getRefreshToken().getToken());
                    tokens.getRefreshToken().setValid(false);
                } else {
                    userAuth.setUsername(username);
                    userAuth.setPassword(stringToHex(hash(password)));
                }

                AuthenticationResponse response = authClient.authenticate(userAuth);
                apiClient.setBearerToken(response.getAuthToken());

                tokens = new AuthenticationTokens(response.getAuthToken(), response.getRefreshToken());
            }
        } catch (ApiException e) {
            throw SdkApiException.create("Unable to authenticate user: " + username, e);
        }
    }

    /**
     * Checks if authentication refresh token is not expired and was not used
     *
     * @return false if token for refreshing is expired or invalid otherwise true
     */
    public boolean isRefreshTokenValid() {
        if (tokens != null) {
            AuthenticationTokens.JWTData refreshToken = tokens.getRefreshToken();
            if (refreshToken != null) {
                return refreshToken.isValid() && !tokens.getRefreshToken().isExpired();
            }
        }

        return false;
    }

    private static byte[] hash(String password) throws SdkException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(password.getBytes());

            return messageDigest.digest();
        } catch (Exception e) {
            throw new SdkException("Unable to compute hash while authorizing: " + e.getMessage());
        }
    }

    private static String stringToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

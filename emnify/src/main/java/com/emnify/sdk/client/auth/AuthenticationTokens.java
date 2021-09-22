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

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.ToString;

@ToString
public class AuthenticationTokens {

    private JWTData authToken;
    private JWTData refreshToken;

    public AuthenticationTokens(String authToken, String refreshToken) throws JWTDecodeException {
        this.authToken = toJwtData(authToken);
        this.refreshToken = toJwtData(refreshToken);
    }

    public JWTData getRefreshToken() {
        return refreshToken;
    }

    public JWTData getAuthToken() {
        return authToken;
    }

    public static class JWTData {
        private String token;
        private DecodedJWT decodedJwt;
        private boolean valid = true;

        public JWTData() {
        }

        public String getToken() {
            return token;
        }

        public boolean isExpired() {
            if (isValid()) {
                Date expirationDate = decodedJwt.getExpiresAt();
                if (expirationDate != null) {
                    return expirationDate.before(new Date());
                }

                return false;
            }

            return true;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public boolean isValid() {
            return decodedJwt != null && valid;
        }
    }

    private JWTData toJwtData(String token) {
        JWTData jwtData = new JWTData();
        jwtData.token = token;
        try {
            jwtData.decodedJwt = JWT.decode(token);
        } catch (JWTDecodeException e) {
            jwtData.valid = false;
        }

        return jwtData;
    }
}

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

package com.emnify.sdk.client.model;

import java.util.Date;

import com.emnify.sdk.client.auth.AuthenticationTokens;
import com.emnify.sdk.client.util.TestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuthenticationTokensTest {

    @Test
    public void test_createToken_Default() {
        Date expirationDate = DateUtils.addDays(new Date(), 20);
        String authToken = TestUtils.getJWTToken(expirationDate);

        Date refreshExpirationDate = DateUtils.addDays(new Date(), 15);
        String refreshToken = TestUtils.getJWTToken(refreshExpirationDate);

        AuthenticationTokens tokens = new AuthenticationTokens(authToken, refreshToken);

        assertNotNull(tokens);

        assertNotNull(tokens.getAuthToken());
        assertFalse(tokens.getAuthToken().isExpired());
        assertTrue(tokens.getRefreshToken().isValid());

        assertNotNull(tokens.getRefreshToken());
        assertFalse(tokens.getRefreshToken().isExpired());
        assertTrue(tokens.getRefreshToken().isValid());
    }

    @Test
    public void test_createToken_Invalid() {
        Date expirationDate = DateUtils.addDays(new Date(), 20);
        String authToken = TestUtils.getJWTToken(expirationDate);

        AuthenticationTokens tokens = new AuthenticationTokens(authToken, "");

        assertNotNull(tokens);

        assertNotNull(tokens.getAuthToken());
        assertFalse(tokens.getAuthToken().isExpired());
        assertTrue(tokens.getAuthToken().isValid());

        assertNotNull(tokens.getRefreshToken());
        assertTrue(tokens.getRefreshToken().isExpired());
        assertFalse(tokens.getRefreshToken().isValid());
    }

    @Test
    public void test_createToken_NoExpirationLimit() {
        AuthenticationTokens tokens = new AuthenticationTokens(TestUtils.getJWTToken(null), "");

        assertNotNull(tokens);

        assertNotNull(tokens.getAuthToken());
        assertFalse(tokens.getAuthToken().isExpired());
        assertTrue(tokens.getAuthToken().isValid());

        assertNotNull(tokens.getRefreshToken());
        assertTrue(tokens.getRefreshToken().isExpired());
        assertFalse(tokens.getRefreshToken().isValid());
    }
}

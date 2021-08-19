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

package com.emnify.sdk.client.config;

import com.emnify.sdk.client.auth.Authentication;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ConfigurationTest {

    @Test
    public void test_createAuthentication_UsernamePassword_Default() {
        Authentication authentication = Configuration.createAuthentication("username", "password");

        assertNotNull(authentication);
        assertTrue(authentication.isExpired());
        assertFalse(authentication.isRefreshTokenValid());
    }

    @Test
    public void test_createAuthentication_UsernamePassword_EmptyFields() {
        Authentication authentication = Configuration.createAuthentication("", "");

        assertNull(authentication);
    }

    @Test
    public void test_createAuthentication_BytApplicationToken_Default() {
        Authentication authentication = Configuration.createAuthentication("token");

        assertNotNull(authentication);
        assertTrue(authentication.isExpired());
        assertTrue(authentication.isRefreshTokenValid());
    }

    @Test
    public void test_createAuthentication_BytApplicationToken_EmptyFields() {
        Authentication authentication = Configuration.createAuthentication("");

        assertNull(authentication);
    }
}

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

import com.emnify.sdk.client.auth.ApplicationTokenAuthenticationTest;
import com.emnify.sdk.client.auth.BasicAuthenticationTest;
import com.emnify.sdk.client.config.ConfigurationTest;
import com.emnify.sdk.client.model.AuthenticationTokensTest;
import com.emnify.sdk.client.model.QuotaActionOnExhaustionTest;
import com.emnify.sdk.client.model.QuotaStatusTypeTest;
import com.emnify.sdk.client.retrier.AuthenticationRetrierTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ConfigurationTest.class,

        // auth package
        AuthenticationTokensTest.class,
        ApplicationTokenAuthenticationTest.class,
        BasicAuthenticationTest.class,

        // retrier
        AuthenticationRetrierTest.class,

        // client tests
        EndpointClientTest.class,
        EMnifyClientTest.class,

        // models
        QuotaActionOnExhaustionTest.class,
        QuotaStatusTypeTest.class,

})
public class TestSuite {
}

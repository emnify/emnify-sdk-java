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

import org.junit.Test;
import static com.emnify.sdk.client.model.QuotaStatus.ACTIVE;
import static com.emnify.sdk.client.model.QuotaStatus.EXHAUSTED;
import static com.emnify.sdk.client.model.QuotaStatus.EXPIRED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class QuotaStatusTest {

    @Test
    public void test_ToClientModel_UnknownType() {
        QuotaStatus result =
                QuotaStatus.toClientModel(new com.emnify.sdk.model.QuotaStatus());

        assertNull(result);
    }

    @Test
    public void test_ToClientModel_ActiveType() {
        QuotaStatus result =
                QuotaStatus.toClientModel(new com.emnify.sdk.model.QuotaStatus().id(com.emnify.sdk.model.QuotaStatus.IdEnum.NUMBER_1));

        assertNotNull(result);
        assertEquals(ACTIVE, result);
    }

    @Test
    public void test_ToClientModel_ExhaustedType() {
        QuotaStatus result =
                QuotaStatus.toClientModel(new com.emnify.sdk.model.QuotaStatus().id(com.emnify.sdk.model.QuotaStatus.IdEnum.NUMBER_2));

        assertNotNull(result);
        assertEquals(EXHAUSTED, result);
    }

    @Test
    public void test_ToClientModel_ExpiredType() {
        QuotaStatus result = QuotaStatus.toClientModel(new com.emnify.sdk.model.QuotaStatus().id(com.emnify.sdk.model.QuotaStatus.IdEnum.NUMBER_3));

        assertNotNull(result);
        assertEquals(EXPIRED, result);
    }
}

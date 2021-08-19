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

import com.emnify.sdk.model.ActionOnExhaustion;
import org.junit.Test;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.ExhaustionActionType.Block;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.ExhaustionActionType.Throttle;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.QuotaPeakThroughput.MEDIUM;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.QuotaPeakThroughput.SLOW;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.QuotaPeakThroughput.VERY_FAST;
import static com.emnify.sdk.model.ActionOnExhaustion.IdEnum.NUMBER_1;
import static com.emnify.sdk.model.ActionOnExhaustion.IdEnum.NUMBER_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class QuotaActionOnExhaustionTest {

    @Test
    public void test_Throttle() {
        QuotaActionOnExhaustion result =
                QuotaActionOnExhaustion.throttle(VERY_FAST);

        assertNotNull(result);
        assertEquals(Throttle, result.getExhaustionActionType());
        assertNotNull(result.getPeakThroughput());
        assertEquals(VERY_FAST, result.getPeakThroughput());
    }

    @Test
    public void test_Blocked() {
        QuotaActionOnExhaustion result =
                QuotaActionOnExhaustion.blocked();

        assertNotNull(result);
        assertEquals(Block, result.getExhaustionActionType());
        assertNull(result.getPeakThroughput());
    }

    @Test
    public void test_ToClientModel_Throttle() {
        QuotaActionOnExhaustion result =
                QuotaActionOnExhaustion.toClientModel(new ActionOnExhaustion().id(NUMBER_2), SLOW.getMbPerSec());

        assertNotNull(result);
        assertEquals(Throttle, result.getExhaustionActionType());
        assertEquals(SLOW, result.getPeakThroughput());
    }

    @Test
    public void test_ToClientModel_Throttle_UnknownPeakThroughput() {
        QuotaActionOnExhaustion result =
                QuotaActionOnExhaustion.toClientModel(new ActionOnExhaustion().id(NUMBER_2), 20200);

        assertNotNull(result);
        assertEquals(Throttle, result.getExhaustionActionType());
        assertNull(result.getPeakThroughput());
    }

    @Test
    public void test_ToClientModel_Blocked() {
        QuotaActionOnExhaustion result =
                QuotaActionOnExhaustion.toClientModel(new ActionOnExhaustion().id(NUMBER_1), SLOW.getMbPerSec());

        assertNotNull(result);
        assertEquals(Block, result.getExhaustionActionType());
        assertNull(result.getPeakThroughput());
    }

    @Test
    public void test_ToClientModel_Unknown() {
        QuotaActionOnExhaustion result =
                QuotaActionOnExhaustion.toClientModel(new ActionOnExhaustion(), 0);

        assertNull(result);
    }

    @Test
    public void test_ToApiModel_Throttle() {
        QuotaActionOnExhaustion action = QuotaActionOnExhaustion.throttle(MEDIUM);

        ActionOnExhaustion result = QuotaActionOnExhaustion.toApiModel(action);

        assertNotNull(result);
        assertEquals(NUMBER_2, result.getId());
        assertEquals(Integer.valueOf(MEDIUM.getMbPerSec()), result.getPeakThroughput());
    }

    @Test
    public void test_ToApiModel_Blocked() {
        QuotaActionOnExhaustion action = QuotaActionOnExhaustion.blocked();

        ActionOnExhaustion result = QuotaActionOnExhaustion.toApiModel(action);

        assertNotNull(result);
        assertEquals(NUMBER_1, result.getId());
        assertNull(result.getPeakThroughput());
    }
}

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

import com.emnify.sdk.model.QuotaStatus;

public enum QuotaStatusType {
    ACTIVE, EXHAUSTED, EXPIRED;

    public static QuotaStatusType toClientModel(QuotaStatus status) {
        if (status == null) {
            return null;
        }

        QuotaStatus.IdEnum id = status.getId();
        if (id == QuotaStatus.IdEnum.NUMBER_1) {
            return ACTIVE;
        } else if (id == QuotaStatus.IdEnum.NUMBER_2) {
            return EXHAUSTED;
        } else if (id == QuotaStatus.IdEnum.NUMBER_3) {
            return EXPIRED;
        }

        return null;
    }
}

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

import java.util.Objects;

import com.emnify.sdk.model.ActionOnExhaustion;

public class QuotaActionOnExhaustion {

    private final ExhaustionActionType exhaustionActionType;
    private final QuotaPeakThroughput peakThroughput;

    private QuotaActionOnExhaustion(ExhaustionActionType exhaustionActionType, QuotaPeakThroughput peakThroughput) {
        this.exhaustionActionType = exhaustionActionType;
        this.peakThroughput = peakThroughput;
    }

    public enum QuotaPeakThroughput {
        SLOW(64000), MEDIUM(128000), FAST(256000), VERY_FAST(384000);

        private final int mbPerSec;


        QuotaPeakThroughput(int mbPerSec) {
            this.mbPerSec = mbPerSec;
        }

        public static QuotaPeakThroughput getByValue(Integer value) {
            if (value != null) {
                for (QuotaPeakThroughput peak : QuotaPeakThroughput.values()) {
                    if (peak.getMbPerSec() == value) {
                        return peak;
                    }
                }
            }
            return null;
        }

        public int getMbPerSec() {
            return mbPerSec;
        }

    }

    public enum ExhaustionActionType {
        Throttle, Block
    }

    public static QuotaActionOnExhaustion throttle(QuotaPeakThroughput peakThroughput) {
        return new QuotaActionOnExhaustion(ExhaustionActionType.Throttle, peakThroughput);
    }

    public static QuotaActionOnExhaustion blocked() {
        return new QuotaActionOnExhaustion(ExhaustionActionType.Block, null);
    }

    public static QuotaActionOnExhaustion toClientModel(ActionOnExhaustion action, Integer peakThroughput) {
        if (action == null) {
            return null;
        }

        QuotaActionOnExhaustion actionOnExhaustion = null;
        if (action.getId() == ActionOnExhaustion.IdEnum.NUMBER_2) {
            QuotaPeakThroughput quotaPeakThroughput = QuotaPeakThroughput.getByValue(peakThroughput);
            actionOnExhaustion = QuotaActionOnExhaustion.throttle(quotaPeakThroughput);
        } else if (action.getId() == ActionOnExhaustion.IdEnum.NUMBER_1) {
            actionOnExhaustion = QuotaActionOnExhaustion.blocked();
        }

        return actionOnExhaustion;
    }

    public static ActionOnExhaustion toApiModel(QuotaActionOnExhaustion action) {
        if (action == null) {
            return null;
        }

        ActionOnExhaustion actionOnExhaustion = null;
        if (action.getExhaustionActionType() == ExhaustionActionType.Throttle) {
            actionOnExhaustion = new ActionOnExhaustion();
            actionOnExhaustion.setId(ActionOnExhaustion.IdEnum.NUMBER_2);
            actionOnExhaustion.setPeakThroughput(action.getPeakThroughput().getMbPerSec());
        } else if (action.getExhaustionActionType() == ExhaustionActionType.Block) {
            actionOnExhaustion = new ActionOnExhaustion();
            actionOnExhaustion.setId(ActionOnExhaustion.IdEnum.NUMBER_1);
        }

        return actionOnExhaustion;
    }

    public ExhaustionActionType getExhaustionActionType() {
        return exhaustionActionType;
    }

    public QuotaPeakThroughput getPeakThroughput() {
        return peakThroughput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuotaActionOnExhaustion actionOnExhaustion = (QuotaActionOnExhaustion) o;
        return Objects.equals(this.exhaustionActionType, actionOnExhaustion.exhaustionActionType) &&
                Objects.equals(this.peakThroughput, actionOnExhaustion.peakThroughput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exhaustionActionType, peakThroughput);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuotaActionOnExhaustion{");
        sb.append("exhaustionActionType=").append(exhaustionActionType);
        sb.append(", peakThroughput=").append(peakThroughput);
        sb.append('}');
        return sb.toString();
    }
}

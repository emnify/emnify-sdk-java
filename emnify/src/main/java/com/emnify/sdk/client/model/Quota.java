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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.emnify.sdk.model.EndpointQuota;
import com.emnify.sdk.model.EndpointQuota1;
import com.emnify.sdk.model.QuotaStatus;
import org.apache.commons.lang3.BooleanUtils;

public class Quota {

    public static final DateTimeFormatter QUOTA_DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

    private QuotaStatusType status;
    private Float volume;
    private LocalDateTime expiryDate;
    private boolean autoRefill;
    private Float thresholdPercentage;
    private Float thresholdVolume;
    private QuotaActionOnExhaustion actionOnExhaustion;
    private LocalDateTime lastStatusChangeDate;
    private Float lastVolumeAdded;

    public Quota() {
    }

    private Quota(QuotaStatusType status) {
        this.status = status;
    }

    public static Quota toClientModel(EndpointQuota source) {
        Quota quota = new Quota(QuotaStatusType.toClientModel(source.getStatus()));
        quota.setVolume(source.getVolume() != null ? source.getVolume() : 0f);
        quota.setAutoRefill(Optional.ofNullable(source.getAutoRefill()).orElse(0) > 0);
        quota.setActionOnExhaustion(QuotaActionOnExhaustion.toClientModel(source.getActionOnExhaustion(), source.getPeakThroughput()));

        if (source.getExpiryDate() != null) {
            LocalDateTime expireDate = LocalDateTime.parse(source.getExpiryDate(), QUOTA_DATE_TIME_PATTERN);
            quota.setExpiryDate(expireDate);
        }

        if (source.getLastStatusChangeDate() != null) {
            LocalDateTime lastStatusChangeDate = LocalDateTime.parse(source.getLastStatusChangeDate(), QUOTA_DATE_TIME_PATTERN);
            quota.setLastStatusChangeDate(lastStatusChangeDate);
        }

        if (source.getThresholdVolume() != null) {
            quota.setThresholdVolume(source.getThresholdVolume());
        }
        if (source.getThresholdPercentage() != null) {
            quota.setThresholdPercentage(source.getThresholdPercentage());
        }
        if (source.getLastVolumeAdded() != null) {
            quota.setLastVolumeAdded(source.getLastVolumeAdded());
        }
        if (source.getThresholdVolume() != null) {
            quota.setThresholdVolume(source.getThresholdVolume());
        }
        if (source.getThresholdPercentage() != null) {
            quota.setThresholdPercentage(source.getThresholdPercentage());
        }
        return quota;
    }

    public static EndpointQuota1 toApiModel(Quota quota) {
        EndpointQuota1 quotaForSaving = new EndpointQuota1()
                .status(new QuotaStatus().id(QuotaStatus.IdEnum.NUMBER_1))
                .autoRefill(BooleanUtils.toInteger(quota.isAutoRefill()))
                .actionOnExhaustion(QuotaActionOnExhaustion.toApiModel(quota.getActionOnExhaustion()))
                .volume(quota.getVolume())
                .thresholdVolume(quota.getThresholdVolume())
                .thresholdPercentage(quota.getThresholdPercentage());

        if (quota.getExpiryDate() != null) {
            quotaForSaving.expiryDate(quota.getExpiryDate().format(QUOTA_DATE_TIME_PATTERN));
        }
        return quotaForSaving;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setAutoRefill(boolean isEnabled) {
        this.autoRefill = isEnabled;
    }

    public void setThresholdPercentage(float thresholdPercentage) {
        this.thresholdPercentage = thresholdPercentage;
    }

    public void setActionOnExhaustion(QuotaActionOnExhaustion actionOnExhaustion) {
        this.actionOnExhaustion = actionOnExhaustion;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public Float getVolume() {
        return volume;
    }

    public QuotaStatusType getStatus() {
        return status;
    }

    public Float getThresholdPercentage() {
        return thresholdPercentage;
    }

    public boolean isAutoRefill() {
        return autoRefill;
    }

    public QuotaActionOnExhaustion getActionOnExhaustion() {
        return actionOnExhaustion;
    }

    public Float getThresholdVolume() {
        return thresholdVolume;
    }

    public void setThresholdVolume(float thresholdVolume) {
        this.thresholdVolume = thresholdVolume;
    }

    public LocalDateTime getLastStatusChangeDate() {
        return lastStatusChangeDate;
    }

    private void setLastStatusChangeDate(LocalDateTime lastStatusChangeDate) {
        this.lastStatusChangeDate = lastStatusChangeDate;
    }

    private void setLastVolumeAdded(Float lastVolumeAdded) {
        this.lastVolumeAdded = lastVolumeAdded;
    }

    public Float getLastVolumeAdded() {
        return lastVolumeAdded;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Quota{");
        sb.append("status=").append(status);
        sb.append(", volume=").append(volume);
        sb.append(", expiryDate=").append(expiryDate);
        sb.append(", autoRefill=").append(autoRefill);
        sb.append(", lastStatusChangeDate=").append(lastStatusChangeDate);
        sb.append(", lastVolumeAdded=").append(lastVolumeAdded);
        sb.append(", thresholdPercentage=").append(thresholdPercentage);
        sb.append(", thresholdVolume=").append(thresholdVolume);
        sb.append(", actionOnExhaustion=").append(actionOnExhaustion);
        sb.append('}');
        return sb.toString();
    }
}

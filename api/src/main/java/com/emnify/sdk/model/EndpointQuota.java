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


package com.emnify.sdk.model;

import java.util.Objects;
import java.util.Arrays;
import com.emnify.sdk.model.ActionOnExhaustion;
import com.emnify.sdk.model.QuotaStatus;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * EndpointQuota
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class EndpointQuota {
  public static final String SERIALIZED_NAME_LAST_VOLUME_ADDED = "last_volume_added";
  @SerializedName(SERIALIZED_NAME_LAST_VOLUME_ADDED)
  private Float lastVolumeAdded;

  public static final String SERIALIZED_NAME_LAST_STATUS_CHANGE_DATE = "last_status_change_date";
  @SerializedName(SERIALIZED_NAME_LAST_STATUS_CHANGE_DATE)
  private String lastStatusChangeDate;

  public static final String SERIALIZED_NAME_AUTO_REFILL = "auto_refill";
  @SerializedName(SERIALIZED_NAME_AUTO_REFILL)
  private Integer autoRefill;

  public static final String SERIALIZED_NAME_THRESHOLD_VOLUME = "threshold_volume";
  @SerializedName(SERIALIZED_NAME_THRESHOLD_VOLUME)
  private Float thresholdVolume;

  public static final String SERIALIZED_NAME_THRESHOLD_PERCENTAGE = "threshold_percentage";
  @SerializedName(SERIALIZED_NAME_THRESHOLD_PERCENTAGE)
  private Float thresholdPercentage;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private QuotaStatus status;

  public static final String SERIALIZED_NAME_VOLUME = "volume";
  @SerializedName(SERIALIZED_NAME_VOLUME)
  private Float volume;

  public static final String SERIALIZED_NAME_EXPIRY_DATE = "expiry_date";
  @SerializedName(SERIALIZED_NAME_EXPIRY_DATE)
  private String expiryDate;

  public static final String SERIALIZED_NAME_ACTION_ON_EXHAUSTION = "action_on_exhaustion";
  @SerializedName(SERIALIZED_NAME_ACTION_ON_EXHAUSTION)
  private ActionOnExhaustion actionOnExhaustion;

  public static final String SERIALIZED_NAME_PEAK_THROUGHPUT = "peak_throughput";
  @SerializedName(SERIALIZED_NAME_PEAK_THROUGHPUT)
  private Integer peakThroughput;


  public EndpointQuota lastVolumeAdded(Float lastVolumeAdded) {
    
    this.lastVolumeAdded = lastVolumeAdded;
    return this;
  }

   /**
   * Get lastVolumeAdded
   * @return lastVolumeAdded
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Float getLastVolumeAdded() {
    return lastVolumeAdded;
  }


  public void setLastVolumeAdded(Float lastVolumeAdded) {
    this.lastVolumeAdded = lastVolumeAdded;
  }


  public EndpointQuota lastStatusChangeDate(String lastStatusChangeDate) {
    
    this.lastStatusChangeDate = lastStatusChangeDate;
    return this;
  }

   /**
   * Get lastStatusChangeDate
   * @return lastStatusChangeDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getLastStatusChangeDate() {
    return lastStatusChangeDate;
  }


  public void setLastStatusChangeDate(String lastStatusChangeDate) {
    this.lastStatusChangeDate = lastStatusChangeDate;
  }


  public EndpointQuota autoRefill(Integer autoRefill) {
    
    this.autoRefill = autoRefill;
    return this;
  }

   /**
   * Get autoRefill
   * @return autoRefill
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getAutoRefill() {
    return autoRefill;
  }


  public void setAutoRefill(Integer autoRefill) {
    this.autoRefill = autoRefill;
  }


  public EndpointQuota thresholdVolume(Float thresholdVolume) {
    
    this.thresholdVolume = thresholdVolume;
    return this;
  }

   /**
   * Get thresholdVolume
   * @return thresholdVolume
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Float getThresholdVolume() {
    return thresholdVolume;
  }


  public void setThresholdVolume(Float thresholdVolume) {
    this.thresholdVolume = thresholdVolume;
  }


  public EndpointQuota thresholdPercentage(Float thresholdPercentage) {
    
    this.thresholdPercentage = thresholdPercentage;
    return this;
  }

   /**
   * Get thresholdPercentage
   * @return thresholdPercentage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Float getThresholdPercentage() {
    return thresholdPercentage;
  }


  public void setThresholdPercentage(Float thresholdPercentage) {
    this.thresholdPercentage = thresholdPercentage;
  }


  public EndpointQuota status(QuotaStatus status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")

  public QuotaStatus getStatus() {
    return status;
  }


  public void setStatus(QuotaStatus status) {
    this.status = status;
  }


  public EndpointQuota volume(Float volume) {
    
    this.volume = volume;
    return this;
  }

   /**
   * Get volume
   * @return volume
  **/
  @ApiModelProperty(required = true, value = "")

  public Float getVolume() {
    return volume;
  }


  public void setVolume(Float volume) {
    this.volume = volume;
  }


  public EndpointQuota expiryDate(String expiryDate) {
    
    this.expiryDate = expiryDate;
    return this;
  }

   /**
   * Get expiryDate
   * @return expiryDate
  **/
  @ApiModelProperty(required = true, value = "")

  public String getExpiryDate() {
    return expiryDate;
  }


  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }


  public EndpointQuota actionOnExhaustion(ActionOnExhaustion actionOnExhaustion) {
    
    this.actionOnExhaustion = actionOnExhaustion;
    return this;
  }

   /**
   * Get actionOnExhaustion
   * @return actionOnExhaustion
  **/
  @ApiModelProperty(required = true, value = "")

  public ActionOnExhaustion getActionOnExhaustion() {
    return actionOnExhaustion;
  }


  public void setActionOnExhaustion(ActionOnExhaustion actionOnExhaustion) {
    this.actionOnExhaustion = actionOnExhaustion;
  }


  public EndpointQuota peakThroughput(Integer peakThroughput) {
    
    this.peakThroughput = peakThroughput;
    return this;
  }

   /**
   * Get peakThroughput
   * @return peakThroughput
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getPeakThroughput() {
    return peakThroughput;
  }


  public void setPeakThroughput(Integer peakThroughput) {
    this.peakThroughput = peakThroughput;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EndpointQuota endpointQuota = (EndpointQuota) o;
    return Objects.equals(this.lastVolumeAdded, endpointQuota.lastVolumeAdded) &&
        Objects.equals(this.lastStatusChangeDate, endpointQuota.lastStatusChangeDate) &&
        Objects.equals(this.autoRefill, endpointQuota.autoRefill) &&
        Objects.equals(this.thresholdVolume, endpointQuota.thresholdVolume) &&
        Objects.equals(this.thresholdPercentage, endpointQuota.thresholdPercentage) &&
        Objects.equals(this.status, endpointQuota.status) &&
        Objects.equals(this.volume, endpointQuota.volume) &&
        Objects.equals(this.expiryDate, endpointQuota.expiryDate) &&
        Objects.equals(this.actionOnExhaustion, endpointQuota.actionOnExhaustion) &&
        Objects.equals(this.peakThroughput, endpointQuota.peakThroughput);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastVolumeAdded, lastStatusChangeDate, autoRefill, thresholdVolume, thresholdPercentage, status, volume, expiryDate, actionOnExhaustion, peakThroughput);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EndpointQuota {\n");
    sb.append("    lastVolumeAdded: ").append(toIndentedString(lastVolumeAdded)).append("\n");
    sb.append("    lastStatusChangeDate: ").append(toIndentedString(lastStatusChangeDate)).append("\n");
    sb.append("    autoRefill: ").append(toIndentedString(autoRefill)).append("\n");
    sb.append("    thresholdVolume: ").append(toIndentedString(thresholdVolume)).append("\n");
    sb.append("    thresholdPercentage: ").append(toIndentedString(thresholdPercentage)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    volume: ").append(toIndentedString(volume)).append("\n");
    sb.append("    expiryDate: ").append(toIndentedString(expiryDate)).append("\n");
    sb.append("    actionOnExhaustion: ").append(toIndentedString(actionOnExhaustion)).append("\n");
    sb.append("    peakThroughput: ").append(toIndentedString(peakThroughput)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}


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
import com.emnify.sdk.model.EndpointStatus;
import com.emnify.sdk.model.IpAddressSpace;
import com.emnify.sdk.model.ServiceProfile;
import com.emnify.sdk.model.Sim;
import com.emnify.sdk.model.TariffProfile;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.OffsetDateTime;

/**
 * Endpoint
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class Endpoint {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_TAGS = "tags";
  @SerializedName(SERIALIZED_NAME_TAGS)
  private String tags;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private EndpointStatus status;

  public static final String SERIALIZED_NAME_SERVICE_PROFILE = "service_profile";
  @SerializedName(SERIALIZED_NAME_SERVICE_PROFILE)
  private ServiceProfile serviceProfile;

  public static final String SERIALIZED_NAME_TARIFF_PROFILE = "tariff_profile";
  @SerializedName(SERIALIZED_NAME_TARIFF_PROFILE)
  private TariffProfile tariffProfile;

  public static final String SERIALIZED_NAME_IP_ADDRESS = "ip_address";
  @SerializedName(SERIALIZED_NAME_IP_ADDRESS)
  private String ipAddress;

  public static final String SERIALIZED_NAME_IP_ADDRESS_SPACE = "ip_address_space";
  @SerializedName(SERIALIZED_NAME_IP_ADDRESS_SPACE)
  private IpAddressSpace ipAddressSpace;

  public static final String SERIALIZED_NAME_SIM = "sim";
  @SerializedName(SERIALIZED_NAME_SIM)
  private Sim sim;

  public static final String SERIALIZED_NAME_IMEI = "imei";
  @SerializedName(SERIALIZED_NAME_IMEI)
  private String imei;

  public static final String SERIALIZED_NAME_IMEI_LOCK = "imei_lock";
  @SerializedName(SERIALIZED_NAME_IMEI_LOCK)
  private Boolean imeiLock;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  private OffsetDateTime created;

  public static final String SERIALIZED_NAME_LAST_UPDATED = "last_updated";
  @SerializedName(SERIALIZED_NAME_LAST_UPDATED)
  private OffsetDateTime lastUpdated;


  public Endpoint id(Integer id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public Endpoint name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public Endpoint tags(String tags) {
    
    this.tags = tags;
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getTags() {
    return tags;
  }


  public void setTags(String tags) {
    this.tags = tags;
  }


  public Endpoint status(EndpointStatus status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")

  public EndpointStatus getStatus() {
    return status;
  }


  public void setStatus(EndpointStatus status) {
    this.status = status;
  }


  public Endpoint serviceProfile(ServiceProfile serviceProfile) {
    
    this.serviceProfile = serviceProfile;
    return this;
  }

   /**
   * Get serviceProfile
   * @return serviceProfile
  **/
  @ApiModelProperty(required = true, value = "")

  public ServiceProfile getServiceProfile() {
    return serviceProfile;
  }


  public void setServiceProfile(ServiceProfile serviceProfile) {
    this.serviceProfile = serviceProfile;
  }


  public Endpoint tariffProfile(TariffProfile tariffProfile) {
    
    this.tariffProfile = tariffProfile;
    return this;
  }

   /**
   * Get tariffProfile
   * @return tariffProfile
  **/
  @ApiModelProperty(required = true, value = "")

  public TariffProfile getTariffProfile() {
    return tariffProfile;
  }


  public void setTariffProfile(TariffProfile tariffProfile) {
    this.tariffProfile = tariffProfile;
  }


  public Endpoint ipAddress(String ipAddress) {
    
    this.ipAddress = ipAddress;
    return this;
  }

   /**
   * Get ipAddress
   * @return ipAddress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getIpAddress() {
    return ipAddress;
  }


  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }


  public Endpoint ipAddressSpace(IpAddressSpace ipAddressSpace) {
    
    this.ipAddressSpace = ipAddressSpace;
    return this;
  }

   /**
   * Get ipAddressSpace
   * @return ipAddressSpace
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public IpAddressSpace getIpAddressSpace() {
    return ipAddressSpace;
  }


  public void setIpAddressSpace(IpAddressSpace ipAddressSpace) {
    this.ipAddressSpace = ipAddressSpace;
  }


  public Endpoint sim(Sim sim) {
    
    this.sim = sim;
    return this;
  }

   /**
   * Get sim
   * @return sim
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Sim getSim() {
    return sim;
  }


  public void setSim(Sim sim) {
    this.sim = sim;
  }


  public Endpoint imei(String imei) {
    
    this.imei = imei;
    return this;
  }

   /**
   * Get imei
   * @return imei
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getImei() {
    return imei;
  }


  public void setImei(String imei) {
    this.imei = imei;
  }


  public Endpoint imeiLock(Boolean imeiLock) {
    
    this.imeiLock = imeiLock;
    return this;
  }

   /**
   * Get imeiLock
   * @return imeiLock
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getImeiLock() {
    return imeiLock;
  }


  public void setImeiLock(Boolean imeiLock) {
    this.imeiLock = imeiLock;
  }


  public Endpoint created(OffsetDateTime created) {
    
    this.created = created;
    return this;
  }

   /**
   * Get created
   * @return created
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OffsetDateTime getCreated() {
    return created;
  }


  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }


  public Endpoint lastUpdated(OffsetDateTime lastUpdated) {
    
    this.lastUpdated = lastUpdated;
    return this;
  }

   /**
   * Get lastUpdated
   * @return lastUpdated
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OffsetDateTime getLastUpdated() {
    return lastUpdated;
  }


  public void setLastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Endpoint endpoint = (Endpoint) o;
    return Objects.equals(this.id, endpoint.id) &&
        Objects.equals(this.name, endpoint.name) &&
        Objects.equals(this.tags, endpoint.tags) &&
        Objects.equals(this.status, endpoint.status) &&
        Objects.equals(this.serviceProfile, endpoint.serviceProfile) &&
        Objects.equals(this.tariffProfile, endpoint.tariffProfile) &&
        Objects.equals(this.ipAddress, endpoint.ipAddress) &&
        Objects.equals(this.ipAddressSpace, endpoint.ipAddressSpace) &&
        Objects.equals(this.sim, endpoint.sim) &&
        Objects.equals(this.imei, endpoint.imei) &&
        Objects.equals(this.imeiLock, endpoint.imeiLock) &&
        Objects.equals(this.created, endpoint.created) &&
        Objects.equals(this.lastUpdated, endpoint.lastUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, tags, status, serviceProfile, tariffProfile, ipAddress, ipAddressSpace, sim, imei, imeiLock, created, lastUpdated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Endpoint {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    serviceProfile: ").append(toIndentedString(serviceProfile)).append("\n");
    sb.append("    tariffProfile: ").append(toIndentedString(tariffProfile)).append("\n");
    sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
    sb.append("    ipAddressSpace: ").append(toIndentedString(ipAddressSpace)).append("\n");
    sb.append("    sim: ").append(toIndentedString(sim)).append("\n");
    sb.append("    imei: ").append(toIndentedString(imei)).append("\n");
    sb.append("    imeiLock: ").append(toIndentedString(imeiLock)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
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


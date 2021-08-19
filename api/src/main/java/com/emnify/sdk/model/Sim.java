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
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Sim
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class Sim {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;

  public static final String SERIALIZED_NAME_ICCID = "iccid";
  @SerializedName(SERIALIZED_NAME_ICCID)
  private String iccid;

  public static final String SERIALIZED_NAME_MSISDN = "msisdn";
  @SerializedName(SERIALIZED_NAME_MSISDN)
  private String msisdn;

  public static final String SERIALIZED_NAME_IMSI = "imsi";
  @SerializedName(SERIALIZED_NAME_IMSI)
  private String imsi;


  public Sim id(Integer id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public Sim iccid(String iccid) {
    
    this.iccid = iccid;
    return this;
  }

   /**
   * Get iccid
   * @return iccid
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getIccid() {
    return iccid;
  }


  public void setIccid(String iccid) {
    this.iccid = iccid;
  }


  public Sim msisdn(String msisdn) {
    
    this.msisdn = msisdn;
    return this;
  }

   /**
   * Get msisdn
   * @return msisdn
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getMsisdn() {
    return msisdn;
  }


  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }


  public Sim imsi(String imsi) {
    
    this.imsi = imsi;
    return this;
  }

   /**
   * Get imsi
   * @return imsi
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getImsi() {
    return imsi;
  }


  public void setImsi(String imsi) {
    this.imsi = imsi;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sim sim = (Sim) o;
    return Objects.equals(this.id, sim.id) &&
        Objects.equals(this.iccid, sim.iccid) &&
        Objects.equals(this.msisdn, sim.msisdn) &&
        Objects.equals(this.imsi, sim.imsi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, iccid, msisdn, imsi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sim {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    iccid: ").append(toIndentedString(iccid)).append("\n");
    sb.append("    msisdn: ").append(toIndentedString(msisdn)).append("\n");
    sb.append("    imsi: ").append(toIndentedString(imsi)).append("\n");
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


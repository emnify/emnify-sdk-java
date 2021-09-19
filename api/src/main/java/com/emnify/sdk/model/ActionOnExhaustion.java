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

import java.io.IOException;
import java.util.Objects;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModelProperty;

/**
 * ActionOnExhaustion
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ActionOnExhaustion {
  /**
   * Gets or Sets id
   */
  @JsonAdapter(IdEnum.Adapter.class)
  public enum IdEnum {
    NUMBER_1(1),
    
    NUMBER_2(2);

    private Integer value;

    IdEnum(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static IdEnum fromValue(Integer value) {
      for (IdEnum b : IdEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<IdEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final IdEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public IdEnum read(final JsonReader jsonReader) throws IOException {
        Integer value =  jsonReader.nextInt();
        return IdEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private IdEnum id;

  /**
   * Gets or Sets description
   */
  @JsonAdapter(DescriptionEnum.Adapter.class)
  public enum DescriptionEnum {
    THROTTLE("Throttle"),
    
    BLOCK("Block");

    private String value;

    DescriptionEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static DescriptionEnum fromValue(String value) {
      for (DescriptionEnum b : DescriptionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<DescriptionEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final DescriptionEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public DescriptionEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return DescriptionEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private DescriptionEnum description;

  public static final String SERIALIZED_NAME_PEAK_THROUGHPUT = "peak_throughput";
  @SerializedName(SERIALIZED_NAME_PEAK_THROUGHPUT)
  private Integer peakThroughput;


  public ActionOnExhaustion id(IdEnum id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")

  public IdEnum getId() {
    return id;
  }


  public void setId(IdEnum id) {
    this.id = id;
  }


  public ActionOnExhaustion description(DescriptionEnum description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DescriptionEnum getDescription() {
    return description;
  }


  public void setDescription(DescriptionEnum description) {
    this.description = description;
  }


  public ActionOnExhaustion peakThroughput(Integer peakThroughput) {
    
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
    ActionOnExhaustion actionOnExhaustion = (ActionOnExhaustion) o;
    return Objects.equals(this.id, actionOnExhaustion.id) &&
        Objects.equals(this.description, actionOnExhaustion.description) &&
        Objects.equals(this.peakThroughput, actionOnExhaustion.peakThroughput);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, peakThroughput);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionOnExhaustion {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

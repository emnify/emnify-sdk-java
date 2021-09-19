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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@EqualsAndHashCode
public class Endpoint {

  private Integer id;
  private String name;
  private String tags;
  private EndpointStatus status;
  private ServiceProfile serviceProfile;
  private TariffProfile tariffProfile;
  private String ipAddress;
  private IpAddressSpace ipAddressSpace;
  private Sim sim;
  private String imei;
  private Boolean imeiLock;
  private LocalDateTime created;
  private LocalDateTime lastUpdated;

  public static Endpoint toClientModel(com.emnify.sdk.model.Endpoint source) {
    Endpoint endpoint = new Endpoint();
    endpoint.setId(source.getId());
    endpoint.setName(source.getName());
    endpoint.setTags(source.getTags());
    endpoint.setStatus(EndpointStatus.toClientModel(source.getStatus()));
    endpoint.setTariffProfile(TariffProfile.toClientModel(source.getTariffProfile()));
    endpoint.setIpAddress(source.getIpAddress());
    endpoint.setIpAddressSpace(IpAddressSpace.toClientModel(source.getIpAddressSpace()));
    endpoint.setSim(Sim.toClientModel(source.getSim()));
    endpoint.setImei(source.getImei());

    if (source.getCreated() != null) {
      endpoint.setCreated(source.getCreated().toLocalDateTime());
    }

    if (source.getLastUpdated() != null) {
      endpoint.setLastUpdated(source.getLastUpdated().toLocalDateTime());
    }
    return endpoint;
  }
}

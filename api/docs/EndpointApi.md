# EndpointApi

All URIs are relative to *https://cdn.emnify.net*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createEndpoint**](EndpointApi.md#createEndpoint) | **POST** /api/v1/endpoint | Create Endpoint
[**deleteEndpointDataQuotaById**](EndpointApi.md#deleteEndpointDataQuotaById) | **DELETE** /api/v1/endpoint/{endpoint_id}/quota/data | Remove Data Quota
[**endpointQuotaDataByEndpointIdGet**](EndpointApi.md#endpointQuotaDataByEndpointIdGet) | **GET** /api/v1/endpoint/{endpoint_id}/quota/data | Retrieve Data Quota details
[**endpointQuotaDataByEndpointIdPost**](EndpointApi.md#endpointQuotaDataByEndpointIdPost) | **POST** /api/v1/endpoint/{endpoint_id}/quota/data | Set Data Quota
[**getEndpoints**](EndpointApi.md#getEndpoints) | **GET** /api/v1/endpoint | List Endpoints


<a name="createEndpoint"></a>
# **createEndpoint**
> createEndpoint(endpoint)

Create Endpoint

If a &#x60;sim&#x60; object is provided, the SIM with the contained ID will be assigned to the endpoint. The &#x60;activate&#x60; property defaults to &#x60;true&#x60; and can be omitted unless the SIM should not be activated with this API call.  The following fields may be provided: * &#x60;name&#x60; (String required) * &#x60;service_profile&#x60; (Object required) * &#x60;tariff_profile&#x60; (Object required) * &#x60;status&#x60; (Object required) - &#x60;0&#x60; &#x3D; __Enabled__, &#x60;1&#x60; &#x3D; __Disabled__! * &#x60;tags&#x60; (String optional) * &#x60;imei&#x60; (String optional) * &#x60;imei_lock&#x60; (Boolean optional) * &#x60;sim&#x60; (Object optional)   - &#x60;id&#x60; (number required) SIM ID to be assigned to this endpoint   - &#x60;activate&#x60; (Boolean, optional, default:true) * &#x60;ip_address&#x60; (String optional) * &#x60;ip_address_space&#x60; (Object, optional if IP address is omitted, mandatory when IP address is set) 

### Example
```java
// Import classes:
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.Configuration;
import com.emnify.sdk.auth.*;
import com.emnify.sdk.models.*;
import com.emnify.sdk.api.EndpointApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://cdn.emnify.net");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    EndpointApi apiInstance = new EndpointApi(defaultClient);
    Endpoint endpoint = new Endpoint(); // Endpoint | 
    try {
      apiInstance.createEndpoint(endpoint);
    } catch (ApiException e) {
      System.err.println("Exception when calling EndpointApi#createEndpoint");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **endpoint** | [**Endpoint**](Endpoint.md)|  |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Resource successfully created |  * Location - Relative location of the newly-created resource. <br>  |
**404** | Unexpected error in API call. See HTTP response body for details. |  -  |
**409** | Unexpected error in API call. See HTTP response body for details. |  -  |
**422** | Unprocessable Entity. See HTTP response body for details. |  -  |

<a name="deleteEndpointDataQuotaById"></a>
# **deleteEndpointDataQuotaById**
> deleteEndpointDataQuotaById(endpointId)

Remove Data Quota

Will delete the data quota for the endpoint, if any is set. Note that if &#x60;apply_data_quota&#x60; is still set in the service profile, the endpoint will get blocked from data service. 

### Example
```java
// Import classes:
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.Configuration;
import com.emnify.sdk.auth.*;
import com.emnify.sdk.models.*;
import com.emnify.sdk.api.EndpointApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://cdn.emnify.net");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    EndpointApi apiInstance = new EndpointApi(defaultClient);
    Integer endpointId = 56; // Integer | The numeric ID of an Endpoint
    try {
      apiInstance.deleteEndpointDataQuotaById(endpointId);
    } catch (ApiException e) {
      System.err.println("Exception when calling EndpointApi#deleteEndpointDataQuotaById");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **endpointId** | **Integer**| The numeric ID of an Endpoint |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Success |  -  |
**404** | Can be one of the following reasons: - No Data Quota configured - Endpoint not found - Endpoint not part of this organisation  |  -  |

<a name="endpointQuotaDataByEndpointIdGet"></a>
# **endpointQuotaDataByEndpointIdGet**
> EndpointQuota endpointQuotaDataByEndpointIdGet(endpointId)

Retrieve Data Quota details

Returns details about the assigned Data Quota for an endpoint. * &#x60;status&#x60;: this indicates the current status of the quota and may contain the following values:     - &#x60;ACTIVE&#x60;: the endpoint can currently connect and has quota left     - &#x60;EXHAUSTED&#x60;: the endpoint has exceeded the quota volume, if it still can use data service depends on the action chosen to be performed on exhaustion     - &#x60;EXPIRED&#x60;: the quota has expired; the endpoint is denied from using data services (until new quota is added) * &#x60;volume&#x60;: returns the volume left on this quota in MB * &#x60;expiry_date&#x60;: timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (regardless if the quota volume was used up or not) * &#x60;peak_throughput&#x60;: The maximum bandwidth in octets per second after the endpoint has been throttled. * &#x60;action_on_exhaustion&#x60;: returns the behaviour defined to be applied when quota volume is used up (exhausted).     - &#x60;Throttle&#x60;: bandwidth will be throttle to the defined peak throughput until quota expires     - &#x60;Block&#x60;: data service will be instantly blocked once volume used up, regardless if the expiry date is already reached or not * &#x60;auto_refill&#x60;: 0 (false) / 1 (true), refill the quota with the last added volume on a daily basis 

### Example
```java
// Import classes:
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.Configuration;
import com.emnify.sdk.auth.*;
import com.emnify.sdk.models.*;
import com.emnify.sdk.api.EndpointApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://cdn.emnify.net");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    EndpointApi apiInstance = new EndpointApi(defaultClient);
    Integer endpointId = 56; // Integer | The numeric ID of an Endpoint
    try {
      EndpointQuota result = apiInstance.endpointQuotaDataByEndpointIdGet(endpointId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EndpointApi#endpointQuotaDataByEndpointIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **endpointId** | **Integer**| The numeric ID of an Endpoint |

### Return type

[**EndpointQuota**](EndpointQuota.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |

<a name="endpointQuotaDataByEndpointIdPost"></a>
# **endpointQuotaDataByEndpointIdPost**
> endpointQuotaDataByEndpointIdPost(endpointId, endpointQuota)

Set Data Quota

At any time, a new data quota can be set for an endpoint. At an initial state when no data quota is set, the endpoint will be denied from using data services. To top-up the data volume you need to retrieve the currently remaining volume, increase it by the top-up volume and set it as the new quota volume.  The following parameters can be configured: * &#x60;status&#x60; - The status of the quota (mandatory):   - 1: &#x60;ACTIVE&#x60;   - 2: &#x60;EXHAUSTED&#x60;   - 3: &#x60;EXPIRED&#x60; * &#x60;volume&#x60;: The volume left on this quota in MB * &#x60;expiry_date&#x60;: Timestamp when this quota will expire and the endpoint will definitely be denied from using further data services (mandatory) * &#x60;auto_refill&#x60;: Wether the quota shall be refilled on a daily basis (defaults to disabled):   - 0: &#x60;disabled&#x60;   - 1: &#x60;enabled&#x60; * &#x60;threshold_percentage&#x60;: The percentage of remaining quota at which the system should generate a &#x60;threshold reached&#x60; event * &#x60;action_on_exhaustion&#x60;: The behaviour of the system after the quota is exhausted:   - id: ID of the action on quota exhaustion (mandatory)     - 1: &#x60;Block&#x60;     - 2: &#x60;Throttle&#x60;   - peak_throughput: The maximum bandwidth in octets per second after the endpoint has been throttled. (mandatory)   Allowed values are 64000, 128000, 256000, 384000. (will not take any effect on &#x60;action_on_exhaustion&#x60; \&quot;Block\&quot;)  #### Events The system generates a \&quot;Quota Used Up\&quot; Event in case the data quota is completely depleted. The endpoint will be blocked from further consumption of data. The quota object will be included in the details of the event. Example events can be found in the Events of an Endpoint section.  #### Notes  The endpoint can instantly use data services after the API call to this entrypoint is successfully made. Any timestamp with a future date can be set, this allows to create data packages (e.g. for 1 hour, 24 hour, 7 days or any other timeframe) as required. 

### Example
```java
// Import classes:
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.Configuration;
import com.emnify.sdk.auth.*;
import com.emnify.sdk.models.*;
import com.emnify.sdk.api.EndpointApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://cdn.emnify.net");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    EndpointApi apiInstance = new EndpointApi(defaultClient);
    Integer endpointId = 56; // Integer | The numeric ID of an Endpoint
    EndpointQuota endpointQuota = new EndpointQuota(); // EndpointQuota | 
    try {
      apiInstance.endpointQuotaDataByEndpointIdPost(endpointId, endpointQuota);
    } catch (ApiException e) {
      System.err.println("Exception when calling EndpointApi#endpointQuotaDataByEndpointIdPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **endpointId** | **Integer**| The numeric ID of an Endpoint |
 **endpointQuota** | [**EndpointQuota**](EndpointQuota.md)|  |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Resource Created |  -  |
**422** | Unprocessable Entity. |  -  |

<a name="getEndpoints"></a>
# **getEndpoints**
> List&lt;Endpoint&gt; getEndpoints(q, sort, page, perPage)

List Endpoints

Returns the list of endpoints, filtered, sorted and paged according to query parameters.

### Example
```java
// Import classes:
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.Configuration;
import com.emnify.sdk.auth.*;
import com.emnify.sdk.models.*;
import com.emnify.sdk.api.EndpointApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://cdn.emnify.net");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    EndpointApi apiInstance = new EndpointApi(defaultClient);
    String q = ""; // String | Filter parameter in `<filter>:<value>` format. Expects comma separated list of filtering criteria out of the following fields:  * `status` * `service_profile` * `tariff_profile` * `last_updated` * `created` * `name` * `tags` * `ip_address` * `imei` 
    String sort = ""; // String | Sort properties in a comma separated list from the following fields:  * `status` * `service_profile` * `tariff_profile` * `last_updated` * `created` * `name` * `tags` * `id` * `ip_address` * `imei` 
    Integer page = 56; // Integer | Current page number
    Integer perPage = 56; // Integer | Paging parameters defining the number of items per page
    try {
      List<Endpoint> result = apiInstance.getEndpoints(q, sort, page, perPage);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EndpointApi#getEndpoints");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **q** | **String**| Filter parameter in &#x60;&lt;filter&gt;:&lt;value&gt;&#x60; format. Expects comma separated list of filtering criteria out of the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  | [optional]
 **sort** | **String**| Sort properties in a comma separated list from the following fields:  * &#x60;status&#x60; * &#x60;service_profile&#x60; * &#x60;tariff_profile&#x60; * &#x60;last_updated&#x60; * &#x60;created&#x60; * &#x60;name&#x60; * &#x60;tags&#x60; * &#x60;id&#x60; * &#x60;ip_address&#x60; * &#x60;imei&#x60;  | [optional]
 **page** | **Integer**| Current page number | [optional]
 **perPage** | **Integer**| Paging parameters defining the number of items per page | [optional]

### Return type

[**List&lt;Endpoint&gt;**](Endpoint.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully returned a list of Endpoints. |  * Link - URLs for navigational links used to retrieve first, previous, next and last page in a dataset <br>  * X-Count-Per-Page - The requested &#x60;per_page&#x60; parameter <br>  * X-Current-Page - The current page number within the current data set <br>  * X-Total-Count - The total element count in the current data set <br>  * X-Total-Pages - The total page count in the current data set <br>  * X-Filter - If filtering is applied using &#x60;?q&#x3D;field:criteria&#x60; URL query parameters, the filter criteria is returned here <br>  * X-Sort - If sorting is applied using &#x60;?sort&#x3D;sort_criteria&#x60; URL query parameters, the sort criteria is returned here <br>  |

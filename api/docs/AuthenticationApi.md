# AuthenticationApi

All URIs are relative to *https://cdn.emnify.net*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authenticate**](AuthenticationApi.md#authenticate) | **POST** /api/v1/authenticate | Retrieve Authentication Token


<a name="authenticate"></a>
# **authenticate**
> AuthenticationResponse authenticate(authentication)

Retrieve Authentication Token

This entrypoint returns a JWT &#x60;auth_token&#x60; for authenticating further requests to the API. 

### Example
```java
// Import classes:
import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.Configuration;
import com.emnify.sdk.models.*;
import com.emnify.sdk.api.AuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://cdn.emnify.net");

    AuthenticationApi apiInstance = new AuthenticationApi(defaultClient);
    Authentication authentication = new Authentication(); // Authentication | Authentication using Application Tokens or user/password combination
    try {
      AuthenticationResponse result = apiInstance.authenticate(authentication);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AuthenticationApi#authenticate");
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
 **authentication** | [**Authentication**](Authentication.md)| Authentication using Application Tokens or user/password combination |

### Return type

[**AuthenticationResponse**](AuthenticationResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful Authentication Request |  -  |
**401** | Unauthorized |  -  |
**404** | Unexpected error in API call. See HTTP response body for details. |  -  |

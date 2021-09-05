# emnify-sdk-java

[![Maven Central](https://img.shields.io/maven-central/v/com.emnify.sdk/emnify)](https://search.maven.org/search?q=g:com.emnify.sddk%20AND%20a:emnify)

## Documentation

The EMnify System Documentation can be found [here](https://cdn.emnify.net/api/doc/index.html).

The OpenAPI Documentation can be found [here](https://cdn.emnify.net/api/doc/swagger.html).

The Java library documentation can be found [here](https://emnify.github.io/emnify-sdk-java/).

## Requirements

### Supported Java Versions

This library supports the following Java implementations:

* OpenJDK 8
* OpenJDK 11
* OracleJDK 8
* OracleJDK 11

### TLS Requirements

Supported TLS versions can be found [here](https://www.ssllabs.com/ssltest/analyze.html?d=cdn.emnify.net&latest).

## Getting Started

### Installation

The recommended method for installing the SDK is with a build automation tool, like Maven or Gradle. 
You can add the EMnify dependency to your existing project, specifying the latest version.

#### Maven

Use the following dependency in your project to grab via Maven:

```xml
    <dependency>
        <groupId>com.emnify.sdk</groupId>
        <artifactId>emnify</artifactId>
        <version>{version}</version>
    </dependency>
```

#### Gradle
```gradle
    implementation group: "com.emnify.sdk", name: "emnify", version: "{version}"
```

Regardless of the package manager you are using, remember to specify the latest version of the EMnify Java SDK.

If you want to compile it yourself, here's how:

```shell
    git clone git@github.com:EMnify/emnify-sdk-java
    cd emnify-sdk-java
    mvn install       # Requires maven, download from https://maven.apache.org/download.html
```
If you want to build your own .jar, execute the following from within the cloned directory:
```shell
  mvn package
```

### Environment Variables

You can use Environment Variables for storing configuration like application token or base URL rather than hardcoding them into your application.

| Name                              | Description                                                                                                      |
|-----------------------------------|------------------------------------------------------------------------------------------------------------------|
| `EMNIFY_BASE_PATH`                  | the base URL to form a request. Default value:  `https://cdn.emnify.net`                                         |
| `EMNIFY_APPLICATION_TOKEN`          | variable for authentication via application token. [more](https://cdn.emnify.net/api/doc/application-token.html) |
| `EMNIFY_USERNAME` and `EMNIFY_PASSWORD` | variable for authentication via username and password. [more](https://cdn.emnify.net/api/doc/basic-auth.html)      |

### Examples

#### Initialize the Client using environment variables

```java
    EMnify.authenticate();
```

#### Initialize the Client via passing authorization parameter

```java
    String username = "user@domain.com";
    String password = "2fd4e1c67a2d28...";
    
    EMnify.authenticate(username, password)
```

#### Initialize the Client via application token

```java
    String applicationToken = "KAOp24TuMgjO2FpZmZ3ZFjSqpk7ea_mY8...";
    
    EMnify.authenticate(applicationToken)
```

#### Retrieve list of endpoints

```java
    EMnify client = EMnify.authenticate();
    List<Endpoint> endpoints = client.getEndpointClient().listEndpoints();
```

#### Update Endpoint Quota data

```java
    Quota quota = new Quota();
    quota.setVolume(actualVolume + quotaTopUpVolume);
    quota.setExpiryDate(expirationDate);
    quota.setThresholdPercentage(threholdPercentage);
    quota.setActionOnExhaustion(QuotaActionOnExhaustion.throttle(QuotaActionOnExhaustion.QuotaPeakThroughput.SLOW));
    
    endpointClient.saveQuota(endpointId, quota);
```

## Getting Help

If you need help installing or using the library, please [file a support ticket](https://support.emnify.com/hc/en-us/requests/new).

If you've instead found a bug in the library or would like new features added, go ahead and open issues or pull requests against this repo.

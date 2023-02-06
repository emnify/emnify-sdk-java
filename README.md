# emnify Java SDK

![Build Status](https://github.com/EMnify/emnify-sdk-java/actions/workflows/build.yaml/badge.svg)
[![Maven Central](https://img.shields.io/maven-central/v/com.emnify.sdk/emnify)](https://search.maven.org/search?q=g:com.emnify.sdk%20AND%20a:emnify)
[![codecov](https://codecov.io/gh/EMnify/emnify-sdk-java/branch/main/graph/badge.svg?token=PKQ5909911)](https://codecov.io/gh/EMnify/emnify-sdk-java)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=EMnify_emnify-sdk-java&metric=bugs)](https://sonarcloud.io/dashboard?id=EMnify_emnify-sdk-java)

Supply your swarm of IoT devices with cloud connectivity by [emnify](https://emnify.com).
Automate your routines with this SDK for Java.  

## Installation

The recommended method for installing the SDK is with a build automation tool, like Maven or Gradle. 
You can add the emnify dependency to your existing project, specifying the latest version.

> Regardless of the package manager you are using, you need to specify the latest version of the emnify Java SDK.
> The following examples use `{version}` where this should be specified.

### Maven

Use the following dependency in your project to grab via [Maven](https://maven.apache.org/download.cgi):

```xml
    <dependency>
        <groupId>com.emnify.sdk</groupId>
        <artifactId>emnify</artifactId>
        <version>{version}</version>
    </dependency>
```

### Gradle

Use the following to add the emnify dependency to your project via [Gradle](https://gradle.org/install/):

```gradle
    implementation group: "com.emnify.sdk", name: "emnify", version: "{version}"
```

### Compile the SDK yourself

If you want to compile it yourself, here's how:

```shell
    git clone git@github.com:EMnify/emnify-sdk-java
    cd emnify-sdk-java
    mvn install # Requires Maven
```
If you want to build your own `.jar`, execute the following from within the cloned directory:

```shell
  mvn package
```

## Supported Java versions 

This library supports the following Java implementations:

- [OpenJDK 8](https://openjdk.org/projects/jdk8/)
- [OpenJDK 11](https://openjdk.org/projects/jdk/11/)
- [OracleJDK 8](https://www.oracle.com/java/technologies/downloads/#java8)
- [OracleJDK 11](https://www.oracle.com/java/technologies/downloads/#java11)

## TLS requirements

Refer to the [SSL Report table](https://www.ssllabs.com/ssltest/analyze.html?d=cdn.emnify.net&latest) for the supported Transport Layer Security (TLS) versions.

## Environment variables

You can use environment variables for storing configuration settings like an application token or a base URL instead of hardcoding them in your application.

| Name               | Description                                                              |
|--------------------|--------------------------------------------------------------------------|
| `EMNIFY_BASE_PATH` | Base URL to form a request. Default value:  `https://cdn.emnify.net` |
| `EMNIFY_APPLICATION_TOKEN`  | Variable for authenticating via an [application token](https://cdn.emnify.net/api/doc/application-token.html). |
| `EMNIFY_USERNAME` and `EMNIFY_PASSWORD` | Variables for authenticating via [username and password](https://cdn.emnify.net/api/doc/basic-auth.html). |

## Documentation

Read more about working with the Java SDK and the underlying concepts in the [_emnify product documentation_](https://docs.emnify.com/sdks/java).

## Contributing

If you've found a bug or would like to add new features, [open an issue](https://github.com/emnify/emnify-sdk-java/issues/new) or [create a pull request](https://github.com/emnify/emnify-sdk-java/pulls) to this Github repository.

Please note that this project is governed by [emnify's Code of Conduct](https://github.com/emnify/.github/blob/main/CODE_OF_CONDUCT.md). By participating, you agree to abide by its terms.

## Get support

If you need help using our services, please [file a support ticket](https://support.emnify.com/hc/en-us/requests/new).

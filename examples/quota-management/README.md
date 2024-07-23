# Managing the device's quota data with the emnify Java SDK

This example shows how to manage the device's quota data.

Here's a high-level overview of what happens in the script:

1. Authorizes the client using configured credentials
1. Fetches a list of devices and selects the first device
1. Checks the current state of the quota
1. Configures a new quota with the following settings:
    - 10 MB of volume
    - Expires in 7 days
    - 15% threshold 
      - If the remaining volume goes below this percentage of the applied quota, the system will generate a [Quota threshold reached event](https://docs.emnify.com/services/events/event-types#quota-threshold-reached)
    - Exhaustion action is "Throttle" with a peak throughput of 64000 octets per second
1. Fetches the quota settings to check if the configuration has been saved successfully

## Running the example

### Install the SDK

<!-- TODO: Replace relative path with link to docs.emnify.com when live -->
First, follow the [SDK installation instructions]( ../../README.md#Installation).

### Configure authorization credentials

Next, you need to configure your authorization credentials. 
There are two ways to do this:

#### Application token

<!-- TODO: Replace relative path with link to docs.emnify.com when live -->
To [authenticate via application token](https://cdn.emnify.net/api/doc/application-token.html), you need to add `EMNIFY_APPLICATION_TOKEN` as an [environment variable](../../README.md#environment-variables).


### Execute the example

After configuring one of the authentication methods described in the previous section, you can execute the example.

Here's how to run it in the terminal using [Maven](https://maven.apache.org/download.cgi):

```shell
  mvn clean install exec:java

  # from root folder
  mvn -f examples/quota-management/ clean install exec:java
```

## Overview of the examples

### Initialize the client using environment variables

```java
    EMnify.authenticate();
```

### Update endpoint quota data

```java
      Quota quotaForSave = new Quota();
      quotaForSave.setVolume(actualVolume + quotaTopUpVolume);
      quotaForSave.setExpiryDate(expirationDate);
      quotaForSave.setThresholdPercentage(threholdPercentage);
      quotaForSave.setActionOnExhaustion(QuotaActionOnExhaustion.throttle(QuotaActionOnExhaustion.QuotaPeakThroughput.SLOW));
      
      endpointClient.saveQuota(endpointId, quotaForSave);
```

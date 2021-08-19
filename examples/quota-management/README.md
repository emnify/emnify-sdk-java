# Quota Management example

This example shows how to manage endpoint's quota data.

1. Script authorizes the client using configured credentials.
2. The list of endpoints is fetched then a first endpoint is taken to work with.
3. Then check the current state of quota
4. Script configures a quota with the following data:
    - set new volume of 10MB
    - the quota expires in 7 days
    - a threshold is set to 15%. If the remaining volume goes below this percentage of the applied quota, the system
      will generate a "threshold reached" event.
    - the action of exhaustion is configured to Throttle with a peak throughput of 64000 octets per second
5. Script fetches the quota data to check if configuration has been saved successfully

## Running the example

Firstly, follow the [SDK installation]( ../../README.md#Installation ) instruction.

Then you need to configure you authorization credentials. There are two options:

#### Application Token
If you want to authenticate via application token, you need to add system environment `EMNIFY_APPLICATION_TOKEN`. <a href="https://cdn.emnify.net/api/doc/application-token.html" target="_blank">Read more about Application Token</a>.

#### User Authentication 

If you want to authenticate via user authentication, you need to add environments `EMNIFY_USERNAME` and `EMNIFY_PASSWORD`. <a href="https://cdn.emnify.net/api/doc/basic-auth.html" target="_blank">Read more about User Authentication</a>.

Configure one of the authentication described above and execute example.

OR just run via terminal:

```shell
  mvn clean install exec:java

  # from root folder
  mvn -f examples/quota-management/ clean install exec:java
```

## Examples

### Initialize the Client using environment variables

```java
    EMnify.authenticate();
```

### Update Endpoint Quota data

```java
      Quota quotaForSave = new Quota();
      quotaForSave.setVolume(actualVolume + quotaTopUpVolume);
      quotaForSave.setExpiryDate(expirationDate);
      quotaForSave.setThresholdPercentage(threholdPercentage);
      quotaForSave.setActionOnExhaustion(QuotaActionOnExhaustion.throttle(QuotaActionOnExhaustion.QuotaPeakThroughput.SLOW));
      
      endpointClient.saveQuota(endpointId, quotaForSave);
```

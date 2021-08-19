package com.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import com.emnify.sdk.client.exception.ClientException;
import com.emnify.sdk.client.EMnify;
import com.emnify.sdk.client.EndpointClient;
import com.emnify.sdk.client.model.QueryParams;
import com.emnify.sdk.client.model.Quota;
import com.emnify.sdk.client.model.QuotaActionOnExhaustion;
import com.emnify.sdk.model.Endpoint;

/**
 * This example shows how to manage endpoint's quota data.
 * <p>
 * 1. Script authorizes the client using configured credentials.
 * 2. The list of endpoints is fetched and a first endpoint is taken to work with.
 * 3. Then check the current state of quota
 * 4. Script configures a quota with the following data:
 * - set new volume of 10MB
 * - the quota expires in 7 days
 * - a threshold is set to 15%. If the remaining volume goes below this percentage of the applied quota, the system will generate a "threshold reached" event.
 * - the action of exhaustion is configured to Throttle with a peak throughput of 64000 octets per second
 * 5. Script fetches the quota data to check if configuration has been saved successfully
 */
public class QuotaManagementExample {

    private static final float QUOTA_TOP_UP_VOLUME = 10;
    private static final float THRESHOLD_PERCENTAGE = 15;
    private static final boolean AUTO_REFILL = true;

    private static final int EXPIRATION_SLOT = 7;
    private static final TemporalUnit EXPIRATION_UNIT = ChronoUnit.DAYS;

    public static void main(String[] args) throws ClientException {
        EMnify emnifyClient = EMnify.authenticate();
        EndpointClient endpointClient = emnifyClient.buildEndpointClient();

        System.out.println("Fetching endpoints...");
        List<Endpoint> endpoints = endpointClient.listEndpoints(QueryParams.builder().build());
        if (endpoints.size() <= 0) {
            throw new RuntimeException("No endpoint found");
        }

        int endpointId = endpoints.get(0).getId();

        System.out.println("Fetching quota of endpoint " + endpointId);
        float quotaVolume = 0;
        try {
            Quota quota = endpointClient.getQuota(endpointId);
            quotaVolume = quota.getVolume();
            System.out.println("Quota volume is set to " + quota.getVolume() + " and expires " + quota.getExpiryDate());
        } catch (ClientException e) {
            System.out.println("Fetching quota exception: " + e.getMessage());
        }

        System.out.println("Setting new quota data for endpoint " + endpointId);

        Quota quotaForSave = new Quota();
        // increase actual quota data volume
        quotaForSave.setVolume(quotaVolume + QUOTA_TOP_UP_VOLUME);
        quotaForSave.setAutoRefill(AUTO_REFILL);
        quotaForSave.setExpiryDate(LocalDateTime.now().plus(EXPIRATION_SLOT, EXPIRATION_UNIT));
        quotaForSave.setThresholdPercentage(THRESHOLD_PERCENTAGE);
        quotaForSave.setActionOnExhaustion(QuotaActionOnExhaustion.throttle(QuotaActionOnExhaustion.QuotaPeakThroughput.SLOW));

        endpointClient.saveQuota(endpointId, quotaForSave);

        Quota savedQuota = endpointClient.getQuota(endpointId);
        System.out.println("Saved Quota: " + savedQuota.toString());
    }
}

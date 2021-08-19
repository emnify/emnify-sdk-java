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

package com.emnify.sdk.client;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.emnify.sdk.ApiClient;
import com.emnify.sdk.ApiException;
import com.emnify.sdk.api.EndpointApi;
import com.emnify.sdk.client.auth.Authentication;
import com.emnify.sdk.client.config.Configuration;
import com.emnify.sdk.client.exception.ClientException;
import com.emnify.sdk.client.model.QueryParams;
import com.emnify.sdk.client.model.Quota;
import com.emnify.sdk.client.model.QuotaActionOnExhaustion;
import com.emnify.sdk.client.model.QuotaStatusType;
import com.emnify.sdk.client.retrier.AuthenticationRetrier;
import com.emnify.sdk.client.util.TestUtils;
import com.emnify.sdk.model.ActionOnExhaustion;
import com.emnify.sdk.model.Endpoint;
import com.emnify.sdk.model.EndpointQuota;
import com.emnify.sdk.model.EndpointQuota1;
import com.emnify.sdk.model.EndpointStatus;
import com.emnify.sdk.model.QuotaStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static com.emnify.sdk.client.ApiExceptionUtils.UNAUTHORIZED_CODE;
import static com.emnify.sdk.client.model.Quota.QUOTA_DATE_TIME_PATTERN;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.QuotaPeakThroughput.FAST;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.QuotaPeakThroughput.SLOW;
import static com.emnify.sdk.client.model.QuotaActionOnExhaustion.throttle;
import static com.emnify.sdk.client.util.TestUtils.expectException;
import static com.emnify.sdk.model.ActionOnExhaustion.IdEnum.NUMBER_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EndpointClient.class})
@PowerMockIgnore("javax.net.ssl.*")
public class EndpointClientTest {

    private static final int ENDPOINT_ID = TestUtils.generateId();

    @Mock
    private ApiClient apiClientMock;

    @Mock
    private Authentication authenticationMock;

    @Mock
    private EndpointApi endpointApiMock;

    private AuthenticationRetrier authenticationRetrier;

    @Before
    public void setUp() throws Exception {
        whenNew(EndpointApi.class).withArguments(apiClientMock).thenReturn(endpointApiMock);
        authenticationRetrier = Configuration.createAuthenticationRetrier(authenticationMock, apiClientMock);
    }

    @Test
    public void test_ListEndpoints_Default() throws Exception {
        QueryParams params = QueryParams.builder().build();
        List<Endpoint> expectedEndpoints = collectEndpoints();

        when(endpointApiMock.getEndpoints(null, null, null, null))
                .thenReturn(expectedEndpoints);

        // execute
        List<Endpoint> endpoints = new EndpointClient(apiClientMock, authenticationRetrier).listEndpoints(params);

        // verify
        verify(endpointApiMock).getEndpoints(null, null, null, null);

        assertNotNull(endpoints);
        assertEquals(expectedEndpoints, endpoints);
    }


    @Test
    public void test_ListEndpoints_Paginated() throws Exception {
        int PAGE = 5;
        int PAGE_SIZE = 100;
        String FILTER = "name:John";
        String SORT = "status";

        QueryParams params = QueryParams.builder()
                .setFilter(FILTER)
                .setSort(SORT)
                .setPage(PAGE)
                .setPerPage(PAGE_SIZE)
                .build();

        List<Endpoint> expectedEndpoints = collectEndpoints();

        when(endpointApiMock.getEndpoints(FILTER, SORT, PAGE, PAGE_SIZE))
                .thenReturn(expectedEndpoints);

        // execute
        List<Endpoint> endpoints = new EndpointClient(apiClientMock, authenticationRetrier).listEndpoints(params);

        // verify
        verify(endpointApiMock).getEndpoints(FILTER, SORT, PAGE, PAGE_SIZE);

        assertNotNull(endpoints);
        assertEquals(expectedEndpoints, endpoints);
    }

    @Test
    public void test_ListEndpoints_Unauthorized_Exception() throws Exception {
        int PAGE = 5;
        int PAGE_SIZE = 100;
        String FILTER = "name:John";
        String SORT = "status";

        QueryParams params = QueryParams.builder()
                .setFilter(FILTER)
                .setSort(SORT)
                .setPage(PAGE)
                .setPerPage(PAGE_SIZE)
                .build();

        List<Endpoint> expectedEndpoints = collectEndpoints();

        when(endpointApiMock.getEndpoints(FILTER, SORT, PAGE, PAGE_SIZE))
                .thenThrow(new ApiException(UNAUTHORIZED_CODE, Collections.emptyMap(), ""))
                .thenReturn(expectedEndpoints);

        // execute
        List<Endpoint> endpoints = new EndpointClient(apiClientMock, authenticationRetrier).listEndpoints(params);

        // verify
        verify(endpointApiMock, times(2))
                .getEndpoints(FILTER, SORT, PAGE, PAGE_SIZE);

        assertNotNull(endpoints);
        assertEquals(expectedEndpoints, endpoints);
    }

    @Test
    public void test_ListEndpoints_EmptyParamsException() throws Exception {
        String expectedCauseMsg = "Test exception message";

        when(endpointApiMock.getEndpoints(any(), any(), any(), any()))
                .thenThrow(new ApiException(expectedCauseMsg));

        // execute
        expectException(() -> new EndpointClient(apiClientMock, authenticationRetrier).listEndpoints(),
                ClientException.class,
                String.format("Error getting list of endpoints, cause: %s %s", expectedCauseMsg, ""));

        // verify
        verify(endpointApiMock).getEndpoints(any(), any(), any(), any());
    }

    @Test
    public void test_GetQuota_Default() throws Exception {
        final Float VOLUME = 100f;
        final int PEEK_THROUGHPUT = SLOW.getMbPerSec();
        final Float LAST_VOLUME_ADDED = 10f;
        final Float THRESHOLD = 10f;

        LocalDateTime expiryDate = LocalDateTime.now().plusDays(7).truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime lastChangeDate = LocalDateTime.now().minusDays(7).truncatedTo(ChronoUnit.SECONDS);

        EndpointQuota quota = new EndpointQuota()
                .status(new QuotaStatus().id(QuotaStatus.IdEnum.NUMBER_1))
                .volume(VOLUME)
                .autoRefill(1)
                .actionOnExhaustion(new ActionOnExhaustion().id(NUMBER_2).peakThroughput(60000))
                .peakThroughput(PEEK_THROUGHPUT)
                .expiryDate(expiryDate.format(QUOTA_DATE_TIME_PATTERN))
                .lastStatusChangeDate(lastChangeDate.format(QUOTA_DATE_TIME_PATTERN))
                .lastVolumeAdded(LAST_VOLUME_ADDED)
                .thresholdPercentage(THRESHOLD)
                .thresholdVolume(THRESHOLD);

        when(endpointApiMock.endpointQuotaDataByEndpointIdGet(ENDPOINT_ID))
                .thenReturn(quota);

        // execute
        Quota result = new EndpointClient(apiClientMock, authenticationRetrier).getQuota(ENDPOINT_ID);

        // verify
        verify(endpointApiMock).endpointQuotaDataByEndpointIdGet(ENDPOINT_ID);

        // assert
        assertNotNull(result);
        assertEquals(QuotaStatusType.ACTIVE, result.getStatus());
        assertEquals(VOLUME, result.getVolume());
        assertTrue(result.isAutoRefill());
        assertEquals(throttle(SLOW), result.getActionOnExhaustion());
        assertTrue(expiryDate.isEqual(result.getExpiryDate()));
        assertEquals(lastChangeDate, result.getLastStatusChangeDate());
        assertEquals(LAST_VOLUME_ADDED, result.getLastVolumeAdded());
        assertEquals(THRESHOLD, result.getThresholdPercentage());
        assertEquals(THRESHOLD, result.getThresholdVolume());
    }
    @Test
    public void test_GetQuota_Exception_Unauthorized_Exception() throws Exception {
        final Float VOLUME = 100f;
        final int PEEK_THROUGHPUT = SLOW.getMbPerSec();
        final Float LAST_VOLUME_ADDED = 10f;
        final Float THRESHOLD = 10f;

        LocalDateTime expiryDate = LocalDateTime.now().plusDays(7).truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime lastChangeDate = LocalDateTime.now().minusDays(7).truncatedTo(ChronoUnit.SECONDS);

        EndpointQuota quota = new EndpointQuota()
                .status(new QuotaStatus().id(QuotaStatus.IdEnum.NUMBER_1))
                .volume(VOLUME)
                .autoRefill(1)
                .actionOnExhaustion(new ActionOnExhaustion().id(NUMBER_2).peakThroughput(60000))
                .peakThroughput(PEEK_THROUGHPUT)
                .expiryDate(expiryDate.format(QUOTA_DATE_TIME_PATTERN))
                .lastStatusChangeDate(lastChangeDate.format(QUOTA_DATE_TIME_PATTERN))
                .lastVolumeAdded(LAST_VOLUME_ADDED)
                .thresholdPercentage(THRESHOLD)
                .thresholdVolume(THRESHOLD);

        when(endpointApiMock.endpointQuotaDataByEndpointIdGet(ENDPOINT_ID))
                .thenThrow(new ApiException(UNAUTHORIZED_CODE, Collections.emptyMap(), ""))
                .thenReturn(quota);

        // execute
        Quota result = new EndpointClient(apiClientMock, authenticationRetrier).getQuota(ENDPOINT_ID);

        // verify
        verify(endpointApiMock, times(2)).endpointQuotaDataByEndpointIdGet(ENDPOINT_ID);

        // assert
        assertNotNull(result);
        assertEquals(QuotaStatusType.ACTIVE, result.getStatus());
        assertEquals(VOLUME, result.getVolume());
        assertTrue(result.isAutoRefill());
        assertEquals(throttle(SLOW), result.getActionOnExhaustion());
        assertTrue(expiryDate.isEqual(result.getExpiryDate()));
        assertEquals(lastChangeDate, result.getLastStatusChangeDate());
        assertEquals(LAST_VOLUME_ADDED, result.getLastVolumeAdded());
        assertEquals(THRESHOLD, result.getThresholdPercentage());
        assertEquals(THRESHOLD, result.getThresholdVolume());
    }

    @Test
    public void test_GetQuota_Exception() throws ApiException {
        when(endpointApiMock.endpointQuotaDataByEndpointIdGet(ENDPOINT_ID))
                .thenThrow(new ApiException(404, "", Collections.emptyMap(), "Quota not found"));

        // execute
        expectException(() -> new EndpointClient(apiClientMock, authenticationRetrier).getQuota(ENDPOINT_ID),
                ClientException.class,
                String.format("Error getting quota by endpoint ID: %d, cause: %s %s",
                        ENDPOINT_ID, "", "Quota not found"));

        // verify
        verify(endpointApiMock).endpointQuotaDataByEndpointIdGet(ENDPOINT_ID);
    }

    @Test
    public void test_SaveQuota_Default() throws Exception {
        final Float VOLUME = 100f;
        final QuotaActionOnExhaustion.QuotaPeakThroughput PEEK_THROUGHPUT = FAST;
        final Float THRESHOLD = 10f;

        LocalDateTime expiryDate = LocalDateTime.now().plusDays(7).truncatedTo(ChronoUnit.SECONDS);

        Quota quota = new Quota();
        quota.setVolume(VOLUME);
        quota.setAutoRefill(true);
        quota.setActionOnExhaustion(QuotaActionOnExhaustion.throttle(PEEK_THROUGHPUT));
        quota.setExpiryDate(expiryDate);
        quota.setThresholdVolume(THRESHOLD);
        quota.setThresholdPercentage(THRESHOLD);

        ArgumentCaptor<EndpointQuota1> quotaCapture = ArgumentCaptor.forClass(EndpointQuota1.class);

        doNothing().when(endpointApiMock).endpointQuotaDataByEndpointIdPost(eq(ENDPOINT_ID), quotaCapture.capture());

        // execute
        new EndpointClient(apiClientMock, authenticationRetrier).saveQuota(ENDPOINT_ID, quota);

        // verify
        verify(endpointApiMock).endpointQuotaDataByEndpointIdPost(eq(ENDPOINT_ID), quotaCapture.capture());

        // assert
        EndpointQuota1 result = quotaCapture.getValue();
        assertNotNull(result);
        assertEquals(new QuotaStatus().id(QuotaStatus.IdEnum.NUMBER_1), result.getStatus());
        assertEquals(VOLUME, result.getVolume());
        assertEquals(new Integer(1), result.getAutoRefill());
        assertEquals(new ActionOnExhaustion().id(NUMBER_2).peakThroughput(PEEK_THROUGHPUT.getMbPerSec()),
                result.getActionOnExhaustion());
        assertEquals(expiryDate.format(QUOTA_DATE_TIME_PATTERN), result.getExpiryDate());
        assertNull(result.getLastStatusChangeDate());
        assertNull(result.getLastVolumeAdded());
        assertEquals(THRESHOLD, result.getThresholdPercentage());
        assertEquals(THRESHOLD, result.getThresholdVolume());
    }

    @Test
    public void test_SaveQuota_UnauthorizedException() throws Exception {
        final Float VOLUME = 100f;
        final QuotaActionOnExhaustion.QuotaPeakThroughput PEEK_THROUGHPUT = FAST;
        final Float THRESHOLD = 10f;

        LocalDateTime expiryDate = LocalDateTime.now().plusDays(7).truncatedTo(ChronoUnit.SECONDS);

        Quota quota = new Quota();
        quota.setVolume(VOLUME);
        quota.setAutoRefill(true);
        quota.setActionOnExhaustion(QuotaActionOnExhaustion.throttle(PEEK_THROUGHPUT));
        quota.setExpiryDate(expiryDate);
        quota.setThresholdVolume(THRESHOLD);
        quota.setThresholdPercentage(THRESHOLD);

        ArgumentCaptor<EndpointQuota1> quotaCapture = ArgumentCaptor.forClass(EndpointQuota1.class);

        doThrow(new ApiException(UNAUTHORIZED_CODE, Collections.emptyMap(), "")).doNothing()
                .when(endpointApiMock).endpointQuotaDataByEndpointIdPost(eq(ENDPOINT_ID), quotaCapture.capture());

        // execute
        new EndpointClient(apiClientMock, authenticationRetrier).saveQuota(ENDPOINT_ID, quota);

        // verify
        verify(endpointApiMock, times(2)).endpointQuotaDataByEndpointIdPost(eq(ENDPOINT_ID), quotaCapture.capture());

        // assert
        EndpointQuota1 result = quotaCapture.getValue();
        assertNotNull(result);
        assertEquals(new QuotaStatus().id(QuotaStatus.IdEnum.NUMBER_1), result.getStatus());
        assertEquals(VOLUME, result.getVolume());
        assertEquals(new Integer(1), result.getAutoRefill());
        assertEquals(new ActionOnExhaustion().id(NUMBER_2).peakThroughput(PEEK_THROUGHPUT.getMbPerSec()),
                result.getActionOnExhaustion());
        assertEquals(expiryDate.format(QUOTA_DATE_TIME_PATTERN), result.getExpiryDate());
        assertNull(result.getLastStatusChangeDate());
        assertNull(result.getLastVolumeAdded());
        assertEquals(THRESHOLD, result.getThresholdPercentage());
        assertEquals(THRESHOLD, result.getThresholdVolume());
    }

    @Test
    public void test_SaveQuota_Exception() throws ApiException {
        doThrow(new ApiException(422, "", Collections.emptyMap(), "Unprocessable Entity"))
                .when(endpointApiMock).endpointQuotaDataByEndpointIdPost(eq(ENDPOINT_ID), any());

        // execute
        expectException(() -> new EndpointClient(apiClientMock, authenticationRetrier).saveQuota(ENDPOINT_ID, new Quota()),
                ClientException.class,
                String.format("Error saving quota for endpoint ID: %d, cause: %s %s",
                        ENDPOINT_ID, "", "Unprocessable Entity"));

        // verify
        verify(endpointApiMock).endpointQuotaDataByEndpointIdPost(eq(ENDPOINT_ID), any());
    }

    @Test
    public void test_SaveQuota_InvalidQuotaParams() throws ApiException {

        // execute
        expectException(() -> new EndpointClient(apiClientMock, authenticationRetrier).saveQuota(ENDPOINT_ID, null),
                ClientException.class, "Endpoint ID and Quota data are required!");

        // verify
        verify(endpointApiMock, never()).endpointQuotaDataByEndpointIdPost(ENDPOINT_ID, null);
    }

    private List<Endpoint> collectEndpoints() {
        List<Endpoint> endpoints = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            Endpoint endpoint = new Endpoint();
            endpoint.setId(i);
            endpoint.setName("Endpoint " + i);
            endpoint.setStatus(new EndpointStatus().id(1));

            endpoints.add(endpoint);
        }
        return endpoints;
    }
}

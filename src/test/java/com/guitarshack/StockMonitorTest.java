package com.guitarshack;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;

public class StockMonitorTest {

    @Test
    public void quantitySoldExceedsThreshold_alertSent() {
        Alert alert = Mockito.mock(Alert.class);
        HttpService httpService = Mockito.mock(HttpService.class);
        Mockito.when(httpService.fetchResponse(any(), any())).thenReturn(
                "{\"stock\":53, \"id\":811, \"leadTime\":14}",
                "{\"total\":60}"
        );

        StockMonitor stockMonitor = new StockMonitor(alert, httpService);
        stockMonitor.productSold(811, 53);

        Mockito.verify(alert).send(any());
    }
}
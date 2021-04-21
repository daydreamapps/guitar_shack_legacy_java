package com.guitarshack;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class StockMonitorTest {

    @Test
    public void quantitySoldExceedsThreshold_alertSent() {
        Alert alert = mock(Alert.class);
        HttpService httpService = mock(HttpService.class);
        when(httpService.fetchResponse(any(), any())).thenReturn(
                "{\"stock\":53, \"id\":811, \"leadTime\":14}",
                "{\"total\":60}"
        );

        StockMonitor stockMonitor = new StockMonitor(alert, new ReorderThreshold(new SalesHistory(httpService, new QueryBuilder())), new Warehouse(httpService, new QueryBuilder()));
        stockMonitor.productSold(811, 53);

        verify(alert).send(any());
    }
}
package com.guitarshack;

public class StockMonitor {

    private final Alert alert;
    private final HttpService httpService;
    private final ReorderThreshold reorderThreshold;

    public StockMonitor(
            Alert alert,
            HttpService httpService,
            ReorderThreshold reorderThreshold
    ) {
        this.alert = alert;
        this.httpService = httpService;
        this.reorderThreshold = reorderThreshold;
    }

    public void productSold(int productId, int quantity) {
        Product product = new Warehouse(httpService).getProduct(productId);

        if (product.getStock() - quantity <= reorderThreshold.getForProduct(product)) {
            alert.send(product);
        }
    }
}

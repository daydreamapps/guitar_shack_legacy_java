package com.guitarshack;

public class StockMonitor {

    private final Alert alert;
    private final ReorderThreshold reorderThreshold;
    private final Warehouse warehouse;

    public StockMonitor(
            Alert alert,
            ReorderThreshold reorderThreshold,
            Warehouse warehouse
    ) {
        this.alert = alert;
        this.reorderThreshold = reorderThreshold;
        this.warehouse = warehouse;
    }

    public void productSold(int productId, int quantity) {
        Product product = warehouse.getProduct(productId);

        if (product.getStock() - quantity <= reorderThreshold.getForProduct(product)) {
            alert.send(product);
        }
    }
}

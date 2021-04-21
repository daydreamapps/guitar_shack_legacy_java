package com.guitarshack;

public class Program {

    private static StockMonitor monitor;

    static {
        HttpService httpService = new HttpService();
        Alert alert = product -> {
            // We are faking this for now
            System.out.println(
                    "You need to reorder product " + product.getId() +
                            ". Only " + product.getStock() + " remaining in stock");
        };

        monitor = new StockMonitor(
                alert,
                httpService,
                new ReorderThreshold(new SalesHistory(httpService))
        );
    }

    public static void main(String[] args) {
        int productId = Integer.parseInt(args[0]);
        int quantity = Integer.parseInt(args[1]);

        monitor.productSold(productId, quantity);
    }
}

package com.guitarshack;

public class Program {

    private final static String warehouseBaseURL = "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product";
    private final static String SalesHistoryBaseUrl = "https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales";

    private static StockMonitor monitor;

    static {
        Alert alert = product -> {
            // We are faking this for now
            System.out.println(
                    "You need to reorder product " + product.getId() +
                            ". Only " + product.getStock() + " remaining in stock");
        };

        HttpService httpService = new HttpService();
        QueryBuilder queryBuilder = new QueryBuilder();

        monitor = new StockMonitor(
                alert,
                new ReorderThreshold(new SalesHistory(httpService, queryBuilder, SalesHistoryBaseUrl)),
                new Warehouse(httpService, queryBuilder, warehouseBaseURL)
        );
    }

    public static void main(String[] args) {
        int productId = Integer.parseInt(args[0]);
        int quantity = Integer.parseInt(args[1]);

        monitor.productSold(productId, quantity);
    }
}

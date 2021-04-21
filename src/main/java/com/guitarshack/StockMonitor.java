package com.guitarshack;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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
        Product product = getProduct(productId, httpService);

        if (product.getStock() - quantity <= reorderThreshold.getForProduct(product)) {
            alert.send(product);
        }
    }

    private Product getProduct(int productId, HttpService httpService) {
        String baseURL = "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product";
        Map<String, Object> params = new HashMap<>() {{
            put("id", productId);
        }};
        String paramString = "?";

        for (String key : params.keySet()) {
            paramString += key + "=" + params.get(key).toString() + "&";
        }
        String result = httpService.fetchResponse(baseURL, paramString);
        Product product = new Gson().fromJson(result, Product.class);
        return product;
    }
}

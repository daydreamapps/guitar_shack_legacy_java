package com.guitarshack;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private final HttpService httpService;
    private final QueryBuilder queryBuilder;

    public Warehouse(HttpService httpService, QueryBuilder queryBuilder) {
        this.httpService = httpService;
        this.queryBuilder = queryBuilder;
    }

    Product getProduct(int productId) {
        String baseURL = "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product";
        Map<String, Object> params = new HashMap<>() {{
            put("id", productId);
        }};
        String paramString = queryBuilder.getString(params);
        String result = httpService.fetchResponse(baseURL, paramString);

        return new Gson().fromJson(result, Product.class);
    }
}

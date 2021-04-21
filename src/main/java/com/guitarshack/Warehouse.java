package com.guitarshack;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private final HttpService httpService;
    private final QueryBuilder queryBuilder;
    private final String baseURL;

    public Warehouse(HttpService httpService, QueryBuilder queryBuilder, String baseURL) {
        this.httpService = httpService;
        this.queryBuilder = queryBuilder;
        this.baseURL = baseURL;
    }

    Product getProduct(int productId) {
        Map<String, Object> params = new HashMap<>() {{
            put("id", productId);
        }};
        String paramString = queryBuilder.getString(params);
        String result = httpService.fetchResponse(baseURL, paramString);

        return new Gson().fromJson(result, Product.class);
    }
}

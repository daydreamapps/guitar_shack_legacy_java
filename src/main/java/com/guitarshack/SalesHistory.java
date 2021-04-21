package com.guitarshack;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalesHistory {

    private final HttpService httpService;
    private final QueryBuilder queryBuilder;
    private final String baseUrl;

    public SalesHistory(HttpService httpService, QueryBuilder queryBuilder, String baseUrl) {
        this.httpService = httpService;
        this.queryBuilder = queryBuilder;
        this.baseUrl = baseUrl;
    }

    SalesTotal getSalesTotal(Product product, Date startDate, Date endDate) {
        DateFormat format = new SimpleDateFormat("M/d/yyyy");

        Map<String, Object> params1 = new HashMap<>() {{
            put("productId", product.getId());
            put("startDate", format.format(startDate));
            put("endDate", format.format(endDate));
            put("action", "total");
        }};
        String paramString1 = queryBuilder.getString(params1);

        String result1 = httpService.fetchResponse(baseUrl, paramString1);
        SalesTotal total = new Gson().fromJson(result1, SalesTotal.class);
        return total;
    }
}

package com.guitarshack;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalesHistory {
    private final HttpService httpService;

    public SalesHistory(HttpService httpService) {
        this.httpService = httpService;
    }

    SalesTotal getSalesTotal(Product product, Date startDate, Date endDate) {
        DateFormat format = new SimpleDateFormat("M/d/yyyy");

        Map<String, Object> params1 = new HashMap<>(){{
            put("productId", product.getId());
            put("startDate", format.format(startDate));
            put("endDate", format.format(endDate));
            put("action", "total");
        }};
        String paramString1 = "?";

        for (String key : params1.keySet()) {
            paramString1 += key + "=" + params1.get(key).toString() + "&";
        }
        String result1 = httpService.fetchResponse("https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales", paramString1);
        SalesTotal total = new Gson().fromJson(result1, SalesTotal.class);
        return total;
    }
}

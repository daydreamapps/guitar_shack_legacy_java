package com.guitarshack;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StockMonitor {

    private final Alert alert;
    private final HttpService httpService;

    public StockMonitor(Alert alert, HttpService httpService) {
        this.alert = alert;
        this.httpService = httpService;
    }

    public void productSold(int productId, int quantity) {
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


        int reorderThreshold = getReorderThreshold(product);
        if(product.getStock() - quantity <= reorderThreshold) {
            alert.send(product);
        }
    }

    private int getReorderThreshold(Product product) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());

        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);

        Date startDate = calendar.getTime();
        SalesTotal total = new SalesHistory(httpService).getSalesTotal(product, startDate, endDate);

        return (int) ((double) (total.getTotal() / 30) * product.getLeadTime());
    }
}

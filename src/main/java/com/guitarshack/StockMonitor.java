package com.guitarshack;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StockMonitor {

    private final Alert alert;

    public StockMonitor(Alert alert) {
        this.alert = alert;
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
        String result = fetchResponse(baseURL, paramString);
        Product product = new Gson().fromJson(result, Product.class);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);
        Date startDate = calendar.getTime();
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
        String result1 = fetchResponse("https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales", paramString1);
        SalesTotal total = new Gson().fromJson(result1, SalesTotal.class);
        if(product.getStock() - quantity <= (int) ((double) (total.getTotal() / 30) * product.getLeadTime()))
            alert.send(product);
    }

    private String fetchResponse(String baseUrl, String query) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(baseUrl + query))
                .build();

        String result = "";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}

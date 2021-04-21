package com.guitarshack;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    String fetchResponse(String baseUrl, String query) {
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
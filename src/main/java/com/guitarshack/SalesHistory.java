package com.guitarshack;

public class SalesHistory {
    private final HttpService httpService;

    public SalesHistory(HttpService httpService) {
        this.httpService = httpService;
    }

    public HttpService getHttpService() {
        return httpService;
    }
}

package com.guitarshack;

import java.util.Map;

public class QueryBuilder {

    String getString(Map<String, Object> params) {
        String paramString = "?";

        for (String key : params.keySet()) {
            paramString += key + "=" + params.get(key).toString() + "&";
        }
        return paramString;
    }
}